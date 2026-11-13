# Selenium Practice: Sauce Demo Test Automation

Automated UI tests for https://www.saucedemo.com

A matching Playwright version is in my [playwright-practice](https://github.com/ankita2613/playwright-practice) repository, so the two tools can be compared.

## Tools
Java 25, Selenium WebDriver, TestNG, Maven, Page Object Model

## What is tested
- Login (valid, invalid, locked-out user, 7 data-driven negative cases)
- Cart (adding items, cart contents)
- Checkout (full purchase journey)

## How to run
- All tests: `mvn clean test`
- Smoke tests only: `mvn clean test "-Dgroups=smoke"`

## Features
- Page Object Model
- Data-driven tests with TestNG DataProvider
- Smoke and regression groups
- Automatic screenshot on test failure

## Selenium vs Playwright: what I measured
| Measure | Selenium | Playwright |
|---|---|---|
| Time, full run | 39.57 s (12 tests) | 27.2 s (10 tests) |
| Time, smoke run | 29.63 s (3 tests) | 19.7 s (2 tests) |
| Wait code in page classes | Explicit waits needed | None (auto-wait) |
| Failure evidence | Screenshot | Trace with steps, screenshots, network |
| Browser popup problem | Needed a workaround | Not needed |
