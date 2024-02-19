package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    //Личный кабинет
    private final By personalArea = By.xpath("//p[text()='Личный Кабинет']");
    //Кнопка "Войти в аккаунт"
    private final By signIn = By.xpath("//button[text()='Войти в аккаунт']");
    //Элемент соберите бургер
    private final By assembleBurger = By.xpath("//h1[text()='Соберите бургер']");
    //Вкладка "Булки"
    private final By bunTab = By.xpath("//span[text()='Булки']/parent::*");
    //Заголовок "Булки"
    private final By bunHeader = By.xpath("//h2[text()='Булки']");
    //Вкладка "Соусы"
    private final By sauceTab = By.xpath("//span[text()='Соусы']");
    //Заголовок "Соусы"
    private final By sauceHeader = By.xpath("//h2[text()='Соусы']");
    //Вкладка "Начинки
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    //Заголовок "Начинки"
    private final By fillingsHeader = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание и нажатие на кнопку "Личный кабинет"
    @Step("Нажать кнопку \"Личный кабинет\"")
    public void clickPersonalArea(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(personalArea));
        driver.findElement(personalArea).click();

    }
    //Нажатие на кнопку
    @Step("Нажать кнопку \"Личный кабинет\"/\"Войти в аккаунт\"")
    public void clickButton(String xpath){
        driver.findElement(By.xpath(xpath)).click();
    }

    //Ожидание элемента "Соберите бургер"
    @Step("Ожидание элемента \"Соберите бургер\"")
    public void expectationAssembleBurger(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(assembleBurger));
    }

    //Нажатие на вкладку "Булки"
    @Step("Нажатие на вкладку \"Булки\"")
    public void clickBunTab(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(bunTab));
        driver.findElement(bunTab).click();
    }

    //Ожидание элемента заголовок "Булки"
    @Step("Ожидание элемента заголовок \"Булки\"")
    public void expectedBunHeader(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(bunHeader));
    }

    //Нажатие на вкладку "Соусы"
    @Step("Нажатие на вкладку \"Соусы\"")
    public void clickSauceTab(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(sauceTab));
        driver.findElement(sauceTab).click();
    }

    //Ожидание элемента заголовок "Соусы"
    @Step("Ожидание элемента заголовок \"Соусы\"")
    public void expectedSauceHeader(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(sauceHeader));
    }

    //Нажатие на вкладку "Начинки"
    @Step("Нажатие на вкладку \"Начинки\"")
    public void clickFillingsTab(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(fillingsTab));
        driver.findElement(fillingsTab).click();
    }

    //Ожидание элемента заголовок "Начинки"
    @Step("Ожидание элемента заголовок \"Начинки\"")
    public void expectedFillingsHeader(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(fillingsHeader));
    }


}
