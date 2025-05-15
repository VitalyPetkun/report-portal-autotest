package framework.forms;

import framework.elements.BaseElement;
import framework.utils.SmartLogger;
import framework.utils.WaiterUtils;

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

    public boolean isFormOpen() {
        if (WaiterUtils.visibilityOfElementLocated(uniqElement.getLocator()).isDisplayed()) {
            SmartLogger.logInfo("Open ".concat(formName));
        }

        return uniqElement.isPresent();
    }
}
