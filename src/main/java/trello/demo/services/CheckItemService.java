package trello.demo.services;

import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.CheckItem;

import java.util.Map;

public class CheckItemService extends BaseServiceObject {

    private static final String checkItemUrl = "/checklists/{id}/checkItems";

    public CheckItemService(String url, Method method, Map<String, String> pathParams, Map<String, String> queryParams, Object body) {
        super(url, method, pathParams, queryParams, body);
    }

    public static CheckItemRequestBuilder requestBuilder() {
        return new CheckItemRequestBuilder();
    }

    public static CheckItem extractCheckItem(Response response) {
        return extract(response, CheckItem.class);
    }

    public static class CheckItemRequestBuilder extends ApiRequestBuilder<CheckItemRequestBuilder, CheckItemService> {

        public CheckItemRequestBuilder setChecklistId(String checklistId) {
            addPathParam("id", checklistId);
            return this;
        }

        public CheckItemRequestBuilder setName(String name) {
            addQueryParam("name", name);
            return this;
        }

        public CheckItemRequestBuilder setChecked(boolean checked) {
            addQueryParam("checked", String.valueOf(checked));
            return this;
        }

        @Override
        public CheckItemService build() {
            return new CheckItemService(checkItemUrl, method, pathParams, queryParams, queryBody);
        }
    }
}
