package com.example.nano1.moviedb;


/**
 * Created by nano1 on 1/30/2018.
 */

public class NetworkState {

    private boolean mIsInternetConnected;
    private String networkType;

   public NetworkState(boolean isInternetConnected) {
        this.mIsInternetConnected = isInternetConnected;
    }

    public NetworkState(boolean isInternetConnected, String networkType) {
        this.mIsInternetConnected = isInternetConnected;
        this.networkType = networkType;
    }

    public boolean isInternetConnected() {
        return this.mIsInternetConnected;
    }

    public String getNetworkType() {
        return networkType;
    }
}
