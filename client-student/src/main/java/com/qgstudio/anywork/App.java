package com.qgstudio.anywork;

import android.app.Application;
import android.content.Context;

import com.qgstudio.anywork.data.model.User;

/**
 * Created by Yason on 2017/4/14.
 */

public class App extends Application {

    private static Context context;

    private static App app;

    public static App getInstance() {
        return app;
    }

    /**
     * 用于获得全局上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        app = this;
    }

    private User user;

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
