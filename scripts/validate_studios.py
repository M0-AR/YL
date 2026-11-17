#!/usr/bin/env python3
"""Validate the generated Chapter Studios and their authored data."""

from __future__ import annotations

import html
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SCRIPTS = ROOT / "scripts"
OUT = ROOT / "docs" / "studios"
if str(SCRIPTS) not in sys.path:
    sys.path.insert(0, str(SCRIPTS))

from studio_data import CHAPTERS  # noqa: E402

ENGINES = {
    "state",
    "expr",
    "trace",
    "structure",
    "sort",
    "graph",
    "growth",
    "data",
    "contract",
    "layout",
    "parsons",
}
REQUIRED = [
    "n",
    "title",
    "part",
    "tagline",
    "minutes",
    "tags",
    "objectives",
    "subgoals",
    "example",
    "engine",
    "spec",
    "parsons",
    "quiz",
    "artifact",
    "reflection",
    "studio",
    "links",
]
PROBLEMS: list[str] = []


def fail(message: str) -> None:
    PROBLEMS.append(message)


def text(value: object) -> str:
    return html.unescape(re.sub(r"<[^>]+>", "", str(value)))


def check_data() -> None:
    numbers = [chapter.get("n") for chapter in CHAPTERS]
    if len(CHAPTERS) != 43:
        fail(f"expected 43 chapters, found {len(CHAPTERS)}")
    if numbers != list(range(1, 44)):
        fail(f"chapter numbers are not exactly 1..43: {numbers}")
    titles = [chapter.get("title") for chapter in CHAPTERS]
    if len(set(titles)) != len(titles):
        fail("chapter titles are not unique")

    for chapter in CHAPTERS:
        number = chapter.get("n")
        label = f"chapter {number}"
        for key in REQUIRED:
            if key not in chapter:
                fail(f"{label}: missing key {key}")
        if chapter.get("engine") not in ENGINES:
            fail(f"{label}: unknown engine {chapter.get('engine')!r}")
        if not 20 <= int(chapter.get("minutes", 0)) <= 90:
            fail(f"{label}: minutes outside 20..90")
        if not 3 <= len(chapter.get("objectives", [])) <= 5:
            fail(f"{label}: expected 3..5 objectives")
        if not 4 <= len(chapter.get("subgoals", [])) <= 8:
            fail(f"{label}: expected 4..8 subgoals")
        for subgoal in chapter.get("subgoals", []):
            if not text(subgoal.get("title", "")).strip() or not text(subgoal.get("idea", "")).strip():
                fail(f"{label}: empty subgoal")
        example = chapter.get("example", {})
        if len(example.get("code", [])) < 3 or len(example.get("steps", [])) < 4:
            fail(f"{label}: example needs code and at least four steps")
        if not example.get("predict", {}).get("prompt"):
            fail(f"{label}: missing predict prompt")

        parsons = chapter.get("parsons", {})
        lines = parsons.get("lines", [])
        solution = parsons.get("solution", [])
        distractors = parsons.get("distractors", [])
        total = len(lines) + len(distractors)
        if len(lines) < 4:
            fail(f"{label}: Parsons needs at least four lines")
        if sorted(solution) != list(range(total)):
            fail(f"{label}: Parsons solution is not a permutation of the pool")
        if not parsons.get("why"):
            fail(f"{label}: Parsons is missing an explanation")

        quiz = chapter.get("quiz", [])
        if len(quiz) != 3:
            fail(f"{label}: expected exactly three quiz questions")
        for index, question in enumerate(quiz, start=1):
            options = question.get("options", [])
            if len(options) != 4:
                fail(f"{label}: quiz {index} needs four options")
            if not 0 <= int(question.get("a", -1)) < len(options):
                fail(f"{label}: quiz {index} has an invalid answer")
            if not question.get("why"):
                fail(f"{label}: quiz {index} is missing feedback")

        artifact = chapter.get("artifact", {})
        if not artifact.get("name") or len(artifact.get("code", "")) < 40:
            fail(f"{label}: artifact is incomplete")
        if not artifact.get("href", "").startswith("https://github.com/M0-AR/YL/"):
            fail(f"{label}: artifact href is not a repository link")
        if len(text(chapter.get("reflection", ""))) < 30:
            fail(f"{label}: reflection is too short")
        links = chapter.get("links", {})
        for key in ("companion", "repo"):
            if not links.get(key, "").startswith("https://github.com/M0-AR/YL/"):
                fail(f"{label}: {key} link is not a repository link")

        blob = json.dumps(chapter, ensure_ascii=False)
        lowered = blob.lower()
        for banned in ("lorem ipsum", "pearson", "copyrighted exercise"):
            if banned in lowered:
                fail(f"{label}: contains banned text {banned!r}")


def check_pages() -> None:
    pages = sorted(OUT.glob("ch*.html"))
    if len(pages) != 43:
        fail(f"expected 43 generated chapter pages, found {len(pages)}")

    for chapter in CHAPTERS:
        page = OUT / f"ch{chapter['n']:02d}.html"
        if not page.exists():
            fail(f"missing page {page.name}")
            continue
        source = page.read_text(encoding="utf-8")
        lower = source.lower()
        if source.count("<h1") != 1:
            fail(f"{page.name}: expected exactly one h1")
        if '<html lang="en">' not in source:
            fail(f"{page.name}: missing lang attribute")
        if 'class="studio-skip"' not in source:
            fail(f"{page.name}: missing skip link")
        if "<noscript>" not in source:
            fail(f"{page.name}: missing noscript fallback")
        if 'data-engine="' not in source:
            fail(f"{page.name}: missing engine island")
        if f"data-engine=\"{chapter['engine']}\"" not in source:
            fail(f"{page.name}: engine island does not match data")
        for banned in ("lorem ipsum", "pearson"):
            if banned in lower:
                fail(f"{page.name}: contains banned text {banned!r}")

        match = re.search(
            r'<script type="application/json" id="studio-data">(.*?)</script>',
            source,
            re.DOTALL,
        )
        if not match:
            fail(f"{page.name}: missing studio data payload")
            continue
        try:
            payload = json.loads(match.group(1))
        except json.JSONDecodeError as error:
            fail(f"{page.name}: invalid JSON payload: {error}")
            continue
        if payload.get("n") != chapter["n"]:
            fail(f"{page.name}: payload chapter number mismatch")
        for key in ("spec", "parsons", "artifact"):
            if key not in payload:
                fail(f"{page.name}: payload missing {key}")

        engine_file = OUT / "engines" / f"{chapter['engine']}.js"
        if not engine_file.exists():
            fail(f"{page.name}: engine file {engine_file.name} does not exist")

    index = OUT / "index.html"
    if not index.exists():
        fail("missing studio index")
        return
    index_source = index.read_text(encoding="utf-8")
    cards = index_source.count("data-chapter=")
    if cards != 43:
        fail(f"studio index has {cards} cards, expected 43")
    for chapter in CHAPTERS:
        if f"ch{chapter['n']:02d}.html" not in index_source:
            fail(f"studio index does not link chapter {chapter['n']}")


def main() -> int:
    check_data()
    check_pages()
    if PROBLEMS:
        print(f"Studio validation failed with {len(PROBLEMS)} problem(s):")
        for problem in PROBLEMS:
            print(f"  - {problem}")
        return 1
    print(
        "Validated 43 chapters: schema, engines, Parsons permutations, quizzes, "
        "artifacts, originality guards, pages, payloads, and index."
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
