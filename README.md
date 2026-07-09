# SeleniumAgain

SeleniumAgain is a Java Selenium WebDriver practice project. It uses Firefox, GeckoDriver, and JUnit 5 to automate browser tests against small HTML pages that live in this repository.

## What It Tests

- A simple calculator page (`simpleCaculator.html`)
- A user registration form (`inputFormSignUp.html`)
- A Batman-themed login page (`batmanLogin.html`)
- Basic browser navigation and page checks

The tests demonstrate common Selenium tasks: opening a page, finding elements by name/class/tag, filling inputs, selecting dropdown values, clicking buttons, and asserting page content.

## Stack

- Java
- Selenium WebDriver 3.141.59
- JUnit 5
- Firefox + GeckoDriver
- Local HTML/CSS/JavaScript test pages

## Setup

Install Firefox and download GeckoDriver:

```bash
https://github.com/mozilla/geckodriver/releases
```

Place the GeckoDriver binary at:

```text
drivers/geckodriver
```

Make it executable on macOS/Linux:

```bash
chmod +x drivers/geckodriver
```

Several test files expect Firefox at the macOS path:

```text
/Applications/Firefox.app/Contents/MacOS/firefox
```

If Firefox is installed somewhere else, update the `FirefoxBinary` path in the relevant test file before running it.

## Run

Open the project in IntelliJ IDEA, make sure the `Tests/` folder is marked as a test source root, then run individual test classes from the IDE.

Useful starting points:

```text
Tests/TestCalculator_4_22_Selenium.java
Tests/TestFireFoxSimpleCalc1.java
Tests/TestSignUpForm.java
Tests/InlineParameterizedCalculatorTest.java
```

The tests now load pages from this repository with `file://` URLs instead of relying on an instructor-hosted server.

## Notes

- `simpleCaculator.html` keeps the original misspelling used by the project.
- `SeleniumInClassPractice.java` now points at `SimpleHtmlPage.html` as a local placeholder because the original remote practice page is not included in the repo.
- Some classes under `src/` are runnable examples rather than JUnit tests.
