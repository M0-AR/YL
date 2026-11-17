import { defineConfig, devices } from "@playwright/test";

export default defineConfig({
  testDir: "./tests/e2e",
  fullyParallel: true,
  forbidOnly: !!process.env.CI,
  retries: process.env.CI ? 1 : 0,
  workers: process.env.CI ? 2 : 4,
  reporter: [["list"]],
  expect: {
    toHaveScreenshot: { maxDiffPixelRatio: 0.01 },
  },
  use: {
    baseURL: "http://127.0.0.1:8124",
    trace: "on-first-retry",
    screenshot: "only-on-failure",
  },
  webServer: {
    command: "python3 -m http.server 8124 --directory docs",
    url: "http://127.0.0.1:8124/",
    reuseExistingServer: true,
    timeout: 20000,
  },
  projects: [
    { name: "desktop-chromium", use: { ...devices["Desktop Chrome"] } },
    {
      name: "mobile-chromium",
      use: { ...devices["Pixel 7"] },
    },
  ],
});
