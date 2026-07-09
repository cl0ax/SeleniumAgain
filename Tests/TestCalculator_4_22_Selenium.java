import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.nio.file.Paths;

public class TestCalculator_4_22_Selenium {
    private static WebDriver driver;
    @BeforeAll
    static void setUpClass(){
        String geckoDriver = System.getProperty("user.dir")
                + File.separator + "drivers"
                + File.separator + "geckodriver";
        System.setProperty("webdriver.gecko.driver", geckoDriver);
    }
    @BeforeEach
    void setUp() {
        FirefoxBinary binary = new FirefoxBinary(
                new File("/Applications/Firefox.app/Contents/MacOS/firefox")
        );
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(binary);
        driver = new FirefoxDriver(options);
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
