package pages.dashboards.dashboard;

import framework.elements.Button;
import framework.elements.Label;
import framework.elements.Link;
import org.openqa.selenium.By;
import pages.sidebar.SidebarForm;

public class DashboardPage extends SidebarForm {
    private static final String ADD_NEW_WIDGET_BTN =
            "//*[local-name()='path' and @id=\"ic_add-wdgt-a\"]//ancestor::button";
    private final String WIDGET_NAME_LBL = "//div[text()='%s']";
    private final String DELETE_WIDGET_BTN = WIDGET_NAME_LBL.concat(
            "/ancestor::div[contains(@class,'info')]/following-sibling::div//div[contains(@class,'hide')][last()]");

    public DashboardPage() {
        super(new Button(By.xpath(ADD_NEW_WIDGET_BTN), "'Add new widget' button"),
                "Dashboard page");
    }

    public void setDashboardPageName(String dashboardName) {
        this.setFormName(dashboardName);
    }

    public void clickAddNewWidgetBtn() {
        getAddNewWidgetBtn().click();
    }

    public boolean isExistWidget(String widgetName) {
        return getWidgetNameLbl(widgetName).isVisible();
    }

    public boolean isNotExistWidget(String widgetName) {
        return getWidgetNameLbl(widgetName).isInvisible();
    }

    public DashboardPage focusWidgetLbl(String widgetName) {
        getWidgetNameLbl(widgetName).focus();
        return this;
    }

    public void clickDeleteWidgetBtn(String widgetName) {
        getDeleteWidgetBtn(widgetName).click();
    }

    private Link getAddNewWidgetBtn() {
        return new Link(By.xpath(ADD_NEW_WIDGET_BTN), "'Add new widget' button");
    }

    private Label getWidgetNameLbl(String widgetName) {
        return new Label(By.xpath(String.format(WIDGET_NAME_LBL, widgetName)), "'Widget name' label");
    }

    private Button getDeleteWidgetBtn(String widgetName) {
        return new Button(By.xpath(String.format(DELETE_WIDGET_BTN, widgetName)), "'Delete Widget' button");
    }
}
