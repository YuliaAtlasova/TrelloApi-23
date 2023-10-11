package trello.demo.services;

import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.Checklist;

import java.util.Map;

public class ChecklistService extends BaseServiceObject {

    private static final String ChecklistUrl = "/checklists";

    public ChecklistService(String url, Method method, Map<String, String> pathParams, Map<String, String> queryParams, Object body) {
        super(url, method, pathParams, queryParams, body);
    }

    public static ChecklistRequestBuilder requestBuilder() {
        return new ChecklistRequestBuilder();
    }

    public static Checklist extractChecklist(Response response) {
        return extract(response, Checklist.class);
    }

    public static class ChecklistRequestBuilder extends ApiRequestBuilder<ChecklistRequestBuilder, ChecklistService> {

        public ChecklistRequestBuilder setCardId(String cardId) {
            addQueryParam("idCard", cardId);
            return this;
        }

        public ChecklistRequestBuilder setName(String name) {
            addQueryParam("name", name);
            return this;
        }

        public ChecklistRequestBuilder setId(String id) {
            addPathParam("id", id);
            return this;
        }

        @Override
        public ChecklistService build() {
            return new ChecklistService(ChecklistUrl, method, pathParams, queryParams, queryBody);
        }
    }
}
