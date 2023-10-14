package trello.demo.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.qameta.allure.Attachment;

public class LogUtils {
    @Attachment(value = "Api Response Json", type = "application/json", fileExtension = ".txt")
    public static JsonObject logApiResponse(final String message) {
        try {
            JsonObject o = JsonParser.parseString(message).getAsJsonObject();
            return o;
        } catch (JsonSyntaxException e) {
            System.out.println(e);
            return JsonParser.parseString("{}").getAsJsonObject();
        }
    }
}
