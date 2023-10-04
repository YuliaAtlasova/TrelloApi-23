import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.demo.apiSteps.BoardApiSteps;
import trello.demo.apiSteps.ListApiSteps;
import trello.demo.driver.DriverSingleton;
import trello.demo.entities.Board;
import trello.demo.entities.List;
import trello.demo.pages.BoardsPage;
import trello.demo.pages.LoginPage;
import trello.demo.pages.OneBoardPage;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.Assert.assertTrue;
import static trello.demo.utils.TestData.testData;

public class TrelloListTest extends BaseUiTest{

    Board testBoard;
    List testList;
    LoginPage loginPage;

    @Before
    public void createBoardAndList(){
        testBoard = BoardApiSteps.createBoard();
        loginPage = new LoginPage(driver);
    }

    @After
    public void deleteAllBoards(){
        DriverSingleton.closeDriver();
        BoardApiSteps.deleteBoard(testBoard.getId());
    }

    @Test
    public void longListNameShouldBeVisible() {
        String longName = randomAlphanumeric(512);
        testList = ListApiSteps.createListWithName(testBoard.getId(), longName);
        loginPage.openPage();
        BoardsPage boards = loginPage.login(testData().getProperty("userLogin"), testData().getProperty("userPassword"));
        OneBoardPage oneBoard = boards.openBoardByName(testBoard.getName());
        oneBoard.getListByName(testList.getName());
        int myListsNumber = oneBoard.getNumberOfListsByName(testList.getName());
        assertTrue ("Expect to see the list with name: " + longName, myListsNumber == 1);
    }

    @Test
    public void listNameCanBeUsedForSeveralLists() {
        String listName = randomAlphanumeric(15);
        testList = ListApiSteps.createListWithName(testBoard.getId(), listName);
        testList = ListApiSteps.createListWithName(testBoard.getId(), listName);
        testList = ListApiSteps.createListWithName(testBoard.getId(), listName);
        loginPage.openPage();
        BoardsPage boards = loginPage.login(testData().getProperty("userLogin"), testData().getProperty("userPassword"));
        OneBoardPage oneBoard = boards.openBoardByName(testBoard.getName());
        int myListsNumber = oneBoard.getNumberOfListsByName(testList.getName());
        assertTrue ("Expect to get 3 list with name: " + listName + ", but got " + myListsNumber, myListsNumber == 3);
    }
}
