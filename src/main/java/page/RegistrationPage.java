package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;
    //Кнопка "Зарегестрироваться"
    private final By register = By.xpath("//button[text()='Зарегистрироваться']");
    //Поле "Имя"
    private final By name = By.xpath("//label[text()='Имя']/following-sibling::input[@name='name']");
    //Поле "Email"
    private final By email = By.xpath("//label[text()='Email']/following-sibling::input[@name='name']");
    //Поле "Пароль"
    private final By password = By.xpath("//input[@name='Пароль']");
    //Уведомление "Некорректный пароль"
    private final By incorrectPassword = By.xpath("//p[text()='Некорректный пароль']");
    //Кнопка "Войти"
    private final By signIn = By.xpath("//a[text()='Войти']");
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //Заполнить поле "Имя"
    @Step("Заполнить поле \"Имя\"")
    public void enterName(String nameText){
        driver.findElement(name).sendKeys(nameText);
    }
    //Заполнить поле "Email"
    @Step("Заполнить поле \"Email\"")
    public void enterEmail(String emailText){
        driver.findElement(email).sendKeys(emailText);
    }
    //Заполнить поле "Пароль"
    @Step("Заполнить поле \"Пароль\"")
    public void enterPassword(String passwordText){;
        driver.findElement(password).sendKeys(passwordText);
    }

    //Заполнить обязательные поля для регистрации пользователя
    @Step("Заполнить обязательные поля для регистрации пользователя")
    public void registerUser(String nameText, String emailText, String passwordText){
        enterName(nameText);
        enterEmail(emailText);
        enterPassword(passwordText);
    }

    //Нажать на кнопку "Зарегестрироваться"
    @Step("Нажать на кнопку \"Зарегестрироваться\"")
    public void clickRegister(){
        driver.findElement(register).click();
    }

    //Нажать кнопку "Войти"
    @Step("Нажать кнопку \"Войти\"")
    public void clickSignIn(){
        driver.findElement(signIn).click();
    }

    //Ожидание элемента "Имя"
    @Step("Ожидание элемента \"Имя\"")
    public void expectationName(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(name));
    }

    //Ожидание элемента "Некорректный пароль"
    @Step("Ожидание элемента \"Некорректный пароль\"")
    public void expectationIncorrectPassword(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(incorrectPassword));
    }
}
