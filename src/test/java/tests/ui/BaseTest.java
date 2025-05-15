package tests.ui;

import framework.browser.Browser;
import framework.config.Data;
import framework.utils.SmartLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.authorization.AuthorizationPageSteps;
import steps.launches.LaunchesPageSteps;

public class BaseTest {
    protected int step;
    public final String LOGIN = Data.getValue().login();
    public final String PASSWORD = Data.getValue().password();

    @BeforeMethod
    public void setup() {
        step = 0;

        Allure.step("Open Authorization page", () -> {
            SmartLogger.logStep(++step, "Open Authorization page");
            Browser.setMaximizeWindow();
            Browser.timeouts();
            Browser.openBaseUrl();
            AuthorizationPageSteps.assertIsOpen();
            Allure.addByteAttachmentAsync("Authorization page", "image/png", ".png",
                    Browser.getScreenshotBytes());
        });
    }

    @AfterMethod
    public void browserClose() {
        Allure.addByteAttachmentAsync("Before browser close", "image/png", ".png",
                Browser.getScreenshotBytes());
        Browser.quit();
    }

    @Step
    public void authorizationUser(String login, String password) {
        Allure.step("Authorization user", () -> {
            SmartLogger.logStep(++step, "Authorization user by login and password");
            AuthorizationPageSteps.authorizationLoginPassword(login, password);
            LaunchesPageSteps.assertIsOpen();

            Allure.addByteAttachmentAsync("User authorized", "image/png", ".png",
                    Browser.getScreenshotBytes());
        });
    }
}
