# Contributing to Java Atlas

Thank you for helping make this atlas clearer, more correct, or easier to use. Small, focused contributions are the easiest to review and the most likely to be merged.

## Before you start

- Read the [README](README.md) and the [companion curriculum](companion/README.md) so you know which chapter or lab you are improving.
- Search the [issues](https://github.com/M0-AR/YL/issues) for the same idea. If it is not there, open one before a large pull request.
- This project is an independent companion. Do not paste chapter text, figures, or exercise statements from the textbook. See [Copyright and attribution](README.md#copyright-and-attribution).

## What makes a good contribution

| Kind | Examples | What to include |
| :--- | :--- | :--- |
| **Fix** | A typo, a broken link, a wrong explanation, a failing test | The smallest diff that fixes the problem |
| **Test** | A new JUnit case, an edge case, a regression test | The failing case and the fix together |
| **Lab** | A clearer control, a missing step, a keyboard fix | A short note on what changes for the learner |
| **Lesson** | A new explanation, example, or practice task | Original prose and a checkpoint |
| **Chapter file** | Repairing an original study file | The compiler or runtime error and the environment you used |

Refactors are welcome when they make a teaching example easier to understand. Pure formatting churn is not.

## Ground rules

1. **Original words only.** Write your own explanations. Never copy textbook prose.
2. **One idea per pull request.** A fix and a new lab belong in separate pull requests.
3. **Keep examples self-contained.** A reader should be able to compile one file without a private setup.
4. **Prefer clarity over cleverness.** A short loop is better than a stream pipeline that needs a paragraph to explain.
5. **Do not add secrets.** No passwords, tokens, private URLs, or personal data in code, comments, screenshots, or fixtures.
6. **Respect the reader.** Assume they are intelligent and new to the topic.

## Development setup

### Companion Java project

Requirements: JDK 17+ and Maven 3.9+.

```bash
cd companion/java
mvn test
```

Every new data structure or algorithm should have a test that covers the normal path and at least one edge case: empty input, duplicates, a missing key, a single element, or a negative weight as appropriate.

### Site

No build step is needed.

```bash
python3 -m http.server 8123 -d docs
```

Then open `http://localhost:8123/`. Check your change in a real browser at both desktop and narrow widths. The labs are keyboard-operable; keep them that way.

Run the integrity check before opening a pull request:

```bash
python3 scripts/check_site.py
```

It verifies local links, anchors into the companion lessons, the 42 chapter rows, asset paths, and required files.

## Pull request checklist

- [ ] The change is focused and has a clear reason.
- [ ] `mvn test` passes if Java changed.
- [ ] `python3 scripts/check_site.py` passes if the site, lessons, or README changed.
- [ ] New interactive controls work with a keyboard and with reduced motion enabled.
- [ ] No textbook text, figures, exercise statements, secrets, or personal data were added.
- [ ] The description says **what changed** and **how you verified it**.

## Commit messages

Write the reason, not just the action:

- `Fix Dijkstra frontier reset when target changes`
- `Add red-black tree invariant tests`
- `Clarify chapter 1 JDK/JRE/JVM distinction`

## Review and merge

A maintainer will review for correctness, clarity, and scope. Expect questions about edge cases and learner experience. Once the checks pass and the discussion is resolved, a maintainer merges the change.

## License

By contributing, you agree that your contribution is released under the [MIT License](LICENSE), and that you have the right to submit it.
