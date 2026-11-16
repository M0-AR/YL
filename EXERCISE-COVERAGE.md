# Exercise Coverage Audit

This repository is a **chapter atlas and selective exercise companion**, not a complete solution manual. It verifies that every chapter has runnable code; it does not claim that every textbook exercise is solved.

## Method

- Official source: the per-chapter exercise dropdown at [Pearson CheckExercise](https://liveexample.pearsoncmg.com/CheckExercise/faces/CheckExercise.xhtml).
- Counted unique exercise variants, including `Extra` variants, for chapters where the dropdown is public.
- Matched those numbers against numbered `src/Exercise_XX/_NN/` folders in this repository.
- No exercise prompts, textbook text, or solutions are reproduced here.

## Result

- **1,026 official exercise variants** are publicly listed for chapters 1–30: **825 base exercises + 201 Extra variants**.
- **344 base exercises** have code in this repository: **33.5%** of the public 1–30 total.
- **0 Extra variants** have code.
- **481 base exercises and 201 Extra variants are missing** from chapters 1–30.
- Chapters 31–43 expose **no public exercise dropdown** in CheckExercise, so their official counts cannot be verified without Pearson access. The repo has one numbered folder there (`34/_01`) and none elsewhere.

Eight chapters cover every **base** exercise (4–11), but none cover the `Extra` variants.

## Chapter detail

| Ch | Official variants | Base | Extra | In repo | Coverage | Missing base | Missing Extra |
|---:|---:|---:|---:|---:|---:|---|---:|
| 01 | 16 | 13 | 3 | 0 | 0.0% | 01–13 | 3 |
| 02 | 29 | 23 | 6 | 1 | 3.4% | 01–06, 08–23 | 6 |
| 03 | 38 | 34 | 4 | 33 | 86.8% | 01 | 4 |
| 04 | 34 | 26 | 8 | 26 | 76.5% | — | 8 |
| 05 | 66 | 51 | 15 | 51 | 77.3% | — | 15 |
| 06 | 53 | 39 | 14 | 39 | 73.6% | — | 14 |
| 07 | 49 | 35 | 14 | 35 | 71.4% | — | 14 |
| 08 | 44 | 37 | 7 | 37 | 84.1% | — | 7 |
| 09 | 16 | 13 | 3 | 13 | 81.2% | — | 3 |
| 10 | 32 | 28 | 4 | 28 | 87.5% | — | 4 |
| 11 | 21 | 17 | 4 | 17 | 81.0% | — | 4 |
| 12 | 41 | 33 | 8 | 5 | 12.2% | 06–33 | 8 |
| 13 | 27 | 21 | 6 | 7 | 25.9% | 08–21 | 6 |
| 14 | 42 | 29 | 13 | 12 | 28.6% | 13–29 | 13 |
| 15 | 56 | 36 | 20 | 0 | 0.0% | 01–36 | 20 |
| 16 | 57 | 40 | 17 | 0 | 0.0% | 01–40 | 17 |
| 17 | 21 | 21 | 0 | 6 | 28.6% | 07–21 | 0 |
| 18 | 44 | 39 | 5 | 19 | 43.2% | 20–39 | 5 |
| 19 | 21 | 21 | 0 | 11 | 52.4% | 12–21 | 0 |
| 20 | 41 | 38 | 3 | 3 | 7.3% | 04–38 | 3 |
| 21 | 19 | 15 | 4 | 0 | 0.0% | 01–15 | 4 |
| 22 | 30 | 25 | 5 | 0 | 0.0% | 01–25 | 5 |
| 23 | 26 | 25 | 1 | 0 | 0.0% | 01–25 | 1 |
| 24 | 28 | 26 | 2 | 0 | 0.0% | 01–26 | 2 |
| 25 | 28 | 23 | 5 | 0 | 0.0% | 01–23 | 5 |
| 26 | 25 | 25 | 0 | 0 | 0.0% | 01–25 | 0 |
| 27 | 34 | 30 | 4 | 0 | 0.0% | 01–30 | 4 |
| 28 | 43 | 23 | 20 | 1 | 2.3% | 02–23 | 20 |
| 29 | 25 | 20 | 5 | 0 | 0.0% | 01–20 | 5 |
| 30 | 20 | 19 | 1 | 0 | 0.0% | 01–19 | 1 |
| 31 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 32 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 33 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 34 | n/a | n/a | n/a | 1 | unverified | n/a | n/a |
| 35 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 36 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 37 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 38 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 39 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 40 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 41 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 42 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |
| 43 | n/a | n/a | n/a | 0 | unverified | n/a | n/a |

## What this means

- The 43-chapter claim is about **chapter coverage**: every chapter has runnable code and a place in the atlas.
- The exercise claim is narrower: this repo contains **selected solutions**, mostly for chapters 3–11, plus partial coverage in chapters 2, 12–14, 17–20, and 28.
- Completing every official exercise would require implementing hundreds of missing base exercises and all 201 Extra variants, and chapters 31–43 would still need an authoritative exercise list.

## Sources

- [Official 10th-edition table of contents](https://liveexample.pearsoncmg.com/liang/intro10e/toc.html)
- [Official CheckExercise](https://liveexample.pearsoncmg.com/CheckExercise/faces/CheckExercise.xhtml)
