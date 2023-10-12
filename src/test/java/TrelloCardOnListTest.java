import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.demo.apiSteps.*;
import trello.demo.driver.DriverSingleton;
import trello.demo.entities.Board;
import trello.demo.entities.Card;
import trello.demo.entities.Checklist;
import trello.demo.entities.List;
import trello.demo.pages.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrelloCardOnListTest extends BaseUiTest {

    Board testBoard;
    List testList;
    Card testCard;
    Checklist testChecklist;
    LoginPage loginPage;

    @Before
    public void createBoardAndList() {
        testBoard = BoardApiSteps.createBoard();
        testList = ListApiSteps.createList(testBoard.getId());
        testCard = CardApiSteps.createCardWithName(testList.getId(), "card1");
        testChecklist = ChecklistApiSteps.createChecklist(testCard.getId());
        loginPage = new LoginPage(driver);
    }

    @After
    public void deleteTestBoard() {
        DriverSingleton.closeDriver();
        BoardApiSteps.deleteBoard(testBoard.getId());
    }

    @Test
    @DisplayName("Trello Card Should Show Not Completed CheckItems")
    public void TrelloCardShouldShowNotCompletedCheckItems() {
        CheckItemApiSteps.createNotCompletedCheckItem(testChecklist.getId());
        loginPage.openPage();
        BoardsPage boards = loginPage.login(LOGIN, PASSWORD);
        OneBoardPage oneBoard = boards.openBoardByName(testBoard.getName());
        ListOnBoardPage myList = oneBoard.getListByName(testList.getName());
        CardOnListPage myCard = myList.getCardOnList(testCard.getName());
        assertTrue(myCard.isCheckListIconPresent());
        assertFalse(myCard.isCheckListCompleted());
    }

    @Test
    @DisplayName("Trello Card Should Show Completed CheckItems")
    public void TrelloCardShouldShowCompletedCheckItems() {
        CheckItemApiSteps.createCompletedCheckItem(testChecklist.getId());
        loginPage.openPage();
        BoardsPage boards = loginPage.login(LOGIN, PASSWORD);
        CardOnListPage myCard = boards.openBoardByName(testBoard.getName())
                .getListByName(testList.getName())
                .getCardOnList(testCard.getName());
        assertTrue(myCard.isCheckListIconPresent());
        assertTrue(myCard.isCheckListCompleted());
    }
}
