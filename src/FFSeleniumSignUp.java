import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
public class FFSeleniumSignUp {
    public static void main(String[] args) {
        System.out.println("🚀 Starting Registration Form Selenium Demo...");

        // Set up the driver
        System.setProperty("webdriver.gecko.driver", "C:\\Resources\\FireFoxDriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        try {
            // Navigate to the registration form
            String url = "http://45.55.136.114/~dlash/seleniumTest/inputFormSignUp.html";
            driver.get(url);
            System.out.println("📄 Opened registration form page");

            // ==== PART 1: ACCESSING ELEMENTS BY NAME ====
            System.out.println("\n🏷️  PART 1: Finding elements BY NAME");

            WebElement firstNameField = driver.findElement(By.name("firstName"));
            WebElement lastNameField = driver.findElement(By.name("lastName"));
            WebElement emailField = driver.findElement(By.name("email"));
            WebElement ageGroupDropdown = driver.findElement(By.name("ageGroup"));

            System.out.println("✅ Found firstName field by name");
            System.out.println("✅ Found lastName field by name");
            System.out.println("✅ Found email field by name");
            System.out.println("✅ Found ageGroup dropdown by name");

            // ==== PART 2: ACCESSING ELEMENTS BY CLASS ====
            System.out.println("\n🎨 PART 2: Finding elements BY CLASS");

            List<WebElement> textInputs = driver.findElements(By.className("text-input"));
            WebElement submitButton = driver.findElement(By.className("submit-button"));
            WebElement dropdown = driver.findElement(By.className("dropdown"));
            List<WebElement> checkboxes = driver.findElements(By.className("interest-checkbox"));

            System.out.println("✅ Found " + textInputs.size() + " text input fields by class");
            System.out.println("✅ Found submit button by class");
            System.out.println("✅ Found dropdown by class");
            System.out.println("✅ Found " + checkboxes.size() + " interest checkboxes by class");

            // ==== PART 3: ACCESSING ELEMENTS BY TAG ====
            System.out.println("\n🏷️  PART 3: Finding elements BY TAG");

            WebElement form = driver.findElement(By.tagName("form"));
            List<WebElement> allInputs = driver.findElements(By.tagName("input"));
            WebElement selectElement = driver.findElement(By.tagName("select"));
            WebElement buttonElement = driver.findElement(By.tagName("button"));

            System.out.println("✅ Found form by tag name");
            System.out.println("✅ Found " + allInputs.size() + " total input elements by tag");
            System.out.println("✅ Found select element by tag");
            System.out.println("✅ Found button element by tag");

            // ==== PART 4: FILLING OUT THE FORM ====
            System.out.println("\n📝 PART 4: Filling out the form");

            // Fill text fields using name selectors
            firstNameField.clear();
            firstNameField.sendKeys("John");
            System.out.println("✏️ Entered 'John' in first name field");

            lastNameField.clear();
            lastNameField.sendKeys("Doe");
            System.out.println("✏️ Entered 'Doe' in last name field");

            emailField.clear();
            emailField.sendKeys("john.doe@email.com");
            System.out.println("✏️ Entered 'john.doe@email.com' in email field");

            // Handle dropdown using Select class
            Select ageSelect = new Select(ageGroupDropdown);
            ageSelect.selectByValue("26-35");
            System.out.println("📋 Selected '26-35' from age group dropdown");

            // Handle checkboxes - select a few interests
            List<WebElement> interestCheckboxes = driver.findElements(By.name("interests"));
            System.out.println("🔍 Found " + interestCheckboxes.size() + " interest checkboxes");

            // Click the first two checkboxes (Sports and Music)
            if (interestCheckboxes.size() >= 2) {
                interestCheckboxes.get(0).click(); // Sports
                interestCheckboxes.get(1).click(); // Music
                System.out.println("☑️ Checked Sports and Music interests");
            }

            // ==== PART 5: GETTING VALUES BACK ====
            System.out.println("\n📤 PART 5: Reading values from form");

            String enteredFirstName = firstNameField.getAttribute("value");
            String enteredLastName = lastNameField.getAttribute("value");
            String enteredEmail = emailField.getAttribute("value");
            String selectedAge = ageSelect.getFirstSelectedOption().getText();

            System.out.println("📥 First Name: " + enteredFirstName);
            System.out.println("📥 Last Name: " + enteredLastName);
            System.out.println("📥 Email: " + enteredEmail);
            System.out.println("📥 Selected Age Group: " + selectedAge);

            // Check which interests are selected
            System.out.print("📥 Selected Interests: ");
            for (WebElement checkbox : interestCheckboxes) {
                if (checkbox.isSelected()) {
                    String value = checkbox.getAttribute("value");
                    System.out.print(value + " ");
                }
            }
            System.out.println();

            // ==== PART 6: SUBMIT THE FORM ====
            System.out.println("\n🖱️  PART 6: Submitting the form");

            submitButton.click();
            System.out.println("🖱️ Clicked submit button");

            // Wait a moment for JavaScript to process
            Thread.sleep(2000);

            // Check if success message appeared
            try {
                WebElement successMessage = driver.findElement(By.className("success-message"));
                if (successMessage.isDisplayed()) {
                    System.out.println("✅ SUCCESS MESSAGE DISPLAYED!");

                    // Get the user summary
                    WebElement userSummary = driver.findElement(By.className("user-summary"));
                    String summaryText = userSummary.getText();
                    System.out.println("📋 Registration Summary:");
                    System.out.println(summaryText);
                } else {
                    System.out.println("⚠️ Success message exists but not visible");
                }
            } catch (Exception e) {
                System.out.println("❌ No success message found - checking for errors...");

                try {
                    WebElement errorMessage = driver.findElement(By.className("error-message"));
                    if (errorMessage.isDisplayed()) {
                        String errorText = errorMessage.getText();
                        System.out.println("❌ Error message: " + errorText);
                    }
                } catch (Exception e2) {
                    System.out.println("❓ No error message found either");
                }
            }

            // Check if page title changed
            String pageTitle = driver.getTitle();
            System.out.println("📄 Page title: " + pageTitle);

            System.out.println("\n🎯 SUMMARY OF WHAT WE LEARNED:");
            System.out.println("✅ By.name() - Found specific form fields");
            System.out.println("✅ By.className() - Found groups of similar elements");
            System.out.println("✅ By.tagName() - Found elements by HTML tag type");
            System.out.println("✅ Select class - Handled dropdown selections");
            System.out.println("✅ Multiple elements - Worked with checkbox groups");
            System.out.println("✅ Form validation - Tested success/error scenarios");

        } catch (Exception e) {
            System.err.println("❌ Something went wrong: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Keep browser open for 5 seconds to see results
            try {
                System.out.println("⏰ Keeping browser open for 5 seconds...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // Ignore
            }

            // Close the browser
            driver.quit();
            System.out.println("🔒 Browser closed");
        }

        System.out.println("🏁 Demo complete!");
    }
}
