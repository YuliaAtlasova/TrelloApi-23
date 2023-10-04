package trello.demo.apiSteps;

import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.List;
import trello.demo.services.ListService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class ListApiSteps {

    public static List createList(String boardId) {
        Response resp = ListService
                .requestBuilder()
                .setName(randomAlphanumeric(10, 20))
                .setBoardId(boardId)
                .setMethod(Method.POST)
                .build().sendRequest();
                List list = ListService.extractList(resp);
                return list;
    }

    public static void deleteList(String listId) {
        ListService
                .requestBuilder()
                .setId(listId)
                .setMethod(Method.DELETE)
                .build()
                .sendRequest();
    }

}
