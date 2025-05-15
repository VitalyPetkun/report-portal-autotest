package framework.utils;

import framework.browser.Browser;
import framework.config.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaiterUtils {

    public static WebElement elementToBeClickable(By locator) {
        return new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(Data.getValue().timeout()))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean invisibilityOfElementLocated(By locator) {
        return new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(Data.getValue().timeout()))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement visibilityOfElementLocated(By locator) {
        return new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(Data.getValue().timeout()))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
