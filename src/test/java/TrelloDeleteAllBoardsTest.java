import org.junit.Test;
import trello.demo.apiSteps.BoardApiSteps;

public class TrelloDeleteAllBoardsTest extends BaseUiTest {

    @Test
    public void JustDeleteAllBoards() {
        BoardApiSteps.deleteAllBoards();
    }
}
