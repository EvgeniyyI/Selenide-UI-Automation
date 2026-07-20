package config;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

public class TestConfig {

    @BeforeEach
    void setUp() {
        Configuration.browser = "edge";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "http://localhost:5173";
        Configuration.fastSetValue = true;
//       Configuration.savePageSource = true;
//       Configuration.screenshots = true;
//       Configuration.reportsFolder = "target/selenide-reports";
//
//        // Allure интеграция
//        SelenideLogger.addListener("AllureSelenide",
//                new AllureSelenide()
//                        .screenshots(true)
//                        .savePageSource(true))
//                        .includeSelenideSteps(true));

    }
}