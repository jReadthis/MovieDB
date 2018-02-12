package com.example.nano1.moviedb.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nano1 on 2/11/2018.
 */

public class Northeast implements Parcelable
{

    @SerializedName("lat")
    @Expose
    private float lat;
    @SerializedName("lng")
    @Expose
    private float lng;
    public final static Parcelable.Creator<Northeast> CREATOR = new Creator<Northeast>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Northeast createFromParcel(Parcel in) {
            return new Northeast(in);
        }

        public Northeast[] newArray(int size) {
            return (new Northeast[size]);
        }

    }
            ;

    protected Northeast(Parcel in) {
        this.lat = ((float) in.readValue((float.class.getClassLoader())));
        this.lng = ((float) in.readValue((float.class.getClassLoader())));
    }

    public Northeast() {
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lat);
        dest.writeValue(lng);
    }

    public int describeContents() {
        return 0;
    }

}

