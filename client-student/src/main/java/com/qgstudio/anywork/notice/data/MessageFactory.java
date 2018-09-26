package com.qgstudio.anywork.notice.data;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.JsonObject;
import com.qgstudio.anywork.App;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.notice.NoticeActivity;

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
                makeNotice(notice.content);
                break;
            default:
                message = null;
                break;
        }
        return message;
    }

    public static void makeNotice(String content) {
        Log.d("makeNotice", "makeNotice");
        //获取通知管理器
        NotificationManager mNotificationManager = (NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        int icon = R.drawable.ic_icon;
        long when = System.currentTimeMillis();
        //新建一个通知，指定其图标和标题
        Notification notification = new Notification(icon, null, when);//第一个参数为图标,第二个参数为标题,第三个为通知时间
        notification.defaults = Notification.DEFAULT_SOUND;//发出默认声音
        Intent openintent = new Intent(App.getContext(), NoticeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(App.getContext(), 0, openintent, 0);//当点击消息时就会向系统发送openintent意图
        notification.setLatestEventInfo(App.getContext(), "AnyWork有一条未读公告", content, contentIntent);
        mNotificationManager.notify(1, notification);//第一个参数为自定义的通知唯一标识
    }
}
