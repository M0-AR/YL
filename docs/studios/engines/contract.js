import { announce, clear, h } from "./util.js";

function runCheck(check, values) {
  const value = values[check.field];
  switch (check.type) {
    case "nonnull":
      return value !== undefined && value !== null;
    case "nonneg":
      return typeof value === "number" && value >= 0;
    case "positive":
      return typeof value === "number" && value > 0;
    case "inrange":
      return typeof value === "number" && value >= check.min && value <= check.max;
    case "length":
      return Array.isArray(value) && value.length >= (check.min ?? 0) && value.length <= (check.max ?? Infinity);
    case "sorted":
      return (
        Array.isArray(value) &&
        value.every((item, index) => index === 0 || value[index - 1] <= item)
      );
    case "unique":
      return Array.isArray(value) && new Set(value.map(String)).size === value.length;
    case "equal":
      return value === check.value;
    case "manual":
      return check.field in values ? Boolean(value) : Boolean(check.ok);
    default:
      return false;
  }
}

export function evaluateContract(spec, values) {
  return spec.clauses.map((clause) => ({
    clause,
    pass: runCheck(clause.check, values || {}),
  }));
}

export function mount(host, spec, ctx) {
  host.append(h("p", { class: "studio-hint" }, spec.hint || "Test inputs against the contract clauses."));

  if (spec.signature) {
    host.append(h("pre", { class: "studio-code" }, h("code", {}, spec.signature)));
  }

  const clauseBox = h("ul", { class: "contract-clauses", "aria-label": "Contract clauses" });
  host.append(clauseBox);

  const caseLabel = h("label", { class: "studio-field" }, "Test case");
  const select = h(
    "select",
    { class: "studio-select", "aria-label": "Test case" },
    spec.cases.map((testCase, index) =>
      h("option", { value: index }, testCase.label || `Case ${index + 1}`),
    ),
  );
  caseLabel.append(select);
  const verdict = h("p", { class: "contract-verdict", role: "status", "aria-live": "polite" });
  const note = h("p", { class: "studio-note" });
  const inputs = h("div", { class: "contract-inputs" });
  const tableBox = h("div", {
    class: "contract-table",
    role: "region",
    "aria-label": "Contract test matrix",
    tabindex: "0",
  });
  host.append(caseLabel, inputs, verdict, note, tableBox);

  function render() {
    const index = Number(select.value);
    const testCase = spec.cases[index];
    const results = evaluateContract(spec, testCase.inputs);
    const pass = results.every((result) => result.pass);
    const expected = testCase.expected !== false;

    clear(clauseBox);
    for (const result of results) {
      clauseBox.append(
        h(
          "li",
          { class: result.pass ? "is-pass" : "is-fail" },
          h("span", { class: "contract-kind" }, result.clause.kind),
          h("code", {}, result.clause.text),
          h("span", { class: "contract-badge" }, result.pass ? "pass" : "fail"),
        ),
      );
    }

    clear(inputs);
    inputs.append(
      h(
        "dl",
        {},
        Object.entries(testCase.inputs)
          .map(([key, value]) => [
            h("dt", {}, key),
            h("dd", {}, JSON.stringify(value)),
          ])
          .flat(),
      ),
    );

    verdict.textContent = `${pass ? "Contract satisfied" : "Contract violated"} — expected ${
      expected ? "satisfied" : "violated"
    }.`;
    verdict.dataset.tone = pass === expected ? "ok" : "warn";
    note.textContent = testCase.note || "";
    announce(host, `${verdict.textContent} ${testCase.note || ""}`, pass === expected ? "ok" : "warn");

    clear(tableBox);
    tableBox.append(
      h(
        "table",
        { class: "studio-table" },
        h(
          "thead",
          {},
          h("tr", {}, h("th", { scope: "col" }, "Case"), h("th", { scope: "col" }, "All clauses pass?"), h("th", { scope: "col" }, "Expected")),
        ),
        h(
          "tbody",
          {},
          spec.cases.map((item, caseIndex) => {
            const casePass = evaluateContract(spec, item.inputs).every((result) => result.pass);
            return h(
              "tr",
              { class: caseIndex === index ? "is-current" : "" },
              h("th", { scope: "row" }, item.label || `Case ${caseIndex + 1}`),
              h("td", {}, casePass ? "yes" : "no"),
              h("td", {}, item.expected === false ? "no" : "yes"),
            );
          }),
        ),
      ),
    );
    if (ctx && ctx.onStep) ctx.onStep(index, { pass });
  }

  select.addEventListener("change", render);
  render();
  return { destroy() {} };
}
