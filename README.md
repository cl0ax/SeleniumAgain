# SeleniumAgain

This is a Java coursework repository containing in-class exercises and assignments for learning Selenium WebDriver and JUnit 5. It is a collection of practice programs, not a production test suite or reusable testing framework.

## What it demonstrates

- Opening local HTML pages and `example.com` in Firefox.
- Locating elements by ID, name, class, tag, and other Selenium selectors.
- Filling registration and login forms.
- Selecting dropdown values.
- Clicking calculator controls and checking displayed results.
- Writing JUnit 5 tests, parameterized tests, setup methods, and assertions.

The included pages cover a calculator, a signup form, a Batman-themed login exercise, and a basic HTML practice page.

## Repository structure

- `Tests/`: JUnit 5 test classes and classroom exercises.
- `src/`: standalone Selenium examples plus two empty introductory Java classes.
- Root HTML files: local pages used by the exercises.
- `SeleniumAgain.iml`: IntelliJ module metadata with JUnit 5.8.1 references.

## Requirements and setup limits

The repository has no Maven or Gradle build file and does not vendor Selenium. Its IntelliJ module refers to a project-level library named `lib`, so a clean clone is not immediately runnable until Selenium is configured in the IDE.

The exercises also use inconsistent local paths:

- Several classes hard-code `C:\\Resources\\FireFoxDriver\\geckodriver.exe`.
- Some classes expect `drivers/geckodriver` under the repository.
- Some classes expect Firefox at `/Applications/Firefox.app/Contents/MacOS/firefox`.

Install Firefox and GeckoDriver, configure Selenium and JUnit in IntelliJ, then update the relevant path before running an individual class. GeckoDriver releases are available at:

```text
https://github.com/mozilla/geckodriver/releases
```

## Running exercises

Mark `Tests/` as a test source root in IntelliJ and run individual classes. Useful examples include:

```text
Tests/TestCalculator_4_22_Selenium.java
Tests/TestFireFoxSimpleCalc1.java
Tests/TestSignUpForm.java
Tests/InlineParameterizedCalculatorTest.java
Tests/DotComTest.java
```

Most exercises use repository-local `file://` pages. `DotComTest` and `src/FireFoxSeleniumTest.java` use `example.com`.

## Technologies shown in source

- Java
- Selenium WebDriver APIs
- JUnit 5.8.1 metadata
- Firefox and GeckoDriver
- Local HTML, CSS, and JavaScript fixtures

No Selenium version can be verified from the checked-in dependency configuration because the `lib` project library is not included.
