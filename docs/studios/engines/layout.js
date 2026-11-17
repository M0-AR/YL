import { announce, clear, h } from "./util.js";

const LAYOUTS = {
  hbox: "HBox",
  vbox: "VBox",
  flow: "FlowPane",
  grid: "GridPane",
  border: "BorderPane",
  stack: "StackPane",
};

export function mount(host, spec, ctx) {
  host.append(h("p", { class: "studio-hint" }, spec.hint || "Change the layout and watch the children move."));

  const children = spec.children || ["A", "B", "C"];
  let mode = spec.mode || "hbox";
  let gap = spec.gap ?? 12;
  let padding = spec.padding ?? 16;
  let alignment = "start";

  const controls = h("div", { class: "layout-controls" });
  const layoutSelect = h(
    "select",
    { class: "studio-select", "aria-label": "Layout manager" },
    Object.entries(LAYOUTS).map(([value, label]) =>
      h("option", { value, selected: value === mode }, label),
    ),
  );
  const gapRange = h("input", {
    type: "range",
    min: 0,
    max: 40,
    step: 2,
    value: gap,
    "aria-label": "Gap",
  });
  const gapOut = h("output", {}, `${gap}px`);
  const paddingRange = h("input", {
    type: "range",
    min: 0,
    max: 48,
    step: 4,
    value: padding,
    "aria-label": "Padding",
  });
  const paddingOut = h("output", {}, `${padding}px`);
  const alignSelect = h(
    "select",
    { class: "studio-select", "aria-label": "Alignment" },
    ["start", "center", "end", "space-between"].map((value) =>
      h("option", { value, selected: value === alignment }, value),
    ),
  );
  controls.append(
    h("label", { class: "studio-field" }, "Layout", layoutSelect),
    h("label", { class: "studio-field" }, "Gap", gapRange, gapOut),
    h("label", { class: "studio-field" }, "Padding", paddingRange, paddingOut),
    h("label", { class: "studio-field" }, "Align", alignSelect),
  );
  host.append(controls);

  const stage = h("div", { class: "layout-stage" });
  const readout = h("p", { class: "studio-note", role: "status", "aria-live": "polite" });
  const positions = h("div", { class: "layout-positions" });
  host.append(stage, readout, positions);

  function apply() {
    clear(stage);
    const container = h("div", { class: "layout-box" });
    container.dataset.layout = mode;
    container.style.padding = `${padding}px`;
    container.style.gap = `${gap}px`;
    const justify =
      alignment === "space-between" ? "space-between" : `flex-${alignment === "start" ? "start" : alignment}`;
    if (mode !== "grid" && mode !== "border") container.style.justifyContent = justify;

    if (mode === "grid") {
      container.style.display = "grid";
      container.style.gridTemplateColumns = "repeat(2, minmax(0, 1fr))";
    } else if (mode === "border") {
      container.style.display = "grid";
      container.style.gridTemplateAreas = '"top top" "left center" "bottom bottom"';
      container.style.gridTemplateColumns = "1fr 1.4fr";
    } else {
      container.style.display = "flex";
      container.style.flexDirection = mode === "vbox" ? "column" : "row";
      if (mode === "flow") container.style.flexWrap = "wrap";
      if (mode === "stack") {
        container.style.position = "relative";
        container.style.alignItems = "center";
        container.style.justifyContent = "center";
      }
    }

    children.forEach((label, index) => {
      const child = h("div", { class: "layout-child" }, String(label));
      if (mode === "border") {
        child.style.gridArea = ["top", "left", "center", "bottom"][index % 4];
      }
      if (mode === "stack") {
        child.style.position = "absolute";
        child.style.transform = `translate(${index * 14}px, ${index * 14}px)`;
      }
      container.append(child);
    });
    stage.append(container);

    requestAnimationFrame(() => {
      const parent = container.getBoundingClientRect();
      const items = Array.from(container.children).map((child, index) => {
        const box = child.getBoundingClientRect();
        return `${children[index]}: x=${Math.round(box.left - parent.left)} y=${Math.round(
          box.top - parent.top,
        )} w=${Math.round(box.width)} h=${Math.round(box.height)}`;
      });
      clear(positions);
      positions.append(
        h("ul", {}, items.map((line) => h("li", {}, line))),
      );
      readout.textContent = `${LAYOUTS[mode]}: ${children.length} children, gap ${gap}px, padding ${padding}px, align ${alignment}.`;
      announce(host, readout.textContent, "info");
    });
    if (ctx && ctx.onStep) ctx.onStep(0, { mode });
  }

  layoutSelect.addEventListener("change", () => {
    mode = layoutSelect.value;
    apply();
  });
  gapRange.addEventListener("input", () => {
    gap = Number(gapRange.value);
    gapOut.textContent = `${gap}px`;
    apply();
  });
  paddingRange.addEventListener("input", () => {
    padding = Number(paddingRange.value);
    paddingOut.textContent = `${padding}px`;
    apply();
  });
  alignSelect.addEventListener("change", () => {
    alignment = alignSelect.value;
    apply();
  });

  apply();
  return { destroy() {} };
}
