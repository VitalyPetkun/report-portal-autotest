package framework.browser;

import com.google.common.io.Files;
import framework.config.Data;
import framework.utils.SmartLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;

public class Browser {

    private static WebDriver driver;

    private Browser() {
        try {
            driver = setupDriver(Data.getValue().browser());
        } catch (Exception ex) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--incognito");
            driver = new ChromeDriver(chromeOptions);
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            new Browser();
        }

        return driver;
    }

    public static void openBaseUrl() {
        openUrl(Data.getValue().baseUrl());
    }

    public static void openUrl(String url) {
        getDriver().get(url);
    }

    public static void quit() {
        getDriver().manage().deleteAllCookies();
        getDriver().quit();
        driver = null;
    }

    public static void setMaximizeWindow() {
        getDriver().manage().window().maximize();
    }

    public static void timeouts() {
        getDriver().manage().timeouts();
    }

    public static Supplier<byte[]> getScreenshotBytes() {
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        return screenshot == null ? null : () -> {
            try {
                return Files.toByteArray(screenshot);
            } catch (IOException | NullPointerException e) {
                SmartLogger.logError("ERROR: ".concat(e.toString()));
                throw new RuntimeException(e);
            }
        };
    }

    private WebDriver setupDriver(String browserName) throws Exception {
        WebDriver driver;
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--incognito");
                driver = new EdgeDriver(edgeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--incognito");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                SmartLogger.logError("ERROR: Incorrect browser name.");
                throw new Exception("Incorrect browser name.");
        }

        return driver;
    }
}
