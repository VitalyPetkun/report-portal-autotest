package tests.api;

import framework.config.Data;
import framework.response.Response;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {
    public static int step;
    public static Response response;

    @BeforeMethod
    public void setup() {
        step = 0;
        baseURI = Data.getValue().baseUrl();
    }
}
