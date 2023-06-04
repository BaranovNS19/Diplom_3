import com.github.javafaker.Faker;
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
import setting.SettingProperty;

import java.io.IOException;

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
    private SettingProperty settingProperty;

    public LoginTest(String name, String email, String password, String xpathMain, String xpathLogin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.xpathMain = xpathMain;
        this.xpathLogin = xpathLogin;
    }

    @Parameterized.Parameters
    public static Object[][]getData(){
        Faker faker = new Faker();
        return new Object[][]{
                //Вход через кнопку "Войти в аккаунт
                {faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(), "//button[text()='Войти в аккаунт']", "//a[text()='Зарегистрироваться']"},
                //Вход через кнопку "Личный кабинет"
                {faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(), "//p[text()='Личный Кабинет']", "//a[text()='Восстановить пароль']"}

        };
    }

    @Before
    public void openBrowser() throws IOException {
        settingProperty = new SettingProperty();
        System.setProperty("webdriver.chrome.driver", settingProperty.getDriverPath());
        driver = new ChromeDriver();
        driver.get(settingProperty.getPropertyUrl());
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
