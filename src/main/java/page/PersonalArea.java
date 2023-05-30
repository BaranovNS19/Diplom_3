package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalArea {
    private WebDriver driver;

    //Кнопка "Выход"
    private final By exit = By.xpath("//button[text()='Выход']");
    //Логотип Stellar Burgers
    private final By logoStellarBurgers = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    //Кнопка "Конструктор"
    private final By constructor = By.xpath("//p[text()='Конструктор']");
    public PersonalArea(WebDriver driver) {
        this.driver = driver;
    }

    //Нажать "Выход"
    @Step("Нажать \"Выход\"")
    public void clickExit(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(exit));
        driver.findElement(exit).click();
    }

    //Ожидание элемента "Выход"
    @Step("Ожидание элемента \"Выход\"")
    public void expectedExit(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(exit));
    }

    //Нажать на кнопку "Конструктор"
    @Step("Нажать на кнопку \"Конструктор\"")
    public void clickConstructor(){
        driver.findElement(constructor).click();
    }

    //Нажать на логотип Stellar Burgers
    @Step("Нажать на логотип Stellar Burgers")
    public void clickLogo(){
        driver.findElement(logoStellarBurgers).click();
    }
}
