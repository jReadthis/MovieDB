package com.example.nano1.moviedb.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nano1 on 2/11/2018.
 */

public class Southwest implements Parcelable {

    @SerializedName("lat")
    @Expose
    private float lat;
    @SerializedName("lng")
    @Expose
    private float lng;
    public final static Parcelable.Creator<Southwest> CREATOR = new Creator<Southwest>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Southwest createFromParcel(Parcel in) {
            return new Southwest(in);
        }

        public Southwest[] newArray(int size) {
            return (new Southwest[size]);
        }

    }
            ;

    protected Southwest(Parcel in) {
        this.lat = ((float) in.readValue((float.class.getClassLoader())));
        this.lng = ((float) in.readValue((float.class.getClassLoader())));
    }

    public Southwest() {
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
