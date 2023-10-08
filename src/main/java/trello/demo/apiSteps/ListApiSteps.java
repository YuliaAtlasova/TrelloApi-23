package trello.demo.apiSteps;

import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.List;
import trello.demo.services.ListService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static trello.demo.specifications.ResponseSpecProvider.successJsonResponse;

public class ListApiSteps {

    public static List createList(String boardId) {
        String name = randomAlphanumeric(10, 20);
        return createListWithName(boardId, name);
    }

    public static List createListWithName(String boardId, String listName) {
        Response resp = ListService
                .requestBuilder()
                .setName(listName)
                .setBoardId(boardId)
                .setMethod(Method.POST)
                .build().sendRequest();
        resp.then().spec(successJsonResponse());
        return ListService.extractList(resp);
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
