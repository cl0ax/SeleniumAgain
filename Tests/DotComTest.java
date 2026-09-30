import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DotComTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        System.out.printf("\nBefore each running");
    }

    @Test
    @DisplayName("Page title should be Example Domain")
    void pageTitle_shouldBe_Example() {
        String url = "http://example.com";
        System.out.printf("\nRunning example test");

        driver.get(url);
        String actualTitle = driver.getTitle();
        String expected = "Example Domain";

        assertEquals(expected, actualTitle);
    }

    @Test
    @DisplayName("Main Title is 'Example Domain")
    void mainTitle_shouldBe_Example() {
        // practicing getting element by tagName in java
        String url = "http://example.com";
        driver.get(url);

        WebElement heading = driver.findElement(By.tagName("h1"));
        String headingText = heading.getText();

        System.out.printf("\n H1 Test is :%s ", headingText);

        assertEquals(headingText, "Example Domain");
    }

    @Test
    @DisplayName("Check link goes to java.org")
    void mainLink_showBe_javaOrg() {
        String url = "http://example.com";
        driver.get(url);

        WebElement link = driver.findElement(By.tagName("a"));
        String href = link.getAttribute("href");

        System.out.printf("\n href Test is :%s ", href);

        assertTrue(href.contains("iana.org"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
