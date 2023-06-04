package setting;

import io.qameta.allure.Step;

import java.io.IOException;

public class SettingProperty {
    private String urlProperty;
    private String driverPath;

    @Step("Получить URL")
    public String getPropertyUrl() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        urlProperty = System.getProperty("url");
        return urlProperty;
    }

    @Step("Получить путь к вебдрайверу")
    public String getDriverPath() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        driverPath = System.getProperty("driverPath");
        return driverPath;
    }
}
