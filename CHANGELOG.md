# Changelog

All notable changes to this project are documented here. The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and the project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- Completed 10e Extra coverage under `extra11e/src`: all **201 10e Extra names** now have code, including **23 newly inferred Extras** (05.17, 12.08, 15.21, 16.16, 16.17, 18.08, 20.01, 20.03, 20.04, 21.05–21.07, 22.05, 25.04, 25.05, 27.02–27.04, 28.19, 28.20, 29.04, 29.05, and 30.01). The tree now has **223 Java files**: **213 exercise classes + 10 shared helpers**. The full Extra build compiles with `-Xlint:all` and zero warnings.
- Added the Chapter 26 `MyBST`/`isAVLTree` checker gap at `src/Exercise_26/_07/Exercise_26_07.java`; balanced, chain, and near-AVL sample inputs produce `true`, `false`, and `true`.
- Completed every printed exercise in Chapters 16, 20, and 22–30, including 16.11, 16.24, 16.27–16.31, 20.7, 20.15, 20.17, 24.3, 24.7–24.16, 28.24, and all of Chapters 29 and 30. Chapters 1–30 now have code for every printed exercise.
- Added **43 original Chapter Studios**, one for every chapter, generated from structured Python definitions and shipped as static, dependency-free HTML/CSS/JS. Each studio includes subgoal-labelled worked examples, predict–observe–explain steps, Parsons practice, a three-question mastery check with feedback, a downloadable Java artifact, reflection prompts, and local-only progress and notes.
- Added 11 reusable studio engines: state, expression, trace, structure, sorting, graph, growth, data, contract, layout, and Parsons, with keyboard and screen-reader support.
- Completed Chapter 14's remaining base exercises: 14.20 random arrow line, 14.27 detailed clock, and 14.29 bean machine.
- Chapter 14 now covers all 29 base exercises.
- Completed Chapter 15's remaining base exercises: 15.10 keyboard text display, 15.17 bounding rectangle, 15.21 draggable triangle angles, 15.23 resizable stop sign, 15.25 ball on a sine curve, 15.31 pendulum, 15.33 bean-machine animation, 15.34 self-avoiding walk, 15.35 animated self-avoiding walk, and 15.36 dead-end simulation.
- Chapter 15 now covers all 36 base exercises.
- Completed Chapter 17's remaining base exercises: 17.16 character frequencies, 17.17 `BitOutputStream`, 17.18 view bits, 17.19 view hex, 17.20 binary editor, and 17.21 hex editor.
- Chapter 17 now covers all 21 base exercises.
- Completed Chapter 18's remaining base exercises: 18.25 string permutation, 18.26 maze, 18.27 Koch snowflake, 18.28 nonrecursive directory size, 18.29 file count, 18.30 word search, 18.31 word replacement, 18.32 Knight's Tour, 18.33 animated Knight's Tour, 18.34 Eight Queens, 18.35 H-tree, 18.37 Hilbert curve, 18.38 recursive tree, and 18.39 draggable tree.
- Chapter 18 now covers all 39 base exercises.
- Completed Chapter 21's remaining base exercise: 21.05 Java syntax highlighter, which converts a source file to HTML with styled keywords, comments, and literals.
- Chapter 21 now covers all 15 base exercises.
- Completed Chapter 25's remaining base exercises: 25.08 bidirectional iterator, 25.11 horizontal tree view, 25.12 BST test program, 25.13 BST animation buttons, 25.15 parent links and leaf paths, 25.16 Huffman frequency table, 25.17 Huffman animation, 25.18 file compression, 25.19 file decompression, 25.20 first-fit bin packing, 25.21 smallest-first bin packing, 25.22 largest-first bin packing, and 25.23 optimal bin packing.
- Chapter 25 now covers all 23 base exercises.
- Completed the 86 remaining public base exercises: 16.32–16.40, 19.12–19.21, 20.24–20.38, 23.21–23.25, 24.17–24.26, 26.08–26.25, and 27.12–27.30. Exact prompts are instructor-only, so these implementations are inferred from chapter topics and marked as inferred in the coverage audit.
- All 86 inferred exercises compile and pass deterministic `main` smoke tests; the full base build compiles 1,155 sources, excluding 11 legacy JUnit 4 files.

### Changed

- Expanded site navigation, the labs index, and the README to feature the 43 Chapter Studios; updated homepage statistics.
- Added `scripts/build_studios.py` and `scripts/validate_studios.py`, 18 unit tests, and 92 Playwright checks (46 per desktop and mobile project) covering every generated chapter, axe WCAG 2.2 AA rules, mobile overflow, artifact downloads, saved notes, and Parsons keyboard operation.
- Increased the chapter code collection to **1,166 `src/` Java files** and **223 `extra11e/src/` Java files**, for **1,389 Java files** in the working tree.
- Updated the exercise audit to **1,026 of 1,026 public 10e variants (100.0%)**: **825 base + 201 Extra**, with **86 base and 23 Extra inferred** because their prompts are instructor-only, and **12 extra11e-only Extras** outside the 10e count.
- Updated the README, site statistics, and source tree counts to reflect the `src/` and `extra11e/` split.
- Documented that Chapter 19's 19.12–19.21 are instructor-only extra exercises rather than printed base exercises; they are now implemented from inferred specs.

### Planned

- Additional companion tests for collections and graph edge cases.
- A printable one-page curriculum map.
- More keyboard and screen-reader checks for the labs.

## [1.2.0] — 2026-11-16

### Added

- 19 new original companion lessons covering chapters 2–14, 17–20, 28, and 34.
- Three new companion volumes: `core-language.md`, `io-generics-graphs.md`, and `advanced-gui.md`.
- The chapter map now renders both a code dot and a lesson dot for every chapter.

### Changed

- Expanded the companion curriculum from 24 lessons in four volumes to 43 lessons in seven volumes.
- Updated the README, site statistics, curriculum tree, and integrity checker to the 43-lesson curriculum.
- Prepared the 1.2.0 release metadata and assets.

### Known gaps

- Exercise coverage remains **568 of 825 public base exercises (68.8%)**; 257 base and 201 Extra variants remain missing.
- Chapters 31–43 still have no public exercise dropdown, so their official exercise counts remain unverified.

## [1.1.0] — 2026-11-16

### Added

- 224 exercise solutions adapted from MIT-licensed reference repositories: 222 from `jsquared21/Intro-to-Java-Programming` and 2 from `chill4Eng2Dev/IntroToJava`.
- `THIRD_PARTY_NOTICES.md` with both upstream MIT notices.
- Repaired 13 previously empty or incomplete exercises, including ISBN-10, monetary units, execution timing, the bank-failure simulation, polygon sub-areas, the string split method, the container, the smiley face, and the randomized capitals quiz.

### Changed

- Increased the chapter code collection to **842 Java files**.
- Updated the exercise audit to **568 of 825 public base exercises (68.8%)** and **55.4%** of all 1,026 public variants; chapters 1–13 now cover every base exercise.
- Updated the README, site, companion curriculum, checker expectations, and release metadata for 1.1.0.

### Known gaps

- **257 base exercises and 201 Extra variants** remain missing for chapters 1–30.
- Chapters 31–43 still have no public exercise dropdown, so their official exercise counts remain unverified.

## [1.0.2] — 2026-11-16

### Added

- Chapter 43, **Aggregate Operations for Collection Streams**, with an original companion lesson and a runnable `Chapter43.java` demo covering filter/map pipelines, grouping collectors, and summary statistics.
- A verified 43-chapter atlas that matches the official comprehensive-edition table of contents.

### Changed

- Updated the README, site, companion curriculum, integrity checker, and repository statistics to 43 chapters, 24 lesson chapters, and 463 Java files.
- Refreshed release metadata for 1.0.2.

## [1.0.1] — 2026-11-16

### Added

- A runnable `ChapterXX.java` demo for each of the 23 lesson chapters, covering networking, JDBC, JSF, database joins, internationalization, servlets, JSP, JSON, B-trees, red-black trees, and JUnit-style assertions.
- Integrity checks that every one of the 42 chapter directories contains Java source and that all 23 lesson-chapter demos exist.

### Changed

- Updated the atlas, README, site, and repository statistics to 462 Java files with code coverage for all 42 chapters.

## [1.0.0] — 2026-11-16

### Added

- A static Java Atlas site with a 42-chapter map, learning path, beginner guide, FAQ, and accessible navigation.
- Six interactive labs: sorting, graph traversal, data structures, payroll, a 26-question quiz, and the Neural Nexus 3D game.
- Twenty-three original companion lessons covering chapters 1, 15–16, 21–27, 29–33, and 35–42.
- A Java 17 Maven project with runnable examples and JUnit 5 tests for sorting, growable lists, binary search trees, AVL trees, hash tables, red-black trees, and weighted graphs.
- GitHub Actions CI for the Java test suite and site integrity.
- A site integrity checker for local links, anchors, chapter rows, assets, and claimed file counts.
- Project documentation: README, contributing guide, code of conduct, security policy, and MIT license.

### Changed

- Replaced the previous README with a beginner-first guide and a complete chapter atlas.
- Clarified the copyright boundary between original companion material and the textbook.

### Fixed

- Corrected lab navigation paths and stale operation logs.
- Added missing color tokens used by the quiz and payroll labs.
- Made the game's hidden overlays stay out of the accessibility tree.

[Unreleased]: https://github.com/M0-AR/YL/compare/v1.2.0...HEAD
[1.2.0]: https://github.com/M0-AR/YL/releases/tag/v1.2.0
[1.1.0]: https://github.com/M0-AR/YL/releases/tag/v1.1.0
[1.0.2]: https://github.com/M0-AR/YL/releases/tag/v1.0.2
[1.0.1]: https://github.com/M0-AR/YL/releases/tag/v1.0.1
[1.0.0]: https://github.com/M0-AR/YL/releases/tag/v1.0.0
