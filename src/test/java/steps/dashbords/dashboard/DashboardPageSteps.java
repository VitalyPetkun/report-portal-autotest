package steps.dashbords.dashboard;

import org.testng.Assert;
import pages.dashboards.dashboard.DashboardPage;

public class DashboardPageSteps {
    private static final DashboardPage dashboardPage = new DashboardPage();

    private DashboardPageSteps() {
    }

    public static void setDashboardPageName(String dashboardName) {
        dashboardPage.setDashboardPageName(dashboardName);
    }

    public static void openAddNewWidgetForm() {
        dashboardPage.clickAddNewWidgetBtn();
    }

    public static void deleteWidget(String widgetName) {
        dashboardPage
                .focusWidgetLbl(widgetName)
                .clickDeleteWidgetBtn(widgetName);
    }

    public static void assertIsExistWidget(String widgetName) {
        dashboardPage.scrollUp();
        dashboardPage.scrollDown();
        Assert.assertTrue(dashboardPage.isExistWidget(widgetName), widgetName.concat(" widget didn't found"));
    }

    public static void assertIsNotExistWidget(String widgetName) {
        dashboardPage.scrollUp();
        dashboardPage.scrollDown();
        Assert.assertTrue(dashboardPage.isNotExistWidget(widgetName), widgetName.concat(" widget exist"));
    }

    public static void assertIsOpen() {
        Assert.assertTrue(dashboardPage.isFormOpen(), dashboardPage.getFormName().concat(" isn't open"));
    }
}
