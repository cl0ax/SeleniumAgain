import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCalculator_4_22_Selenium {
    private static WebDriver driver;
    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        System.out.printf("\nBefore each running");
    }
    @Test
    public void TestCalculator() {
        System.out.printf("\n Starting");
        //WebDriver driver = new FirefoxDriver();
        final String URL = "file://" + System.getProperty("user.dir") + "/simpleCaculator.html";
        try {
            driver.get(URL);
            System.out.printf("\n Browser Opened");
        } catch ( Exception e){
            System.out.printf("\n Exception on Start msg=%s", e.getMessage());
        } finally {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.printf("\n Exception on Sleep msg=%s", e.getMessage());
                e.printStackTrace();
            }
            driver.quit();
        }

    }
}
