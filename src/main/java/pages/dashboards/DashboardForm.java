package pages.dashboards;

import framework.elements.Button;
import framework.elements.TextBox;
import framework.forms.BaseForm;
import org.openqa.selenium.By;

public class DashboardForm extends BaseForm {
    private static final String DASHBOARD_NAME_TXT_BOX = "//div[@data-automation-id]/input";
    private final String ADD_BTN = "//div[contains(@class,'block')]//div[last()]//button[not(contains(@class,'Edit'))]";

    public DashboardForm() {
        super(new TextBox(By.xpath(DASHBOARD_NAME_TXT_BOX), "'Dashboard name' text box"),
                "Dashboard form");
    }

    public DashboardForm inputDashboardName(String dashboardName) {
        getDashboardNameTxtBox().inputText(dashboardName);
        return this;
    }

    public void clickAddBtn() {
        getAddBtn().click();
    }

    private TextBox getDashboardNameTxtBox() {
        return new TextBox(By.xpath(DASHBOARD_NAME_TXT_BOX), "'Dashboard name' text box");
    }

    private Button getAddBtn() {
        return new Button(By.xpath(ADD_BTN), "'Add' button");
    }
}
