package pages.dashboards.dashboard.widgetform;

import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import framework.forms.BaseForm;
import org.openqa.selenium.By;

public class WidgetForm extends BaseForm {
    private static final String WIDGET_INFO_BLOCK = "//div[contains(@class,'edit-widget')]";
    private final String WIDGET_TYPE = "//input[@value='%s']/parent::label"; //overallStatistics
    private final String FILTER = "//label//span[normalize-space()='%s']/span"; //DEMO_FILTER
    private final String WIDGET_NAME = "//div[@data-automation-id]//input";
    private final String NEXT_STEP_BTN =
            "//div[contains(@class,'block')]/div[last()]/button[not(contains(@class,'big'))]";
    private final String ADD_BTN = "//div[contains(@class,'block')]/div[last()]/button[contains(@class,'big')]";

    public WidgetForm() {
        super(new Label(By.xpath(WIDGET_INFO_BLOCK), "'Wwidget info block' label"), "Wigdet form");
    }

    public WidgetForm clickWidgetTypeLbl(String widgetTypeValue) {
        getWidgetTypeLbl(widgetTypeValue).click();
        return this;
    }

    public WidgetForm clickFilterLbl(String filterName) {
        getFilterLbl(filterName).click();
        return this;
    }

    public WidgetForm inputWidgetNameTxtBox(String widgetName) {
        getWidgetNameTxtBox().inputText(widgetName);
        return this;
    }

    public WidgetForm clickNextStepBtn() {
        getNextStepBtn().click();
        return this;
    }

    public void clickAddBtn() {
        getAddBtn().click();
    }

    private Label getWidgetTypeLbl(String widgetTypeValue) {
        return new Label(By.xpath(String.format(WIDGET_TYPE, widgetTypeValue)),
                widgetTypeValue.concat(" type label"));
    }

    private Label getFilterLbl(String filterName) {
        return new Label(By.xpath(String.format(FILTER, filterName)), filterName.concat(" filter label"));
    }

    private TextBox getWidgetNameTxtBox() {
        return new TextBox(By.xpath(WIDGET_NAME), "'Widget name' text box");
    }

    private Button getNextStepBtn() {
        return new Button(By.xpath(NEXT_STEP_BTN), "'Next step' button");
    }

    private Button getAddBtn() {
        return new Button(By.xpath(ADD_BTN), "'Add' button");
    }
}
