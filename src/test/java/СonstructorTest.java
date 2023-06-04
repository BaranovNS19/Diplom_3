import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;
import setting.SettingProperty;

import java.io.IOException;

public class СonstructorTest {
    private WebDriver driver;
    private MainPage mainPage;
    private SettingProperty settingProperty;

    @Before
    public void openBrowser() throws IOException {
        settingProperty = new SettingProperty();
        System.setProperty("webdriver.chrome.driver", settingProperty.getDriverPath());
        driver = new ChromeDriver();
        driver.get(settingProperty.getPropertyUrl());
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переключение ингредиентов в конструкторе")
    public void constructorRedirectTest(){
        mainPage.expectationAssembleBurger();
        mainPage.clickFillingsTab();
        mainPage.expectedFillingsHeader();
        mainPage.clickBunTab();
        mainPage.expectedBunHeader();
        mainPage.clickSauceTab();
        mainPage.expectedSauceHeader();
    }

    @After
    public void close(){
        driver.quit();
    }
}
