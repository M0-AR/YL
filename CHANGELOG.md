# Changelog

All notable changes to this project are documented here. The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and the project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Planned

- Additional companion tests for collections and graph edge cases.
- A printable one-page curriculum map.
- More keyboard and screen-reader checks for the labs.

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

[Unreleased]: https://github.com/M0-AR/YL/compare/v1.0.0...HEAD
[1.0.0]: https://github.com/M0-AR/YL/releases/tag/v1.0.0
