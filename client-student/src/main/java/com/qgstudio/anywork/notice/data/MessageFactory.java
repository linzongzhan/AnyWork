package com.qgstudio.anywork.notice.data;

import com.google.gson.JsonObject;

public class MessageFactory {
    public static Message fromJsonObject(JsonObject jsonObject) {
        int messageType = jsonObject.get("type").getAsInt();
        Message message;
        switch (messageType) {
            case Message.TYPE_ONLINE_COUNT:
                OnlineCount onlineCount = new OnlineCount();
                onlineCount.type = Message.TYPE_ONLINE_COUNT;
                onlineCount.onlineCount = jsonObject.get("onlineCount").getAsInt();
                message = onlineCount;
                break;
            case Message.TYPE_NOTICE:
                Notice notice = new Notice();
                notice.messageId = jsonObject.get("messageId").getAsInt();
                notice.title = jsonObject.get("title").getAsString();
                notice.content = jsonObject.get("content").getAsString();
                notice.publisher = jsonObject.get("publisher").getAsString();
                notice.status = jsonObject.get("status").getAsInt();
                notice.type = Message.TYPE_NOTICE;
                message = notice;
                break;
            default:
                message = null;
                break;
        }
        return message;
    }
}
