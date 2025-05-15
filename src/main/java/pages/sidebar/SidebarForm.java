package pages.sidebar;

import framework.elements.BaseElement;
import framework.elements.Button;
import framework.forms.BaseForm;
import org.openqa.selenium.By;

public abstract class SidebarForm extends BaseForm {
    private String DASHBOARDS_BTN = "//a[contains(@href,'dashboard')]/span";

    protected SidebarForm(BaseElement uniqElement, String formName) {
        super(uniqElement, formName);
    }

    public void clickDashboardsBtn() {
        getDashboardsBtn().click();
    }

    private Button getDashboardsBtn() {
        return new Button(By.xpath(DASHBOARDS_BTN), "'Dashboards' button");
    }
}
