import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

public class FireFoxSeleniumTest {

    public static void main(String[] args) {
        System.out.println("🚀 Starting Selenium Test with Firefox...");

        String geckoDriver = System.getProperty("user.dir")
                + File.separator + "drivers"
                + File.separator + "geckodriver";
        System.setProperty("webdriver.gecko.driver", geckoDriver);

        WebDriver driver = null;

        try {
            System.out.println("🦊 Opening Firefox browser...");
            FirefoxBinary binary = new FirefoxBinary(
                    new File("/Applications/Firefox.app/Contents/MacOS/firefox")
            );
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(binary);
            driver = new FirefoxDriver(options);

            System.out.println("🌐 Navigating to example.com...");
            driver.get("https://example.com");

            String title = driver.getTitle();
            System.out.println("📄 Page title: " + title);

            WebElement heading = driver.findElement(By.tagName("h1"));
            System.out.println("📝 Main heading: " + heading.getText());

            System.out.println("🔍 Finding links on the page...");
            WebElement link = driver.findElement(By.tagName("a"));
            System.out.println("📎 Found link: " + link.getText());

            System.out.println("⏳ Waiting 5 seconds...");
            Thread.sleep(5000);

            System.out.println("✅ Test completed successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error occurred: " + e.getMessage());
            System.err.println("💡 Make sure:");
            System.err.println("   1. Firefox is installed in /Applications/Firefox.app");
            System.err.println("   2. GeckoDriver is at: " + geckoDriver);
            System.err.println("   3. GeckoDriver is executable: chmod +x drivers/geckodriver");
            System.err.println("   4. Download from: https://github.com/mozilla/geckodriver/releases");
        } finally {
            if (driver != null) {
                System.out.println("🔒 Closing browser...");
                driver.quit();
            }
        }
        System.out.println("🏁 Test finished!");
    }
}