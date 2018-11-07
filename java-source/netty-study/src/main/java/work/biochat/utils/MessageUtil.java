package work.biochat.utils;

import com.google.gson.Gson;
import work.biochat.entity.ChatMessage;

public class MessageUtil {

    private static Gson gson = new Gson();

    public static ChatMessage parseMessage(String result) {

        ChatMessage message = gson.fromJson(result, ChatMessage.class);
        return  message;

    }

    public static String deParseMessage(ChatMessage message) {
        return  gson.toJson(message);
    }
}
