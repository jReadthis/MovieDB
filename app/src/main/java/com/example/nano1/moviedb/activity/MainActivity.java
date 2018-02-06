package com.example.nano1.moviedb.activity;


import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.Spinner;
import android.widget.Toast;
import com.example.nano1.moviedb.GenreEnum;
import com.example.nano1.moviedb.MovieDbService;
import com.example.nano1.moviedb.MyApp;
import com.example.nano1.moviedb.NetworkReceiver;
import com.example.nano1.moviedb.NetworkState;
import com.example.nano1.moviedb.R;
import com.example.nano1.moviedb.pojos.Movie;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int PERMISSIONS_REQUEST_CODE = 1111;

    /**
     * Arrays to hold values for spinner
     */
    private String[] mRatingArray;
    private String[] mVotesArray;
    private String[] mGenreArray;

    private Movie mMovies;
    private List<Movie.ResultsEntity> mResults;
    private Map<String,Integer> mapGenre;
    private float rating;
    private int votes;
    private String key;
    private int genre;
    NetworkReceiver mReciever;
    EventBus bus = MyApp.getBus();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.button);
        mapGenre = loadMap();

        mRatingArray = getResources().getStringArray(R.array.rating);
        mVotesArray = getResources().getStringArray(R.array.votes);
        mGenreArray = getResources().getStringArray(R.array.genre);

        Spinner spinnerRating = findViewById(R.id.spinnerRating);
        Spinner spinnerVotes = findViewById(R.id.spinnerVotes);
        Spinner spinnerGenre = findViewById(R.id.spinnerGenre);

        /**
         * Build spinner to hold rating
         */
        ArrayAdapter<String> ratingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mRatingArray);
        spinnerRating.setAdapter(ratingAdapter);
        setSpinnerListener(spinnerRating, "rating");

        /**
         * Build spinner to hold votes
         */
        ArrayAdapter<String> votesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mVotesArray);
        spinnerVotes.setAdapter(votesAdapter);
        setSpinnerListener(spinnerVotes, "votes");

        /**
         * Build spinner to hold genres
         */
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mGenreArray);
        spinnerGenre.setAdapter(genreAdapter);
        setSpinnerListener(spinnerGenre, "genre");

        /**
         * Check Permissions
         */
        if (!havePermissions()) {
            Log.i(TAG, "Requesting permissions needed for this app.");
            requestPermissions();
        }

    }


    /**
     * Make Call to API call
     * @param view
     */
    public void fetchMovies(View view) {
        Call<Movie> call = MovieDbService.Implementation.get().getMovies(votes, rating, genre, 1);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                mMovies = response.body();
                if (mMovies != null)
                    mResults = mMovies.getResults();
                    Log.i(TAG, String.valueOf(mResults.size()));
                    if (mResults.size() > 1) {
                        int num = randomNumber(1, mResults.size()-1);

                        Movie.ResultsEntity mResult;
                        mResult = mResults.get(num);
                        Log.i(TAG, mResult.getTitle());

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
                Log.i(TAG, t.toString());
            }
        });
    }

    /**
     * Generate Random Number
     * @param min
     * @param max
     * @return random Number
     */
    public int randomNumber(int min, int max){
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }


    /**
     * Load Movie genres in Map
     * @return genres
     */
    private Map<String,Integer> loadMap(){
        Map<String, Integer> genres = new HashMap<>();

        for (GenreEnum g : GenreEnum.values()){
            genres.put(g.getName(), g.getCode());
        }

        return genres;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        if (id == R.id.nav_search) {
            Intent myIntent = new Intent(MainActivity.this, SearchActivity.class);
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_popular) {
            Intent myIntent = new Intent(MainActivity.this, ListActivity.class);
            String[] myStrings = new String[]{"popular"};
            myIntent.putExtra("list", myStrings);
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        this.mReciever = new NetworkReceiver();
        registerReceiver(this.mReciever, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mReciever);
        super.onPause();
        bus.unregister(this);
    }


    /**
     * Called when someone posts an event NetworkStateChanged
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NetworkState event) {
        if (!event.isInternetConnected()) {
            Toast.makeText(this, "No Internet connection!", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, event.getNetworkType() + " Connection", Toast.LENGTH_SHORT).show();}
        }
    }


    private boolean havePermissions() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_CODE);
    }

    private void setSpinnerListener(Spinner spinner, final String spinnerType) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (spinnerType){
                    case "votes":
                        votes = Integer.parseInt(parent.getItemAtPosition(position).toString());
                        break;
                    case "genre":
                        key = parent.getItemAtPosition(position).toString();
                        genre = mapGenre.get(key);
                        break;
                    case "rating":
                        rating = Float.parseFloat(parent.getItemAtPosition(position).toString());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
