import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.LoginPage;
import page.MainPage;
import page.RegistrationPage;
import requestsAPI.model.User;
import requestsAPI.requests.UserClient;

@RunWith(Parameterized.class)
public class RegisterTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private UserClient userClient;
    private User user;

    private String name;
    private String email;
    private String password;

    public RegisterTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][]getData(){
        return new Object[][]{

        };
    }


    @Before
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nikol\\Documents\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

    }
    @Test
    public void registerUserTest(){
        mainPage.clickPersonalArea();
        loginPage.clickRegisterRedirect();
        registrationPage.expectationName();
        registrationPage.registerUser(name, email, password);
        registrationPage.clickRegister();
        loginPage.expectedInput();
    }

    @After
    public void close(){
        //Закрыть браузер
        driver.quit();
        //Удалить пользователя с помощью API запроса
        user = new User(name, email, password);
        userClient = new UserClient();
        userClient.deleteUser(user);
    }
}
