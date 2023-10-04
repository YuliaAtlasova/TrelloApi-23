package trello.demo.services;

import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.Board;
import trello.demo.entities.List;

import java.util.Locale;
import java.util.Map;

public class ListService extends BaseServiceObject {

    private static final String listUrl = "/list/{id}";

    public ListService(String url,
                       Method method,
                       Map<String, String> pathParams,
                       Map<String, String> queryParams,
                       Object body) {
        super(url, method, pathParams, queryParams, body);
    }

    public static ListRequestBuilder requestBuilder() {
        return new ListRequestBuilder();
    }

    public static class ListRequestBuilder extends ApiRequestBuilder<ListRequestBuilder, ListService> {

        public ListRequestBuilder setId(String id) {
            addPathParam("id", id);
            return this;
        }

        public ListRequestBuilder setName(String name) {
            addQueryParam("name", name);
            return this;
        }

        public ListRequestBuilder setClosed(boolean closed) {
            addQueryParam("closed", String.valueOf(closed));
            return this;
        }

        public ListRequestBuilder setBoardId(String boardId) {
            addQueryParam("idBoard", boardId);
            return this;
        }

        @Override
        public ListService build() {
            return new ListService(listUrl, method, pathParams, queryParams, queryBody);
        }
    }

    public static List extractList(Response response) {
        return extract(response, List.class);
    }
}
