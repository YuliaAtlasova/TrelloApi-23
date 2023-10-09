import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trello.demo.apiSteps.*;
import trello.demo.driver.DriverSingleton;
import trello.demo.entities.Board;
import trello.demo.entities.Card;
import trello.demo.entities.List;
import trello.demo.pages.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrelloCardOnListTest extends BaseUiTest {

    Board testBoard;
    List testList;
    LoginPage loginPage;

    @Before
    public void createBoardAndList() {
        testBoard = BoardApiSteps.createBoard();
        testList = ListApiSteps.createList(testBoard.getId());
        loginPage = new LoginPage(driver);
    }

    @After
    public void deleteAllBoards() {
        DriverSingleton.closeDriver();
        BoardApiSteps.deleteBoard(testBoard.getId());
    }

    @Test
    @DisplayName("TrelloCardShouldShowNotCompletedCheckItems")
    public void TrelloCardShouldShowNotCompletedCheckItems() {
        Card card1 = CardApiSteps.createCardWithName(testList.getId(), "card1");
        loginPage.openPage();
        BoardsPage boards = loginPage.login(LOGIN, PASSWORD);
        OneBoardPage oneBoard = boards.openBoardByName(testBoard.getName());
        ListOnBoardPage myList = oneBoard.getListByName(testList.getName());
        CardOnListPage myCardBefore = myList.getCardOnList(card1.getName());
        assertFalse(myCardBefore.isCheckListIconPresent());
        String checklistId = ChecklistApiSteps.createChecklist(card1.getId()).getId();
        CheckItemApiSteps.createNotCompletedCheckItem(checklistId);
        driver.navigate().refresh();
        CardOnListPage myCardAfter = myList.getCardOnList(card1.getName());
        assertTrue(myCardAfter.isCheckListIconPresent());
        assertFalse(myCardAfter.isCheckListCompleted());
    }

    @Test
    @DisplayName("TrelloCardShouldShowCompletedCheckItems")
    public void TrelloCardShouldShowCompletedCheckItems() {
        Card card1 = CardApiSteps.createCardWithName(testList.getId(), "card1");
        String checklistId = ChecklistApiSteps.createChecklist(card1.getId()).getId();
        CheckItemApiSteps.createCompletedCheckItem(checklistId);
        loginPage.openPage();
        BoardsPage boards = loginPage.login(LOGIN, PASSWORD);
        CardOnListPage myCard = boards.openBoardByName(testBoard.getName())
                .getListByName(testList.getName())
                .getCardOnList(card1.getName());
        assertTrue(myCard.isCheckListIconPresent());
        assertTrue(myCard.isCheckListCompleted());
    }
}
