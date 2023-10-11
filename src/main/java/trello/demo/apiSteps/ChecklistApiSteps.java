package trello.demo.apiSteps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.Checklist;
import trello.demo.services.ChecklistService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static trello.demo.specifications.ResponseSpecProvider.successJsonResponse;

public class ChecklistApiSteps {

    @Attachment
    @Step("createChecklist")
    public static Checklist createChecklist(String cardId) {
        String name = randomAlphanumeric(10, 20);
        return createChecklistWithName(cardId, name);
    }

    @Attachment
    @Step("createChecklistWithName")
    public static Checklist createChecklistWithName(String cardId, String checklistName) {
        Response resp = ChecklistService.requestBuilder().setName(checklistName).setCardId(cardId).setMethod(Method.POST).build().sendRequest();
        resp.then().spec(successJsonResponse());
        return ChecklistService.extractChecklist(resp);
    }

    @Attachment
    @Step("deleteChecklist")
    public static void deleteChecklist(String checklistId) {
        ChecklistService.requestBuilder().setId(checklistId).setMethod(Method.DELETE).build().sendRequest();
    }
}
