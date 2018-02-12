package com.example.nano1.moviedb.service;

import com.example.nano1.moviedb.BuildConfig;
import com.example.nano1.moviedb.pojos.Theater;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Created by nano1 on 2/11/2018.
 */
public class PlacesServiceTest {


    Call<Theater> call;
    String location;
    int radius;
    String type;
    private static final String KEY= BuildConfig.PLACES_API_KEY;

    @Before
    public void setUp() throws Exception {
        location = "38.8599164,-77.094126";
        radius = 5000;
        type = "movie_theater";
    }

    @After
    public void tearDown() throws Exception {
        call = null;
    }

    @Test
    public void getNearByResult() throws Exception {
        call = PlacesService.Implementation.get().getNearBy(location,radius,type,KEY);
        Response<Theater> response = call.execute();
        assertTrue(response.isSuccessful());
    }

    @Test
    public void getNearByResultSize() throws Exception {
        call = PlacesService.Implementation.get().getNearBy(location,radius,type,KEY);
        Response<Theater> response = call.execute();
        assertTrue(response.body().getResults().size() > 0);
    }

    @Test
    public void getNearByResultName() throws Exception {
        call = PlacesService.Implementation.get().getNearBy(location,radius,type,KEY);
        Response<Theater> response = call.execute();
        String expected = "Regal Cinemas Ballston Common 12";
        String actual = response.body().getResults().get(0).getName();
        assertEquals(expected, actual);
    }

}