package trello.demo.specifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static trello.demo.utils.TestData.testData;

public class RequestSpecProvider {
    public static final RequestSpecification BASE_SPEC = new RequestSpecBuilder()
            .setBaseUri(testData().getProperty("apiBaseUrl"))
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addQueryParam("key", testData().getProperty("apiKey"))
            .addQueryParam("token", testData().getProperty("apiToken"))
            .build()
            .filter(new AllureRestAssured());

    private RequestSpecProvider() {
    }
}
