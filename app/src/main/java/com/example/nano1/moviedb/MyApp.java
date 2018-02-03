package com.example.nano1.moviedb;

import android.app.Application;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by nano1 on 1/31/2018.
 */

public class MyApp extends Application{

    private static EventBus mEventBus;

    public static EventBus getBus() {
        return mEventBus;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mEventBus = EventBus.getDefault();
        Log.i("BUS", "onCreate called");
    }

}
