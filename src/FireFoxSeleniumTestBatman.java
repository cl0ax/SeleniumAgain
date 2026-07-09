
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxSeleniumTestBatman {

public static void main(String[] args) {
    System.out.println("🚀 Starting Selenium Test with Firefox...");

    System.setProperty("webdriver.gecko.driver", "C:\\Resources\\FireFoxDriver\\geckodriver.exe");
    WebDriver driver = null;

    try {
        // Step 1: Open Firefox browser
        System.out.println("🦊 Opening Firefox browser...");
        driver = new FirefoxDriver();
        // Step 2: Navigate to a website
        String url = "file://" + System.getProperty("user.dir") + "/batmanClassic.html";
        System.out.println("🌐 Navigating to" + url);
        driver.get( url );
        // Step 3: Get page information
        String title = driver.getTitle();
        System.out.println("📄 Page title: " + title);
        // Step 4: Find an element on the page
        WebElement heading = driver.findElement(By.tagName("h1"));
        System.out.println("📝 Main heading: " + heading.getText());
        // Step 5: Test basic interaction
        System.out.println("🔍 Finding links on the page...");
        WebElement link = driver.findElement(By.tagName("a"));
        System.out.println("📎 Found link: " + link.getText());
        // Step 6: Wait so students can see it working
        System.out.println("⏳ Waiting 5 seconds...");
        Thread.sleep(5000);

        System.out.println("✅ Test completed successfully!");

    } catch (Exception e) {
        System.err.println("❌ Error occurred: " + e.getMessage());
        System.err.println("💡 Make sure:");
        System.err.println("   1. Firefox browser is installed");
        System.err.println("   2. GeckoDriver is at C:\\geckodriver\\geckodriver.exe");
        System.err.println("   3. Download from: https://github.com/mozilla/geckodriver/releases");
    } finally {
        // Step 7: Always close the browser
        if (driver != null) {
            System.out.println("🔒 Closing browser...");
            driver.quit();
        }
    }
    System.out.println("🏁 Test finished!");
}
}