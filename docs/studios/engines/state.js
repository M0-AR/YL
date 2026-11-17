import {
  announce,
  clear,
  codePanel,
  h,
  stepper,
} from "./util.js";

export function mount(host, spec, ctx) {
  host.append(h("p", { class: "studio-hint" }, spec.hint || "Step through the program and watch each variable change."));

  const code = codePanel(host, spec.code, { label: spec.codeLabel || "Program" });
  const varGrid = h("div", { class: "state-grid", role: "group", "aria-label": "Variables" });
  host.append(varGrid);
  const note = h("p", { class: "studio-note", "aria-live": "off" });
  host.append(note);

  const timeline = h("ol", { class: "studio-timeline", "aria-label": "Program steps", tabindex: "0" });
  const timelineItems = spec.steps.map((step, index) =>
    h(
      "li",
      {},
      h("span", { class: "studio-stepno", "aria-hidden": "true" }, String(index + 1)),
      h("span", {}, step.note || ""),
    ),
  );
  for (const item of timelineItems) timeline.append(item);
  host.append(timeline);

  let previous = null;

  function render(index) {
    const step = spec.steps[index];
    const vars = step.vars || {};
    clear(varGrid);
    for (const name of spec.vars) {
      const value = vars[name] ?? "—";
      const changed = previous && String(previous[name]) !== String(value);
      const box = h(
        "div",
        { class: `state-var${changed ? " is-changed" : ""}` },
        h("span", { class: "state-name" }, name),
        h("span", { class: "state-value" }, String(value)),
        changed ? h("span", { class: "state-flag" }, "changed") : null,
      );
      varGrid.append(box);
    }
    code.highlight(Number(step.line));
    note.textContent = step.note || "";
    timelineItems.forEach((item, itemIndex) => {
      item.classList.toggle("is-current", itemIndex === index);
      if (itemIndex === index) item.setAttribute("aria-current", "step");
      else item.removeAttribute("aria-current");
    });
    previous = vars;
    announce(host, `Step ${index + 1} of ${spec.steps.length}. ${step.note || ""}`, "info");
    if (ctx && ctx.onStep) ctx.onStep(index, step);
  }

  const control = stepper(host, {
    total: spec.steps.length,
    label: "Program step",
    onStep: render,
  });

  return {
    destroy() {
      control.destroy();
    },
  };
}
