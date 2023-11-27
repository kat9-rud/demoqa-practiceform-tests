package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

import java.util.Map;

public class TestBase {
    RegistrationPage page = new RegistrationPage();
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = System.getProperty("browser", "");
        Configuration.browserSize = System.getProperty("resolution", "");
        Configuration.baseUrl = "https://demoqa.com";

        Configuration.browserVersion = System.getProperty("browser_version", "");
        String login = System.getProperty("remote_login", "");
        String pass = System.getProperty("remote_pass", "");
        String remote = System.getProperty("remote", "");

        Configuration.remote =  remote.replace("//", "//" + login + ":" + pass + "@") + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
