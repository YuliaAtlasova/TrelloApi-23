package trello.demo.apiSteps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.CheckItem;
import trello.demo.services.CheckItemService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class CheckItemApiSteps {

    @Attachment
    @Step("createCompletedCheckItem")
    public static CheckItem createCompletedCheckItem(String checkListId) {
        return createCheckItem(checkListId, true);
    }

    @Attachment
    @Step("createNotCompletedCheckItem")
    public static CheckItem createNotCompletedCheckItem(String checkListId) {
        return createCheckItem(checkListId, false);
    }

    @Attachment
    @Step("createCheckItem")
    public static CheckItem createCheckItem(String checkListId, boolean completed) {
        Response response = CheckItemService.requestBuilder().setMethod(Method.POST).setName(randomAlphanumeric(10, 20)).setChecklistId(checkListId).setChecked(completed).build().sendRequest();
        return CheckItemService.extractCheckItem(response);
    }
}
