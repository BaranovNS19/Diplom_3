package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    //"Зарегестрироваться"
    private final By register = By.xpath("//a[text()='Зарегистрироваться']");
    //Заголовок "Вход"
    private final By headerInput = By.xpath("//h2[text()='Вход']");
    //Поле "Email"
    private final By email = By.xpath("//label[text()='Email']/following-sibling::input[@name='name']");
    //Поле "Пароль"
    private final By password = By.xpath("//input[@name='Пароль']");
    //Кнопка "Войти"
    private final By signIn = By.xpath("//button[text()='Войти']");
    //Кнопка "Восстановить пароль"
    private final By restorePassword = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание заголовка "Вход"
    @Step("Ожидание заголовка \"Вход\"")
    public void expectedInput(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(headerInput));
    }

    //Нажать кнопку "Зарегестрироваться" для перехода на форму регистрации
    @Step("Нажать кнопку \"Зарегестрироваться\" для перехода на форму регистрации")
    public void clickRegisterRedirect(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(register));
        driver.findElement(register).click();
    }

    //Нажать на кнопку "Восстановить пароль"
    @Step("Нажать на кнопку \"Восстановить пароль\"")
    public void clickRestorePassword(){
        driver.findElement(restorePassword).click();
    }

    //Заполнение поля "Email"
    @Step("Заполнить поле \"Email\"")
    public void enterEmail(String emailText){
        driver.findElement(email).sendKeys(emailText);
    }
    //Заполнение поля "Пароль"
    @Step("Заполнить поле \"Пароль\"")
    public void enterPassword(String passwordText){;
        driver.findElement(password).sendKeys(passwordText);
    }


    //Нажать кнопку "Войти"
    @Step("Нажать кнопку \"Войти\"")
    public void clickSignIn(){
        driver.findElement(signIn).click();
    }
    //Нажать кнопку
    @Step("Нажать кнопку \"Зарегестрироваться\"/\"Восстановить пароль\"")
    public void clickButton(String xpath){
        driver.findElement(By.xpath(xpath)).click();
    }
}
