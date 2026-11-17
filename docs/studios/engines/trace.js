import { announce, clear, codePanel, h, stepper } from "./util.js";

export function mount(host, spec, ctx) {
  host.append(h("p", { class: "studio-hint" }, spec.hint || "Follow the highlighted line and the call stack."));

  const code = codePanel(host, spec.code, { label: "Executing program" });
  const stackBox = h("div", { class: "trace-stack", role: "group", "aria-label": "Call stack" });
  host.append(stackBox);
  const output = h("p", { class: "trace-output" });
  const note = h("p", { class: "studio-note" });
  host.append(note, output);

  const log = h("ol", { class: "studio-timeline", "aria-label": "Execution trace", tabindex: "0" });
  host.append(log);

  function render(index) {
    const step = spec.steps[index];
    code.highlight(Number(step.line));
    clear(stackBox);
    for (const frame of step.stack || []) {
      const vars = Object.entries(frame.vars || {})
        .map(([key, value]) => `${key} = ${value}`)
        .join(" · ");
      stackBox.append(
        h(
          "div",
          { class: "trace-frame" },
          h("b", {}, frame.name),
          h("span", {}, vars || "no locals"),
        ),
      );
    }
    note.textContent = step.note || "";
    output.textContent = step.output === undefined ? "" : `Output: ${step.output}`;
    log.append(
      h(
        "li",
        { class: "is-current" },
        h("span", { class: "studio-stepno", "aria-hidden": "true" }, String(index + 1)),
        h("span", {}, step.note || `Line ${Number(step.line) + 1}`),
      ),
    );
    if (log.children.length > 12) log.firstElementChild.remove();
    announce(host, `Step ${index + 1} of ${spec.steps.length}. ${step.note || ""}`, "info");
    if (ctx && ctx.onStep) ctx.onStep(index, step);
  }

  const control = stepper(host, {
    total: spec.steps.length,
    label: "Execution step",
    onStep: render,
  });

  return {
    destroy() {
      control.destroy();
    },
  };
}
