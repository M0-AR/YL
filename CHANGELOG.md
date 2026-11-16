# Changelog

All notable changes to this project are documented here. The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and the project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

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
