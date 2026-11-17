"""Aggregate authored chapter data from the part files."""

from __future__ import annotations

import importlib
from pathlib import Path

MODULES = sorted(
    path.stem
    for path in Path(__file__).resolve().parent.glob("studio_data_[a-z].py")
)

CHAPTERS: list[dict] = []
for module_name in MODULES:
    module = importlib.import_module(module_name)
    CHAPTERS.extend(module.CHAPTERS)

CHAPTERS.sort(key=lambda chapter: chapter["n"])
