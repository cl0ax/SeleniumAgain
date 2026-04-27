import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
public class FireFoxSimpleCalc1 {
    public static void main(String[] args) {
        System.out.println("🚀 Starting Basic Selenium Example...");
        System.setProperty("webdriver.gecko.driver", "C:\\Resources\\FireFoxDriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        try {
            // Go to the web page
         String url = "http://45.55.136.114/~dlash/seleniumTest/simpleCaculator.html";
            driver.get( url );
            System.out.println("📄 Opened the calculator page:" + url);
            // STEP 1: Get the current value from the text box
            WebElement inputBox = driver.findElement(By.id("numberInput"));
            String currentValue = inputBox.getAttribute("value");
            System.out.println("📥 Current value in text box: " + currentValue);
            // STEP 2: Put a new number in the text box
            inputBox.clear();
            inputBox.sendKeys("25");
            System.out.println("✏️ Entered 25 into the text box");
            // STEP 3: Get the value we just entered (to verify it worked)
            String newValue = inputBox.getAttribute("value");
            System.out.println("📥 Value after entering: " + newValue);
            // STEP 4: Click the button
            WebElement button = driver.findElement(By.id("submitBtn"));
            button.click();
            System.out.println("🖱️ Clicked the 'Add 10' button");
            // STEP 5: Wait a moment for the result to appear
            Thread.sleep(1000); // Wait 1 second
            // STEP 6: Get the output result
            WebElement resultElement = driver.findElement(By.id("resultValue"));
            String result = resultElement.getText();
            System.out.println("📤 Result displayed: " + result);

            // SUMMARY: Print everything we learned
            System.out.println("\n📊 SUMMARY:");
            System.out.println("   Started with: " + currentValue);
            System.out.println("   We entered: " + newValue);
            System.out.println("   Final result: " + result);
            System.out.println("   Expected: " + (Integer.parseInt(newValue) + 10));
        } catch (Exception e) {
            System.err.println("❌ Something went wrong: " + e.getMessage());
        } finally {
            // Keep browser open for 5 seconds so you can see the result
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
        System.out.println("🏁 Done!");
    }
}
