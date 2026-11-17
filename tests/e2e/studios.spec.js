import { test, expect } from "@playwright/test";
import AxeBuilder from "@axe-core/playwright";
import { readdirSync } from "node:fs";

const STUDIO_DIR = new URL("../../docs/studios/", import.meta.url);
const CHAPTER_PAGES = readdirSync(STUDIO_DIR)
  .filter((name) => /^ch\d{2}\.html$/.test(name))
  .sort();

test("studio index lists every generated chapter and filters", async ({ page }) => {
  await page.goto("/studios/index.html");
  await expect(page.getByRole("heading", { level: 1 })).toContainText("Touch the mechanism");
  await expect(page.locator("[data-studio-index] [data-chapter]")).toHaveCount(CHAPTER_PAGES.length);
  const filter = page.getByLabel("Filter by chapter, part, topic, or engine");
  await filter.fill("sorting");
  await expect(page.locator("[data-studio-index] [data-chapter]:visible")).not.toHaveCount(0);
  await expect(page.locator("[data-studio-index] [data-chapter]:visible").first()).toContainText("Sorting");
});

for (const file of CHAPTER_PAGES) {
  test(`${file} lesson, playground, quiz, and accessibility`, async ({ page }, testInfo) => {
    const errors = [];
    page.on("console", (message) => {
      if (message.type() === "error") errors.push(message.text());
    });
    page.on("pageerror", (error) => errors.push(error.message));

    await page.goto(`/studios/${file}`);
    await expect(page).toHaveTitle(/Chapter \d+ Studio/);
    await expect(page.getByRole("heading", { level: 1 })).toBeVisible();
    await expect(page.locator("main#main")).toHaveCount(1);
    await expect(page.locator(".studio-objectives li")).not.toHaveCount(0);

    const island = page.locator('#studio [data-engine]').first();
    await island.scrollIntoViewIfNeeded();
    await expect(island).toHaveAttribute("data-mounted", "true", { timeout: 5000 });
    const counter = island.locator(".studio-counter");
    if ((await counter.count()) > 0) {
      await expect(counter.first()).toContainText("1 /");
      const next = island.getByRole("button", { name: "Next" }).first();
      if (await next.isEnabled()) {
        await next.click();
        await expect(counter.first()).toContainText("2 /");
      }
    } else {
      await expect(island.locator("button, select, input").first()).toBeVisible();
    }

    const questions = page.locator(".quiz-question");
    await expect(questions).toHaveCount(3);
    for (let index = 0; index < 3; index += 1) {
      const question = questions.nth(index);
      const answer = Number(await question.getAttribute("data-answer"));
      await question.locator("input[type=radio]").nth(answer).check();
    }
    await page.getByRole("button", { name: "Check answers" }).click();
    await expect(page.locator(".studio-quiz-score")).toContainText("3 / 3");

    const complete = page.locator("[data-complete]");
    await complete.click();
    await expect(complete).toHaveAttribute("aria-pressed", "true");

    const overflow = await page.evaluate(
      () => document.documentElement.scrollWidth - document.documentElement.clientWidth,
    );
    expect(overflow).toBeLessThanOrEqual(1);

    if (testInfo.project.name === "desktop-chromium") {
      const results = await new AxeBuilder({ page })
        .withTags(["wcag2a", "wcag2aa", "wcag21a", "wcag21aa", "wcag22aa"])
        .analyze();
      const blocking = results.violations.filter(
        (violation) => violation.impact === "serious" || violation.impact === "critical",
      );
      expect(
        blocking.map((violation) => `${violation.id}: ${violation.nodes.length} nodes`),
      ).toEqual([]);
    }

    expect(errors).toEqual([]);
  });
}

test("artifact downloads and notes persist locally", async ({ page }) => {
  await page.goto("/studios/ch01.html");
  const downloadPromise = page.waitForEvent("download");
  await page.getByRole("link", { name: /Download HelloBlueprint\.java/ }).click();
  const download = await downloadPromise;
  expect(download.suggestedFilename()).toBe("HelloBlueprint.java");

  const notes = page.locator("[data-notes]");
  await notes.fill("I can explain source, bytecode, and JVM.");
  await page.waitForTimeout(600);
  await page.reload();
  await expect(notes).toHaveValue("I can explain source, bytecode, and JVM.");
});

test("Parsons problem supports keyboard reordering and reveal", async ({ page }) => {
  await page.goto("/studios/ch23.html");
  const island = page.locator('[data-engine="parsons"]');
  await island.scrollIntoViewIfNeeded();
  await expect(island).toHaveAttribute("data-mounted", "true");
  const firstCode = await island.locator(".parsons-code").first().textContent();
  const down = island.getByRole("button", { name: /Move line 1 down/ });
  await down.click();
  await expect(island.locator(".parsons-code").nth(1)).toHaveText(firstCode);

  await island.getByRole("button", { name: "Show solution" }).click();
  await expect(island.locator(".parsons-feedback")).not.toBeEmpty();
  await expect(island.locator(".parsons-feedback")).toHaveAttribute("data-tone", "ok");
  await island.getByRole("button", { name: "Check order" }).click();
  await expect(island.locator(".parsons-feedback")).toHaveAttribute("data-tone", "ok");
});
