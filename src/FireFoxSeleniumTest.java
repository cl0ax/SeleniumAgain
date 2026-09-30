import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxSeleniumTest {

    public static void main(String[] args) {
        System.out.println("🚀 Starting Selenium Test with Firefox...");

        WebDriver driver = null;

        try {
            System.out.println("🦊 Opening Firefox browser...");
            driver = new FirefoxDriver();

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
            System.err.println("   1. Firefox is installed");
            System.err.println("   2. Selenium Manager can locate or download geckodriver");
        } finally {
            if (driver != null) {
                System.out.println("🔒 Closing browser...");
                driver.quit();
            }
        }
        System.out.println("🏁 Test finished!");
    }
}
