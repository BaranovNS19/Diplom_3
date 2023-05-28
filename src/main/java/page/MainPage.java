package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    //Личный кабинет
    private final By personalArea = By.xpath("//p[text()='Личный Кабинет']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание и нажатие на кнопку "Личный кабинет"
    public void clickPersonalArea(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(personalArea));
        driver.findElement(personalArea).click();

    }


}
