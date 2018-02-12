package com.example.nano1.moviedb.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nano1 on 2/11/2018.
 */

public class OpeningHours implements Parcelable
{

    @SerializedName("open_now")
    @Expose
    private boolean openNow;
    @SerializedName("weekday_text")
    @Expose
    private List<Object> weekdayText = null;
    public final static Parcelable.Creator<OpeningHours> CREATOR = new Creator<OpeningHours>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OpeningHours createFromParcel(Parcel in) {
            return new OpeningHours(in);
        }

        public OpeningHours[] newArray(int size) {
            return (new OpeningHours[size]);
        }

    }
            ;

    protected OpeningHours(Parcel in) {
        this.openNow = ((boolean) in.readValue((boolean.class.getClassLoader())));
        in.readList(this.weekdayText, (java.lang.Object.class.getClassLoader()));
    }

    public OpeningHours() {
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public List<Object> getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(List<Object> weekdayText) {
        this.weekdayText = weekdayText;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(openNow);
        dest.writeList(weekdayText);
    }

    public int describeContents() {
        return 0;
    }

}
