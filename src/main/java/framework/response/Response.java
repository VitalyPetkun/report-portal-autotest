package framework.response;

import framework.utils.JsonConverter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class Response {
    private ResponseBodyExtractionOptions restAssuredBody;
    private int statusCode;

    public Response(ValidatableResponse validatableResponse) {
        restAssuredBody = validatableResponse.extract().body();
        statusCode = validatableResponse.extract().statusCode();
    }

    public <T> T getBody(Class<T> cls) {
        return JsonConverter.getObject(restAssuredBody.asString(), cls);
    }

    public String getBodyToJson() {
        return restAssuredBody.asString();
    }


    public static RequestSpecification specGetRequest() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification specPostRequest() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}