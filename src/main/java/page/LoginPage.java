package page;

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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание заголовка "Вход"
    public void expectedInput(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(headerInput));
    }

    //Нажать кнопку "Зарегестрироваться" для перехода на форму регистрации
    public void clickRegisterRedirect(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(register));
        driver.findElement(register).click();
    }
}
