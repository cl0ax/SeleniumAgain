
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
public class TestSignUpForm {

    private WebDriver driver;
    private final String BASE_URL = "file://" + System.getProperty("user.dir") + "/inputFormSignUp.html";

    @BeforeEach
    public void setUp() {
        System.out.println("🔧 Setting up browser for test...");
        System.setProperty("webdriver.gecko.driver", "C:\\Resources\\FireFoxDriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🔒 Browser closed");
        }
    }

    // Test 1: Valid Registration Data (Parameterized)
    @ParameterizedTest(name = "Valid Registration: {0} {1}, Age: {3}")
    @CsvSource({
            "John, Doe, john.doe@email.com, 26-35, sports",
            "Jane, Smith, jane.smith@gmail.com, 18-25, music",
            "Bob, Johnson, bob.j@yahoo.com, 36-50, technology",
            "Alice, Williams, alice.w@hotmail.com, 51+, travel",
            "Mike, Brown, mike.brown@test.com, 26-35, sports,music"
    })
    public void testValidRegistrations(String firstName, String lastName, String email, String ageGroup, String interests) {
        System.out.println("\n🧪 Testing valid registration: " + firstName + " " + lastName);

        try {
            // Fill out the form using NAME selectors
            driver.findElement(By.name("firstName")).sendKeys(firstName);
            driver.findElement(By.name("lastName")).sendKeys(lastName);
            driver.findElement(By.name("email")).sendKeys(email);

            // Handle dropdown using SELECT
            Select ageSelect = new Select(driver.findElement(By.name("ageGroup")));
            ageSelect.selectByValue(ageGroup);

            // Handle interests checkboxes
            String[] interestArray = interests.split(",");
            List<WebElement> checkboxes = driver.findElements(By.name("interests"));

            for (String interest : interestArray) {
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.getAttribute("value").equals(interest.trim())) {
                        checkbox.click();
                        break;
                    }
                }
            }

            // Submit using CLASS selector
            driver.findElement(By.className("submit-button")).click();

            // Wait for processing
            Thread.sleep(1500);

            // Verify success message appears
            WebElement successMessage = driver.findElement(By.className("success-message"));
            Assertions.assertTrue(successMessage.isDisplayed(),
                    "Success message should be visible for valid registration");

            // Verify page title changed
            String expectedTitle = "Registration Complete - Selenium Practice";
            Assertions.assertEquals(expectedTitle, driver.getTitle(),
                    "Page title should change after successful registration");

            // Verify user summary contains correct data
            WebElement userSummary = driver.findElement(By.className("user-summary"));
            String summaryText = userSummary.getText();

            Assertions.assertTrue(summaryText.contains(firstName),
                    "Summary should contain first name");
            Assertions.assertTrue(summaryText.contains(lastName),
                    "Summary should contain last name");
            Assertions.assertTrue(summaryText.contains(email),
                    "Summary should contain email");

            System.out.println("✅ Valid registration test PASSED for: " + firstName + " " + lastName);

        } catch (Exception e) {
            Assertions.fail("Valid registration test failed: " + e.getMessage());
        }
    }

    // Test 2: Invalid Email Formats (Parameterized)
    @ParameterizedTest(name = "Invalid Email Test: {0}")
    @CsvSource({
            "invalid-email",
            "missing@domain",
            "@missinglocal.com",
            "no-at-symbol.com",
            "double@@domain.com",
            "trailing.dot@domain.com.",
            "spaces in@email.com"
    })
    public void testInvalidEmailFormats(String invalidEmail) {
        System.out.println("\n🧪 Testing invalid email: " + invalidEmail);

        try {
            // Fill required fields with valid data except email
            driver.findElement(By.name("firstName")).sendKeys("Test");
            driver.findElement(By.name("lastName")).sendKeys("User");
            driver.findElement(By.name("email")).sendKeys(invalidEmail);

            // Submit the form
            driver.findElement(By.className("submit-button")).click();

            Thread.sleep(1000);

            // Verify error message appears
            WebElement errorMessage = driver.findElement(By.className("error-message"));
            Assertions.assertTrue(errorMessage.isDisplayed(),
                    "Error message should appear for invalid email: " + invalidEmail);

            // Verify we're still on the same page (title didn't change)
            String currentTitle = driver.getTitle();
            Assertions.assertEquals("User Registration - Selenium Practice", currentTitle,
                    "Page title should not change for invalid submission");

            System.out.println("✅ Invalid email test PASSED for: " + invalidEmail);

        } catch (Exception e) {
            Assertions.fail("Invalid email test failed for " + invalidEmail + ": " + e.getMessage());
        }
    }

    // Test 3: Missing Required Fields (Parameterized)
    @ParameterizedTest(name = "Missing Required Field Test: {0}")
    @CsvSource({
            "firstName, '', 'Doe', 'test@email.com'",
            "lastName, 'John', '', 'test@email.com'",
            "email, 'John', 'Doe', ''",
            "all, '', '', ''"
    })
    public void testMissingRequiredFields(String missingField, String firstName, String lastName, String email) {
        System.out.println("\n🧪 Testing missing required field: " + missingField);

        try {
            // Fill fields based on parameters (empty string means don't fill)
            if (!firstName.isEmpty()) {
                driver.findElement(By.name("firstName")).sendKeys(firstName);
            }
            if (!lastName.isEmpty()) {
                driver.findElement(By.name("lastName")).sendKeys(lastName);
            }
            if (!email.isEmpty()) {
                driver.findElement(By.name("email")).sendKeys(email);
            }

            // Submit the form
            driver.findElement(By.className("submit-button")).click();

            Thread.sleep(1000);

            // Verify error message appears
            WebElement errorMessage = driver.findElement(By.className("error-message"));
            Assertions.assertTrue(errorMessage.isDisplayed(),
                    "Error message should appear when " + missingField + " is missing");

            // Verify success message does NOT appear
            WebElement successMessage = driver.findElement(By.className("success-message"));
            Assertions.assertFalse(successMessage.isDisplayed(),
                    "Success message should not appear when required fields are missing");

            System.out.println("✅ Missing field test PASSED for: " + missingField);

        } catch (Exception e) {
            Assertions.fail("Missing field test failed for " + missingField + ": " + e.getMessage());
        }
    }

    // Test 4: Age Group Selection (Parameterized)
    @ParameterizedTest(name = "Age Group Test: {0}")
    @CsvSource({
            "18-25",
            "26-35",
            "36-50",
            "51+"
    })
    public void testAgeGroupSelections(String ageGroup) {
        System.out.println("\n🧪 Testing age group selection: " + ageGroup);

        try {
            // Fill required fields
            driver.findElement(By.name("firstName")).sendKeys("Test");
            driver.findElement(By.name("lastName")).sendKeys("User");
            driver.findElement(By.name("email")).sendKeys("test@email.com");

            // Select age group using TAG selector to find select element
            WebElement selectElement = driver.findElement(By.tagName("select"));
            Select ageSelect = new Select(selectElement);
            ageSelect.selectByValue(ageGroup);

            // Verify the selection was made
            String selectedValue = ageSelect.getFirstSelectedOption().getAttribute("value");
            Assertions.assertEquals(ageGroup, selectedValue,
                    "Selected age group should match expected value");

            // Submit and verify success
            driver.findElement(By.className("submit-button")).click();
            Thread.sleep(1500);

            WebElement successMessage = driver.findElement(By.className("success-message"));
            Assertions.assertTrue(successMessage.isDisplayed(),
                    "Registration should succeed with age group: " + ageGroup);

            // Verify age group appears in summary
            WebElement userSummary = driver.findElement(By.className("user-summary"));
            String summaryText = userSummary.getText();
            Assertions.assertTrue(summaryText.contains(ageGroup),
                    "User summary should contain selected age group");

            System.out.println("✅ Age group test PASSED for: " + ageGroup);

        } catch (Exception e) {
            Assertions.fail("Age group test failed for " + ageGroup + ": " + e.getMessage());
        }
    }

    // Test 5: Interest Combinations (Parameterized)
    @ParameterizedTest(name = "Interest Test: {0}")
    @CsvSource({
            "sports",
            "music",
            "technology",
            "travel",
            "sports,music",
            "technology,travel",
            "sports,music,technology",
            "sports,music,technology,travel"
    })
    public void testInterestSelections(String interests) {
        System.out.println("\n🧪 Testing interest selections: " + interests);

        try {
            // Fill required fields
            driver.findElement(By.name("firstName")).sendKeys("Test");
            driver.findElement(By.name("lastName")).sendKeys("User");
            driver.findElement(By.name("email")).sendKeys("test@email.com");

            // Select interests using CLASS selector for checkboxes
            String[] interestArray = interests.split(",");
            List<WebElement> checkboxes = driver.findElements(By.className("interest-checkbox"));

            for (String interest : interestArray) {
                for (WebElement checkbox : checkboxes) {
                    if (checkbox.getAttribute("value").equals(interest.trim())) {
                        checkbox.click();
                        System.out.println("☑️ Selected interest: " + interest.trim());
                        break;
                    }
                }
            }

            // Verify selections were made
            int expectedSelections = interestArray.length;
            int actualSelections = 0;
            for (WebElement checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    actualSelections++;
                }
            }

            Assertions.assertEquals(expectedSelections, actualSelections,
                    "Number of selected interests should match expected");

            // Submit and verify
            driver.findElement(By.className("submit-button")).click();
            Thread.sleep(1500);

            WebElement successMessage = driver.findElement(By.className("success-message"));
            Assertions.assertTrue(successMessage.isDisplayed(),
                    "Registration should succeed with interests: " + interests);

            System.out.println("✅ Interest test PASSED for: " + interests);

        } catch (Exception e) {
            Assertions.fail("Interest test failed for " + interests + ": " + e.getMessage());
        }
    }

    // Test 6: Element Selector Verification (Non-parameterized)
    @Test
    public void testElementSelectorTypes() {
        System.out.println("\n🧪 Testing different selector types work correctly");

        try {
            // Test NAME selectors
            WebElement firstNameByName = driver.findElement(By.name("firstName"));
            Assertions.assertNotNull(firstNameByName, "Should find firstName by name");

            // Test CLASS selectors
            List<WebElement> textInputsByClass = driver.findElements(By.className("text-input"));
            Assertions.assertTrue(textInputsByClass.size() >= 3,
                    "Should find at least 3 text inputs by class");

            // Test TAG selectors
            WebElement formByTag = driver.findElement(By.tagName("form"));
            Assertions.assertNotNull(formByTag, "Should find form by tag");

            List<WebElement> inputsByTag = driver.findElements(By.tagName("input"));
            Assertions.assertTrue(inputsByTag.size() >= 6,
                    "Should find at least 6 input elements by tag");

            WebElement selectByTag = driver.findElement(By.tagName("select"));
            Assertions.assertNotNull(selectByTag, "Should find select element by tag");

            WebElement buttonByTag = driver.findElement(By.tagName("button"));
            Assertions.assertNotNull(buttonByTag, "Should find button by tag");

            System.out.println("✅ All selector types working correctly");

        } catch (Exception e) {
            Assertions.fail("Selector verification test failed: " + e.getMessage());
        }
    }
}