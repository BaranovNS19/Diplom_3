import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;

public class СonstructorTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nikol\\Documents\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переключение ингредиентов в конструкторе")
    public void constructorRedirectTest(){
        mainPage.expectationAssembleBurger();
        mainPage.clickFillingsTab();
        mainPage.expectedFillingsHeader();
        mainPage.clickBunTab();
        mainPage.expectedBunHeader();
        mainPage.clickSauceTab();
        mainPage.expectedSauceHeader();
    }

    @After
    public void close(){
        driver.quit();
    }
}
