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

import static trello.demo.utils.TestData.testData;

public class TrelloCardOnListTest extends BaseUiTest{

    Board testBoard;
    List testList;

    @Before
    public void createBoardAndList(){
        testBoard = BoardApiSteps.createBoard();
        testList = ListApiSteps.createList(testBoard.getId());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        BoardsPage boards = loginPage.login(testData().getProperty("userLogin"), testData().getProperty("userPassword"));
        boards.openBoardByName(testBoard.getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @After
    public void deleteAllBoards(){
        DriverSingleton.closeDriver();
        BoardApiSteps.deleteBoard(testBoard.getId());
    }

    @Test
    public void EmptyTrelloCard() {
        System.out.println("HERE WE ARE");
    }

}
