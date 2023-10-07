package trello.demo.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.lessThan;

public class ResponseSpecProvider {

    public static ResponseSpecification successJsonResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(JSON)
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }
}
