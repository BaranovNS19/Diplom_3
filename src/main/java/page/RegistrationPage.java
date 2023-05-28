package page;

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
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //Заполнение обязательных полей для регистрации
    public void registerUser(String nameText, String emailText, String passwordText){
        driver.findElement(name).sendKeys(nameText);
        driver.findElement(email).sendKeys(emailText);
        driver.findElement(password).sendKeys(passwordText);
    }

    //Нажатие на кнопку "Зарегестрироваться"
    public void clickRegister(){
        driver.findElement(register).click();
    }

    //Ожидание элемента "Имя"
    public void expectationName(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(name));
    }

    //Ожидание элемента "Некорректный пароль"
    public void expectationIncorrectPassword(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(incorrectPassword));
    }
}
