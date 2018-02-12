package com.example.nano1.moviedb.pojos;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by nano1 on 2/10/2018.
 */


public class Theater implements Parcelable
{

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @SerializedName("next_page_token")
    @Expose
    private String nextPageToken;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<Theater> CREATOR = new Creator<Theater>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Theater createFromParcel(Parcel in) {
            return new Theater(in);
        }

        public Theater[] newArray(int size) {
            return (new Theater[size]);
        }

    }
            ;

    protected Theater(Parcel in) {
        in.readList(this.htmlAttributions, (java.lang.Object.class.getClassLoader()));
        this.nextPageToken = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.results, (com.example.nano1.moviedb.pojos.Result.class.getClassLoader()));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Theater() {
    }

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(htmlAttributions);
        dest.writeValue(nextPageToken);
        dest.writeList(results);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}




