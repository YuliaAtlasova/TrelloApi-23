package trello.demo.apiSteps;

import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.Card;
import trello.demo.services.CardService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static trello.demo.specifications.ResponseSpecProvider.successJsonResponse;

public class CardApiSteps {

    public static Card createCard(String listId) {
        String name = randomAlphanumeric(10, 20);
        return createCardWithName(listId, name);
    }

    public static Card createCardWithName(String listId, String cardName) {
        Response response = CardService
                .requestBuilder()
                .setName(cardName)
                .setListId(listId)
                .setMethod(Method.POST)
                .build().sendRequest();
        response.then().spec(successJsonResponse());
        return CardService.extractCard(response);
    }

    public static void deleteCard(String cardId) {
        CardService
                .requestBuilder()
                .setId(cardId)
                .setMethod(Method.DELETE)
                .build()
                .sendRequest();
    }
}
