# Exercise Coverage Audit

This repository is a **chapter atlas and selective exercise companion**, not a complete solution manual. It verifies that every chapter has runnable code; it does not claim that every textbook exercise is solved.

## Method

- Official source: the per-chapter exercise dropdown at [Pearson CheckExercise](https://liveexample.pearsoncmg.com/CheckExercise/faces/CheckExercise.xhtml).
- Counted unique exercise variants, including `Extra` variants, for chapters where the dropdown is public.
- Matched those numbers against numbered `src/Exercise_XX/_NN/` folders in this repository.
- No exercise prompts, textbook text, or solutions are reproduced here.
- Some solutions are adapted from MIT-licensed repositories. See [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md).

## Result

- **1,026 official exercise variants** are publicly listed for chapters 1–30: **825 base exercises + 201 Extra variants**.
- **568 base exercises** have code in this repository: **68.8%** of the public base exercises and **55.4%** of all public 1–30 variants.
- **0 Extra variants** have code.
- **257 base exercises and 201 Extra variants are missing** from chapters 1–30.
- Chapters 31–43 expose **no public exercise dropdown** in CheckExercise, so their official counts cannot be verified without Pearson access. The repo has one numbered folder there (`34/_01`) and none elsewhere.

Chapters 1–13 cover every **base** exercise. No chapter covers the `Extra` variants.

## Chapter detail

| Ch | Official variants | Base | Extra | In repo | Coverage | Missing base | Missing Extra |
|---:|---:|---:|---:|---:|---:|---|---:|
| 01 | 16 | 13 | 3 | 13 | 81.2% | — | 3 |
| 02 | 29 | 23 | 6 | 23 | 79.3% | — | 6 |
| 03 | 38 | 34 | 4 | 34 | 89.5% | — | 4 |
| 04 | 34 | 26 | 8 | 26 | 76.5% | — | 8 |
| 05 | 66 | 51 | 15 | 51 | 77.3% | — | 15 |
| 06 | 53 | 39 | 14 | 39 | 73.6% | — | 14 |
| 07 | 49 | 35 | 14 | 35 | 71.4% | — | 14 |
| 08 | 44 | 37 | 7 | 37 | 84.1% | — | 7 |
| 09 | 16 | 13 | 3 | 13 | 81.2% | — | 3 |
| 10 | 32 | 28 | 4 | 28 | 87.5% | — | 4 |
| 11 | 21 | 17 | 4 | 17 | 81.0% | — | 4 |
| 12 | 41 | 33 | 8 | 33 | 80.5% | — | 8 |
| 13 | 27 | 21 | 6 | 21 | 77.8% | — | 6 |
| 14 | 42 | 29 | 13 | 26 | 61.9% | 20, 27, 29 | 13 |
| 15 | 56 | 36 | 20 | 26 | 46.4% | 10, 17, 21, 23, 25, 31, 33–36 | 20 |
| 16 | 57 | 40 | 17 | 24 | 42.1% | 11, 24, 27–40 | 17 |
| 17 | 21 | 21 | 0 | 15 | 71.4% | 16–21 | 0 |
| 18 | 44 | 39 | 5 | 25 | 56.8% | 25–35, 37–39 | 5 |
| 19 | 21 | 21 | 0 | 11 | 52.4% | 12–21 | 0 |
| 20 | 41 | 38 | 3 | 17 | 41.5% | 07, 15, 17, 19–20, 22, 24–38 | 3 |
| 21 | 19 | 15 | 4 | 14 | 73.7% | 05 | 4 |
| 22 | 30 | 25 | 5 | 4 | 13.3% | 05–25 | 5 |
| 23 | 26 | 25 | 1 | 10 | 38.5% | 10, 12–25 | 1 |
| 24 | 28 | 26 | 2 | 5 | 17.9% | 03, 07–26 | 2 |
| 25 | 28 | 23 | 5 | 10 | 35.7% | 08, 11–13, 15–23 | 5 |
| 26 | 25 | 25 | 0 | 0 | 0.0% | 01–25 | 0 |
| 27 | 34 | 30 | 4 | 8 | 23.5% | 06–08, 12–30 | 4 |
| 28 | 43 | 23 | 20 | 3 | 7.0% | 03, 05–23 | 20 |
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
- The exercise claim is narrower: this repo now covers **568 of 825 public base exercises** (68.8%). Chapters 1–13 are complete; chapters 14–30 are partial.
- Completing every official exercise would require implementing **257 missing base exercises and all 201 Extra variants**, and chapters 31–43 would still need an authoritative exercise list.

## Sources

- [Official 10th-edition table of contents](https://liveexample.pearsoncmg.com/liang/intro10e/toc.html)
- [Official CheckExercise](https://liveexample.pearsoncmg.com/CheckExercise/faces/CheckExercise.xhtml)
