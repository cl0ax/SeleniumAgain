<h1 align="center">Selenium Test Suite</h1>

<p align="center">
  Browser automation in Java: Selenium WebDriver drives Firefox through local pages while JUnit 5 checks the results.
</p>

<p align="center">
  <a href="#features">Features</a> ·
  <a href="#quick-start">Quick start</a> ·
  <a href="#how-it-works">How it works</a> ·
  <a href="#project-notes">Project notes</a>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/language-Java-blue" alt="Language: Java">
  <img src="https://img.shields.io/badge/framework-Selenium%20%2B%20JUnit%205-green" alt="Frameworks: Selenium WebDriver and JUnit 5">
  <img src="https://img.shields.io/badge/license-MIT-green" alt="License: MIT">
</p>

<p align="center">
  <img src="docs/demo.gif" width="720" alt="Headless Firefox calculator interactions followed by eight passing JUnit cases">
</p>

This repository collects browser automation exercises and local HTML fixtures. It was created for CSC3510 coursework and demonstrates how Selenium can interact with forms and page controls while JUnit checks the results.

## Features

### Calculator tests

- **Check calculator behavior.** Parameterized cases enter values, click Add 10, and compare the displayed result.

<p align="center"><img src="docs/features/calculator/calculator.png" width="640" alt="Local calculator page with a number input and Add 10 button"></p>

### Form tests

- **Exercise registration flows.** The signup fixture includes required fields, an age dropdown, interest checkboxes, and success and error feedback.

<p align="center"><img src="docs/features/signup/signup-form.png" width="640" alt="Local signup form with name and email fields, age selection, and interest checkboxes"></p>

### Login practice

- **Check login states.** The local login page handles valid and invalid credentials and switches between login and welcome views.

<p align="center"><img src="docs/features/login/login.png" width="640" alt="Local login page with username and password fields"></p>

## Quick start

Prerequisites: JDK 17 or newer, Maven, and Firefox. From a terminal, run:

```bash
git clone https://github.com/cl0ax/SeleniumAgain.git
cd SeleniumAgain
mvn test
```

To keep Firefox headless, prefix Maven with `MOZ_HEADLESS=1`:

```bash
MOZ_HEADLESS=1 mvn test
```

Maven resolves Selenium and JUnit from the checked-in `pom.xml`. Selenium Manager locates or downloads geckodriver as needed. Run Maven from the repository root so the tests can open the HTML fixtures there.

## How it works

`Tests/` contains JUnit examples and parameterized tests. The test setup creates a `FirefoxDriver`, loads a local `file://` fixture, exercises elements through Selenium, and checks the resulting page state with JUnit assertions. Root-level HTML files provide the calculator, signup, login, and other practice pages; `src/` contains standalone exercises.

## Project notes

This is CSC3510 coursework and a collection of practice programs, not a production test framework. The default Maven run includes the local calculator tests. I exclude `DotComTest` because it visits `example.com`, and `SeleniumInClassPractice` because its test bodies are unfinished TODO exercises. I also exclude `TestSignUpForm` for now: six invalid-email cases expect a custom error message, but the page's native email validation blocks submission before that message appears. The network example in `src/FireFoxSeleniumTest.java` is a standalone manual example, outside Surefire's test run.

## License

Distributed under the MIT License. See `LICENSE`.
