import { announce, clear, h } from "./util.js";

function compare(value, op, target) {
  if (op === ">=") return value >= target;
  if (op === "<=") return value <= target;
  if (op === ">") return value > target;
  if (op === "<") return value < target;
  if (op === "===" || op === "=") return value === target;
  if (op === "!==" || op === "!=") return value !== target;
  if (op === "includes") return String(value).includes(String(target));
  return false;
}

export function applyOperations(rows, columns, operations) {
  let data = rows.map((row) => row.slice());
  for (const op of operations) {
    const index = columns.indexOf(op.column);
    if (op.type === "filter" && index >= 0) {
      data = data.filter((row) => compare(row[index], op.op || ">=", op.value));
    } else if (op.type === "sort" && index >= 0) {
      data = data.slice().sort((a, b) => {
        const result = a[index] < b[index] ? -1 : a[index] > b[index] ? 1 : 0;
        return op.dir === "desc" ? -result : result;
      });
    } else if (op.type === "select") {
      const keep = op.columns.map((name) => columns.indexOf(name)).filter((i) => i >= 0);
      data = data.map((row) => keep.map((i) => row[i]));
    } else if (op.type === "limit") {
      data = data.slice(0, op.n);
    } else if (op.type === "group" && index >= 0) {
      const counts = new Map();
      for (const row of data) counts.set(row[index], (counts.get(row[index]) || 0) + 1);
      const grouped = Array.from(counts.entries()).sort((a, b) => b[1] - a[1]);
      return {
        columns: [op.column, "count"],
        rows: grouped,
      };
    }
  }
  return { columns: columns.slice(), rows: data };
}

export function mount(host, spec, ctx) {
  host.append(h("p", { class: "studio-hint" }, spec.hint || "Toggle pipeline steps and watch the rows change."));

  const baseColumns = spec.columns;
  const baseRows = spec.rows;
  const operations = spec.operations || [];
  const selected = new Set(spec.defaults || []);
  let locale = spec.locale || "en-US";

  const localeRow = h("div", { class: "data-locale" });
  if (spec.i18n) {
    const select = h(
      "select",
      { class: "studio-select", "aria-label": "Number format locale" },
      ["en-US", "de-DE", "fr-FR", "ja-JP"].map((code) =>
        h("option", { value: code, selected: code === locale }, code),
      ),
    );
    select.addEventListener("change", () => {
      locale = select.value;
      draw();
    });
    localeRow.append(h("label", { class: "studio-field" }, "Locale", select));
  }

  const opsBox = h("fieldset", { class: "data-ops" }, h("legend", {}, "Pipeline"));
  const checkboxes = operations.map((op, index) => {
    const input = h("input", { type: "checkbox", id: `data-op-${index}` });
    input.checked = selected.has(op.id);
    input.addEventListener("change", () => {
      if (input.checked) selected.add(op.id);
      else selected.delete(op.id);
      draw();
    });
    opsBox.append(h("label", { class: "studio-check" }, input, op.label));
    return input;
  });

  const resultBox = h("div", { class: "data-result" });
  host.append(localeRow, opsBox, resultBox);

  function formatCell(value, column) {
    if (typeof value === "number" && spec.i18n) {
      const options = spec.currencyColumn === column
        ? { style: "currency", currency: spec.currency || "USD" }
        : { maximumFractionDigits: 2 };
      try {
        return new Intl.NumberFormat(locale, options).format(value);
      } catch {
        return String(value);
      }
    }
    return String(value);
  }

  function draw() {
    const active = operations.filter((op) => selected.has(op.id));
    const result = applyOperations(baseRows, baseColumns, active);
    clear(resultBox);
    const summary = h(
      "p",
      { class: "data-summary", role: "status", "aria-live": "polite" },
      `${result.rows.length} row${result.rows.length === 1 ? "" : "s"} · ${active.length} pipeline step${
        active.length === 1 ? "" : "s"
      }`,
    );
    const table = h(
      "table",
      { class: "studio-table" },
      h(
        "thead",
        {},
        h("tr", {}, result.columns.map((name) => h("th", { scope: "col" }, name))),
      ),
      h(
        "tbody",
        {},
        result.rows.slice(0, 12).map((row) =>
          h(
            "tr",
            {},
            row.map((value, index) =>
              h("td", {}, formatCell(value, baseColumns[index] ?? result.columns[index])),
            ),
          ),
        ),
      ),
    );
    resultBox.append(summary, table);
    if (result.rows.length > 12) {
      resultBox.append(h("p", { class: "studio-hint" }, "Showing the first 12 rows."));
    }
    announce(host, `${result.rows.length} rows after ${active.length} pipeline steps.`, "ok");
    if (ctx && ctx.onStep) ctx.onStep(0, { rows: result.rows.length });
  }

  draw();
  return { destroy() {} };
}
