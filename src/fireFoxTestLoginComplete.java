import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
public class fireFoxTestLoginComplete {
        public static void main(String[] args) {
            System.out.println("🚀 Starting Selenium Test with Firefox...");
            System.setProperty("webdriver.gecko.driver", "C:\\Resources\\FireFoxDriver\\geckodriver.exe");
            WebDriver driver = null;
            WebDriverWait wait = null;

            try {
                // Step 1: Open Firefox browser
                System.out.println("🦊 Opening Firefox browser...");
                driver = new FirefoxDriver();
                wait = new WebDriverWait(driver, 10);

                // Step 2: Navigate to the website
                String url = "file://" + System.getProperty("user.dir") + "/batmanLogin.html";
                System.out.println("🌐 Navigating to " + url);
                driver.get(url);

                // Test Case 1: Test Failed Login
                System.out.println("\n🧪 Test Case 1: Testing Failed Login...");
                testFailedLogin(driver, wait);

                // Test Case 2: Test Successful Login
                System.out.println("\n🧪 Test Case 2: Testing Successful Login...");
                testSuccessfulLogin(driver, wait);

                // Test Case 3: Test Logout
                System.out.println("\n🧪 Test Case 3: Testing Logout...");
                testLogout(driver, wait);

                System.out.println("\n✅ All tests completed successfully!");

            } catch (Exception e) {
                System.err.println("❌ Error occurred: " + e.getMessage());
                e.printStackTrace();
                System.err.println("💡 Make sure:");
                System.err.println("   1. Firefox browser is installed");
                System.err.println("   2. GeckoDriver is at the correct path");
                System.err.println("   3. The website URL is accessible");
            } finally {
                // Always close the browser
                if (driver != null) {
                    System.out.println("🔒 Closing browser...");
                    // Add a small delay to see the results
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    driver.quit();
                }
            }
            System.out.println("🏁 Test finished!");
        }

        // Test failed login scenario
        private static void testFailedLogin(WebDriver driver, WebDriverWait wait) {
            try {
                // Find and fill username field
                WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
                usernameField.clear();
                usernameField.sendKeys("Batman"); // Wrong case - should be "batman"
                System.out.println("👤 Entered username: Batman");

                // Find and fill password field
                WebElement passwordField = driver.findElement(By.id("password"));
                passwordField.clear();
                passwordField.sendKeys("joker was here"); // Wrong password - should be "joker"
                System.out.println("🔑 Entered password: joker was here");

                // Click login button
                WebElement loginButton = driver.findElement(By.className("login-btn"));
                loginButton.click();
                System.out.println("🖱️ Clicked login button");

                // Wait for error message to appear and verify it
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
                String errorText = errorMessage.getText();
                System.out.println("⚠️ Error message displayed: " + errorText);

                // Verify error message is displayed
                if (errorMessage.isDisplayed()) {
                    System.out.println("✅ Failed login test PASSED - Error message shown correctly");
                } else {
                    System.out.println("❌ Failed login test FAILED - Error message not shown");
                }

            } catch (Exception e) {
                System.err.println("❌ Failed login test ERROR: " + e.getMessage());
            }
        }

        // Test successful login scenario
        private static void testSuccessfulLogin(WebDriver driver, WebDriverWait wait) {
            try {
                // Clear and enter correct credentials
                WebElement usernameField = driver.findElement(By.id("username"));
                usernameField.clear();
                usernameField.sendKeys("batman"); // Correct username
                System.out.println("👤 Entered correct username: batman");

                WebElement passwordField = driver.findElement(By.id("password"));
                passwordField.clear();
                passwordField.sendKeys("joker"); // Correct password
                System.out.println("🔑 Entered correct password: joker");

                // Click login button
                WebElement loginButton = driver.findElement(By.className("login-btn"));
                loginButton.click();
                System.out.println("🖱️ Clicked login button");

                // Wait for welcome page to appear
                WebElement welcomePage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcomePage")));
                System.out.println("🎉 Welcome page is displayed");

                // Verify welcome message
                WebElement welcomeUser = driver.findElement(By.id("welcomeUser"));
                String welcomeText = welcomeUser.getText();
                System.out.println("👋 Welcome message: Hello " + welcomeText + "!");

                // Verify page title changed
                String pageTitle = driver.getTitle();
                System.out.println("📄 Page title: " + pageTitle);

                // Verify login container is hidden
                WebElement loginContainer = driver.findElement(By.id("loginContainer"));
                if (!loginContainer.isDisplayed()) {
                    System.out.println("✅ Successful login test PASSED - Welcome page shown, login form hidden");
                } else {
                    System.out.println("❌ Successful login test FAILED - Login form still visible");
                }

            } catch (Exception e) {
                System.err.println("❌ Successful login test ERROR: " + e.getMessage());
            }
        }

        // Test logout functionality
        private static void testLogout(WebDriver driver, WebDriverWait wait) {
            try {
                // Find and click logout button
                WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutBtn")));
                logoutButton.click();
                System.out.println("🚪 Clicked logout button");

                // Wait for login container to reappear
                WebElement loginContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginContainer")));
                System.out.println("🔄 Login form is displayed again");

                // Verify welcome page is hidden
                WebElement welcomePage = driver.findElement(By.id("welcomePage"));
                if (!welcomePage.isDisplayed()) {
                    System.out.println("✅ Logout test PASSED - Back to login form");
                } else {
                    System.out.println("❌ Logout test FAILED - Welcome page still visible");
                }

                // Verify page title reset
                String pageTitle = driver.getTitle();
                System.out.println("📄 Page title after logout: " + pageTitle);

                // Verify form fields are cleared
                WebElement usernameField = driver.findElement(By.id("username"));
                WebElement passwordField = driver.findElement(By.id("password"));

                if (usernameField.getAttribute("value").isEmpty() &&
                        passwordField.getAttribute("value").isEmpty()) {
                    System.out.println("✅ Form fields cleared successfully");
                } else {
                    System.out.println("⚠️ Form fields not cleared properly");
                }

            } catch (Exception e) {
                System.err.println("❌ Logout test ERROR: " + e.getMessage());
            }
        }
    }
