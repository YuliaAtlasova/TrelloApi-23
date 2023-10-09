package trello.demo.apiSteps;

import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.Checklist;
import trello.demo.services.ChecklistService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static trello.demo.specifications.ResponseSpecProvider.successJsonResponse;

public class ChecklistApiSteps {

    public static Checklist createChecklist(String cardId) {
        String name = randomAlphanumeric(10, 20);
        return createChecklistWithName(cardId, name);
    }

    public static Checklist createChecklistWithName(String cardId, String checklistName) {
        System.out.println("Here we are 111 createChecklistWithName");
        Response resp = ChecklistService.requestBuilder().setName(checklistName).setCardId(cardId).setMethod(Method.POST).build().sendRequest();
        resp.then().spec(successJsonResponse());
        return ChecklistService.extractChecklist(resp);
    }

    public static void deleteChecklist(String checklistId) {
        ChecklistService.requestBuilder().setId(checklistId).setMethod(Method.DELETE).build().sendRequest();
    }
}
