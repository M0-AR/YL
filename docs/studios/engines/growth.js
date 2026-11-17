import { announce, clear, h, svg } from "./util.js";

export const FORMULAS = {
  one: { label: "1", fn: () => 1 },
  log: { label: "log n", fn: (n) => Math.log2(Math.max(n, 2)) },
  n: { label: "n", fn: (n) => n },
  nlogn: { label: "n log n", fn: (n) => n * Math.log2(Math.max(n, 2)) },
  square: { label: "n²", fn: (n) => n * n },
  cube: { label: "n³", fn: (n) => n * n * n },
  exp: { label: "2ⁿ", fn: (n) => Math.pow(2, n) },
};

export function seriesValue(key, n) {
  const formula = FORMULAS[key];
  return formula ? formula.fn(n) : 0;
}

export function mount(host, spec, ctx) {
  host.append(
    h("p", { class: "studio-hint" }, spec.hint || "Change the input size and compare how the work grows."),
  );

  const series = spec.series || [
    { key: "n", tone: "cyan" },
    { key: "nlogn", tone: "violet" },
    { key: "square", tone: "amber" },
  ];
  let nMax = spec.nMax || 64;
  let logScale = spec.logScale !== false;

  const controls = h("div", { class: "growth-controls" });
  const range = h("input", {
    type: "range",
    min: 4,
    max: 128,
    step: 4,
    value: nMax,
    "aria-label": "Largest input size",
  });
  const rangeOut = h("output", {}, `${nMax}`);
  const logToggle = h("input", { type: "checkbox", id: `growth-log-${spec.id || "x"}` });
  logToggle.checked = logScale;
  controls.append(
    h("label", { class: "studio-field" }, "Largest n ", range, " ", rangeOut),
    h("label", { class: "studio-check" }, logToggle, "Logarithmic vertical scale"),
  );
  host.append(controls);

  const chartBox = h("div", { class: "growth-chart" });
  const tableBox = h("div", { class: "growth-table" });
  host.append(chartBox, tableBox);

  function draw() {
    clear(chartBox);
    const width = 660;
    const height = 300;
    const pad = 46;
    const canvas = svg("svg", {
      viewBox: `0 0 ${width} ${height}`,
      class: "growth-svg",
      role: "img",
      "aria-label": "Growth chart; the table below lists the same values.",
    });
    const maxValue = Math.max(
      ...series.flatMap((item) => [4, 8, 16, 32, 64, 128].map((n) => seriesValue(item.key, n))),
      1,
    );
    const transform = (value) => {
      if (!logScale) return value / maxValue;
      return Math.log10(1 + value) / Math.log10(1 + maxValue);
    };
    for (let line = 0; line <= 4; line += 1) {
      const y = pad + (line / 4) * (height - pad * 2);
      canvas.append(
        svg("line", { x1: pad, y1: y, x2: width - pad, y2: y, class: "growth-grid" }),
        svg(
          "text",
          { x: pad - 8, y: y + 4, "text-anchor": "end", class: "growth-tick" },
          logScale ? "" : String(Math.round(maxValue * (1 - line / 4))),
        ),
      );
    }
    for (let step = 0; step <= 4; step += 1) {
      const n = Math.round((step / 4) * nMax);
      const x = pad + (step / 4) * (width - pad * 2);
      canvas.append(
        svg("line", { x1: x, y1: pad, x2: x, y2: height - pad, class: "growth-grid" }),
        svg(
          "text",
          { x, y: height - pad + 18, "text-anchor": "middle", class: "growth-tick" },
          `n=${n}`,
        ),
      );
    }
    for (const item of series) {
      const formula = FORMULAS[item.key];
      if (!formula) continue;
      const points = [];
      for (let step = 0; step <= 60; step += 1) {
        const n = 1 + (step / 60) * (nMax - 1);
        const x = pad + (step / 60) * (width - pad * 2);
        const y = height - pad - transform(formula.fn(n)) * (height - pad * 2);
        points.push(`${x.toFixed(1)},${y.toFixed(1)}`);
      }
      canvas.append(
        svg("polyline", {
          points: points.join(" "),
          class: `growth-line tone-${item.tone || "cyan"}`,
        }),
      );
    }
    chartBox.append(canvas);

    const legend = h("ul", { class: "studio-legend" });
    for (const item of series) {
      const formula = FORMULAS[item.key];
      legend.append(
        h(
          "li",
          {},
          h("i", { class: `swatch swatch-${item.tone || "cyan"}`, "aria-hidden": "true" }),
          formula ? formula.label : item.key,
        ),
      );
    }
    chartBox.append(legend);

    clear(tableBox);
    const rows = [8, 16, 32, 64, 128].filter((n) => n <= nMax);
    if (!rows.includes(nMax)) rows.push(nMax);
    const table = h(
      "table",
      { class: "studio-table" },
      h(
        "thead",
        {},
        h(
          "tr",
          {},
          h("th", { scope: "col" }, "n"),
          series.map((item) =>
            h("th", { scope: "col" }, FORMULAS[item.key]?.label || item.key),
          ),
        ),
      ),
      h(
        "tbody",
        {},
        rows.map((n) =>
          h(
            "tr",
            {},
            h("th", { scope: "row" }, String(n)),
            series.map((item) => h("td", {}, String(Math.round(seriesValue(item.key, n))))),
          ),
        ),
      ),
    );
    tableBox.append(table);
    announce(host, `Chart updated for n up to ${nMax}.`, "info");
    if (ctx && ctx.onStep) ctx.onStep(0, { nMax });
  }

  range.addEventListener("input", () => {
    nMax = Number(range.value);
    rangeOut.textContent = String(nMax);
    draw();
  });
  logToggle.addEventListener("change", () => {
    logScale = logToggle.checked;
    draw();
  });

  draw();
  return { destroy() {} };
}
