import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.demo.apiSteps.BoardApiSteps;
import trello.demo.apiSteps.CardApiSteps;
import trello.demo.apiSteps.ListApiSteps;
import trello.demo.driver.DriverSingleton;
import trello.demo.entities.Board;
import trello.demo.entities.Card;
import trello.demo.entities.List;
import trello.demo.pages.BoardsPage;
import trello.demo.pages.ListOnBoardPage;
import trello.demo.pages.LoginPage;
import trello.demo.pages.OneBoardPage;

import static trello.demo.utils.TestData.testData;

public class TrelloCardOnListTest extends BaseUiTest{

    Board testBoard;
    List testList;
    LoginPage loginPage;

    @Before
    public void createBoardAndList(){
        testBoard = BoardApiSteps.createBoard();
        testList = ListApiSteps.createList(testBoard.getId());
        loginPage = new LoginPage(driver);
    }

    @After
    public void deleteAllBoards(){
        DriverSingleton.closeDriver();
        BoardApiSteps.deleteBoard(testBoard.getId());
    }

    @Test
    public void EmptyTrelloCard() {
        Card myCard  = CardApiSteps.createCard(testList.getId());
        loginPage.openPage();
        BoardsPage boards = loginPage.login(testData().getProperty("userLogin"), testData().getProperty("userPassword"));
        OneBoardPage oneBoard = boards.openBoardByName(testBoard.getName());
        ListOnBoardPage myList = oneBoard.getListByName(testList.getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
