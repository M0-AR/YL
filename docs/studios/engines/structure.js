import { announce, clamp, clear, h, stepper, svg } from "./util.js";

function renderArray(host, frame, spec) {
  const wrap = h("div", { class: `struct-array${spec.mode === "list" ? " is-list" : ""}` });
  const cells = frame.cells || [];
  cells.forEach((cell, index) => {
    const value = typeof cell === "object" ? cell.value : cell;
    const tone = typeof cell === "object" ? cell.tone : "";
    const highlighted = (frame.highlight || []).includes(index);
    const item = h(
      "div",
      {
        class: `struct-cell${tone ? ` tone-${tone}` : ""}${highlighted ? " is-highlight" : ""}`,
      },
      h("span", { class: "struct-index", "aria-hidden": "true" }, String(index)),
      h("span", { class: "struct-value" }, String(value)),
    );
    wrap.append(item);
    if (spec.mode === "list" && index < cells.length - 1) {
      wrap.append(h("span", { class: "struct-arrow", "aria-hidden": "true" }, "→"));
    }
  });
  host.append(wrap);
}

function renderTree(host, frame) {
  const width = 660;
  const height = 340;
  const pad = 34;
  const canvas = svg("svg", {
    viewBox: `0 0 ${width} ${height}`,
    class: "struct-tree",
    role: "img",
    "aria-label": frame.label ? `Tree state: ${frame.label}` : "Tree state",
  });
  const nodes = frame.nodes || [];
  const byId = new Map(nodes.map((node) => [node.id, node]));
  const px = (node) => pad + node.x * (width - pad * 2);
  const py = (node) => pad + node.y * (height - pad * 2);
  for (const node of nodes) {
    if (!node.parent || !byId.has(node.parent)) continue;
    const parent = byId.get(node.parent);
    canvas.append(
      svg("line", {
        x1: px(parent),
        y1: py(parent) + 16,
        x2: px(node),
        y2: py(node) - 16,
        class: "struct-edge",
      }),
    );
  }
  for (const node of nodes) {
    const highlighted = (frame.highlight || []).includes(node.id);
    canvas.append(
      svg("circle", {
        cx: px(node),
        cy: py(node),
        r: 18,
        class: `struct-node${highlighted ? " is-highlight" : ""}${
          node.tone ? ` tone-${node.tone}` : ""
        }`,
      }),
      svg(
        "text",
        {
          x: px(node),
          y: py(node) + 5,
          "text-anchor": "middle",
          class: "struct-nodelabel",
        },
        node.label ?? node.id,
      ),
    );
  }
  host.append(canvas);
}

function renderGrid(host, frame) {
  const table = h("table", { class: "struct-grid" });
  const head = h("thead", {}, h("tr", {}, h("th", { scope: "col" }, "")));
  const columnCount = Math.max(...(frame.rows || []).map((row) => row.length), 0);
  for (let column = 0; column < columnCount; column += 1) {
    head.firstElementChild.append(h("th", { scope: "col" }, String(column)));
  }
  table.append(head);
  const body = h("tbody");
  (frame.rows || []).forEach((row, rowIndex) => {
    const tr = h("tr", {}, h("th", { scope: "row" }, String(rowIndex)));
    row.forEach((cell, columnIndex) => {
      const highlighted = (frame.highlight || []).some(
        (pair) => pair[0] === rowIndex && pair[1] === columnIndex,
      );
      tr.append(
        h("td", { class: highlighted ? "is-highlight" : "" }, String(cell)),
      );
    });
    body.append(tr);
  });
  table.append(body);
  host.append(table);
}

function renderHash(host, frame) {
  const list = h("ol", { class: "struct-hash" });
  (frame.buckets || []).forEach((bucket) => {
    const highlighted = (frame.highlight || []).includes(bucket.index);
    list.append(
      h(
        "li",
        { class: highlighted ? "is-highlight" : "" },
        h("span", { class: "struct-bucket", "aria-hidden": "true" }, String(bucket.index)),
        h(
          "span",
          { class: "struct-chain" },
          bucket.items.length
            ? bucket.items.map((item, index) => `${index ? " → " : ""}${item}`).join("")
            : "empty",
        ),
      ),
    );
  });
  host.append(list);
}

export function mount(host, spec, ctx) {
  host.append(h("p", { class: "studio-hint" }, spec.hint || "Step through the structure operations."));
  const stage = h("div", { class: "struct-stage" });
  const label = h("p", { class: "struct-label" });
  const note = h("p", { class: "studio-note" });
  host.append(label, stage, note);

  const timeline = h("ol", { class: "studio-timeline", "aria-label": "Structure states", tabindex: "0" });
  const items = (spec.frames || []).map((frame, index) =>
    h(
      "li",
      {},
      h("span", { class: "studio-stepno", "aria-hidden": "true" }, String(index + 1)),
      h("span", {}, frame.label || `State ${index + 1}`),
    ),
  );
  for (const item of items) timeline.append(item);
  host.append(timeline);

  function render(index) {
    const frame = spec.frames[index];
    clear(stage);
    label.textContent = frame.label || "";
    if (spec.mode === "tree") renderTree(stage, frame);
    else if (spec.mode === "hash") renderHash(stage, frame);
    else if (spec.mode === "grid") renderGrid(stage, frame);
    else renderArray(stage, frame, spec);
    note.textContent = frame.note || "";
    items.forEach((item, itemIndex) => {
      item.classList.toggle("is-current", itemIndex === index);
      if (itemIndex === index) item.setAttribute("aria-current", "step");
      else item.removeAttribute("aria-current");
    });
    announce(
      host,
      `State ${index + 1} of ${spec.frames.length}. ${frame.label || ""}. ${frame.note || ""}`,
      "info",
    );
    if (ctx && ctx.onStep) ctx.onStep(index, frame);
  }

  const control = stepper(host, {
    total: spec.frames.length,
    label: "Structure state",
    onStep: render,
  });

  if (["array", "list", "grid"].includes(spec.mode)) {
    const search = h("label", { class: "struct-search" }, "Find value");
    const input = h("input", { type: "number", inputmode: "numeric", "aria-label": "Find value" });
    const result = h("p", { class: "struct-searchresult", role: "status", "aria-live": "polite" });
    search.append(input);
    host.append(search, result);
    input.addEventListener("input", () => {
      const value = Number(input.value);
      if (input.value === "" || Number.isNaN(value)) {
        result.textContent = "";
        return;
      }
      const frame = spec.frames[clamp(control.index, 0, spec.frames.length - 1)];
      const cells = spec.mode === "grid" ? (frame.rows || []).flat() : frame.cells || [];
      const values = cells.map((cell) => (typeof cell === "object" ? cell.value : cell));
      const found = values.findIndex((item) => Number(item) === value);
      result.textContent = found >= 0 ? `Found at flat index ${found}.` : "Not present in this state.";
      announce(host, result.textContent, found >= 0 ? "ok" : "warn");
    });
  }

  return {
    destroy() {
      control.destroy();
    },
  };
}
