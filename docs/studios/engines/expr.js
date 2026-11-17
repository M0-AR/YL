import { announce, codeBlock, h, stepper } from "./util.js";

export function mount(host, spec, ctx) {
  host.append(h("p", { class: "studio-hint" }, spec.hint || "Evaluate the expression one operator at a time."));

  const expression = h("p", { class: "expr-source" }, h("code", {}, spec.expression));
  host.append(expression);

  const current = h("p", { class: "expr-current" });
  const rule = h("p", { class: "studio-note" });
  const trace = h("ol", { class: "studio-timeline", "aria-label": "Evaluation steps", tabindex: "0" });
  host.append(current, rule, trace);

  const items = spec.steps.map((step, index) =>
    h(
      "li",
      {},
      h("span", { class: "studio-stepno", "aria-hidden": "true" }, String(index + 1)),
      h("span", {}, `Replace ${step.fragment} with ${step.value}${step.note ? ` — ${step.note}` : ""}`),
    ),
  );
  for (const item of items) trace.append(item);

  function render(index) {
    const step = spec.steps[index];
    current.textContent = `→ ${step.result}`;
    rule.textContent = step.note || "";
    items.forEach((item, itemIndex) => {
      item.classList.toggle("is-current", itemIndex === index);
      if (itemIndex === index) item.setAttribute("aria-current", "step");
      else item.removeAttribute("aria-current");
    });
    announce(host, `Step ${index + 1}. ${step.result}. ${step.note || ""}`, "info");
    if (ctx && ctx.onStep) ctx.onStep(index, step);
  }

  if (spec.types) {
    host.append(h("p", { class: "studio-subhead" }, "Type check"));
    host.append(codeBlock(spec.types));
  }

  const control = stepper(host, {
    total: spec.steps.length,
    label: "Evaluation step",
    onStep: render,
  });

  return {
    destroy() {
      control.destroy();
    },
  };
}
