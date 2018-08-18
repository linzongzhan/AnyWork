package com.qgstudio.anywork.websocket;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Subscription {
    Object subscriber;
    List<SubscriberMethod> methods;

    public Subscription(Object subscriber, List<SubscriberMethod> methods) {
        this.subscriber = subscriber;
        this.methods = methods;
    }

    public void invokeMethods(final Object param) {
        Class<?> paramType = param.getClass();
        for (final SubscriberMethod subscriberMethod : methods) {
            if (subscriberMethod.type == paramType) {

                Observable observable = Observable.create(new Observable.OnSubscribe<SubscriberMethod>() {
                    @Override
                    public void call(Subscriber<? super SubscriberMethod> subscriber) {
                        subscriber.onNext(subscriberMethod);
                    }


                });
                if (subscriberMethod.threadMode == ThreadMode.BACKGROUND) {
                    observable.observeOn(Schedulers.io());
                } else {
                    observable.observeOn(AndroidSchedulers.mainThread());
                }
                observable.subscribe(new Observer<SubscriberMethod>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SubscriberMethod subscriberMethod) {
                        try {
                            subscriberMethod.method.invoke(subscriber, param);
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }

                });

            }
        }
    }
}
