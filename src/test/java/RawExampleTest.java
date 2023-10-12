import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import trello.demo.pages.BoardsPage;
import trello.demo.pages.LoginPage;
import trello.demo.pages.OneBoardPage;
import trello.demo.specifications.RequestSpecProvider;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.Assert.assertEquals;
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

    private void validateListName(String boardName, String listName){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        BoardsPage boards = loginPage.login(LOGIN, PASSWORD);
        OneBoardPage oneBoard = boards.openBoardByName(boardName);
        oneBoard.getListByName(listName);
        int myListsNumber = oneBoard.getNumberOfListsByName(listName);
        assertEquals("Expect to see the list with name: " + listName, 1, myListsNumber);
    }

    @Test
    @DisplayName("long List Name Should Be Visible (Raw Version 2)")
    public void longListNameShouldBeVisibleRawVersion2() {
        String boardName = randomAlphanumeric(20);
        String longListName = randomAlphanumeric(512);
        String boardId = RestAssured.given()
                .spec(RequestSpecProvider.BASE_SPEC)
                .queryParam("name", boardName)
                .log().all()
                .post("/boards/").then().extract().path("id");

        RestAssured.given()
                .spec(RequestSpecProvider.BASE_SPEC)
                .queryParam("name", longListName)
                .queryParam("idBoard", boardId)
                .log().all()
                .post("/list/");
        validateListName(boardName, longListName);
    }
}
