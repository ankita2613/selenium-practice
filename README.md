# Selenium Practice: Sauce Demo Test Automation

![Selenium tests](https://github.com/ankita2613/selenium-practice/actions/workflows/tests.yml/badge.svg)

Automated UI tests for https://www.saucedemo.com

A matching Playwright version is in my [playwright-practice](https://github.com/ankita2613/playwright-practice) repository, so the two tools can be compared.

## Tools
Java 25, Selenium WebDriver, TestNG, Maven, Page Object Model

## What is tested
- Login (valid, invalid, locked-out user, 7 data-driven negative cases)
- Cart (adding items, cart contents)
- Checkout (full purchase journey)
=======
# Playwright Practice: Sauce Demo Test Automation

UI tests for https://www.saucedemo.com written with Playwright for Java.
A matching Selenium version is in my `selenium-practice` repository, so the two tools can be compared.

## Tools
Java 25, Playwright for Java, TestNG, Maven, Page Object Model

## What is tested
- Login: valid user, wrong password, and 7 data-driven invalid cases
- Checkout: add two items, check the cart, complete the order
>>>>>>> e6328fa (Add comparison table and Playwright link)

## How to run
- All tests: `mvn clean test`
- Smoke tests only: `mvn clean test "-Dgroups=smoke"`

## Features
- Page Object Model
<<<<<<< HEAD
- Data-driven tests with TestNG DataProvider
- Smoke and regression groups
- Automatic screenshot on test failure
=======
- No explicit waits (Playwright auto-waits)
- Smoke and regression groups
- Trace saved automatically when a test fails (open at trace.playwright.dev)
>>>>>>> e6328fa (Add comparison table and Playwright link)

## Selenium vs Playwright: what I measured
| Measure | Selenium | Playwright |
|---|---|---|
<<<<<<< HEAD
| Time, full run | 39.57 s (12 tests) | 27.2 s (10 tests) |
| Time, smoke run | 29.63 s (3 tests) | 19.7 s (2 tests) |
| Wait code in page classes | Explicit waits needed | None (auto-wait) |
| Failure evidence | Screenshot | Trace with steps, screenshots, network |
| Browser popup problem | Needed a workaround | Not needed |
=======
| Time, full run | 39.57 s | 27.2 s (10 tests) |
| Time, smoke run | 29.63 s | 19.7 s (2 tests) |
| Wait code in page classes | Explicit waits needed | None |
| Failure evidence | Screenshot | Trace with steps, screenshots, network |

## What I learned
My first version launched a new browser for every test and took 45.2 s.
Launching the browser once per class, with a fresh context per test, cut the full run to 27.2 s.
>>>>>>> e6328fa (Add comparison table and Playwright link)
