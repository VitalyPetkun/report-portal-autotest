package framework.forms;

import framework.browser.Browser;
import framework.elements.BaseElement;
import framework.utils.SmartLogger;
import org.openqa.selenium.JavascriptExecutor;

public abstract class BaseForm {

    private BaseElement uniqElement;

    private String formName;

    protected BaseForm(BaseElement uniqElement, String formName) {
        this.uniqElement = uniqElement;
        this.formName = formName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public void scrollDown() {
        ((JavascriptExecutor) Browser.getDriver())
                .executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollUp() {
        ((JavascriptExecutor) Browser.getDriver())
                .executeScript("window.scrollBy(0,-document.body.scrollHeight)");
    }

    public boolean isFormOpen() {
        if (uniqElement.isVisible()) {
            SmartLogger.logInfo("Open ".concat(formName));
        }

        return uniqElement.isVisible();
    }
}
