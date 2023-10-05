package trello.demo.apiSteps;

import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.Card;
import trello.demo.entities.List;
import trello.demo.services.CardService;
import trello.demo.services.ListService;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static trello.demo.specifications.ResponseSpecProvider.successJsonResponse;

public class CardApiSteps {

    public static Card createCard(String listId) {
        String name = randomAlphanumeric(10, 20);
        return createCardWithName(listId, name);
    }

    public static Card createCardWithName(String listId, String listName){
        Response response = CardService
                .requestBuilder()
                .setName(listName)
                .setListId(listId)
                .setMethod(Method.POST)
                .build().sendRequest();
        response.then().spec(successJsonResponse());
        Card card = CardService.extractCard(response);
        return card;
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
