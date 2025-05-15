package tests.ui.widgettests;

import framework.browser.Browser;
import framework.utils.SmartLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import steps.dashbords.DashboardsPageSteps;
import steps.dashbords.dashboard.DashboardPageSteps;
import steps.dashbords.dashboard.DeleteWidgetFormSteps;
import steps.dashbords.dashboard.widgetform.WidgetFormSteps;
import steps.dashbords.dashboard.widgetform.WidgetType;
import steps.launches.LaunchesPageSteps;
import tests.ui.BaseTest;

public class WidgetTest extends BaseTest {
    private final String DASHBOARD_NAME = "First dashboard";

    @Epic("Add Widget")
    @Test
    public void addWidget() {
        String WIDGET_FILTER = "DEMO_FILTER";
        authorizationUser(LOGIN, PASSWORD);

        Allure.step("Open Dashboard", () -> {
            SmartLogger.logStep(++step, "Open Dashboards");
            LaunchesPageSteps.openDashboardsPage();
            DashboardsPageSteps.assertIsOpen();

            SmartLogger.logStep(++step, "Open first dashboard page");
            DashboardsPageSteps.openFirstDashboardPage();
            DashboardPageSteps.assertIsOpen();
            DashboardPageSteps.setDashboardPageName(DASHBOARD_NAME);
            Allure.addByteAttachmentAsync(DASHBOARD_NAME.concat(" page"), "image/png", ".png",
                    Browser.getScreenshotBytes());
        });

        Allure.step("Add new widget form", () -> {
            SmartLogger.logStep(++step, "Open 'Add new widget' form");
            DashboardPageSteps.openAddNewWidgetForm();
            WidgetFormSteps.assertIsOpen();

            SmartLogger.logStep(++step, "Set configuration for new widget");
            String widgetName = WidgetFormSteps.addNewWidget(WidgetType.OVERALL_STATISTICS.getType(), WIDGET_FILTER);

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
