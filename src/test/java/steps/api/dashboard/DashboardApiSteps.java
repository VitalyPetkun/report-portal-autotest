package steps.api.dashboard;

import framework.config.JsonSchema;
import framework.response.Response;
import framework.utils.JsonConverter;
import framework.utils.SmartLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import models.dashboards.Dashboards;
import models.dashboards.dashboard.CreateDashboardResponse;
import models.dashboards.dashboard.Dashboard;
import models.dashboards.dashboard.DeleteDashboardResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import services.DashboardsApi;
import tests.api.BaseTest;

import java.util.List;

public class DashboardApiSteps {
    public static Response response;

    private DashboardApiSteps() {
    }

    @Step("Create dashboard")
    public static CreateDashboardResponse createDashboard(String projectName, Dashboard dashboard) {
        SmartLogger.logStep(++BaseTest.step, "Create dashboard");
        response = DashboardsApi.createDashboard(projectName, dashboard);
        Assert.assertEquals(
                response.getStatusCode(),
                HttpStatus.SC_CREATED,
                "Dashboard didn't create"
        );
        Assert.assertTrue(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(JsonSchema.CREATE_DASHBOARD_RESPONSE.getJsonSchema())
                        .matches(response.getBodyToJson()),
                "Create dashboard response json schema isn't correct"
        );

        return response.getBody(CreateDashboardResponse.class);
    }

    @Step("Check dashboard")
    public static void checkDashboard(String projectName, Dashboard expectedDashboard) {
        SmartLogger.logStep(++BaseTest.step, "Check dashboard");
        response = DashboardsApi.getDashboards(projectName);

        Assert.assertEquals(
                response.getStatusCode(),
                HttpStatus.SC_OK,
                "Dashboards didn't get"
        );
        Assert.assertTrue(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(JsonSchema.DASHBOARDS.getJsonSchema())
                        .matches(response.getBodyToJson()),
                "Dashboards json schema isn't correct"
        );

        Dashboards dashboards = response.getBody(Dashboards.class);
        List<Dashboard> actualDashboards = dashboards.getContent().stream()
                .filter(dashboard -> dashboard.getId() == expectedDashboard.getId()).toList();

        Assert.assertEquals(actualDashboards.size(), 1,
                "Founded someone dashboards with same id = ".concat(String.valueOf(actualDashboards.get(0).getId())));

        Assert.assertTrue(actualDashboards.get(0).equals(expectedDashboard), "Dashboard isn't correct");

        Allure.addAttachment(
                "Dashboards",
                "application/json",
                JsonConverter.getJsonString(dashboards.getContent()));
    }

    @Step("Check dashboard not exist")
    public static void checkDashboardNotExist(String projectName, Dashboard expectedDashboard) {
        SmartLogger.logStep(++BaseTest.step, "Check dashboard not exist");
        response = DashboardsApi.getDashboards(projectName);

        Assert.assertEquals(
                response.getStatusCode(),
                HttpStatus.SC_OK,
                "Dashboards didn't get"
        );
        Assert.assertTrue(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(JsonSchema.DASHBOARDS.getJsonSchema())
                        .matches(response.getBodyToJson()),
                "Dashboards json schema isn't correct"
        );

        Dashboards dashboards = response.getBody(Dashboards.class);
        Allure.addAttachment(
                "Dashboards",
                "application/json",
                JsonConverter.getJsonString(dashboards.getContent()));

        List<Dashboard> actualDashboards = dashboards.getContent().stream()
                .filter(dashboard -> dashboard.getName().equals(expectedDashboard.getName())).toList();

        Assert.assertTrue(actualDashboards.isEmpty(),
                "Found dashboard with name = ".concat(String.valueOf(expectedDashboard.getName())));
    }

    @Step("Delete dashboard")
    public static DeleteDashboardResponse deleteDashboard(String projectName, int dashboardId) {
        SmartLogger.logStep(++BaseTest.step, "Delete dashboard");
        response = DashboardsApi.deleteDashboard(projectName, dashboardId);
        Assert.assertEquals(
                response.getStatusCode(),
                HttpStatus.SC_OK,
                "Dashboard didn't delete"
        );
        Assert.assertTrue(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(JsonSchema.DELETE_DASHBOARD_RESPONSE.getJsonSchema())
                        .matches(response.getBodyToJson()),
                "Delete dashboard response json schema isn't correct"
        );

        return response.getBody(DeleteDashboardResponse.class);
    }
}
