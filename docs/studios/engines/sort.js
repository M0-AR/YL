import { announce, clear, h, stepper } from "./util.js";

export function bubbleSteps(input) {
  const arr = input.slice();
  const steps = [];
  const sorted = [];
  for (let i = 0; i < arr.length - 1; i += 1) {
    let swapped = false;
    for (let j = 0; j < arr.length - i - 1; j += 1) {
      steps.push({
        array: arr.slice(),
        compare: [j, j + 1],
        sorted: sorted.slice(),
        note: `Compare ${arr[j]} and ${arr[j + 1]}.`,
      });
      if (arr[j] > arr[j + 1]) {
        [arr[j], arr[j + 1]] = [arr[j + 1], arr[j]];
        swapped = true;
        steps.push({
          array: arr.slice(),
          compare: [j, j + 1],
          sorted: sorted.slice(),
          note: `Swap: the larger value moves right.`,
        });
      }
    }
    sorted.unshift(arr.length - 1 - i);
    if (!swapped) break;
  }
  steps.push({ array: arr.slice(), compare: [], sorted: arr.map((_, i) => i), note: "Array is sorted." });
  return steps;
}

export function selectionSteps(input) {
  const arr = input.slice();
  const steps = [];
  const sorted = [];
  for (let i = 0; i < arr.length - 1; i += 1) {
    let min = i;
    for (let j = i + 1; j < arr.length; j += 1) {
      steps.push({
        array: arr.slice(),
        compare: [min, j],
        sorted: sorted.slice(),
        note: `Is ${arr[j]} smaller than the current minimum ${arr[min]}?`,
      });
      if (arr[j] < arr[min]) min = j;
    }
    if (min !== i) {
      [arr[i], arr[min]] = [arr[min], arr[i]];
      steps.push({
        array: arr.slice(),
        compare: [],
        sorted: sorted.slice(),
        note: `Place the minimum ${arr[i]} at index ${i}.`,
      });
    }
    sorted.push(i);
  }
  sorted.push(arr.length - 1);
  steps.push({ array: arr.slice(), compare: [], sorted: sorted.slice(), note: "Array is sorted." });
  return steps;
}

export function insertionSteps(input) {
  const arr = input.slice();
  const steps = [];
  const sorted = [0];
  for (let i = 1; i < arr.length; i += 1) {
    const key = arr[i];
    let j = i - 1;
    steps.push({
      array: arr.slice(),
      compare: [i],
      sorted: sorted.slice(),
      note: `Take ${key} from index ${i}.`,
    });
    while (j >= 0 && arr[j] > key) {
      steps.push({
        array: arr.slice(),
        compare: [j, j + 1],
        sorted: sorted.slice(),
        note: `${arr[j]} is larger than ${key}; shift it right.`,
      });
      arr[j + 1] = arr[j];
      j -= 1;
    }
    arr[j + 1] = key;
    steps.push({
      array: arr.slice(),
      compare: [j + 1],
      sorted: [...sorted, i],
      note: `Insert ${key} at index ${j + 1}.`,
    });
    sorted.push(i);
  }
  steps.push({ array: arr.slice(), compare: [], sorted: arr.map((_, i) => i), note: "Array is sorted." });
  return steps;
}

export function mergeSteps(input) {
  const arr = input.slice();
  const steps = [];
  const work = arr.slice();

  function merge(lo, mid, hi) {
    const left = work.slice(lo, mid + 1);
    const right = work.slice(mid + 1, hi + 1);
    let i = 0;
    let j = 0;
    let k = lo;
    while (i < left.length && j < right.length) {
      steps.push({
        array: work.slice(),
        compare: [lo + i, mid + 1 + j],
        sorted: [],
        range: [lo, hi],
        note: `Merge ranges: compare ${left[i]} and ${right[j]}.`,
      });
      if (left[i] <= right[j]) {
        work[k] = left[i];
        i += 1;
      } else {
        work[k] = right[j];
        j += 1;
      }
      k += 1;
      steps.push({
        array: work.slice(),
        compare: [],
        sorted: [],
        range: [lo, hi],
        note: `Write the smaller value into index ${k - 1}.`,
      });
    }
    while (i < left.length) {
      work[k] = left[i];
      i += 1;
      k += 1;
    }
    while (j < right.length) {
      work[k] = right[j];
      j += 1;
      k += 1;
    }
    steps.push({
      array: work.slice(),
      compare: [],
      sorted: [],
      range: [lo, hi],
      note: `Range ${lo}–${hi} is merged.`,
    });
  }

  function sort(lo, hi) {
    if (lo >= hi) return;
    const mid = Math.floor((lo + hi) / 2);
    sort(lo, mid);
    sort(mid + 1, hi);
    merge(lo, mid, hi);
  }

  sort(0, arr.length - 1);
  steps.push({ array: work.slice(), compare: [], sorted: work.map((_, i) => i), note: "Array is sorted." });
  return steps;
}

export function buildSteps(algorithm, input) {
  if (algorithm === "selection") return selectionSteps(input);
  if (algorithm === "insertion") return insertionSteps(input);
  if (algorithm === "merge") return mergeSteps(input);
  return bubbleSteps(input);
}

function renderBars(stage, step, input) {
  clear(stage);
  const max = Math.max(...input.map((value) => Math.abs(value)), 1);
  const row = h("div", { class: "sort-bars" });
  step.array.forEach((value, index) => {
    const tone = step.sorted.includes(index)
      ? "sorted"
      : (step.compare || []).includes(index)
        ? "compare"
        : step.range && index >= step.range[0] && index <= step.range[1]
          ? "range"
          : "";
    row.append(
      h(
        "div",
        { class: `sort-bar${tone ? ` tone-${tone}` : ""}` },
        h("span", { class: "sort-value" }, String(value)),
        h("i", {
          class: "sort-fill",
          style: `height:${Math.max(8, (Math.abs(value) / max) * 100)}%`,
          "aria-hidden": "true",
        }),
        h("span", { class: "sort-index", "aria-hidden": "true" }, String(index)),
      ),
    );
  });
  stage.append(row);
}

export function mount(host, spec, ctx) {
  host.append(h("p", { class: "studio-hint" }, spec.hint || "Compare, swap, and watch the order emerge."));
  const algorithms = spec.algorithms || [spec.algorithm || "bubble"];
  const labels = {
    bubble: "Bubble sort",
    selection: "Selection sort",
    insertion: "Insertion sort",
    merge: "Merge sort",
  };
  let destroyCurrent = null;
  const panel = h("div", { class: "sort-panel" });
  const select = h(
    "select",
    { class: "studio-select", "aria-label": "Sorting algorithm" },
    algorithms.map((algorithm) =>
      h("option", { value: algorithm, selected: algorithm === algorithms[0] }, labels[algorithm] || algorithm),
    ),
  );
  if (algorithms.length > 1) {
    host.append(h("label", { class: "studio-field" }, "Algorithm", select));
  }
  host.append(panel);

  function show(algorithm) {
    if (destroyCurrent) destroyCurrent();
    clear(panel);
    const input = spec.input || [5, 1, 8, 3, 9, 2];
    const steps = buildSteps(algorithm, input);
    const stage = h("div", { class: "sort-stage" });
    const note = h("p", { class: "studio-note" });
    const stats = h("p", { class: "studio-hint" }, `${steps.length} recorded steps.`);
    panel.append(stage, note, stats);
    let last = 0;
    function render(index) {
      const step = steps[index];
      renderBars(stage, step, input);
      note.textContent = step.note;
      announce(
        host,
        `${labels[algorithm] || algorithm}, step ${index + 1} of ${steps.length}. ${step.note}`,
        "info",
      );
      last = index;
      if (ctx && ctx.onStep) ctx.onStep(index, step);
    }
    const control = stepper(panel, {
      total: steps.length,
      label: "Sorting step",
      onStep: render,
    });
    destroyCurrent = () => control.destroy();
    return last;
  }

  select.addEventListener("change", () => show(select.value));
  show(select.value);
  return {
    destroy() {
      if (destroyCurrent) destroyCurrent();
    },
  };
}
