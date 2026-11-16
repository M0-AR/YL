# Changelog

All notable changes to this project are documented here. The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and the project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

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

### Changed

- Increased the chapter code collection to **879 Java files**.
- Updated the exercise audit to **602 of 825 public base exercises (73.0%)** and **58.7%** of all 1,026 public variants.
- Updated the README, site statistics, and source tree counts to 879 Java files.
- Documented that Chapter 19's 19.12–19.21 are instructor-only extra exercises rather than printed base exercises.

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
