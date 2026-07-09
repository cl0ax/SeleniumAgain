import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestFireFoxSimpleCalc1 {
    @Test
    public void testCalculator() {
        System.out.println("🚀 Starting JUnit Test...");

        // Set up the driver
        System.setProperty("webdriver.gecko.driver", "C:\\Resources\\FireFoxDriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        try {
            // Go to the web page
            String url = "file://" + System.getProperty("user.dir") + "/simpleCaculator.html";
            driver.get(url);
            System.out.println("📄 Opened the calculator page");

            // STEP 1: Get the current value from the text box
            WebElement inputBox = driver.findElement(By.id("numberInput"));
            String currentValue = inputBox.getAttribute("value");
            System.out.println("📥 Current value in text box: " + currentValue);

            // STEP 2: Put a new number in the text box
            inputBox.clear();
            inputBox.sendKeys("25");
            System.out.println("✏️ Entered 25 into the text box");

            // STEP 3: Get the value we just entered
            String newValue = inputBox.getAttribute("value");
            System.out.println("📥 Value after entering: " + newValue);

            // STEP 4: Click the button
            WebElement button = driver.findElement(By.id("submitBtn"));
            button.click();
            System.out.println("🖱️ Clicked the 'Add 10' button");

            // STEP 5: Wait a moment for the result to appear
            Thread.sleep(1000);

            // STEP 6: Get the output result
            WebElement resultElement = driver.findElement(By.id("resultValue"));
            String result = resultElement.getText();
            System.out.println("📤 Result displayed: " + result);

            // STEP 7: THE JUNIT TEST - Check if the answer is correct
            int inputNumber = Integer.parseInt(newValue);  // Convert "25" to 25
            int expectedAnswer = inputNumber + 10;         // 25 + 10 = 35
            int actualAnswer = Integer.parseInt(result);   // Convert "35" to 35

            System.out.println("🧮 Testing: " + inputNumber + " + 10 should equal " + expectedAnswer);
            System.out.println("🔍 Actual result: " + actualAnswer);

            // This is the actual test - if this fails, the test fails
            Assertions.assertEquals(expectedAnswer, actualAnswer, "Calculator should add 10 correctly");

            System.out.println("✅ TEST PASSED! Calculator works correctly!");

        } catch (Exception e) {
            System.err.println("❌ Test failed: " + e.getMessage());
            Assertions.fail("Test failed due to exception: " + e.getMessage());
        } finally {
            // Close the browser
            if (driver != null) {
                try {
                    Thread.sleep(2000); // Keep open for 2 seconds
                } catch (InterruptedException e) {
                    // Ignore
                }
                driver.quit();
                System.out.println("🔒 Browser closed");
            }
        }
    }
}