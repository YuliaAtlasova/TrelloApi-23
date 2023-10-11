import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Test;
import trello.demo.pages.BoardsPage;
import trello.demo.pages.LoginPage;
import trello.demo.pages.OneBoardPage;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.Assert.assertEquals;
import static trello.demo.utils.TestData.testData;

public class RawExampleTest extends BaseUiTest{

    @Test
    @DisplayName("long List Name Should Be Visible (Raw Version)")
    public void longListNameShouldBeVisibleRawVersion() {
        String boardName = randomAlphanumeric(20);
        String longListName = randomAlphanumeric(512);
        String boardId = RestAssured.given()
                .baseUri(testData().getProperty("apiBaseUrl"))
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("key", testData().getProperty("apiKey"))
                .queryParam("token", testData().getProperty("apiToken"))
                .queryParam("name", boardName)
                .log().all()
                .post("/boards/").then().extract().path("id");

        RestAssured.given()
                .baseUri(testData().getProperty("apiBaseUrl"))
                .header("Content-Type", "application/json; charset=utf-8")
                .queryParam("key", testData().getProperty("apiKey"))
                .queryParam("token", testData().getProperty("apiToken"))
                .queryParam("name", longListName)
                .queryParam("idBoard", boardId)
                .log().all()
                .post("/list/");
          LoginPage loginPage = new LoginPage(driver);
          loginPage.openPage();
        BoardsPage boards = loginPage.login(LOGIN, PASSWORD);
        OneBoardPage oneBoard = boards.openBoardByName(boardName);
        oneBoard.getListByName(longListName);
        int myListsNumber = oneBoard.getNumberOfListsByName(longListName);
        assertEquals("Expect to see the list with name: " + longListName, 1, myListsNumber);
    }
}
