package tests.ui.widgettests;

import framework.browser.Browser;
import framework.utils.DataGenerator;
import framework.utils.SmartLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.dashbords.DashboardsPageSteps;
import steps.dashbords.dashboard.DashboardPageSteps;
import steps.dashbords.dashboard.DeleteWidgetFormSteps;
import steps.dashbords.dashboard.widgetform.WidgetFormSteps;
import steps.dashbords.dashboard.widgetform.WidgetType;
import steps.launches.LaunchesPageSteps;
import tests.ui.BaseTest;

public class CreateWidgetTest extends BaseTest {

    @DataProvider
    public Object[][] getDataWidget() {
        return new Object[][]
                {
                        {DataGenerator.getString(3, 128), WidgetType.OVERALL_STATISTICS.getType(), "DEMO_FILTER"},
                        {DataGenerator.getString(3, 128), WidgetType.LAUNCHES_DURATION_CHART.getType(), "DEMO_FILTER"}
                };
    }

    @Epic("Widget")
    @Feature("Create Widget")
    @Test(dataProvider="getDataWidget", testName = "Create widget")
    public void addWidget(String widgetName, String widgetType, String widgetFilter) {
        authorizationUser(LOGIN, PASSWORD);

        Allure.step("Open Dashboard", () -> {
            SmartLogger.logStep(++step, "Open Dashboards");
            LaunchesPageSteps.openDashboardsPage();
            DashboardsPageSteps.assertIsOpen();

            SmartLogger.logStep(++step, "Open first dashboard page");
            String dashboardName = DashboardsPageSteps.openFirstDashboardPage();
            DashboardPageSteps.assertIsOpen();
            DashboardPageSteps.setDashboardPageName(dashboardName);
            Allure.addByteAttachmentAsync(dashboardName.concat(" page"), "image/png", ".png",
                    Browser.getScreenshotBytes());
        });

        Allure.step("Add new widget form", () -> {
            SmartLogger.logStep(++step, "Open 'Add new widget' form");
            DashboardPageSteps.openAddNewWidgetForm();
            WidgetFormSteps.assertIsOpen();

            SmartLogger.logStep(++step, "Set configuration for new widget");
            WidgetFormSteps.addNewWidget(widgetType, widgetFilter, widgetName);

            SmartLogger.logStep(++step, "Check new widget");
            DashboardPageSteps.assertIsExistWidget(widgetName);
            Allure.addByteAttachmentAsync("Widget form", "image/png", ".png",
                    Browser.getScreenshotBytes());

            SmartLogger.logStep(++step, "Delete widget");
            DashboardPageSteps.deleteWidget(widgetName);
            DeleteWidgetFormSteps.assertIsOpen();
            DeleteWidgetFormSteps.clickDeleteBtn();
            DashboardPageSteps.assertIsNotExistWidget(widgetName);
        });
    }
}
