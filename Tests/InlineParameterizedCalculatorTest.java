import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InlineParameterizedCalculatorTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.out.println("🔧 Setting up browser for test...");
        System.setProperty("webdriver.gecko.driver", "C:\\Resources\\FireFoxDriver\\geckodriver.exe");
        driver = new FirefoxDriver();

        // Navigate to the calculator page
        String url = "http://45.55.136.114/~dlash/seleniumTest/simpleCaculator.html";
        driver.get(url);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🔒 Browser closed");
        }
    }

    @ParameterizedTest(name = "Test: {0} + 10 should equal {1}")
    @CsvSource({
            "5, 15",          // Positive number
            "-3, 7",          // Negative number
            "0, 10",          // Zero
            "100, 110",       // Large positive number
            "-1, 9",          // Small negative number
            "-100, -90",      // Large negative number
            "25, 35",         // Our original test case
            "999, 1009"       // Very large number
    })
    public void testCalculatorWithDifferentValues(String inputValue, String expectedResult) {
        System.out.println("\n🧪 Testing: " + inputValue + " + 10 should equal " + expectedResult);

        try {
            // STEP 1: Clear and enter the input value
            WebElement inputBox = driver.findElement(By.id("numberInput"));
            inputBox.clear();
            inputBox.sendKeys(inputValue);
            System.out.println("✏️ Entered " + inputValue + " into the text box");

            // STEP 2: Get the value we entered (for verification)
            String enteredValue = inputBox.getAttribute("value");
            System.out.println("📥 Value in text box: " + enteredValue);

            // STEP 3: Click the button
            WebElement button = driver.findElement(By.id("submitBtn"));
            button.click();
            System.out.println("🖱️ Clicked the 'Add 10' button");

            // STEP 4: Wait for result and get it
            Thread.sleep(1000);
            WebElement resultElement = driver.findElement(By.id("resultValue"));
            String actualResult = resultElement.getText();
            System.out.println("📤 Actual result: " + actualResult);

            // STEP 5: Convert to numbers and test
            int inputNumber = Integer.parseInt(enteredValue);
            int expectedNumber = Integer.parseInt(expectedResult);
            int actualNumber = Integer.parseInt(actualResult);

            System.out.println("🧮 Calculation: " + inputNumber + " + 10 = " + actualNumber);
            System.out.println("🎯 Expected: " + expectedNumber + ", Actual: " + actualNumber);

            // THE TEST: Verify the calculation is correct
            Assertions.assertEquals(expectedNumber, actualNumber,
                    "Calculation failed: " + inputNumber + " + 10 should equal " + expectedNumber);

            System.out.println("✅ Test PASSED for input " + inputValue + "!");

        } catch (Exception e) {
            System.err.println("❌ Test FAILED for input " + inputValue + ": " + e.getMessage());
            Assertions.fail("Test failed for input " + inputValue + " with exception: " + e.getMessage());
        }
    }
}