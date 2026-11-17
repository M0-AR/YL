# Exercise Coverage Audit

This repository is a **chapter atlas and selective exercise companion**, not an official solution manual. It verifies that every chapter has runnable code and that every variant in the public 10e CheckExercise list has an implementation. It does not claim that the inferred implementations match instructor-only prompt text.

## Method

- Official source: the per-chapter exercise dropdown at [Pearson CheckExercise](https://liveexample.pearsoncmg.com/CheckExercise/faces/CheckExercise.xhtml).
- Counted unique exercise variants, including `Extra` variants, for chapters where the dropdown is public.
- Matched those numbers against numbered `src/Exercise_XX/_NN/` folders and against `extra11e/src/Exercise_XX/_NNExtra/` classes.
- Where a 10e `Extra` statement is instructor-only, implemented a topic-aligned exercise and marked it as inferred.
- No exercise prompts, textbook text, or official solutions are reproduced here.
- Some solutions are adapted from MIT-licensed repositories. See [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md).

## Result

- **1,026 official exercise variants** are publicly listed for chapters 1–30 in the 10e CheckExercise dropdown: **825 base exercises + 201 Extra variants**.
- **1,026 of 1,026 variants (100.0%)** have code in this repository: **825 base exercises + 201 Extra exercises**. No public listed variant remains missing.
- **86 base exercises and 23 Extra exercises are inferred implementations**. Their exact prompts are instructor-only or otherwise unavailable, so they are topic-aligned practice, not verified textbook statements. That is **109 inferred implementations** in total.
- **201 exercise classes in `extra11e/src` match the 10e Extra names**, including the 23 newly inferred Extras. `extra11e/src` contains **223 Java files**: **213 exercise classes + 10 shared helpers**.
- **12 additional Extra classes** in `extra11e/src` are 11e files outside the 10e CheckExercise list. They are preserved but are **not counted** in the 1,026 denominator.
- All Extra programs compile with `-Xlint:all` and zero warnings. GUI programs are compile-verified; runtime GUI testing is unavailable in this environment.
- Chapters 31–43 expose **no public exercise dropdown** in CheckExercise, so their official counts cannot be verified without Pearson access. The repo has one numbered folder there (`34/_01`) and none elsewhere.

### 11e Extra implementation

The `Extra` column below is the 10e CheckExercise listing. The 11e Extra files are audited separately under `extra11e/src`:

- `extra11e/src` contains **223 Java files**: **213 exercise classes + 10 shared helpers**.
- **201 exercise classes** match the 10e `ExerciseXX_NNExtra.java` names for chapters 1–30. **178** were already present as 11e Extra files; **23** were added from inferred specs because their official statements are instructor-only.
- **12 extra11e-only Extras** are outside the 10e catalog and are not counted toward 1,026: 03.04, 03.05, 05.15, 05.16, 15.15, 18.05, 18.06, 18.07, 21.02, 21.03, 26.01, and 26.02.
- The 28.16, 28.18, 29.01, and 29.02 items are included in the 201 and are implemented; they are not a separate uncoded category.
- All exercises printed in the 10e chapter PDFs for chapters 16, 20, and 22–30 have code; chapters 1–15, 17–19, 21, and 25 were already complete for their printed sets. The 86 unprinted base items are inferred separately.
- Chapters 31–43 expose no public exercise dropdown in CheckExercise, so their official counts cannot be verified without Pearson access. The repo has one numbered folder there (`34/_01`) and none elsewhere.

Chapters 1–30 now cover every **base** exercise and every **10e Extra** variant in the public list. Chapter 19 covers all 21 listed base exercises; 19.12–19.21 are instructor-only extras. Chapters 16, 20, 23, 24, 26, and 27 cover every printed exercise plus their unprinted items through inferred implementations.

### Inferred base exercises

The 11e textbook confirms that the Extra section contains many exercises “not printed in the text.” The 10e CheckExercise dropdown lists those unprinted base items, but Pearson does not expose their statements, and no public mirror was found. The following ranges are therefore inferred from the chapter topics and implemented as topic-aligned practice:

- 16.32–16.40: UI-control and multimedia model exercises
- 19.12–19.21: generic utilities and generic data structures
- 20.24–20.38: list, stack, queue, priority-queue, and cache exercises
- 23.21–23.25: bucket, external, counting, string, and comparison sorts
- 24.17–24.26: array/list, linked-list, deque, stack, queue, and iterator implementations
- 26.08–26.25: AVL traversal, validation, cloning, serialization, and range operations
- 27.12–27.30: hashing, probing, resizing, set operations, and an LRU cache

Each inferred base program has a deterministic `main` smoke test and passes it. These are not official textbook prompts, and they should not be treated as canonical exercise statements.

### Inferred Extra exercises

The 23 10e Extras whose statements are instructor-only were implemented from chapter topics, with deterministic `main` smoke tests for console programs and compile checks for GUI programs:

- 05.17: pyramid rows with summed values
- 12.08: octal parser with a custom number-format exception
- 15.21: rotating JavaFX triangle animation
- 16.16: dual-list transfer interface
- 16.17: RGB color mixer interface
- 18.08: recursive palindrome and digit-sum checks
- 20.01: priority-queue word ordering by length and alphabet
- 20.03: capital quiz using a queue and map
- 20.04: point comparator sorting
- 21.05: keyword frequency ignoring comments and strings
- 21.06: least-frequent integers
- 21.07: word frequency in descending order
- 22.05: longest increasing contiguous run
- 25.04: BST inorder traversal and height
- 25.05: BST level-order traversal
- 27.02: linear-probing map
- 27.03: chained hash set
- 27.04: chained hash map with rehashing
- 28.19: JavaFX shortest-path visualization
- 28.20: connected components
- 29.04: Dijkstra shortest paths
- 29.05: Prim minimum spanning tree
- 30.01: producer-consumer queue

These inferred Extras are not official statements and should not be treated as canonical exercise prompts.

## Chapter detail

| Ch | Official variants | Base | Extra | Base dirs | 10e Extra dirs | Covered | Coverage | Missing base | Missing Extra |
|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| 01 | 16 | 13 | 3 | 13 | 3 | 16 | 100.0% | — | 0 |
| 02 | 29 | 23 | 6 | 23 | 6 | 29 | 100.0% | — | 0 |
| 03 | 38 | 34 | 4 | 34 | 4 | 38 | 100.0% | — | 0 |
| 04 | 34 | 26 | 8 | 26 | 8 | 34 | 100.0% | — | 0 |
| 05 | 66 | 51 | 15 | 51 | 15 | 66 | 100.0% | — | 0 |
| 06 | 53 | 39 | 14 | 39 | 14 | 53 | 100.0% | — | 0 |
| 07 | 49 | 35 | 14 | 37 | 14 | 49 | 100.0% | — | 0 |
| 08 | 44 | 37 | 7 | 37 | 7 | 44 | 100.0% | — | 0 |
| 09 | 16 | 13 | 3 | 13 | 3 | 16 | 100.0% | — | 0 |
| 10 | 32 | 28 | 4 | 28 | 4 | 32 | 100.0% | — | 0 |
| 11 | 21 | 17 | 4 | 19 | 4 | 21 | 100.0% | — | 0 |
| 12 | 41 | 33 | 8 | 33 | 8 | 41 | 100.0% | — | 0 |
| 13 | 27 | 21 | 6 | 21 | 6 | 27 | 100.0% | — | 0 |
| 14 | 42 | 29 | 13 | 29 | 13 | 42 | 100.0% | — | 0 |
| 15 | 56 | 36 | 20 | 36 | 20 | 56 | 100.0% | — | 0 |
| 16 | 57 | 40 | 17 | 40 | 17 | 57 | 100.0% | — | 0 |
| 17 | 21 | 21 | 0 | 21 | 0 | 21 | 100.0% | — | 0 |
| 18 | 44 | 39 | 5 | 39 | 5 | 44 | 100.0% | — | 0 |
| 19 | 21 | 21 | 0 | 21 | 0 | 21 | 100.0% | — | 0 |
| 20 | 41 | 38 | 3 | 38 | 3 | 41 | 100.0% | — | 0 |
| 21 | 19 | 15 | 4 | 15 | 4 | 19 | 100.0% | — | 0 |
| 22 | 30 | 25 | 5 | 25 | 5 | 30 | 100.0% | — | 0 |
| 23 | 26 | 25 | 1 | 25 | 1 | 26 | 100.0% | — | 0 |
| 24 | 28 | 26 | 2 | 26 | 2 | 28 | 100.0% | — | 0 |
| 25 | 28 | 23 | 5 | 23 | 5 | 28 | 100.0% | — | 0 |
| 26 | 25 | 25 | 0 | 25 | 0 | 25 | 100.0% | — | 0 |
| 27 | 34 | 30 | 4 | 30 | 4 | 34 | 100.0% | — | 0 |
| 28 | 43 | 23 | 20 | 24 | 20 | 43 | 100.0% | — | 0 |
| 29 | 25 | 20 | 5 | 20 | 5 | 25 | 100.0% | — | 0 |
| 30 | 20 | 19 | 1 | 22 | 1 | 20 | 100.0% | — | 0 |
| **Total** | **1,026** | **825** | **201** | **833** | **201** | **1,026** | **100.0%** | **0** | **0** |
| 31 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 32 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 33 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 34 | n/a | n/a | n/a | 1 | 0 | n/a | unverified | n/a | n/a |
| 35 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 36 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 37 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 38 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 39 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 40 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 41 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 42 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |
| 43 | n/a | n/a | n/a | 0 | 0 | n/a | unverified | n/a | n/a |

> **Counting note:** `Base dirs` counts physical numbered folders (**833**). Eight surplus folders in Chapters 07, 11, 28, and 30 are not credited, so the official base denominator remains **825**. `10e Extra dirs` counts the **201** 10e-named Extra classes; the 12 extra11e-only Extras are outside the 10e denominator.
>
> **Chapter 19 note:** The 10e textbook prints 19.01–19.11 as the chapter's base exercises. The CheckExercise listing also exposes 19.12–19.21, which the textbook identifies as instructor-only extra exercises. They are counted as listed variants in the table and are implemented from inferred specs because their prompts are not public.
>
> **Chapter 30 note:** The textbook edition used for the printed exercise lists has 22 exercises, while the public CheckExercise dropdown exposes only 20 variants (19 base + 1 Extra). The surplus folders are not credited as base or `Extra` coverage, so the percentage is capped at 100.0%.

## What this means

- The 43-chapter claim is about **chapter coverage**: every chapter has runnable code and a place in the atlas.
- The exercise claim is now **1,026 of 1,026 public 10e variants (100.0%)**: **825 base + 201 Extra**. Chapters 1–30 are complete for the public list.
- Of those, **109 implementations are inferred** — 86 unprinted/instructor-only base items and 23 instructor-only Extra items. They are topic-aligned practice, not verified textbook prompts.
- `extra11e/src` contains **223 Java files**: **213 exercise classes + 10 shared helpers**. Of the exercise classes, **201 match 10e Extra names** and **12 are 11e-only additions** outside the 10e count.
- Chapters 31–43 would still need an authoritative exercise list before their coverage can be audited.

## Sources

- [Official 10th-edition table of contents](https://liveexample.pearsoncmg.com/liang/intro10e/toc.html)
- [Official CheckExercise](https://liveexample.pearsoncmg.com/CheckExercise/faces/CheckExercise.xhtml)
