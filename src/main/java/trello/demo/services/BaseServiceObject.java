package trello.demo.services;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import trello.demo.specifications.RequestSpecProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class BaseServiceObject {

    private String url;
    private Method method;
    private Map<String, String> pathParams;
    private Map<String, String> queryParams;
    private Object queryBody;

    public BaseServiceObject(String url,
                             Method method,
                             Map<String, String> pathParams,
                             Map<String, String> queryParams,
                             Object body) {
        this.url = url;
        this.method = method;
        this.pathParams = pathParams;
        this.queryParams = queryParams;
        this.queryBody = body;
    }

    public static <D> D extract(Response response, Class<D> dataClass) {
        return new Gson().fromJson(response.asString().trim(), dataClass);
    }

    public Response sendRequest() {
        queryParams.put("requestId", String.valueOf(new Random().nextLong()));
        for (String paramName : pathParams.keySet())
            url = url + '{' + paramName + '}';
        return RestAssured
                .given()
                .spec(RequestSpecProvider.BASE_SPEC)
                .pathParams(pathParams)
                .queryParams(queryParams)
                .body(queryBody)
                .log().all()
                .request(method, url)
                .prettyPeek();
    }

    @SuppressWarnings("unchecked")
    public abstract static class ApiRequestBuilder<B extends ApiRequestBuilder<B, T>, T extends BaseServiceObject> {

        protected Method method = Method.GET;
        protected Map<String, String> pathParams = new HashMap<>();
        protected Map<String, String> queryParams = new HashMap<>();
        protected Object queryBody = "";

        public B setMethod(Method method) {
            this.method = method;
            return (B) this;
        }

        protected B addPathParam(String name, String value) {
            this.pathParams.put(name, value);
            return (B) this;
        }

        protected B addQueryParam(String name, String value) {
            this.queryParams.put(name, value);
            return (B) this;
        }

        protected B addQueryBody(Object value) {
            this.queryBody = value;
            return (B) this;
        }

        public abstract T build();
    }
}
