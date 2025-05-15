package steps.dashbords;

import framework.utils.DataGenerator;
import org.testng.Assert;
import pages.dashboards.DashboardsPage;

public class DashboardsPageSteps {
    private static final DashboardsPage dashboardsPage = new DashboardsPage();

    private DashboardsPageSteps() {
    }

    public static String openFirstDashboardPage() {
        String dashboardName = "";

        if (dashboardsPage.isExistDashboard()) {
            dashboardsPage.clickFirstDashboardLnk();
        } else {
            dashboardName = DataGenerator.getTitle();
            addNewDashboard(dashboardName);
        }

        return dashboardName;
    }

    public static void addNewDashboard(String dashboardName) {
        dashboardsPage.clickAddNewDashboardBtn();
        DashboardFormSteps.assertIsOpen();
        DashboardFormSteps.addNewDashboard(dashboardName);
    }

    public static void assertIsOpen() {
        Assert.assertTrue(dashboardsPage.isFormOpen(), dashboardsPage.getFormName().concat(" isn't open"));
    }
}
