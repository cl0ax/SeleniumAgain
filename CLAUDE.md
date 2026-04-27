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
Use IntelliJ IDEA: Project → Build → Build Project (or Cmd+B on macOS)

The build output goes to `out/production/SeleniumAgain/`

### Run Tests
**In IntelliJ:**
- Right-click a test class (in Tests/ folder) → "Run 'ClassName'"
- Right-click a specific test method → "Run 'methodName()'"
- Use Ctrl+Shift+R (macOS: Cmd+Shift+R) to run the last test

**Finding tests:** Look in `Tests/` folder for classes annotated with `@Test`. Note: some classes in `src/` are also example test implementations (Main.java style files)

### Run a Main Class (Example Tests)
Some files in `src/` have main() methods and can be run directly:
- In IntelliJ: Right-click the file → "Run 'ClassName.main()'"
- Example: `FireFoxSeleniumTest.java` has a main method that demonstrates basic Selenium usage

## Dependencies and Setup

### Selenium & Firefox
- **Selenium Version**: 3.141.59 (lib/selenium-*.jar files)
- **Browser**: Firefox - currently hardcoded to `/Applications/Firefox.app/Contents/MacOS/firefox` (macOS only)
  - **For Windows**: Update to `C:\Program Files\Mozilla Firefox\firefox.exe` or your Firefox install path
  - **For Linux**: Update to `/usr/bin/firefox` or your Firefox install path
  - Files to update: search for "Applications/Firefox.app" in test files and update paths
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
Tests configure Firefox via `FirefoxOptions` and `FirefoxBinary`. **Note:** Firefox path is hardcoded and platform-specific:
```java
FirefoxBinary binary = new FirefoxBinary(new File("/Applications/Firefox.app/Contents/MacOS/firefox"));
FirefoxOptions options = new FirefoxOptions();
options.setBinary(binary);
driver = new FirefoxDriver(options);
```
Update the path in your test files to match your OS's Firefox installation before running tests.

### Common Test Patterns
- Use `driver.get(URL)` to navigate
- Use `driver.findElement(By.*)` to locate elements (By.id, By.xpath, By.tagName, etc.)
- Use `driver.quit()` to close browser (not just driver.close())
- Sleep/wait for page loads: `Thread.sleep(milliseconds)`

### Testing with Local HTML Files
The project includes local HTML test pages (simpleCaculator.html, batmanLogin.html, etc.) in the project root. To test against them:
- Use file:// URLs: `driver.get("file://" + System.getProperty("user.dir") + "/simpleCaculator.html")`
- Or use an absolute path: `driver.get("file:///Users/ahernandez54/Documents/SeleniumAgain/simpleCaculator.html")`
- For remote URLs (like the example in TestCalculator_4_22_Selenium.java), use http:// or https:// directly

## IDE Configuration

Project uses IntelliJ IDEA module configuration (SeleniumAgain.iml):
- Source folder: `src/` (non-test)
- Test folder: `Tests/` (test source)
- Libraries are referenced in `.idea/libraries/lib.xml`

## Common Issues

1. **Firefox not found error**: The Firefox path is hardcoded in test files to `/Applications/Firefox.app/Contents/MacOS/firefox` (macOS). You must update this path in each test file to match your OS:
   - macOS: `/Applications/Firefox.app/Contents/MacOS/firefox`
   - Windows: `C:\Program Files\Mozilla Firefox\firefox.exe` (or your install path)
   - Linux: `/usr/bin/firefox` (or your install path)
   - Search for "Applications/Firefox.app" in all test files and replace accordingly

2. **GeckoDriver permission denied**: Make executable with `chmod +x drivers/geckodriver`

3. **JUnit tests not running in IntelliJ**: 
   - Ensure JUnit 5 libraries are configured (they are in SeleniumAgain.iml)
   - Right-click the test class/method and select "Run"
   - If that fails, verify Tests/ folder is marked as "Test Sources Root" in Project Structure

4. **Cannot find HTML test pages**: Local HTML files are in project root. Use file:// URLs:
   - `driver.get("file://" + System.getProperty("user.dir") + "/simpleCaculator.html")`
