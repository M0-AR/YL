#!/usr/bin/env python3
"""Fail the build when the site, chapter map, or companion links drift."""

from __future__ import annotations

import re
import sys
from html.parser import HTMLParser
from pathlib import Path
from urllib.parse import unquote

ROOT = Path(__file__).resolve().parents[1]
DOCS = ROOT / "docs"
EXTERNAL_PREFIXES = ("http:", "https:", "mailto:", "tel:", "data:", "javascript:")
REQUIRED = (
    "README.md",
    "preview.html",
    "docs/preview.html",
    "LICENSE",
    "CONTRIBUTING.md",
    "CODE_OF_CONDUCT.md",
    "SECURITY.md",
    "CHANGELOG.md",
    "companion/README.md",
    "docs/.nojekyll",
    ".github/workflows/ci.yml",
)

problems: list[str] = []
checked_links = 0
checked_fragments = 0


class Document(HTMLParser):
    def __init__(self) -> None:
        super().__init__(convert_charrefs=True)
        self.links: list[tuple[str, str]] = []
        self.ids: set[str] = set()
        self.has_title = False

    def handle_starttag(self, tag: str, attrs: list[tuple[str, str | None]]) -> None:
        values = {name: value or "" for name, value in attrs}
        if tag == "title":
            self.has_title = True
        if values.get("id"):
            self.ids.add(values["id"])
        if tag == "a" and values.get("name"):
            self.ids.add(values["name"])
        for attribute in ("href", "src"):
            if values.get(attribute):
                self.links.append((attribute, values[attribute]))


def parse_html(path: Path) -> Document:
    document = Document()
    document.feed(path.read_text(encoding="utf-8"))
    return document


def markdown_anchors(path: Path) -> set[str]:
    text = path.read_text(encoding="utf-8")
    anchors = set(re.findall(r'<a\s+(?:id|name)="([^"]+)"', text))
    for heading in re.findall(r"^#{1,6}\s+(.+)$", text, flags=re.MULTILINE):
        slug = heading.strip().lower()
        slug = re.sub(r"[^\w\s-]", "", slug)
        slug = re.sub(r"\s+", "-", slug)
        anchors.add(slug)
    return anchors


def is_dynamic(value: str) -> bool:
    return any(marker in value for marker in ("'", '"', "+", "${", "{{", "}}"))


def check_target(source: Path, raw: str) -> None:
    global checked_links, checked_fragments

    value = raw.strip()
    if not value or is_dynamic(value):
        return

    github = re.match(
        r"https://github\.com/M0-AR/YL/(?:blob|tree)/master/(.+)", value
    )
    if github:
        path_part, _, fragment = github.group(1).partition("#")
        target = ROOT / unquote(path_part)
        checked_links += 1
        if not target.exists():
            problems.append(f"{source.relative_to(ROOT)}: missing target {value}")
            return
        if fragment:
            checked_fragments += 1
            anchors = parse_html(target).ids if target.suffix == ".html" else markdown_anchors(target)
            if fragment not in anchors:
                problems.append(f"{source.relative_to(ROOT)}: missing anchor {value}")
        return

    if value.lower().startswith(EXTERNAL_PREFIXES):
        return

    path_part, _, fragment = value.partition("#")
    path_part = unquote(path_part)

    if not path_part:
        checked_fragments += 1
        anchors = markdown_anchors(source) if source.suffix == ".md" else parse_html(source).ids
        if fragment and fragment not in anchors:
            problems.append(f"{source.relative_to(ROOT)}: missing anchor #{fragment}")
        return

    if path_part.startswith("/"):
        target = DOCS / path_part.lstrip("/")
    else:
        target = (source.parent / path_part).resolve()

    checked_links += 1
    if not target.exists():
        problems.append(f"{source.relative_to(ROOT)}: missing target {value}")
        return

    if fragment:
        checked_fragments += 1
        if target.suffix == ".html":
            anchors = parse_html(target).ids
        elif target.suffix == ".md":
            anchors = markdown_anchors(target)
        else:
            return
        if fragment not in anchors:
            problems.append(f"{source.relative_to(ROOT)}: missing anchor {value}")


def main() -> int:
    for relative in REQUIRED:
        if not (ROOT / relative).exists():
            problems.append(f"missing required file: {relative}")

    pages = sorted(DOCS.rglob("*.html"))
    for page in pages:
        document = parse_html(page)
        if not document.has_title:
            problems.append(f"{page.relative_to(ROOT)}: missing <title>")
        for _, link in document.links:
            check_target(page, link)

    index = DOCS / "index.html"
    chapter_rows = re.findall(
        r'\[\d+,\s*"[^"]+",\s*"(?:code|companion)",\s*"([^"]+)"\]',
        index.read_text(encoding="utf-8"),
    )
    if len(chapter_rows) != 42:
        problems.append(f"chapter map has {len(chapter_rows)} rows, expected 42")
    for link in chapter_rows:
        check_target(index, link)

    companion_anchors = 0
    for markdown in (ROOT / "companion").glob("*.md"):
        companion_anchors += len(re.findall(r'<a\s+id="chapter-', markdown.read_text(encoding="utf-8")))
    if companion_anchors != 23:
        problems.append(f"found {companion_anchors} companion chapter anchors, expected 23")

    lesson_chapters = (1, 15, 16, *range(21, 28), *range(29, 34), *range(35, 43))
    for number in range(1, 43):
        exercise = ROOT / "src" / f"Exercise_{number:02d}"
        if not exercise.is_dir() or not list(exercise.rglob("*.java")):
            problems.append(f"chapter {number:02d} has no Java source")
        if number in lesson_chapters:
            demo = exercise / f"Chapter{number:02d}.java"
            if not demo.exists():
                problems.append(f"chapter {number:02d} is missing {demo.relative_to(ROOT)}")

    html = index.read_text(encoding="utf-8")
    java_files = len(list((ROOT / "src").rglob("*.java")))
    claimed_files = re.search(r"(\d+)\s+worked Java files", html)
    if claimed_files and int(claimed_files.group(1)) != java_files:
        problems.append(
            f"site claims {claimed_files.group(1)} Java files, repository has {java_files}"
        )

    readme = ROOT / "README.md"
    if readme.exists():
        text = readme.read_text(encoding="utf-8")
        heading = "## 🌱 Beginner Guide — Read this and you are a professional"
        if heading not in text:
            problems.append("README is missing the required Beginner Guide heading")
        if "donkey" in text.lower():
            problems.append("README contains a forbidden word")
        for _, link in parse_html(readme).links:
            check_target(readme, link)
        for markdown_link in re.findall(r"!?\[[^\]]*\]\(([^)]+)\)", text):
            check_target(readme, markdown_link.split()[0].strip("<>"))
        demo_links = len(re.findall(r"\[Demo\]\(src/Exercise_\d\d/Chapter\d\d\.java\)", text))
        if demo_links != 23:
            problems.append(f"README has {demo_links} chapter demo links, expected 23")

    print(
        f"Checked {len(pages)} pages, {checked_links} local links, "
        f"{checked_fragments} fragments, {len(chapter_rows)} chapter rows, "
        f"{companion_anchors} companion lessons, {java_files} Java files."
    )
    if problems:
        print("\nProblems:", file=sys.stderr)
        for problem in problems:
            print(f"  - {problem}", file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
