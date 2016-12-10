package com.example.nano1.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] mRatingArray;
    String[] mVotesArray;
    String[] mGenreArray;

    private Movie mMovies;
    private List<Movie.ResultsEntity> mResults;
    private Map<String,Integer> mapGenre;
    private float rating;
    private int votes;
    private String key;
    private int genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button button = (Button) findViewById(R.id.button);
        mapGenre = loadMap();

        mRatingArray = getResources().getStringArray(R.array.rating);
        mVotesArray = getResources().getStringArray(R.array.votes);
        mGenreArray = getResources().getStringArray(R.array.genre);

        Spinner spinnerRating = (Spinner) findViewById(R.id.spinnerRating);
        Spinner spinnerVotes = (Spinner) findViewById(R.id.spinnerVotes);
        Spinner spinnerGenre = (Spinner) findViewById(R.id.spinnerGenre);

        ArrayAdapter<String> ratingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mRatingArray);
        spinnerRating.setAdapter(ratingAdapter);

        spinnerRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rating = Float.parseFloat(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> votesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mVotesArray);
        spinnerVotes.setAdapter(votesAdapter);

        spinnerVotes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                votes = Integer.parseInt(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mGenreArray);
        spinnerGenre.setAdapter(genreAdapter);

        spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                key = parent.getItemAtPosition(position).toString();
                genre = mapGenre.get(key);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void fetchMovies(View view) {
        Call<Movie> call = MovieDbService.Implementation.get().getMovies(votes, rating, genre, 1);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                mMovies = response.body();
                mResults = mMovies.getResults();
                Log.i("movieResult", String.valueOf(mResults.size()));
                if (mResults.size() > 1) {
                    int num = randomNumber(1, mResults.size()-1);

                    Movie.ResultsEntity mResult;
                    mResult = mResults.get(num);
                    Log.i("movieResult", mResult.getTitle());

                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra("key", mResult);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(MainActivity.this,"No Movies Found Please Try Again",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });


    }

    public int randomNumber(int min, int max){
        Random rand = new Random();
        int x = min + rand.nextInt(max - min + 1);
        return x;
    }

    public Map<String,Integer> loadMap(){
        Map<String, Integer> genre = new HashMap<>();
        genre.put("Action", 28);
        genre.put("Adventure", 12);
        genre.put("Animation", 16);
        genre.put("Comedy", 35);
        genre.put("Crime", 80);
        genre.put("Documentary", 99);
        genre.put("Drama", 18);
        genre.put("Family", 10751);
        genre.put("Fantasy", 14);
        genre.put("Foreign", 10769);
        genre.put("History", 36);
        genre.put("Horror", 27);
        genre.put("Music", 10402);
        genre.put("Mystery", 9648);
        genre.put("Romance", 10749);
        genre.put("Sci-Fi", 878);
        genre.put("TV Movie", 10770);
        genre.put("Thriller", 53);
        genre.put("War", 10752);
        genre.put("Western", 37);

        return genre;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
