const SVG_NS = "http://www.w3.org/2000/svg";

export const $ = (selector, root = document) => root.querySelector(selector);
export const $$ = (selector, root = document) => Array.from(root.querySelectorAll(selector));

export function h(tag, attrs = {}, ...children) {
  const node = document.createElement(tag);
  for (const [key, value] of Object.entries(attrs || {})) {
    if (value === undefined || value === null || value === false) continue;
    if (key === "class") node.className = value;
    else if (key === "text") node.textContent = value;
    else if (key === "html") node.innerHTML = value;
    else if (key === "dataset") Object.assign(node.dataset, value);
    else if (key.startsWith("on") && typeof value === "function") {
      node.addEventListener(key.slice(2).toLowerCase(), value);
    } else if (value === true) node.setAttribute(key, "");
    else node.setAttribute(key, String(value));
  }
  for (const child of children.flat()) {
    if (child === null || child === undefined || child === false) continue;
    node.append(child.nodeType ? child : document.createTextNode(String(child)));
  }
  return node;
}

export function svg(tag, attrs = {}, ...children) {
  const node = document.createElementNS(SVG_NS, tag);
  for (const [key, value] of Object.entries(attrs || {})) {
    if (value === undefined || value === null) continue;
    node.setAttribute(key, String(value));
  }
  for (const child of children.flat()) {
    if (child === null || child === undefined || child === false) continue;
    node.append(child.nodeType ? child : document.createTextNode(String(child)));
  }
  return node;
}

export function clear(node) {
  while (node.firstChild) node.removeChild(node.firstChild);
  return node;
}

export function clamp(value, min, max) {
  return Math.min(max, Math.max(min, value));
}

export function escapeHtml(text) {
  return String(text)
    .replaceAll("&", "&amp;")
    .replaceAll("<", "&lt;")
    .replaceAll(">", "&gt;")
    .replaceAll('"', "&quot;");
}

export function reducedMotion() {
  return window.matchMedia("(prefers-reduced-motion: reduce)").matches;
}

export function store(key, value) {
  try {
    if (value === undefined) {
      const raw = window.localStorage.getItem(key);
      return raw === null ? null : JSON.parse(raw);
    }
    window.localStorage.setItem(key, JSON.stringify(value));
    return value;
  } catch {
    return null;
  }
}

export function announce(host, message, tone = "info") {
  let live = host.querySelector("[data-studio-live]");
  if (!live) {
    live = h("p", {
      class: "studio-live",
      "data-studio-live": "",
      role: "status",
      "aria-live": "polite",
      "aria-atomic": "true",
    });
    host.append(live);
  }
  live.dataset.tone = tone;
  live.textContent = message;
}

export function svgCanvas(host, { width = 640, height = 320, label = "" } = {}) {
  const canvas = svg("svg", {
    viewBox: `0 0 ${width} ${height}`,
    class: "studio-svg",
    role: "img",
    "aria-label": label,
    preserveAspectRatio: "xMidYMid meet",
  });
  host.append(canvas);
  return canvas;
}

export function stepper(host, { total, label = "Step", onStep, index = 0 }) {
  let current = clamp(index, 0, Math.max(0, total - 1));
  let timer = 0;

  const prev = h("button", { class: "btn btn-sm", type: "button" }, "Previous");
  const next = h("button", { class: "btn btn-sm", type: "button" }, "Next");
  const play = h("button", { class: "btn btn-sm btn-ghost", type: "button" }, "Play");
  const reset = h("button", { class: "btn btn-sm btn-ghost", type: "button" }, "Reset");
  const counter = h("span", { class: "studio-counter", "aria-hidden": "true" });
  const range = h("input", {
    class: "studio-range",
    type: "range",
    min: 0,
    max: Math.max(0, total - 1),
    value: current,
    step: 1,
    "aria-label": `${label} position`,
  });

  const bar = h(
    "div",
    { class: "studio-controls", role: "group", "aria-label": `${label} controls` },
    reset,
    prev,
    play,
    next,
    range,
    counter,
  );
  host.append(bar);

  function stop() {
    if (timer) window.clearInterval(timer);
    timer = 0;
    play.textContent = "Play";
    play.setAttribute("aria-pressed", "false");
  }

  function go(value) {
    current = clamp(Number(value), 0, Math.max(0, total - 1));
    range.value = String(current);
    counter.textContent = `${current + 1} / ${total}`;
    prev.disabled = current === 0;
    next.disabled = current === total - 1;
    onStep(current);
  }

  prev.addEventListener("click", () => {
    stop();
    go(current - 1);
  });
  next.addEventListener("click", () => {
    stop();
    go(current + 1);
  });
  reset.addEventListener("click", () => {
    stop();
    go(0);
  });
  range.addEventListener("input", () => {
    stop();
    go(range.value);
  });
  play.addEventListener("click", () => {
    if (timer) {
      stop();
      return;
    }
    if (current === total - 1) go(0);
    play.textContent = "Pause";
    play.setAttribute("aria-pressed", "true");
    timer = window.setInterval(() => {
      if (current >= total - 1) {
        stop();
        return;
      }
      go(current + 1);
    }, reducedMotion() ? 1400 : 900);
  });

  host.addEventListener("keydown", (event) => {
    if (event.key === "ArrowRight") {
      event.preventDefault();
      stop();
      go(current + 1);
    } else if (event.key === "ArrowLeft") {
      event.preventDefault();
      stop();
      go(current - 1);
    }
  });

  go(current);

  return {
    go,
    stop,
    get index() {
      return current;
    },
    get total() {
      return total;
    },
    destroy() {
      stop();
      bar.remove();
    },
  };
}

export function logPanel(host, { title = "Trace" } = {}) {
  const list = h("ol", { class: "studio-log" });
  const box = h(
    "section",
    { class: "studio-logbox", "aria-label": title },
    h("h3", { class: "studio-logtitle" }, title),
    list,
  );
  host.append(box);

  function push(message, tone = "info") {
    const item = h("li", { class: `tone-${tone}` }, message);
    list.append(item);
    if (list.children.length > 40) list.firstElementChild.remove();
    item.scrollIntoView({ block: "nearest" });
    return item;
  }

  function reset() {
    clear(list);
  }

  return { push, reset, box };
}

export function chipRow(host, values, { className = "studio-chips", label = "" } = {}) {
  const row = h("div", { class: className, role: "list", "aria-label": label });
  for (const value of values) {
    row.append(h("span", { role: "listitem" }, String(value)));
  }
  host.append(row);
  return row;
}

export function legend(host, items) {
  const box = h("ul", { class: "studio-legend" });
  for (const item of items) {
    box.append(
      h(
        "li",
        {},
        h("i", { class: `swatch swatch-${item.tone}`, "aria-hidden": "true" }),
        item.label,
      ),
    );
  }
  host.append(box);
  return box;
}

export function seededShuffle(items, seed = 1) {
  const copy = items.slice();
  let state = seed >>> 0 || 1;
  const random = () => {
    state = (state * 1664525 + 1013904223) >>> 0;
    return state / 4294967296;
  };
  for (let i = copy.length - 1; i > 0; i -= 1) {
    const j = Math.floor(random() * (i + 1));
    [copy[i], copy[j]] = [copy[j], copy[i]];
  }
  return copy;
}

export function format(value) {
  if (Array.isArray(value)) return `[${value.map(format).join(", ")}]`;
  if (typeof value === "number" && !Number.isInteger(value)) {
    return Number(value.toFixed(3)).toString();
  }
  return String(value);
}

export function codeBlock(lines) {
  const list = Array.isArray(lines) ? lines : String(lines).split("\n");
  return h(
    "pre",
    { class: "studio-code", tabindex: 0 },
    h("code", {}, list.map((line, index) => `${String(index + 1).padStart(2, " ")}  ${line}`).join("\n")),
  );
}

export function codePanel(host, lines, { label = "Code under study" } = {}) {
  const list = Array.isArray(lines) ? lines : String(lines).split("\n");
  const rows = list.map((line, index) =>
    h(
      "span",
      { class: "studio-codeline", "data-line": index },
      h("span", { class: "studio-lineno", "aria-hidden": "true" }, String(index + 1)),
      h("span", { class: "studio-linetext" }, line || " "),
    ),
  );
  const pre = h(
    "pre",
    { class: "studio-code studio-code-lines", tabindex: 0, "aria-label": label },
    h("code", {}, rows),
  );
  host.append(pre);
  return {
    node: pre,
    highlight(index) {
      for (const row of rows) row.classList.toggle("is-active", Number(row.dataset.line) === index);
    },
  };
}
