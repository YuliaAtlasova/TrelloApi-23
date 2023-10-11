import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import trello.demo.apiSteps.BoardApiSteps;

public class TrelloDeleteAllBoardsTest extends BaseUiTest {

    @Test
    @DisplayName("Just Delete All Boards")
    public void JustDeleteAllBoards() {
        BoardApiSteps.deleteAllBoards();
    }
}
