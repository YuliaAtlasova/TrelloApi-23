package trello.demo.apiSteps;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.Board;
import trello.demo.entities.Colors;
import trello.demo.services.BoardService;
import trello.demo.specifications.RequestSpecProvider;
import trello.demo.utils.EnumUtils;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class BoardApiSteps {

    public static final String ALL_BOARDS_URL = "/members/me/boards";

    public static Board createBoard() {
         Response resp = BoardService
                .requestBuilder()
                .setMethod(Method.POST)
                .setName(randomAlphanumeric(10, 20))
                .setColor(EnumUtils.randomValue(Colors.class).name())
                .build()
                .sendRequest();
         return BoardService.extractBoard(resp);
    }

    public static Board getBoard(String boardId) {
        Response resp = BoardService
                .requestBuilder()
                .setId(boardId)
                .build().sendRequest();
                return  BoardService.extractBoard(resp);
    }

    public static void deleteBoard(String boardId) {
        BoardService
                .requestBuilder()
                .setMethod(Method.DELETE)
                .setId(boardId)
                .build().sendRequest();
    }

    public static List<Board> getAllBoards() {
        return RestAssured
                .given()
                .spec(RequestSpecProvider.BASE_SPEC)
                .when()
                .get(ALL_BOARDS_URL)
                .as(new TypeRef<List<Board>>() { });
    }

    public static void deleteAllBoards() {
        getAllBoards().forEach(b -> BoardService
                .requestBuilder()
                .setId(b.getId())
                .setMethod(Method.DELETE)
                .build()
                .sendRequest());
    }

}
