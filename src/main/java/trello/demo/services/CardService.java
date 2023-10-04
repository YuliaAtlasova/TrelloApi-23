package trello.demo.services;

import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.Card;

import java.util.Map;

public class CardService extends BaseServiceObject {

    private static final String cardUrl = "cards/?idList={id}";

    public CardService(String url,
                       Method method,
                       Map<String, String> pathParams,
                       Map<String, String> queryParams,
                       Object body) {
        super(url, method, pathParams, queryParams, body);
    }

    public static CardRequestBuilder requestBuilder() {
        return new CardRequestBuilder();
    }

    public static class CardRequestBuilder extends ApiRequestBuilder<CardRequestBuilder, CardService> {

        public CardRequestBuilder setId(String id) {
            addPathParam("id", id);
            return this;
        }

        public CardRequestBuilder setName(String name) {
            addQueryParam("name", name);
            return this;
        }

        public CardRequestBuilder setDescription(String description) {
            addQueryParam("desc", description);
            return this;
        }

        public CardRequestBuilder setClosed(boolean closed) {
            addQueryParam("closed", String.valueOf(closed));
            return this;
        }

        public CardRequestBuilder setListId(String listId) {
            addQueryParam("idList", listId);
            return this;
        }

        @Override
        public CardService build() {
            return new CardService(cardUrl, method, pathParams, queryParams, queryBody);
        }
    }

    public static Card extractCard(Response response) {
        return extract(response, Card.class);
    }
}
