package services;

import framework.config.Data;
import framework.response.Response;
import framework.utils.SmartLogger;
import io.qameta.allure.restassured.AllureRestAssured;
import models.dashboards.dashboard.Dashboard;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DashboardsApi {
    private static final String VERSION = "/api/".concat(Data.getValue().version());
    private static final String PROJECT_NAME = "/{projectName}";
    private static final String DASHBOARD = VERSION.concat(PROJECT_NAME).concat("/dashboard");
    private static final String DASHBOARD_ID =
            VERSION.concat(PROJECT_NAME).concat("/dashboard").concat("/{dashboardId}");


    private DashboardsApi() {
    }

    public static Response createDashboard(String projectName, Dashboard dashboard) {
        SmartLogger.logInfo("Create dashboard: POST ".concat(baseURI).concat(DASHBOARD));

        return new Response(
                given()
                        .filter(new AllureRestAssured())
                        .spec(Response.specPostRequest())
                        .pathParam("projectName", projectName)
                        .body(dashboard)
                        .when()
                        .auth().oauth2(Data.getValue().apiKey())
                        .post(DASHBOARD)
                        .then()
                        .log().all()
        );
    }

    public static Response getDashboards(String projectName) {
        SmartLogger.logInfo("Get dashboards: GET ".concat(baseURI).concat(DASHBOARD));

        return new Response(
                given()
                        .filter(new AllureRestAssured())
                        .spec(Response.specGetRequest())
                        .pathParam("projectName", projectName)
                        .when()
                        .auth().oauth2(Data.getValue().apiKey())
                        .get(DASHBOARD)
                        .then()
                        .log().all()
        );
    }

    public static Response deleteDashboard(String projectName, int dashboardId) {
        SmartLogger.logInfo("Delete dashboard: DELETE ".concat(baseURI).concat(DASHBOARD_ID));

        return new Response(
                given()
                        .filter(new AllureRestAssured())
                        .spec(Response.specPostRequest())
                        .pathParam("projectName", projectName)
                        .pathParam("dashboardId", dashboardId)
                        .when()
                        .auth().oauth2(Data.getValue().apiKey())
                        .delete(DASHBOARD_ID)
                        .then()
                        .log().all()
        );
    }
}
