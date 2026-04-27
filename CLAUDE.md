# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Selenium WebDriver testing project focused on automating browser-based tests using Firefox. The project uses JUnit 5 (Jupiter) for test frameworks and IntelliJ IDEA as the IDE.

## Key Directories

- **Tests/** - JUnit test classes (test sources)
- **src/** - Main source files and example test implementations
- **drivers/** - WebDriver executables (geckodriver for Firefox)
- **lib/** - External dependencies including Selenium libraries
- HTML files (simpleCaculator.html, batmanLogin.html, etc.) - Web pages used as test targets

## Building and Running

### Compile
Use IntelliJ IDEA or run:
```bash
javac -cp lib/*:out/production/SeleniumAgain Tests/TestCalculator_4_22_Selenium.java -d out/production/SeleniumAgain
```

### Run a Specific Test
From IntelliJ: Right-click the test class → "Run 'ClassName'"

From command line (requires IntelliJ to compile first):
```bash
java -cp lib/*:out/production/SeleniumAgain:$MAVEN_REPOSITORY/org/junit/jupiter/* TestCalculator_4_22_Selenium
```

### Run a Main Class
```bash
java -cp lib/*:out/production/SeleniumAgain FireFoxSeleniumTest
```

## Dependencies and Setup

### Selenium & Firefox
- **Selenium Version**: 3.141.59 (lib/selenium-*.jar files)
- **Browser**: Firefox - must be installed at `/Applications/Firefox.app/Contents/MacOS/firefox` (macOS) or update the path in code
- **WebDriver**: GeckoDriver (`drivers/geckodriver`) - ensure it's executable
  - If missing: download from https://github.com/mozilla/geckodriver/releases
  - Make executable: `chmod +x drivers/geckodriver`

### Testing Framework
- **JUnit 5.8.1** (Jupiter) - configured in SeleniumAgain.iml
- Import statements typically use: `org.junit.jupiter.api.*`

### Other Libraries
- guava-25.0-jre.jar - Google utilities
- okhttp-3.11.0.jar, okio-1.14.0.jar - HTTP client
- byte-buddy-1.8.15.jar - Dynamic class generation
- commons-exec-1.3.jar - Process execution

## Architecture Notes

### Test Structure
- Tests use `@BeforeAll` for class-level setup (e.g., setting WebDriver system properties)
- Tests use `@BeforeEach` to initialize a new FirefoxDriver instance before each test
- Tests typically use try-catch-finally blocks with driver.quit() in finally
- WebDriver is accessed via `org.openqa.selenium.WebDriver` interface

### Firefox Configuration
Tests configure Firefox via `FirefoxOptions` and `FirefoxBinary`:
```java
FirefoxBinary binary = new FirefoxBinary(new File("/Applications/Firefox.app/Contents/MacOS/firefox"));
FirefoxOptions options = new FirefoxOptions();
options.setBinary(binary);
driver = new FirefoxDriver(options);
```

### Common Test Patterns
- Use `driver.get(URL)` to navigate
- Use `driver.findElement(By.*)` to locate elements (By.id, By.xpath, By.tagName, etc.)
- Use `driver.quit()` to close browser (not just driver.close())
- Sleep/wait for page loads: `Thread.sleep(milliseconds)`

## IDE Configuration

Project uses IntelliJ IDEA module configuration (SeleniumAgain.iml):
- Source folder: `src/` (non-test)
- Test folder: `Tests/` (test source)
- Libraries are referenced in `.idea/libraries/lib.xml`

## Common Issues

1. **GeckoDriver not found**: Ensure `drivers/geckodriver` is executable and path in code matches system
2. **Firefox not found**: Update the hardcoded Firefox path in test files to match your installation
3. **Cannot find HTML test pages**: Local HTML files (simpleCaculator.html, etc.) are in project root; tests using them should use `file://` URLs or set up a local server
4. **JUnit tests not running**: Ensure JUnit 5 libraries are in classpath (configured in .iml file)
