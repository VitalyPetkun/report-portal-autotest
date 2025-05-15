package pages.dashboards.dashboard;

import framework.elements.Button;
import framework.forms.BaseForm;
import org.openqa.selenium.By;

public class DeleteWidgetForm extends BaseForm {
    private static final String DELETE_BTN = "//div[contains(@class,'block')]/div[last()]//button";

    public DeleteWidgetForm() {
        super(new Button(By.xpath(DELETE_BTN), "'Delete' button"), "Delete widget form");
    }

    public void clickDeleteBtn() {
        getDeleteBtn().click();
    }

    private Button getDeleteBtn() {
        return new Button(By.xpath(DELETE_BTN), "'Delete' button");
    }
}
