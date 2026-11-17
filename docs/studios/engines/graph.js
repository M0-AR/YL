import { announce, clear, h, stepper, svg } from "./util.js";

function edgeKey(a, b) {
  return [a, b].sort().join("—");
}

function adjacency(spec) {
  const map = new Map(spec.nodes.map((node) => [node.id, []]));
  for (const edge of spec.edges) {
    map.get(edge.a).push({ id: edge.b, weight: edge.w ?? 1 });
    if (!spec.directed) map.get(edge.b).push({ id: edge.a, weight: edge.w ?? 1 });
  }
  for (const list of map.values()) list.sort((a, b) => a.id.localeCompare(b.id));
  return map;
}

export function buildGraphSteps(spec) {
  const adj = adjacency(spec);
  const steps = [];
  const start = spec.start || spec.nodes[0].id;
  const push = (state) => steps.push({ visited: [], frontier: [], tree: [], dist: {}, ...state });

  if (spec.algorithm === "dijkstra") {
    const dist = {};
    const prev = {};
    for (const node of spec.nodes) dist[node.id] = Infinity;
    dist[start] = 0;
    const done = new Set();
    push({ visited: [], frontier: [start], dist: { ...dist }, note: `Start at ${start} with distance 0.` });
    while (done.size < spec.nodes.length) {
      let current = null;
      for (const node of spec.nodes) {
        if (done.has(node.id)) continue;
        if (current === null || dist[node.id] < dist[current]) current = node.id;
      }
      if (current === null || dist[current] === Infinity) break;
      done.add(current);
      push({
        visited: [...done],
        frontier: [],
        current,
        dist: { ...dist },
        note: `Settle ${current} at distance ${dist[current]}.`,
      });
      for (const next of adj.get(current)) {
        if (done.has(next.id)) continue;
        const candidate = dist[current] + next.weight;
        if (candidate < dist[next.id]) {
          dist[next.id] = candidate;
          prev[next.id] = current;
          push({
            visited: [...done],
            frontier: [next.id],
            current,
            dist: { ...dist },
            note: `Relax ${current} → ${next.id}: distance ${candidate}.`,
          });
        }
      }
    }
    const tree = Object.entries(prev).map(([node, parent]) => edgeKey(node, parent));
    push({ visited: [...done], tree, dist: { ...dist }, note: "Shortest distances are final." });
    return steps;
  }

  if (spec.algorithm === "prim") {
    const inTree = new Set([start]);
    const tree = [];
    push({ visited: [start], note: `Start the tree at ${start}.` });
    while (inTree.size < spec.nodes.length) {
      let best = null;
      for (const edge of spec.edges) {
        const aIn = inTree.has(edge.a);
        const bIn = inTree.has(edge.b);
        if (aIn === bIn) continue;
        const weight = edge.w ?? 1;
        if (!best || weight < best.weight) best = { ...edge, weight };
      }
      if (!best) break;
      const next = inTree.has(best.a) ? best.b : best.a;
      inTree.add(next);
      tree.push(edgeKey(best.a, best.b));
      push({
        visited: [...inTree],
        tree: tree.slice(),
        current: next,
        note: `Add ${next} with the cheapest crossing edge (weight ${best.weight}).`,
      });
    }
    return steps;
  }

  if (spec.algorithm === "topo") {
    const indegree = new Map(spec.nodes.map((node) => [node.id, 0]));
    for (const edge of spec.edges) indegree.set(edge.b, (indegree.get(edge.b) || 0) + 1);
    const queue = spec.nodes.filter((node) => indegree.get(node.id) === 0).map((node) => node.id);
    const order = [];
    push({ frontier: [...queue], note: "Queue every node with indegree 0." });
    while (queue.length) {
      const current = queue.shift();
      order.push(current);
      push({ visited: [...order], frontier: [...queue], current, note: `Emit ${current}.` });
      for (const next of adj.get(current)) {
        indegree.set(next.id, indegree.get(next.id) - 1);
        if (indegree.get(next.id) === 0) queue.push(next.id);
      }
    }
    push({ visited: [...order], note: order.length === spec.nodes.length ? "Topological order complete." : "Cycle detected." });
    return steps;
  }

  const seen = new Set([start]);
  const visited = [];
  const frontier = [start];
  push({ frontier: [...frontier], note: `Place ${start} in the frontier.` });
  while (frontier.length) {
    const current = spec.algorithm === "dfs" ? frontier.pop() : frontier.shift();
    if (visited.includes(current)) continue;
    visited.push(current);
    push({ visited: [...visited], frontier: [...frontier], current, note: `Visit ${current}.` });
    for (const next of adj.get(current)) {
      if (!seen.has(next.id)) {
        seen.add(next.id);
        frontier.push(next.id);
        push({
          visited: [...visited],
          frontier: [...frontier],
          current,
          note: `Discover ${next.id} from ${current}.`,
        });
      }
    }
  }
  push({ visited: [...visited], note: "Traversal complete." });
  return steps;
}

function drawGraph(stage, spec, step) {
  clear(stage);
  const width = 660;
  const height = 360;
  const pad = 40;
  const canvas = svg("svg", {
    viewBox: `0 0 ${width} ${height}`,
    class: "graph-canvas",
    role: "img",
    "aria-label": `Graph with ${spec.nodes.length} nodes and ${spec.edges.length} edges`,
  });
  const positions = new Map();
  for (const node of spec.nodes) {
    positions.set(node.id, {
      x: pad + (node.x ?? 0.5) * (width - pad * 2),
      y: pad + (node.y ?? 0.5) * (height - pad * 2),
    });
  }
  for (const edge of spec.edges) {
    const a = positions.get(edge.a);
    const b = positions.get(edge.b);
    if (!a || !b) continue;
    const key = edgeKey(edge.a, edge.b);
    const inTree = (step.tree || []).includes(key);
    canvas.append(
      svg("line", {
        x1: a.x,
        y1: a.y,
        x2: b.x,
        y2: b.y,
        class: `graph-edge${inTree ? " is-tree" : ""}`,
      }),
    );
    if (edge.w !== undefined) {
      canvas.append(
        svg(
          "text",
          {
            x: (a.x + b.x) / 2,
            y: (a.y + b.y) / 2 - 5,
            "text-anchor": "middle",
            class: "graph-weight",
          },
          String(edge.w),
        ),
      );
    }
  }
  for (const node of spec.nodes) {
    const point = positions.get(node.id);
    const isVisited = (step.visited || []).includes(node.id);
    const isFrontier = (step.frontier || []).includes(node.id);
    const isCurrent = step.current === node.id;
    canvas.append(
      svg("circle", {
        cx: point.x,
        cy: point.y,
        r: 20,
        class: `graph-node${isVisited ? " is-visited" : ""}${isFrontier ? " is-frontier" : ""}${
          isCurrent ? " is-current" : ""
        }`,
      }),
      svg(
        "text",
        { x: point.x, y: point.y + 5, "text-anchor": "middle", class: "graph-label" },
        node.label ?? node.id,
      ),
    );
    if (step.dist && step.dist[node.id] !== undefined && step.dist[node.id] !== Infinity) {
      canvas.append(
        svg(
          "text",
          { x: point.x, y: point.y + 35, "text-anchor": "middle", class: "graph-dist" },
          `d=${step.dist[node.id]}`,
        ),
      );
    }
  }
  stage.append(canvas);
}

export function mount(host, spec, ctx) {
  host.append(h("p", { class: "studio-hint" }, spec.hint || "Step through the algorithm and read the frontier."));
  const steps = buildGraphSteps(spec);
  const stage = h("div", { class: "graph-stage" });
  const status = h("div", { class: "graph-status", role: "group", "aria-label": "Algorithm state" });
  const note = h("p", { class: "studio-note" });
  host.append(stage, status, note);

  const timeline = h("ol", { class: "studio-timeline", "aria-label": "Algorithm steps", tabindex: "0" });
  const items = steps.map((step, index) =>
    h(
      "li",
      {},
      h("span", { class: "studio-stepno", "aria-hidden": "true" }, String(index + 1)),
      h("span", {}, step.note),
    ),
  );
  for (const item of items) timeline.append(item);
  host.append(timeline);

  function render(index) {
    const step = steps[index];
    drawGraph(stage, spec, step);
    clear(status);
    const rows = [
      ["Current", step.current ?? "—"],
      ["Visited", (step.visited || []).join(", ") || "—"],
      ["Frontier", (step.frontier || []).join(", ") || "—"],
    ];
    if (step.dist && Object.keys(step.dist).length) {
      rows.push([
        "Distances",
        Object.entries(step.dist)
          .map(([node, value]) => `${node}:${value === Infinity ? "∞" : value}`)
          .join(" "),
      ]);
    }
    for (const [label, value] of rows) {
      status.append(h("div", {}, h("b", {}, label), h("span", {}, value)));
    }
    note.textContent = step.note;
    items.forEach((item, itemIndex) => {
      item.classList.toggle("is-current", itemIndex === index);
      if (itemIndex === index) item.setAttribute("aria-current", "step");
      else item.removeAttribute("aria-current");
    });
    announce(host, `Step ${index + 1} of ${steps.length}. ${step.note}`, "info");
    if (ctx && ctx.onStep) ctx.onStep(index, step);
  }

  const control = stepper(host, {
    total: steps.length,
    label: "Algorithm step",
    onStep: render,
  });

  return {
    destroy() {
      control.destroy();
    },
  };
}
