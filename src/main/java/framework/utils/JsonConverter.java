package framework.utils;

import com.google.gson.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonConverter {
    private static Gson gson;

    private JsonConverter() {
        gson = new GsonBuilder().disableHtmlEscaping().create();
    }

    private static Gson getGson() {
        if (gson == null)
            new JsonConverter();
        return gson;
    }

    public static String getJsonString(Object object) {
        return getGson().toJson(object);
    }

    public static <T> T getObject(String jsonString, Class<T> cls) {
        try {
            return getGson().fromJson(jsonString, cls);
        } catch (Throwable e) {
            SmartLogger.logError("Don't converter string to json. String: ".concat(jsonString));
        }

        return null;
    }

    public static <T> T getObject(Object object, Class<T> cls) {
        try {
            return getGson().fromJson(getJsonString(object), cls);
        } catch (Throwable e) {
            SmartLogger.logError("Don't converter object to json. Object: ".concat(object.toString()));
        }

        return null;
    }

    public static boolean isJsonFormat(String jsonString) {
        try {
            getGson().getAdapter(JsonElement.class).fromJson(jsonString);
        } catch (JsonSyntaxException | IOException e) {
            return false;
        }

        return true;
    }

    public static <T> List<T> getList(String jsonString, Class<T> cls) {
        SmartLogger.logInfo("Converting jsonString to List");
        List<T> list = new ArrayList<>();
        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
        jsonArray.forEach(jsonElement -> list.add(getObject(jsonElement.toString(), cls)));

        return list;
    }

    public static <T> List<T> getList(Object object, Class<T> cls) {
        SmartLogger.logInfo("Converting object to List");
        List<T> list = new ArrayList<>();
        JsonArray jsonArray = JsonParser.parseString(getJsonString(object)).getAsJsonArray();
        jsonArray.forEach(jsonElement -> list.add(getObject(jsonElement.toString(), cls)));

        return list;
    }
}