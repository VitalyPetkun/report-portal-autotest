package tests.api.dashboardtest;

import framework.config.Data;
import framework.config.JsonSchema;
import framework.utils.DataGenerator;
import framework.utils.SmartLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.module.jsv.JsonSchemaValidator;
import models.dashboards.dashboard.Dashboard;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import services.DashboardsApi;
import steps.api.dashboard.DashboardApiSteps;
import tests.api.BaseTest;

public class CreateDashboardTest extends BaseTest {

    @DataProvider
    public Object[][] getDashboardDataPositive() {
        return new Object[][]
                {
                        {Data.getValue().projectName(),
                                DataGenerator.getString(3, 128), DataGenerator.getString(1, 1500)},
                        {Data.getValue().projectName(),
                                DataGenerator.getString(3, 128), DataGenerator.getString(1, 1500)}
                };
    }

    @DataProvider
    public Object[][] getDashboardDataNegative() {
        return new Object[][]
                {
                        {Data.getValue().projectName(),
                                DataGenerator.getString(0, 2), DataGenerator.getString(1, 1500)},
                        {Data.getValue().projectName(),
                                DataGenerator.getString(129, 135), DataGenerator.getString(1, 1500)},
                        {Data.getValue().projectName(),
                                DataGenerator.getString(3, 128), DataGenerator.getString(1501, 1505)},
                        {Data.getValue().projectName(),
                                null, DataGenerator.getString(1501, 1505)}
                };
    }

    @Epic("Dashboard")
    @Feature("Create Dashboard")
    @Test(dataProvider = "getDashboardDataPositive", testName = "Create dashboard")
    public void createDashboard(String projectName, String dashboardName, String dashboardDescription) {
        Dashboard dashboard = new Dashboard(dashboardName, dashboardDescription);

        dashboard.setId(DashboardApiSteps.createDashboard(projectName, dashboard).getId());

        DashboardApiSteps.checkDashboard(projectName, dashboard);

        DashboardApiSteps.deleteDashboard(projectName, dashboard.getId());
    }

    @Epic("Dashboard")
    @Feature("Create Dashboard")
    @Test(dataProvider = "getDashboardDataNegative", testName = "Create dashboard without require parameters")
    public void createDashboardNegative(String projectName, String dashboardName, String dashboardDescription) {
        Dashboard dashboard = new Dashboard(dashboardName, dashboardDescription);

        Allure.step("Create dashboard without require parameters", () -> {
            SmartLogger.logStep(++step, "Create dashboard");
            response = DashboardsApi.createDashboard(projectName, dashboard);
            Assert.assertEquals(
                    response.getStatusCode(),
                    HttpStatus.SC_BAD_REQUEST,
                    "Dashboard created"
            );
            Assert.assertTrue(JsonSchemaValidator
                            .matchesJsonSchemaInClasspath(JsonSchema.ERROR_RESPONSE.getJsonSchema())
                            .matches(response.getBodyToJson()),
                    "Error response json schema isn't correct"
            );
        });

        DashboardApiSteps.checkDashboardNotExist(Data.getValue().projectName(), dashboard);
    }
}
