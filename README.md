# Selenium Practice: Sauce Demo Test Automation

Automated UI tests for https://www.saucedemo.com

## Tools
Java 25, Selenium WebDriver, TestNG, Maven, Page Object Model

## What is tested
- Login (valid, invalid, locked-out user, data-driven negative cases)
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
