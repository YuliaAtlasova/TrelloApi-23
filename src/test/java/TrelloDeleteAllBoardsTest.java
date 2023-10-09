import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import trello.demo.apiSteps.BoardApiSteps;

public class TrelloDeleteAllBoardsTest extends BaseUiTest {

    @Test
    @DisplayName("JustDeleteAllBoards")
    public void JustDeleteAllBoards() {
        BoardApiSteps.deleteAllBoards();
    }
}
