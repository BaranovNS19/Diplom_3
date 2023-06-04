package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePassword {
    private WebDriver driver;
    //Кнопка "Войти"
    private final By signIn = By.xpath("//a[text()='Войти']");

    public RestorePassword(WebDriver driver) {
        this.driver = driver;
    }

    //Нажать на кнопку "Войти"
    @Step("Нажать на кнопку \"Войти\"")
    public void clickSignIn(){
        driver.findElement(signIn).click();
    }
}
