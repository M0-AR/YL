import test from "node:test";
import assert from "node:assert/strict";

import {
  bubbleSteps,
  insertionSteps,
  mergeSteps,
  selectionSteps,
} from "../docs/studios/engines/sort.js";
import { buildGraphSteps } from "../docs/studios/engines/graph.js";
import { applyOperations } from "../docs/studios/engines/data.js";
import { evaluateContract } from "../docs/studios/engines/contract.js";
import { seriesValue } from "../docs/studios/engines/growth.js";
import { seededShuffle, format } from "../docs/studios/engines/util.js";

const INPUT = [42, 17, 93, 8, 61, 24, 55, 3];

function sortedCopy(values) {
  return values.slice().sort((a, b) => a - b);
}

for (const [name, build] of Object.entries({
  bubble: bubbleSteps,
  selection: selectionSteps,
  insertion: insertionSteps,
  merge: mergeSteps,
})) {
  test(`${name} sort ends sorted and preserves every value`, () => {
    const steps = build(INPUT);
    assert.ok(steps.length > 1);
    const final = steps.at(-1).array;
    assert.deepEqual(final, sortedCopy(INPUT));
    const allowed = new Set(INPUT);
    for (const step of steps) {
      assert.ok(step.array.every((value) => allowed.has(value)));
    }
  });
}

for (const [name, build] of Object.entries({
  bubble: bubbleSteps,
  selection: selectionSteps,
})) {
  test(`${name} records a full permutation at every step`, () => {
    for (const step of build(INPUT)) {
      assert.deepEqual(step.array.slice().sort((a, b) => a - b), sortedCopy(INPUT));
    }
  });
}

test("insertion sort is linear on sorted input", () => {
  const steps = insertionSteps([1, 2, 3, 4, 5]);
  const compares = steps.filter((step) => step.compare.length === 2).length;
  assert.equal(compares, 0);
});

test("merge sort records range merges", () => {
  const steps = mergeSteps([4, 1, 3, 2]);
  assert.ok(steps.some((step) => step.range && step.range[0] === 0 && step.range[1] === 3));
});

const GRAPH = {
  nodes: [
    { id: "A", x: 0.1, y: 0.2 },
    { id: "B", x: 0.4, y: 0.1 },
    { id: "C", x: 0.4, y: 0.6 },
    { id: "D", x: 0.8, y: 0.3 },
  ],
  edges: [
    { a: "A", b: "B", w: 2 },
    { a: "A", b: "C", w: 5 },
    { a: "B", b: "C", w: 1 },
    { a: "B", b: "D", w: 4 },
    { a: "C", b: "D", w: 1 },
  ],
  start: "A",
};

test("BFS visits every connected node exactly once", () => {
  const steps = buildGraphSteps({ ...GRAPH, algorithm: "bfs" });
  const final = steps.at(-1);
  assert.deepEqual(final.visited.slice().sort(), ["A", "B", "C", "D"]);
});

test("DFS visits every connected node exactly once", () => {
  const steps = buildGraphSteps({ ...GRAPH, algorithm: "dfs" });
  const final = steps.at(-1);
  assert.deepEqual(final.visited.slice().sort(), ["A", "B", "C", "D"]);
});

test("Dijkstra finds the marked shortest distances", () => {
  const steps = buildGraphSteps({ ...GRAPH, algorithm: "dijkstra" });
  const final = steps.at(-1);
  assert.equal(final.dist.A, 0);
  assert.equal(final.dist.B, 2);
  assert.equal(final.dist.C, 3);
  assert.equal(final.dist.D, 4);
});

test("Prim grows a spanning tree with every node", () => {
  const steps = buildGraphSteps({ ...GRAPH, algorithm: "prim" });
  const final = steps.at(-1);
  assert.equal(final.tree.length, 3);
  assert.deepEqual(final.visited.slice().sort(), ["A", "B", "C", "D"]);
});

test("topological order respects every directed edge", () => {
  const steps = buildGraphSteps({
    directed: true,
    start: "A",
    algorithm: "topo",
    nodes: [
      { id: "A" },
      { id: "B" },
      { id: "C" },
      { id: "D" },
    ],
    edges: [
      { a: "A", b: "B" },
      { a: "A", b: "C" },
      { a: "C", b: "D" },
    ],
  });
  const order = steps.at(-1).visited;
  assert.equal(order.length, 4);
  assert.ok(order.indexOf("A") < order.indexOf("B"));
  assert.ok(order.indexOf("A") < order.indexOf("C"));
  assert.ok(order.indexOf("C") < order.indexOf("D"));
});

test("data pipeline filters, sorts, and groups", () => {
  const columns = ["name", "department", "score"];
  const rows = [
    ["Ada", "R&D", 91],
    ["Grace", "Ops", 78],
    ["Linus", "R&D", 84],
  ];
  const filtered = applyOperations(rows, columns, [
    { type: "filter", column: "score", op: ">=", value: 80 },
    { type: "sort", column: "score", dir: "desc" },
  ]);
  assert.deepEqual(filtered.rows.map((row) => row[0]), ["Ada", "Linus"]);

  const grouped = applyOperations(rows, columns, [{ type: "group", column: "department" }]);
  assert.deepEqual(grouped.columns, ["department", "count"]);
  assert.equal(grouped.rows.length, 2);
  assert.equal(grouped.rows[0][1], 2);
});

test("contract evaluation reports every failing clause", () => {
  const spec = {
    clauses: [
      { kind: "pre", text: "values != null", check: { type: "nonnull", field: "values" } },
      { kind: "pre", text: "values.length >= 1", check: { type: "length", field: "values", min: 1 } },
      { kind: "post", text: "result >= 0", check: { type: "nonneg", field: "result" } },
    ],
  };
  const good = evaluateContract(spec, { values: [1, 2], result: 0 });
  assert.ok(good.every((item) => item.pass));
  const bad = evaluateContract(spec, { values: [], result: -1 });
  assert.equal(bad.filter((item) => !item.pass).length, 2);
});

test("growth formulas are monotonic where expected", () => {
  assert.equal(seriesValue("n", 10), 10);
  assert.equal(seriesValue("square", 10), 100);
  assert.ok(seriesValue("nlogn", 10) > 10);
  assert.equal(seriesValue("exp", 4), 16);
});

test("seeded shuffle is deterministic and keeps every item", () => {
  const first = seededShuffle(["a", "b", "c", "d"], 7);
  const second = seededShuffle(["a", "b", "c", "d"], 7);
  assert.deepEqual(first, second);
  assert.deepEqual(first.slice().sort(), ["a", "b", "c", "d"]);
});

test("format renders nested arrays and trims float noise", () => {
  assert.equal(format([1, [2, 3]]), "[1, [2, 3]]");
  assert.equal(format(0.30000000000000004), "0.3");
});
