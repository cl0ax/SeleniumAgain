import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DotComTest {
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
        options.setBinary(binary);    private static WebDriver driver;
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

        driver = new FirefoxDriver(options);
        System.out.printf("\nBefore each running");
    }
    @Test
    @DisplayName("Page title should be Example Domain")
    void pageTitle_shouldBe_Example(){
        String url = "http://example.com";
        System.out.printf("\nRunning example test");

        driver.get(url);
        String actualTitle = driver.getTitle();
        String expected = "Example Domain";

        assertEquals(expected,actualTitle);

    }
    @Test
    @DisplayName("Main Title is 'Example Domain")
    void mainTitle_shouldBe_Example(){
        //practicing getting element by tagName in java
        String url = "http://example.com";
        driver.get(url);

        WebElement heading = driver.findElement(By.tagName("h1"));

        String headingText = heading.getText();

        System.out.printf("\n H1 Test is :%s ", headingText);

        assertEquals(headingText, "Example Domain");
    }

    @Test
    @DisplayName("Check link goes to java.org")
    void mainLink_showBe_javaOrg(){
        String url = "http://example.com";
        driver.get(url);

        WebElement link = driver.findElement(By.tagName("a"));

        String href = link.getAttribute("href");

        System.out.printf("\n href Test is :%s ", href);

        assertTrue(href.contains("iana.org"));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

}