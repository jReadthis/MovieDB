package com.example.nano1.moviedb.service;

import com.example.nano1.moviedb.BuildConfig;
import com.example.nano1.moviedb.pojos.Theater;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nano1 on 2/11/2018.
 */

public interface PlacesService {


    @GET("/maps/api/place/nearbysearch/json?")
    Call<Theater> getNearBy(
            @Query("location") String location,
            @Query("radius") Integer radius,
            @Query("type") String type,
            @Query("key") String key

    );

    class Implementation{

        public static PlacesService get(){
            return getBuilder()
                    .build()
                    .create(PlacesService.class);
        }

        static Retrofit.Builder getBuilder(){
            return new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_PLACES_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(buildLogger());
        }

        static OkHttpClient buildLogger(){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            return new OkHttpClient
                    .Builder()
                    .addInterceptor(interceptor).build();
        }
    }
}
