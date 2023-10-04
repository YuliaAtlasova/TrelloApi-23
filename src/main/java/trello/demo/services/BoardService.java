package trello.demo.services;

import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.entities.Board;

import java.util.Locale;
import java.util.Map;

public class BoardService extends BaseServiceObject {

    private static final String boardUrl = "/boards/{id}";

    public BoardService(String url,
                        Method method,
                        Map<String, String> pathParams,
                        Map<String, String> queryParams,
                        Object body) {
        super(url, method, pathParams, queryParams, body);
    }

    public static BoardRequestBuilder requestBuilder() {
        return new BoardRequestBuilder();
    }

    public static class BoardRequestBuilder extends ApiRequestBuilder<BoardRequestBuilder, BoardService> {

        public BoardRequestBuilder setId(String id) {
            addPathParam("id", id);
            return this;
        }

        public BoardRequestBuilder setName(String name) {
            addQueryParam("name", name);
            return this;
        }
        public BoardRequestBuilder setColor(String color) {
            addQueryParam("prefs_background", color.toLowerCase(Locale.ROOT));
            return this;
        }

        public BoardRequestBuilder setClosed(boolean closed) {
            addQueryParam("closed", String.valueOf(closed));
            return this;
        }

        public BoardRequestBuilder setDesc(String desc) {
            addQueryParam("desc", desc);
            return this;
        }

        @Override
        public BoardService build() {
            return new BoardService(boardUrl, method, pathParams, queryParams, queryBody);
        }
    }

    public static Board extractBoard(Response response) {
        return extract(response, Board.class);
    }

}
