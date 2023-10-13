import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Test;
import trello.demo.entities.Board;
import trello.demo.entities.Colors;
import trello.demo.entities.List;
import trello.demo.pages.BoardsPage;
import trello.demo.pages.LoginPage;
import trello.demo.pages.OneBoardPage;
import trello.demo.services.BoardService;
import trello.demo.services.ListService;
import trello.demo.specifications.RequestSpecProvider;
import trello.demo.utils.EnumUtils;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.Assert.assertEquals;
import static trello.demo.specifications.ResponseSpecProvider.successJsonResponse;
import static trello.demo.utils.TestData.testData;

public class RawExampleTest extends BaseUiTest{

    @Test
    @DisplayName("long List Name Should Be Visible (Raw Version 1)")
    public void longListNameShouldBeVisibleRawVersion1() {
        String boardName = randomAlphanumeric(20);
        Response response1 = RestAssured.given()
                .baseUri(testData().getProperty("apiBaseUrl"))
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("key", testData().getProperty("apiKey"))
                .queryParam("token", testData().getProperty("apiToken"))
                .queryParam("name", boardName)
                .log().all()
                .post("/boards/");
        assertEquals(response1.statusCode(), 200);
        String boardId = response1.then().extract().path("id");

        String longListName = randomAlphanumeric(512);
        Response response2 = RestAssured.given()
                .baseUri(testData().getProperty("apiBaseUrl"))
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("key", testData().getProperty("apiKey"))
                .queryParam("token", testData().getProperty("apiToken"))
                .queryParam("name", longListName)
                .queryParam("idBoard", boardId)
                .log().all()
                .post("/list/");
        assertEquals(response2.statusCode(), 200);
        validateListName(boardName, longListName);
    }

    @Test
    @DisplayName("long List Name Should Be Visible (Raw Version 2)")
    public void longListNameShouldBeVisibleRawVersion2() {
        String boardName = randomAlphanumeric(20);
        Response response1 = RestAssured.given()
                .spec(RequestSpecProvider.BASE_SPEC)
                .queryParam("name", boardName)
                .log().all()
                .post("/boards/");
        assertEquals(response1.statusCode(), 200);
        String boardId = response1.then().extract().path("id");

        String longListName = randomAlphanumeric(512);
        Response response2 = RestAssured.given()
                .spec(RequestSpecProvider.BASE_SPEC)
                .queryParam("name", longListName)
                .queryParam("idBoard", boardId)
                .log().all()
                .post("/list/");
        assertEquals(response2.statusCode(), 200);
        validateListName(boardName, longListName);
    }

    @Test
    @DisplayName("long List Name Should Be Visible (Raw Version 3)")
    public void longListNameShouldBeVisibleRawVersion3() {
        String boardName = randomAlphanumeric(20);
        Response response1 = RestAssured.given()
                .spec(RequestSpecProvider.BASE_SPEC)
                .queryParam("name", boardName)
                .log().all()
                .post("/boards/").then()
                .spec(successJsonResponse()).extract().response();
        String boardId = response1.then().extract().path("id");

        String longListName = randomAlphanumeric(512);
        RestAssured.given()
                .spec(RequestSpecProvider.BASE_SPEC)
                .queryParam("name", longListName)
                .queryParam("idBoard", boardId)
                .log().all()
                .post("/list/").then()
                .spec(successJsonResponse());

        validateListName(boardName, longListName);
    }

    @Test
    @DisplayName("long List Name Should Be Visible (Raw Version 4)")
    public void longListNameShouldBeVisibleRawVersion4() {
        Response response1 = BoardService
                .requestBuilder()
                .setMethod(Method.POST)
                .setName(randomAlphanumeric(10, 20))
                .setColor(EnumUtils.randomValue(Colors.class).name())
                .build().sendRequest();
        response1.then().spec(successJsonResponse());
        Board testBoard = BoardService.extractBoard(response1);

        String longListName = randomAlphanumeric(512);
        Response response2 = ListService
                .requestBuilder()
                .setName(longListName)
                .setBoardId(testBoard.getId())
                .setMethod(Method.POST)
                .build().sendRequest();
        response2.then().spec(successJsonResponse());
        validateListName(testBoard.getName(), longListName);
    }

    private void validateListName(String boardName, String listName){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        BoardsPage boards = loginPage.login(LOGIN, PASSWORD);
        OneBoardPage oneBoard = boards.openBoardByName(boardName);
        oneBoard.getListByName(listName);
        int myListsNumber = oneBoard.getNumberOfListsByName(listName);
        assertEquals("Expect to see the list with name: " + listName, 1, myListsNumber);
    }
}
