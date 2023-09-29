import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TryPom extends BaseUiTest{
    @Test
    public void makeSureThatGoogleIsUp() {
        given().when().get("http://www.google.com").then().statusCode(200);
    }
}
