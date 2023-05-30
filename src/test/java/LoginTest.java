import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.*;
import requestsAPI.model.User;
import requestsAPI.requests.UserClient;

@RunWith(Parameterized.class)
public class LoginTest {
    private WebDriver driver;
    private String name;
    private String email;
    private String password;
    private String xpathMain;
    private String xpathLogin;
    private UserClient userClient;
    private User user;
    private LoginPage loginPage;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private RestorePassword restorePassword;
    private PersonalArea personalArea;

    public LoginTest(String name, String email, String password, String xpathMain, String xpathLogin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.xpathMain = xpathMain;
        this.xpathLogin = xpathLogin;
    }

    @Parameterized.Parameters
    public static Object[][]getData(){
        return new Object[][]{
                //Вход через кнопку "Войти в аккаунт
                {"Nikolay", "qdfdsswew@example.com", "34582913", "//button[text()='Войти в аккаунт']", "//a[text()='Зарегистрироваться']"},
                //Вход через кнопку "Личный кабинет"
                {"Sergey", "sergey783@example.com", "238475621", "//p[text()='Личный Кабинет']", "//a[text()='Восстановить пароль']"}

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
        restorePassword = new RestorePassword(driver);
        userClient = new UserClient();
        personalArea = new PersonalArea(driver);
        user = new User(name, email, password);
        //Создание пользователя с помощью API запроса
        userClient.createUser(user);
    }

    @Test
    @DisplayName("Авторизация и выход пользователя из аккаунта")
    public void loginAndExitTest(){
        mainPage.clickButton(xpathMain);
        loginPage.expectedInput();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        mainPage.expectationAssembleBurger();
        mainPage.clickPersonalArea();
        personalArea.clickExit();
        loginPage.expectedInput();
        loginPage.clickButton(xpathLogin);

        if(xpathLogin.equals("//a[text()='Зарегистрироваться']")){
            registrationPage.expectationName();
            registrationPage.clickSignIn();
        } else if (xpathLogin.equals("//a[text()='Восстановить пароль']")) {
            restorePassword.clickSignIn();
        }

        loginPage.expectedInput();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        mainPage.expectationAssembleBurger();
        mainPage.clickPersonalArea();
        personalArea.clickExit();

    }


    @After
    public void close(){
        //Закрыть браузер
        driver.quit();
        //Удалить пользователя с помощью API запроса
            userClient.deleteUser(user);
    }
}
