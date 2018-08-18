package com.qgstudio.anywork.websocket;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketHolder extends WebSocketListener {
    private static WebSocketHolder defaultHolder;
    private WebSocket webSocket;
    private OkHttpClient client;
    private Map<Object, Subscription> subscriptions;

    private WebSocketHolder() {

    }

    public static WebSocketHolder getDefault() {
        if (defaultHolder == null) {
            defaultHolder = new WebSocketHolder();
        }
        return defaultHolder;
    }

    public void register(Object subscriber) {
        if (subscriptions.get(subscriber) != null) {
            //已经注册过了
            return;
        }
        Class<?> subscriberClass = subscriber.getClass();
        Method[] methods = subscriberClass.getDeclaredMethods();
        List<SubscriberMethod> subscriberMethodList = new ArrayList<>();
        for (Method method : methods) {
            WS wsAnnotation = method.getAnnotation(WS.class);
            if (wsAnnotation != null) {
                SubscriberMethod subscriberMethod = new SubscriberMethod(method.getParameterTypes()[0],
                        wsAnnotation.threadMode(), method);
                subscriberMethodList.add(subscriberMethod);
            }
        }
        Subscription subscription = new Subscription(subscriber, subscriberMethodList);
        if (subscriptions == null) {
            subscriptions = new HashMap<>();
        }
        subscriptions.put(subscriber, subscription);
    }

    public void unregister(Object subscriber) {
        subscriptions.remove(subscriber);
    }

    public void connect(String url) {
        Log.e("webSocketUrl", url);
        if (webSocket == null) {
            Request request = new Request.Builder().url(url).build();
            if (client == null) {
                client = new OkHttpClient.Builder().build();
            }
            client.newWebSocket(request, this);
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        this.webSocket = webSocket;
        Log.e("WebSocketOpen", "opened");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        Log.d("WebSocketMessage", text);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(text, JsonObject.class);

    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        Log.e("WebSocketFailure", t.getMessage());
    }
}
