import com.github.javafaker.Faker;
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
import page.RegistrationPage;
import requestsAPI.model.User;
import requestsAPI.requests.UserClient;
import setting.SettingProperty;

import java.io.IOException;

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
    private SettingProperty settingProperty;

    public RegisterTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][]getData(){
        Faker faker = new Faker();
        return new Object[][]{
                //Пароль 5 символов
                {faker.name().firstName(), faker.internet().emailAddress(), faker.regexify("[A-Za-z0-9]{5}")},
                //Пароль 6 символов
                {faker.name().firstName(), faker.internet().emailAddress(), faker.regexify("[A-Za-z0-9]{6}")},
                //Пароль 7 символов
                {faker.name().firstName(), faker.internet().emailAddress(), faker.regexify("[A-Za-z0-9]{7}")},
                //Пароль 2 символа
                {faker.name().firstName(), faker.internet().emailAddress(), faker.regexify("[A-Za-z0-9]{2}")},
                //Пароль 10 символов
                {faker.name().firstName(), faker.internet().emailAddress(), faker.regexify("[A-Za-z0-9]{10}")}

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

    }
    @Test
    @DisplayName("Регистрация нового пользователя")
    public void registerUserTest(){
        mainPage.clickPersonalArea();
        loginPage.clickRegisterRedirect();
        registrationPage.expectationName();
        registrationPage.registerUser(name, email, password);
        registrationPage.clickRegister();
        if(password.length() < 6) {
            //Ожидание уведомления "Некорректный пароль"
            registrationPage.expectationIncorrectPassword();
        }else {
            loginPage.expectedInput();
        }
    }

    @After
    public void close(){
        //Закрыть браузер
        driver.quit();
        //Удалить пользователя с помощью API запроса
        if (password.length() >= 6) {
            user = new User(name, email, password);
            userClient = new UserClient();
            userClient.deleteUser(user);
        }
    }
}
