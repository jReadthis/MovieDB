package com.example.nano1.moviedb;

import android.util.Log;

import com.example.nano1.moviedb.pojos.Movie;

import junit.framework.TestCase;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by nano1 on 5/25/2016.
 */
public class MovieDbServiceTest extends TestCase {

    private final String MOVIE_RESPONSE = "movieResponse";

    public void testDiscoverVoteCount() throws Exception {
        Call<Movie> call = MovieDbService.Implementation.get().discoverVoteCount();
        Response<Movie> response = call.execute();
        assertTrue(response.isSuccessful());

        Movie decodedResponse = response.body();
        List<Movie.ResultsEntity> movies = decodedResponse.getResults();
        for (Movie.ResultsEntity movie : movies){
            Log.i(MOVIE_RESPONSE,movie.getTitle());
        }
    }

    public void testGetMovies() throws Exception {
        Call<Movie> call = MovieDbService.Implementation.get().getMovies(200,6.5f,18,1);
        Response<Movie> response = call.execute();
        assertTrue(response.isSuccessful());

        Movie decodedResponse = response.body();
        List<Movie.ResultsEntity> movies = decodedResponse.getResults();
        for (Movie.ResultsEntity movie : movies){
            Log.i(MOVIE_RESPONSE, movie.getTitle());
            assertTrue(movie.getGenre_ids().contains(18));
        }
    }

    public void testGetMoviesNullParam() throws Exception {
        Call<Movie> call = MovieDbService.Implementation.get().getMovies(200,6.5f,null,1);
        Response<Movie> response = call.execute();
        assertTrue(response.isSuccessful());

        Movie decodedResponse = response.body();
        List<Movie.ResultsEntity> movies = decodedResponse.getResults();
        for (Movie.ResultsEntity movie : movies){
            Log.i(MOVIE_RESPONSE,movie.getTitle() + "\t" + movie.getGenre_ids());
        }
    }

    public void testGetMoviesNullParam1() throws Exception {
        Call<Movie> call = MovieDbService.Implementation.get().getMovies(null,7.5f,null,1);
        Response<Movie> response = call.execute();
        assertTrue(response.isSuccessful());

        Movie decodedResponse = response.body();
        List<Movie.ResultsEntity> movies = decodedResponse.getResults();
        for (Movie.ResultsEntity movie : movies){
            Log.i(MOVIE_RESPONSE,movie.getTitle() + "\t" + movie.getVote_count());
        }
    }
}
