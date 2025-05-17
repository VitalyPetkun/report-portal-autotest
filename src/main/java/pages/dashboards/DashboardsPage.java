package pages.dashboards;

import framework.elements.Button;
import framework.elements.Link;
import org.openqa.selenium.By;
import pages.sidebar.SidebarForm;

public class DashboardsPage extends SidebarForm {
    private static final String ADD_NEW_DASHBOARDS_BTN =
            "//div[starts-with(@class,'addDashboardButton')]//button";
    private final String FIRST_DASHBOARD_LNK =
            "//div[contains(@class,'table')]/div[contains(@class,'row')][1]//a[contains(@href,'dashboard')]";

    public DashboardsPage() {
        super(new Button(By.xpath(ADD_NEW_DASHBOARDS_BTN), "'Add new dashboard' button"),
                "Dashboards page");
    }

    public void clickFirstDashboardLnk() {
        getFirstDashboardLnk().click();
    }

    public String getFirstDashboardNameLnk() {
        return getFirstDashboardLnk().getText();
    }

    public boolean isExistDashboard() {
        return getFirstDashboardLnk().isVisible();
    }

    public void clickAddNewDashboardBtn() {
        getAddNewDashboardBtn().click();
    }

    private Link getFirstDashboardLnk() {
        return new Link(By.xpath(FIRST_DASHBOARD_LNK), "'First dashboard' link");
    }

    private Button getAddNewDashboardBtn() {
        return new Button(By.xpath(ADD_NEW_DASHBOARDS_BTN), "'Add new dashboard' button");
    }
}
