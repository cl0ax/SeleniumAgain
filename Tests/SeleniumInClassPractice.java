
import org.junit.jupiter.api.*;
        import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Selenium Practice Tests - CSC3510
 * Complete the TODO sections to practice Selenium commands
 */
public class SeleniumInClassPractice {

    private static WebDriver driver;
    private static final String PRACTICE_PAGE = "http://45.55.136.114/~dlash/CSC3510/seleniumPractice.html";

    @BeforeAll
    static void setupClass() {
        System.setProperty("webdriver.gecko.driver",
                System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
    }

    @BeforeEach
    void setup() {
        driver = new FirefoxDriver();
        driver.get(PRACTICE_PAGE);
    }

    // ============================================
    // TEST 1: Getting Page Information
    // ============================================
    @Test
    @DisplayName("Test 1: Verify page title")
    void test01_PageTitle() {
        // TODO: Get the page title using driver.getTitle()

        // TODO: Assert that the title contains "Selenium Practice"
    }

    // ============================================
    // TEST 2: Finding Element by ID
    // ============================================
    @Test
    @DisplayName("Test 2: Find heading by ID and verify text")
    void test02_FindHeadingById() {
        // TODO: Find the element with id="page-title" using By.id()

        // TODO: Get the text from the heading using .getText()


        // TODO: Assert that heading text equals "Selenium Practice Page"
    }

    // ============================================
    // TEST 3: Finding Element by Tag Name
    // ============================================
    @Test
    @DisplayName("Test 3: Find paragraph and verify it contains CSC3510")
    void test03_FindParagraphByTag() {
        // TODO: Find the first <p> tag using By.tagName("p")

        // TODO: Get its text

        // TODO: Assert the text contains "CSC3510"
    }

    // ============================================
    // TEST 4: Typing Text into Input Field
    // ============================================
    @Test
    @DisplayName("Test 4: Type text into username field")
    void test04_TypeIntoField() {
        // TODO: Find the input field with id="username"

        // TODO: Type "testuser" into the field using .sendKeys()

        // TODO: Get the value attribute to verify what was typed

        // TODO: Assert that the value is "testuser"
    }

    // ============================================
    // TEST 5: Clicking a Button
    // ============================================
    @Test
    @DisplayName("Test 5: Click submit button and verify message appears")
    void test05_ClickButton() {
        // TODO: Find the button with id="submit-btn"

        // TODO: Click the button using .click()

        // TODO: Find the result message with id="result-message"

        // TODO: Assert that the message is displayed using .isDisplayed()
    }

    // ============================================
    // TEST 6: Getting Link Information
    // ============================================
    @Test
    @DisplayName("Test 6: Verify link destination")
    void test06_CheckLinkHref() {
        // TODO: Find the link with id="aurora-link"

        // TODO: Get the "href" attribute using .getAttribute("href")

        // TODO: Assert that the href contains "aurora.edu"
    }

    // ============================================
    // STRETCH 1: Finding Multiple Elements
    // ============================================
    @Test
    @DisplayName("STRETCH 1: Find all elements with class name")
    void stretch01_FindMultipleElements() {
        // TODO: Find ALL elements with class="bonus-text" using findElements() (plural!)
        // Hint: Use driver.findElements() which returns a List<WebElement>

        // TODO: Get the size of the list


        // TODO: Assert that you found exactly 2 paragraphs

        // BONUS: Print the text of each paragraph
    }

    // ============================================
    // STRETCH 2: Working with Dropdowns
    // ============================================
    @Test
    @DisplayName("STRETCH 2: Select option from dropdown")
    void stretch02_SelectFromDropdown() {
        // TODO: Find the dropdown with id="course-dropdown"

        // TODO: Create a Select object from the dropdown
        // Hint: Select dropdown = new Select(dropdownElement);

        // TODO: Select the option with value="csc3510" using .selectByValue()

        // TODO: Get the selected option using .getFirstSelectedOption()

        // TODO: Get the text of the selected option


        // TODO: Assert that the selected text contains "CSC3510"
    }

    // ============================================
    // STRETCH 3: Working with Checkboxes
    // ============================================
    @Test
    @DisplayName("STRETCH 3: Click checkbox and verify state")
    void stretch03_CheckboxClick() {
        // TODO: Find the checkbox with id="agree-checkbox"

        // TODO: Check if it's selected initially using .isSelected()

        // TODO: Assert that it starts as NOT selected (false)

        // TODO: Click the checkbox

        // TODO: Check if it's selected now

        // TODO: Assert that it's now selected (true)
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}