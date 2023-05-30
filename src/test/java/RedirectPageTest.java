import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.LoginPage;
import page.MainPage;
import page.PersonalArea;
import requestsAPI.model.User;
import requestsAPI.requests.UserClient;

@RunWith(Parameterized.class)
public class RedirectPageTest {
    private WebDriver driver;
    private String name;
    private String email;
    private String password;
    private MainPage mainPage;
    private LoginPage loginPage;
    private User user;
    private UserClient userClient;
    private PersonalArea personalArea;

    public RedirectPageTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][]getData(){
        return new Object[][]{
                {"Nikolay", "testUser4321@example.com", "3456287213"}

        };
    }

    @Before
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nikol\\Documents\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        userClient = new UserClient();
        personalArea = new PersonalArea(driver);
        user = new User(name, email, password);
        //Создание пользователя с помощью API запроса
        userClient.createUser(user);
    }

    @Test
    @DisplayName("Переход пользователя из личного кабинета на главную")
    public void redirectPageTest(){
        mainPage.clickPersonalArea();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        mainPage.expectationAssembleBurger();
        mainPage.clickPersonalArea();
        personalArea.expectedExit();
        personalArea.clickConstructor();
        mainPage.expectationAssembleBurger();
        mainPage.clickPersonalArea();
        personalArea.expectedExit();
        personalArea.clickLogo();
        mainPage.expectationAssembleBurger();

    }

    @After
    public void close(){
        //Закрыть браузер
        driver.quit();
        //Удалить пользователя с помощью API запроса
        userClient.deleteUser(user);
    }
}
