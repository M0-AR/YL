#!/usr/bin/env python3
"""Generate the 43 Chapter Studios from the authored chapter data."""

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

ENGINE_LABELS = {
    "state": "State machine",
    "expr": "Expression lab",
    "trace": "Execution trace",
    "structure": "Structure viewer",
    "sort": "Sorting lab",
    "graph": "Graph lab",
    "growth": "Growth chart",
    "data": "Data pipeline",
    "contract": "Contract tester",
    "layout": "Layout lab",
    "parsons": "Parsons problem",
}

FONT_LINKS = """<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&family=JetBrains+Mono:wght@400;500&family=Space+Grotesk:wght@400;500;600;700&display=swap" rel="stylesheet">"""

FAVICON = (
    "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 64 64'%3E"
    "%3Crect width='64' height='64' rx='14' fill='%23070b14'/%3E"
    "%3Cpath d='M22 18h20M32 18v20c0 8-5 12-12 12' stroke='%2322d3ee' stroke-width='5' "
    "fill='none' stroke-linecap='round'/%3E%3Ccircle cx='45' cy='44' r='4' fill='%238b5cf6'/%3E%3C/svg%3E"
)

BACK_SVG = (
    '<svg viewBox="0 0 24 24" aria-hidden="true"><path d="M15 5l-7 7 7 7" fill="none" '
    'stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"/></svg>'
)


def esc(value: object) -> str:
    return html.escape(str(value), quote=True)


def inline_code(value: object) -> str:
    parts = re.split(r"(<code>.*?</code>)", str(value), flags=re.S)
    rendered = []
    for part in parts:
        if part.startswith("<code>") and part.endswith("</code>"):
            content = html.escape(html.unescape(part[6:-7]), quote=False)
            rendered.append(f"<code>{content}</code>")
        else:
            rendered.append(html.escape(html.unescape(part), quote=False))
    return "".join(rendered)


def code_block(lines, copy_button: bool = False) -> str:
    if isinstance(lines, str):
        text = lines
    else:
        text = "\n".join(str(line) for line in lines)
    button = (
        '<button class="btn btn-sm btn-ghost studio-copy" type="button" data-copy>Copy</button>'
        if copy_button
        else ""
    )
    return (
        f'<div class="studio-code-wrap">{button}'
        f'<pre class="studio-code" tabindex="0"><code>{esc(text)}</code></pre></div>'
    )


def render_quiz(chapter: dict) -> str:
    parts = ['<form class="studio-quiz" data-quiz>']
    for question_index, question in enumerate(chapter["quiz"], start=1):
        answer = int(question["a"])
        options = []
        for option_index, option in enumerate(question["options"]):
            option_id = f"ch{chapter['n']:02d}-q{question_index}-o{option_index}"
            options.append(
                f'<label for="{option_id}"><input type="radio" id="{option_id}" '
                f'name="ch{chapter["n"]:02d}-q{question_index}" value="{option_index}">'
                f"<span>{esc(option)}</span></label>"
            )
        parts.append(
            f'<fieldset class="quiz-question" data-answer="{answer}" '
            f'data-why="{esc(question.get("why", ""))}">'
            f"<legend>{inline_code(question['q'])}</legend>"
            f'<div class="quiz-options">{"".join(options)}</div>'
            '<p class="quiz-feedback"></p>'
            "</fieldset>"
        )
    parts.append(
        '<div class="studio-quiz-actions">'
        '<button class="btn btn-sm btn-primary" type="submit">Check answers</button>'
        '<p class="studio-quiz-score" role="status" aria-live="polite"></p>'
        "</div></form>"
    )
    return "".join(parts)


def render_lesson(chapter: dict) -> str:
    steps = "".join(
        f'<li class="studio-step"><h3>{esc(step["title"])}</h3><p>{step["idea"]}</p></li>'
        for step in chapter["subgoals"]
    )
    return (
        '<section class="studio-section" id="lesson">'
        '<h2><span class="studio-kicker">Lesson</span>Subgoals</h2>'
        '<p class="studio-prose">Read the subgoals first. Each one is a small, checkable idea '
        "that the worked example and the playground will use.</p>"
        f'<ol class="studio-steps">{steps}</ol></section>'
    )


def render_example(chapter: dict) -> str:
    example = chapter["example"]
    steps = "".join(
        f'<li class="studio-step"><h3>{esc(step["label"])}</h3><p>{step["note"]}</p></li>'
        for step in example["steps"]
    )
    predict = example.get("predict")
    predict_html = ""
    if predict:
        predict_html = (
            '<details class="studio-details"><summary>Predict, then observe</summary>'
            f'<div><p><strong>Before you step:</strong> {predict["prompt"]}</p>'
            f'<p>{predict["answer"]}</p></div></details>'
        )
    return (
        '<section class="studio-section" id="example">'
        '<h2><span class="studio-kicker">Worked example</span>'
        f'{esc(example["title"])}</h2>'
        f'<p class="studio-prose">{example["intro"]}</p>'
        f'{code_block(example["code"], copy_button=True)}'
        f'<ol class="studio-steps">{steps}</ol>{predict_html}</section>'
    )


def render_practice(chapter: dict) -> str:
    return (
        '<section class="studio-section" id="practice">'
        '<h2><span class="studio-kicker">Practice</span>Rebuild the program</h2>'
        '<p class="studio-prose">A Parsons problem: the lines are correct, but the order is not. '
        "Rearrange them until the program would compile and behave as described.</p>"
        '<div class="studio-island" data-engine="parsons">'
        '<noscript><p class="studio-noscript">JavaScript is off, so the drag-and-drop practice is '
        "hidden. Read the worked example and write the lines in a file instead.</p></noscript>"
        "</div></section>"
    )


def render_mastery(chapter: dict) -> str:
    return (
        '<section class="studio-section" id="mastery">'
        '<h2><span class="studio-kicker">Mastery check</span>Three questions</h2>'
        f'{render_quiz(chapter)}</section>'
    )


def render_artifact(chapter: dict) -> str:
    artifact = chapter["artifact"]
    href = artifact.get("href", "index.html")
    return (
        '<section class="studio-section" id="artifact">'
        '<h2><span class="studio-kicker">Signature artifact</span>'
        f'{esc(artifact["name"])}</h2>'
        f'<p class="studio-prose">{artifact["description"]}</p>'
        '<div class="studio-artifact">'
        f'<a class="btn btn-sm" data-artifact href="{esc(href)}" download>'
        f'Download {esc(artifact["name"])}</a>'
        '<span class="studio-artifact-note">Original code, MIT licensed. No textbook text.</span>'
        "</div></section>"
    )


def render_reflection(chapter: dict) -> str:
    notes_id = f"notes-ch{chapter['n']:02d}"
    return (
        '<section class="studio-section" id="reflection">'
        "<h2><span class=\"studio-kicker\">Reflection</span>Make it yours</h2>"
        f'<p class="studio-prose">{chapter["reflection"]}</p>'
        f'<label class="studio-hint" for="{notes_id}">Notes (saved in this browser)</label>'
        f'<textarea class="studio-notes" id="{notes_id}" data-notes></textarea>'
        '<p class="studio-notes-status" data-notes-status>Notes stay in this browser.</p>'
        '<div class="studio-complete"><p data-complete-label>Progress is stored in this browser only.</p>'
        f'<button class="btn btn-sm" type="button" data-complete data-chapter="{chapter["n"]}" '
        'aria-pressed="false">Mark chapter complete</button></div></section>'
    )


def render_pager(chapter: dict, chapters: list[dict]) -> str:
    numbers = [item["n"] for item in chapters]
    index = numbers.index(chapter["n"])
    previous = chapters[index - 1] if index > 0 else None
    following = chapters[index + 1] if index < len(chapters) - 1 else None
    left = (
        f'<a href="ch{previous["n"]:02d}.html">← Chapter {previous["n"]}: {esc(previous["title"])}</a>'
        if previous
        else '<span>Start of the path</span>'
    )
    right = (
        f'<a href="ch{following["n"]:02d}.html">Chapter {following["n"]}: {esc(following["title"])} →</a>'
        if following
        else '<span>End of the path</span>'
    )
    return f'<nav class="studio-pager" aria-label="Chapter navigation">{left}{right}</nav>'


def render_studio(chapter: dict) -> str:
    studio = chapter["studio"]
    return (
        '<section class="studio-section" id="studio">'
        f'<h2><span class="studio-kicker">Playground</span>{esc(studio["title"])}</h2>'
        f'<p class="studio-prose">{studio["intro"]}</p>'
        f'<div class="studio-island" data-engine="{esc(chapter["engine"])}">'
        '<noscript><p class="studio-noscript">JavaScript is off. The written lesson, worked example, '
        "answers, and artifact still work. Turn on JavaScript for the interactive playground.</p>"
        "</noscript></div></section>"
    )


def render_links(chapter: dict) -> str:
    links = chapter["links"]
    return (
        '<section class="studio-section" id="links">'
        "<h2>How this connects</h2>"
        '<p class="studio-prose">The studio is the third pass: the companion lesson explains the idea, '
        "the repository gives you runnable Java, and this page lets you manipulate the mechanism.</p>"
        f'<p class="studio-prose"><a class="btn btn-sm btn-ghost" href="{esc(links["companion"])}" '
        'target="_blank" rel="noopener">Companion lesson</a> '
        f'<a class="btn btn-sm btn-ghost" href="{esc(links["repo"])}" target="_blank" rel="noopener">'
        "Java sources</a></p></section>"
    )


def render_page(chapter: dict, chapters: list[dict]) -> str:
    payload = {
        "n": chapter["n"],
        "title": chapter["title"],
        "spec": chapter["spec"],
        "parsons": chapter["parsons"],
        "artifact": chapter["artifact"],
    }
    data = json.dumps(payload, ensure_ascii=False, separators=(",", ":")).replace("<", "\\u003c")
    part = esc(chapter["part"])
    title = f"Chapter {chapter['n']:02d} Studio: {chapter['title']} — Java Atlas"
    description = esc(chapter["tagline"])[:160]
    objectives = "".join(f"<li>{item}</li>" for item in chapter["objectives"])
    engine = ENGINE_LABELS.get(chapter["engine"], chapter["engine"])
    return f"""<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>{esc(title)}</title>
<meta name="description" content="{description}">
<meta name="theme-color" content="#05070f">
<link rel="icon" href="{FAVICON}">
{FONT_LINKS}
<link rel="stylesheet" href="../assets/lab.css">
<link rel="stylesheet" href="studio.css">
</head>
<body class="lab">
<a class="studio-skip" href="#main">Skip to content</a>
<header class="lab-top">
  <div class="lab-brand">
    <a class="lab-back" href="../index.html" aria-label="Back to Java Atlas">{BACK_SVG}</a>
    <div class="lab-titles"><b>Java Atlas · Chapter Studio</b><span>Chapter {chapter['n']:02d} · {part}</span></div>
  </div>
  <div class="lab-top-actions">
    <span class="pill cyan"><i></i> Interactive</span>
    <a class="btn btn-sm btn-ghost" href="index.html">All studios</a>
  </div>
</header>
<main id="main" class="studio-wrap">
  <header class="studio-hero">
    <p class="studio-eyebrow">{part} · Chapter {chapter['n']:02d}</p>
    <h1 class="studio-title">{esc(chapter['title'])}</h1>
    <p class="studio-tagline">{chapter['tagline']}</p>
    <div class="studio-meta"><span>~{chapter['minutes']} min</span><span>{esc(engine)}</span><span>{len(chapter['objectives'])} objectives</span><span>{len(chapter['subgoals'])} subgoals</span></div>
    <ul class="studio-objectives">{objectives}</ul>
  </header>
  <div class="studio-layout">
    <div class="studio-column">
      {render_lesson(chapter)}
      {render_example(chapter)}
      {render_practice(chapter)}
      {render_mastery(chapter)}
      {render_artifact(chapter)}
      {render_reflection(chapter)}
      {render_pager(chapter, chapters)}
    </div>
    <aside class="studio-column" aria-label="Interactive playground and connections">
      {render_studio(chapter)}
      {render_links(chapter)}
    </aside>
  </div>
</main>
<footer class="lab-foot">
  <span>Java Atlas · an independent MIT-licensed companion. Textbook text, figures, and exercise statements are not reproduced here.</span>
  <a href="../index.html">Home</a>
</footer>
<script type="application/json" id="studio-data">{data}</script>
<script type="module" src="studio.js"></script>
</body>
</html>
"""


def render_index(chapters: list[dict]) -> str:
    cards = []
    for chapter in chapters:
        tags = " ".join(chapter.get("tags", []))
        cards.append(
            f'<a class="studio-card-link" data-chapter="{chapter["n"]}" data-tags="{esc(tags)}" '
            f'href="ch{chapter["n"]:02d}.html">'
            f'<span class="studio-card-meta"><span>CH {chapter["n"]:02d}</span>'
            f'<span>{esc(ENGINE_LABELS.get(chapter["engine"], chapter["engine"]))}</span></span>'
            f'<h2>{esc(chapter["title"])}</h2><p>{esc(chapter["tagline"])}</p>'
            f'<span class="studio-card-meta"><span>{esc(chapter["part"])}</span>'
            f'<span>~{chapter["minutes"]} min</span></span></a>'
        )
    engines = len({chapter["engine"] for chapter in chapters})
    return f"""<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Chapter Studios — 43 interactive Java lessons — Java Atlas</title>
<meta name="description" content="One interactive studio for every chapter of the Java Atlas companion: state machines, traces, structures, sorting, graphs, growth charts, data pipelines, contracts, layout labs, and Parsons problems.">
<meta name="theme-color" content="#05070f">
<link rel="icon" href="{FAVICON}">
{FONT_LINKS}
<link rel="stylesheet" href="../assets/lab.css">
<link rel="stylesheet" href="studio.css">
</head>
<body class="lab">
<a class="studio-skip" href="#main">Skip to content</a>
<header class="lab-top">
  <div class="lab-brand">
    <a class="lab-back" href="../index.html" aria-label="Back to Java Atlas">{BACK_SVG}</a>
    <div class="lab-titles"><b>Java Atlas · Chapter Studios</b><span>One product for every chapter</span></div>
  </div>
  <div class="lab-top-actions">
    <span class="pill cyan"><i></i> 100% client-side</span>
    <a class="btn btn-sm btn-ghost" href="../labs.html">Six labs</a>
  </div>
</header>
<main id="main" class="studio-wrap">
  <header class="studio-index-hero">
    <p class="studio-eyebrow">The full path · 43 chapters</p>
    <h1>Touch the mechanism. Then rebuild it.</h1>
    <p>Every chapter has the same spine: a short lesson, subgoal-labelled worked example, an interactive
    playground, a Parsons problem, a three-question mastery check, one downloadable artifact, and a
    reflection. Everything runs locally in your browser. No accounts, no tracking, no server.</p>
    <div class="studio-meta"><span>{len(chapters)} studios</span><span>{engines} engines</span><span>WCAG 2.2 AA target</span><span>MIT licensed</span></div>
    <p class="studio-hint" data-progress-summary>0 of 43 chapters complete</p>
  </header>
  <div class="studio-filter-row">
    <label for="studio-filter">Filter by chapter, part, topic, or engine</label>
    <input class="studio-filter" id="studio-filter" type="search" data-studio-filter placeholder="e.g. recursion, graph, sorting, Part 2">
    <span class="studio-filter-count" role="status" aria-live="polite" data-studio-filter-count></span>
  </div>
  <div class="studio-cards" data-studio-index>{"".join(cards)}</div>
  <p class="studio-index-note">This is a companion, not a replacement for the textbook. It contains original
  explanations, original code, and interactive models built around the same topic sequence. Read the
  chapter first; use the studio to make the mechanism observable; then write the Java yourself.</p>
</main>
<footer class="lab-foot">
  <span>Java Atlas · independent MIT-licensed companion. No textbook text, figures, or exercise statements are reproduced.</span>
  <a href="../index.html">Home</a>
</footer>
<script type="module" src="studio.js"></script>
</body>
</html>
"""


def main() -> int:
    if not CHAPTERS:
        print("No chapter data found.", file=sys.stderr)
        return 1
    chapters = sorted(CHAPTERS, key=lambda item: item["n"])
    numbers = [chapter["n"] for chapter in chapters]
    if len(numbers) != len(set(numbers)):
        print("Duplicate chapter numbers in studio data.", file=sys.stderr)
        return 1
    OUT.mkdir(parents=True, exist_ok=True)
    for chapter in chapters:
        page = OUT / f"ch{chapter['n']:02d}.html"
        page.write_text(render_page(chapter, chapters), encoding="utf-8")
    (OUT / "index.html").write_text(render_index(chapters), encoding="utf-8")
    print(f"Wrote {len(chapters)} chapter studios and index to docs/studios.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
