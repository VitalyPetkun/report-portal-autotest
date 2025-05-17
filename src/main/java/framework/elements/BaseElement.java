package framework.elements;

import framework.browser.Browser;
import framework.utils.SmartLogger;
import framework.utils.WaiterUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseElement {

    private By locator;

    private String elementName;

    protected BaseElement(By locator, String elementName) {
        this.locator = locator;
        this.elementName = elementName;
    }

    public void click() {
        SmartLogger.logInfo("Click ".concat(elementName));
        try {
            WebElement element = WaiterUtils.elementToBeClickable(locator);
            ((JavascriptExecutor) Browser.getDriver())
                    .executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (Exception | Error ex) {
            SmartLogger.logError(
                    "Don't click ".concat(elementName).concat(" with locator: ").concat(locator.toString()));
        }
    }

    public void clickAndClear() {
        this.click();
        this.findElement().clear();
    }

    public boolean isPresent() {
        return this.findElements().size() > 0;
    }

    public boolean isVisible() {
        return WaiterUtils.visibilityOfElementLocated(locator) != null;
    }

    public boolean isInvisible() {
        return WaiterUtils.invisibilityOfElementLocated(locator);
    }

    public void focus() {
        SmartLogger.logInfo("Focus ".concat(elementName));
        try {
            WebElement element = WaiterUtils.visibilityOfElementLocated(locator);
            ((JavascriptExecutor) Browser.getDriver())
                    .executeScript("arguments[0].scrollIntoView(true);", element);
            new Actions(Browser.getDriver()).moveToElement(element).perform();
        } catch (Exception | Error ex) {
            SmartLogger.logError(
                    "Don't focus ".concat(elementName).concat(" with locator: ").concat(locator.toString()));
        }
    }

    public String getText() {
        return this.findElement().getText();
    }

    public String getElementName() {
        return elementName;
    }

    public By getLocator() {
        return locator;
    }

    protected WebElement findElement() {
        WebElement element = null;
        try {
            element = WaiterUtils.elementToBeClickable(locator);
        } catch (Exception | Error ex) {
            SmartLogger.logError(
                    "Don't find ".concat(elementName).concat(" with locator: ").concat(locator.toString()));
        }

        return element;
    }

    protected List<WebElement> findElements() {
        List<WebElement> elements = new ArrayList<>();
        try {
            elements = Browser.getDriver().findElements(locator);
        } catch (Exception | Error ex) {
            SmartLogger.logError(
                    "Don't find ".concat(elementName).concat(" with locator: ").concat(locator.toString()));
        }

        return elements;
    }
}
