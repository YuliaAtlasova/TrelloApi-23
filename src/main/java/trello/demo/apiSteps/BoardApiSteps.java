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
import static trello.demo.specifications.ResponseSpecProvider.successJsonResponse;

public class BoardApiSteps {

    public static final String ALL_BOARDS_URL = "/members/me/boards";

    public static Board createBoard() {
        Response response = BoardService
                .requestBuilder()
                .setMethod(Method.POST)
                .setName(randomAlphanumeric(10, 20))
                .setColor(EnumUtils.randomValue(Colors.class).name())
                .build()
                .sendRequest();
        return BoardService.extractBoard(response);
    }

    public static Board getBoard(String boardId) {
        Response response = BoardService
                .requestBuilder()
                .setPathId(boardId)
                .build().sendRequest();
        response.then().spec(successJsonResponse());
        return BoardService.extractBoard(response);
    }

    public static void deleteBoard(String boardId) {
        Response response = BoardService
                .requestBuilder()
                .setMethod(Method.DELETE)
                .setPathId(boardId)
                .build().sendRequest();
        response.then().spec(successJsonResponse());
    }

    public static List<Board> getAllBoards() {
        return RestAssured
                .given()
                .spec(RequestSpecProvider.BASE_SPEC)
                .when()
                .get(ALL_BOARDS_URL)
                .as(new TypeRef<List<Board>>() {
                });
    }

    public static void deleteAllBoards() {
        getAllBoards().forEach(b -> deleteBoard(b.getId()));
    }
}
