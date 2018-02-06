package com.example.nano1.moviedb;

import com.example.nano1.moviedb.pojos.Movie;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nano1 on 5/25/2016.
 */
public interface MovieDbService {

    String KEY = BuildConfig.MOVIE_API_KEY;

    @GET("3/discover/movie?api_key=" + KEY)
    Call<Movie> getMovies(
            @Query("vote_count.gte") Integer votes,
            @Query("vote_average.gte") Float voteAvg,
            @Query("with_genres") Integer genreNum,
            @Query("page") Integer pageNum
    );

    @GET("3/search/movie?api_key=" + KEY)
    Call<Movie> searchMovies(
            @Query("language") String language,
            @Query("query") String searchTerm,
            @Query("include_adult") Boolean x,
            @Query("page") Integer pageNum
    );

    @GET("3/discover/movie?api_key=" + KEY + "&sort_by=popularity.desc")
    Call<Movie> getInTheaters(
            @Query("primary_release_date.gte") String gte,
            @Query("primary_release_date.lte") String lte,
            @Query("page") Integer pageNum
    );

    @GET("3/discover/movie?api_key="+ KEY +"&sort_by=popularity.desc")
    Call<Movie> getPopularMovies();

    @GET("3/discover/movie?api_key="+ KEY +"&vote_count.gte=4000")
    Call<Movie> discoverVoteCount();

    class Implementation{

        public static MovieDbService get(){
            return getBuilder()
                    .build()
                    .create(MovieDbService.class);
        }

        static Retrofit.Builder getBuilder(){
            return new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_THEMOVIEDB_URL)
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
