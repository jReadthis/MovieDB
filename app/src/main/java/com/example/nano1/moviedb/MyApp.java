package com.example.nano1.moviedb;

import android.app.Application;
import android.util.Log;
import com.squareup.picasso.Picasso;
import org.greenrobot.eventbus.EventBus;
import com.jakewharton.picasso.OkHttp3Downloader;
import java.io.File;
import okhttp3.Cache;
import okhttp3.OkHttpClient;


/**
 * Created by nano1 on 1/31/2018.
 */

public class MyApp extends Application{

    private static EventBus mEventBus;
    public static Picasso picassoWithCache;

    public static EventBus getBus() {
        return mEventBus;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mEventBus = EventBus.getDefault();
        Log.i("BUS", "onCreate called");

        File httpCacheDirectory = new File(getCacheDir(), "picasso-cache");
        Cache cache = new Cache(httpCacheDirectory, 15 * 1024 * 1024);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().cache(cache);

        picassoWithCache = new Picasso.Builder(this).downloader(new OkHttp3Downloader(okHttpClientBuilder.build())).build();
        picassoWithCache.setLoggingEnabled(true);
        picassoWithCache.setIndicatorsEnabled(true);
    }

}
