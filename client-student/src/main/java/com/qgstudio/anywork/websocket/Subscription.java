package com.qgstudio.anywork.websocket;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Subscription {
    Object subscriber;
    List<SubscriberMethod> methods;

    public Subscription(Object subscriber, List<SubscriberMethod> methods) {
        this.subscriber = subscriber;
        this.methods = methods;
    }
    public void invokeMethods(Object param){
        Class<?> paramType = param.getClass();
        for (SubscriberMethod subscriberMethod : methods) {
            if (subscriberMethod.type ==paramType) {
                try {
                    subscriberMethod.method.invoke(subscriber,param);
                } catch (Exception e) {
                    e.addSuppressed(new Exception("webSocket invoke subscriberMethod error"));
                    e.printStackTrace();
                }
            }
        }
    }
}
