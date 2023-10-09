package trello.demo.apiSteps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.List;
import trello.demo.services.ListService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static trello.demo.specifications.ResponseSpecProvider.successJsonResponse;

public class ListApiSteps {

    @Attachment
    @Step("createList")
    public static List createList(String boardId) {
        String name = randomAlphanumeric(10, 20);
        return createListWithName(boardId, name);
    }

    @Attachment
    @Step("createListWithName")
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

    @Attachment
    @Step("deleteList")
    public static void deleteList(String listId) {
        ListService
                .requestBuilder()
                .setId(listId)
                .setMethod(Method.DELETE)
                .build()
                .sendRequest();
    }
}
