package framework.elements;

import framework.utils.SmartLogger;
import org.openqa.selenium.By;

public class TextBox extends BaseElement {

    public TextBox(By locator, String elementName) {
        super(locator, elementName);
    }

    public void inputText(String text) {
        this.clickAndClear();
        SmartLogger.logInfo("Input ".concat(this.getElementName()));
        this.findElement().sendKeys(text);
    }
}
