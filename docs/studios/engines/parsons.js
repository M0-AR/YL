import { announce, clear, h, seededShuffle } from "./util.js";

function shuffledOrder(total, seed) {
  const identity = Array.from({ length: total }, (_, index) => index);
  for (let attempt = 0; attempt < 20; attempt += 1) {
    const order = seededShuffle(identity, seed + attempt * 97);
    if (order.some((value, index) => value !== index)) return order;
  }
  return identity.slice().reverse();
}

export function mount(host, spec, ctx) {
  host.append(
    h(
      "p",
      { class: "studio-hint" },
      spec.prompt || "Arrange the lines into a working program. Use the move buttons or drag a line.",
    ),
  );

  const pool = (spec.lines || []).slice();
  const distractors = spec.distractors || [];
  for (const line of distractors) pool.push(line);
  const solution = spec.solution || spec.lines.map((_, index) => index);
  let order = shuffledOrder(pool.length, spec.seed || 7);
  let attempts = 0;
  let solved = false;

  const instructionsId = `parsons-help-${spec.id || Math.random().toString(36).slice(2, 7)}`;
  const help = h(
    "p",
    { class: "parsons-help", id: instructionsId },
    "Keyboard: focus a line, then use Move up or Move down. Drag and drop also works.",
  );
  const list = h("ol", { class: "parsons-list", "aria-describedby": instructionsId });
  const feedback = h("p", { class: "parsons-feedback", role: "status", "aria-live": "polite" });
  const controls = h("div", { class: "studio-controls" });
  const check = h("button", { class: "btn btn-sm btn-primary", type: "button" }, "Check order");
  const shuffle = h("button", { class: "btn btn-sm btn-ghost", type: "button" }, "Shuffle");
  const reveal = h("button", { class: "btn btn-sm btn-ghost", type: "button" }, "Show solution");
  controls.append(check, shuffle, reveal);
  const status = h("p", { class: "studio-hint" });
  host.append(help, list, controls, feedback, status);

  function move(from, to) {
    if (to < 0 || to >= order.length) return;
    const copy = order.slice();
    const [item] = copy.splice(from, 1);
    copy.splice(to, 0, item);
    order = copy;
    draw();
    const button = list.querySelectorAll("li")[to]?.querySelector("button");
    if (button) button.focus();
  }

  function draw() {
    clear(list);
    order.forEach((sourceIndex, position) => {
      const text = pool[sourceIndex];
      const item = h(
        "li",
        { draggable: "true", "data-source": String(sourceIndex) },
        h("span", { class: "parsons-grip", "aria-hidden": "true" }, "⠿"),
        h("code", { class: "parsons-code" }, text),
        h(
          "span",
          { class: "parsons-actions" },
          h(
            "button",
            {
              type: "button",
              class: "parsons-move",
              "aria-label": `Move line ${position + 1} up: ${text.slice(0, 40)}`,
              disabled: position === 0,
              onClick: () => move(position, position - 1),
            },
            "↑",
          ),
          h(
            "button",
            {
              type: "button",
              class: "parsons-move",
              "aria-label": `Move line ${position + 1} down: ${text.slice(0, 40)}`,
              disabled: position === order.length - 1,
              onClick: () => move(position, position + 1),
            },
            "↓",
          ),
        ),
      );
      item.addEventListener("dragstart", (event) => {
        event.dataTransfer.setData("text/plain", String(position));
        event.dataTransfer.effectAllowed = "move";
        item.classList.add("is-dragging");
      });
      item.addEventListener("dragend", () => item.classList.remove("is-dragging"));
      item.addEventListener("dragover", (event) => {
        event.preventDefault();
        item.classList.add("is-over");
      });
      item.addEventListener("dragleave", () => item.classList.remove("is-over"));
      item.addEventListener("drop", (event) => {
        event.preventDefault();
        item.classList.remove("is-over");
        const from = Number(event.dataTransfer.getData("text/plain"));
        if (Number.isInteger(from)) move(from, position);
      });
      list.append(item);
    });
  }

  check.addEventListener("click", () => {
    attempts += 1;
    solved = order.every((value, index) => value === solution[index]);
    list.querySelectorAll("li").forEach((item, position) => {
      item.classList.toggle("is-correct", solved || order[position] === solution[position]);
      item.classList.toggle("is-wrong", !solved && order[position] !== solution[position]);
    });
    feedback.dataset.tone = solved ? "ok" : "warn";
    feedback.textContent = solved
      ? `Correct. ${spec.why || "The order builds the intended behavior."}`
      : "Not yet. Compare the highlighted lines and try again.";
    status.textContent = `${attempts} attempt${attempts === 1 ? "" : "s"}.`;
    announce(host, feedback.textContent, solved ? "ok" : "warn");
    if (solved && ctx && ctx.onSolved) ctx.onSolved(attempts);
  });

  shuffle.addEventListener("click", () => {
    order = shuffledOrder(pool.length, Date.now() % 100000);
    solved = false;
    feedback.textContent = "";
    list.querySelectorAll("li").forEach((item) => item.classList.remove("is-correct", "is-wrong"));
    draw();
    announce(host, "Lines shuffled.", "info");
  });

  reveal.addEventListener("click", () => {
    order = solution.slice();
    solved = true;
    draw();
    feedback.dataset.tone = "ok";
    feedback.textContent = spec.why || "Compare this order with your attempt.";
    announce(host, "Solution shown.", "info");
  });

  draw();
  return { destroy() {} };
}
