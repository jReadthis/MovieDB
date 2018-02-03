package com.example.nano1.moviedb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by nano1 on 1/30/2018.
 */

public class NetworkReceiver extends BroadcastReceiver {

    // post event if there is no Internet connection
    public void onReceive(Context context, Intent intent)
    {
        if(intent.getExtras()!=null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                if (networkInfo != null) {
                    Log.i("State", networkInfo.getTypeName());
                    // there is Internet connection
                    MyApp.getBus().post(new NetworkState(true, networkInfo.getTypeName()));
                } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                    // no Internet connection, send network state changed
                    Log.i("State", "No Connectivity");
                    MyApp.getBus().post(new NetworkState(false));
                }
            }
        }
    }
}
