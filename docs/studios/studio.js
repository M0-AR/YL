import { announce, store } from "./engines/util.js";

const ENGINES = new Set([
  "state",
  "expr",
  "trace",
  "structure",
  "sort",
  "graph",
  "growth",
  "data",
  "contract",
  "layout",
  "parsons",
]);

const PROGRESS_KEY = "yl-studio-progress";

function readChapter() {
  const node = document.getElementById("studio-data");
  if (!node) return null;
  try {
    return JSON.parse(node.textContent);
  } catch (error) {
    console.error("Studio data is not valid JSON", error);
    return null;
  }
}

function progress() {
  return store(PROGRESS_KEY) || {};
}

function saveProgress(next) {
  store(PROGRESS_KEY, next);
}

function mountQuiz(form, chapter) {
  form.addEventListener("submit", (event) => {
    event.preventDefault();
    const questions = Array.from(form.querySelectorAll(".quiz-question"));
    let correct = 0;
    let answered = 0;
    for (const question of questions) {
      const answer = Number(question.dataset.answer);
      const selected = question.querySelector("input[type=radio]:checked");
      const feedback = question.querySelector(".quiz-feedback");
      question.classList.remove("is-correct", "is-wrong");
      if (!selected) {
        question.classList.add("is-wrong");
        if (feedback) feedback.textContent = "Choose an answer, then check again.";
        continue;
      }
      answered += 1;
      const isCorrect = Number(selected.value) === answer;
      if (isCorrect) {
        correct += 1;
        question.classList.add("is-correct");
      } else {
        question.classList.add("is-wrong");
      }
      if (feedback) {
        feedback.textContent = (isCorrect ? "Correct. " : "Not this one. ") + (question.dataset.why || "");
      }
    }
    const score = form.querySelector(".studio-quiz-score");
    const passed = answered === questions.length && correct === questions.length;
    if (score) {
      score.textContent = `${correct} / ${questions.length} correct${
        answered < questions.length ? ` · ${questions.length - answered} unanswered` : ""
      }`;
    }
    if (passed) {
      const next = progress();
      next[chapter.n] = { ...(next[chapter.n] || {}), mastery: true };
      saveProgress(next);
      syncCompletion();
    }
    announce(
      form.closest(".studio-island") || form,
      passed
        ? `Mastery check passed: ${correct} of ${questions.length}.`
        : `${correct} of ${questions.length} correct. Review the feedback and try again.`,
      passed ? "ok" : "warn",
    );
  });
}

function syncCompletion() {
  const state = progress();
  const complete = document.querySelector("[data-complete]");
  if (complete) {
    const chapter = Number(complete.dataset.chapter);
    const done = Boolean(state[chapter]?.complete);
    complete.setAttribute("aria-pressed", done ? "true" : "false");
    complete.textContent = done ? "Chapter marked complete" : "Mark chapter complete";
    complete.closest(".studio-complete")?.classList.toggle("is-done", done);
    const label = complete.closest(".studio-complete")?.querySelector("[data-complete-label]");
    if (label) label.textContent = done ? "Progress saved locally." : "Progress is stored in this browser only.";
  }
  for (const card of document.querySelectorAll("[data-studio-index] [data-chapter]")) {
    const chapter = Number(card.dataset.chapter);
    card.dataset.done = state[chapter]?.complete ? "true" : "false";
  }
  const summary = document.querySelector("[data-progress-summary]");
  if (summary) {
    const count = Object.values(state).filter((item) => item?.complete).length;
    summary.textContent = `${count} of 43 chapters complete`;
  }
}

function mountComplete() {
  const button = document.querySelector("[data-complete]");
  if (!button) return;
  syncCompletion();
  button.addEventListener("click", () => {
    const chapter = Number(button.dataset.chapter);
    const state = progress();
    const done = Boolean(state[chapter]?.complete);
    state[chapter] = { ...(state[chapter] || {}), complete: !done };
    saveProgress(state);
    syncCompletion();
    announce(button.closest(".studio-island") || button, done ? "Chapter marked incomplete." : "Chapter marked complete.", "ok");
  });
}

function mountArtifact(chapter) {
  const link = document.querySelector("[data-artifact]");
  if (!link || !chapter.artifact) return;
  link.setAttribute("href", chapter.artifact.href || "#");
  link.addEventListener("click", (event) => {
    event.preventDefault();
    const blob = new Blob([chapter.artifact.code], { type: "text/x-java-source" });
    const url = URL.createObjectURL(blob);
    const anchor = document.createElement("a");
    anchor.href = url;
    anchor.download = chapter.artifact.name;
    document.body.append(anchor);
    anchor.click();
    anchor.remove();
    window.setTimeout(() => URL.revokeObjectURL(url), 1000);
    announce(link.closest(".studio-island") || link, `Downloaded ${chapter.artifact.name}.`, "ok");
  });
}

function mountCopyButtons() {
  for (const button of document.querySelectorAll("[data-copy]")) {
    button.addEventListener("click", async () => {
      const wrapper = button.closest(".studio-code-wrap") || document;
      const code = wrapper.querySelector("code");
      if (!code) return;
      const text = code.innerText;
      let copied = false;
      try {
        if (navigator.clipboard && window.isSecureContext) {
          await navigator.clipboard.writeText(text);
          copied = true;
        }
      } catch {
        copied = false;
      }
      if (!copied) {
        const area = document.createElement("textarea");
        area.value = text;
        area.setAttribute("readonly", "");
        area.style.position = "fixed";
        area.style.opacity = "0";
        document.body.append(area);
        area.select();
        try {
          copied = document.execCommand("copy");
        } catch {
          copied = false;
        }
        area.remove();
      }
      button.textContent = copied ? "Copied" : "Copy failed";
      window.setTimeout(() => {
        button.textContent = "Copy";
      }, 1600);
    });
  }
}

function mountNotes(chapter) {
  const area = document.querySelector("[data-notes]");
  if (!area) return;
  const key = `yl-studio-notes-${chapter.n}`;
  const status = document.querySelector("[data-notes-status]");
  const saved = store(key);
  if (typeof saved === "string") area.value = saved;
  let timer = 0;
  area.addEventListener("input", () => {
    window.clearTimeout(timer);
    timer = window.setTimeout(() => {
      store(key, area.value);
      if (status) status.textContent = "Saved locally.";
      window.setTimeout(() => {
        if (status) status.textContent = "Notes stay in this browser.";
      }, 1800);
    }, 350);
  });
}

function makeCodeFocusable(root) {
  for (const block of root.querySelectorAll("pre.studio-code")) {
    if (!block.hasAttribute("tabindex")) block.setAttribute("tabindex", "0");
  }
}

function mountIsland(island, chapter) {
  const engine = island.dataset.engine;
  if (!ENGINES.has(engine)) {
    island.classList.remove("booting");
    island.append(
      Object.assign(document.createElement("p"), {
        className: "studio-noscript",
        textContent: `Unknown studio engine: ${engine}.`,
      }),
    );
    return;
  }
  const spec = engine === "parsons" ? chapter.parsons : chapter.spec;
  if (!spec) {
    island.classList.remove("booting");
    island.append(
      Object.assign(document.createElement("p"), {
        className: "studio-noscript",
        textContent: "This studio is still being written. The lesson and checklist above are complete.",
      }),
    );
    return;
  }
  import(new URL(`./engines/${engine}.js`, import.meta.url))
    .then((module) => {
      module.mount(island, spec, {
        chapter,
        onSolved: () => {
          const state = progress();
          state[chapter.n] = { ...(state[chapter.n] || {}), practice: true };
          saveProgress(state);
        },
      });
      island.classList.remove("booting");
      island.dataset.mounted = "true";
      makeCodeFocusable(island);
    })
    .catch((error) => {
      console.error(`Failed to load ${engine} studio`, error);
      island.classList.remove("booting");
      island.append(
        Object.assign(document.createElement("p"), {
          className: "studio-noscript",
          textContent:
            "The interactive studio could not load. You can still read the worked example, practice description, and mastery check.",
        }),
      );
    });
}

function mountIslands(chapter) {
  const islands = Array.from(document.querySelectorAll("[data-engine]"));
  if (!islands.length) return;
  const start = (island) => {
    if (island.dataset.started) return;
    island.dataset.started = "true";
    island.classList.add("booting");
    mountIsland(island, chapter);
  };
  if (!("IntersectionObserver" in window)) {
    islands.forEach(start);
    return;
  }
  const observer = new IntersectionObserver(
    (entries) => {
      for (const entry of entries) {
        if (!entry.isIntersecting) continue;
        observer.unobserve(entry.target);
        start(entry.target);
      }
    },
    { rootMargin: "320px 0px" },
  );
  for (const island of islands) observer.observe(island);
}

function mountFilter() {
  const root = document.querySelector("[data-studio-index]");
  const input = document.querySelector("[data-studio-filter]");
  if (!root || !input) return;
  const cards = Array.from(root.querySelectorAll("[data-chapter]"));
  const count = document.querySelector("[data-studio-filter-count]");
  const filter = () => {
    const needle = input.value.trim().toLowerCase();
    let visible = 0;
    for (const card of cards) {
      const haystack = `${card.textContent} ${card.dataset.tags || ""}`.toLowerCase();
      const match = !needle || haystack.includes(needle);
      card.hidden = !match;
      if (match) visible += 1;
    }
    if (count) count.textContent = `${visible} of ${cards.length} studios`;
  };
  input.addEventListener("input", filter);
  filter();
}

function boot() {
  const chapter = readChapter();
  if (chapter) {
    mountIslands(chapter);
    mountArtifact(chapter);
    mountNotes(chapter);
    for (const form of document.querySelectorAll("[data-quiz]")) {
      mountQuiz(form, chapter);
    }
  }
  mountComplete();
  mountCopyButtons();
  makeCodeFocusable(document);
  mountFilter();
  syncCompletion();
  document.body.dataset.studioReady = "true";
}

if (document.readyState === "loading") {
  document.addEventListener("DOMContentLoaded", boot, { once: true });
} else {
  boot();
}
