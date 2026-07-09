
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxSeleniumTestLogin {
public static void main(String[] args) {
    System.out.println("🚀 Starting Selenium Test with Firefox...");
    System.setProperty("webdriver.gecko.driver", "C:\\Resources\\FireFoxDriver\\geckodriver.exe");
    WebDriver driver = null;

    try {
        // Step 1: Open Firefox browser
        System.out.println("🦊 Opening Firefox browser...");
        driver = new FirefoxDriver();
        // Step 2: Navigate to a website
        String url = "file://" + System.getProperty("user.dir") + "/batmanLogin.html";
        System.out.println("🌐 Navigating to" + url);
        driver.get( url );
        driver.findElement(By.id("username")).sendKeys("Batman");
        System.out.println("✅ Test completed successfully!");
        driver.findElement(By.name("password")).sendKeys("joker was here");
        driver.findElement(By.className("login-btn")).click();
        String results = driver.findElement(By.cssSelector("div.error-message")).getText();
        System.out.printf("\n results:%s\n", results);
    } catch (Exception e) {
        System.err.println("❌ Error occurred: " + e.getMessage());
        System.err.println("💡 Make sure:");
        System.err.println("   1. Firefox browser is installed");
        System.err.println("   2. GeckoDriver is at C:\\geckodriver\\geckodriver.exe");
        System.err.println("   3. Download from: https://github.com/mozilla/geckodriver/releases");
    } finally {
//        // Step 7: Always close the browser
//        if (driver != null) {
//            System.out.println("🔒 Closing browser...");
//            driver.quit();
//        }
    }
    System.out.println("🏁 Test finished!");
}
}