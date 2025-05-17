package steps.dashbords;

import org.testng.Assert;
import pages.dashboards.DashboardForm;

public class DashboardFormSteps {
    private static final DashboardForm dashboardForm = new DashboardForm();

    private DashboardFormSteps() {
    }

    public static void addNewDashboard(String dashboardName) {
        dashboardForm
                .inputDashboardName(dashboardName)
                .clickAddBtn();
    }

    public static void assertIsOpen() {
        Assert.assertTrue(dashboardForm.isFormOpen(), dashboardForm.getFormName().concat(" isn't open"));
    }
}
