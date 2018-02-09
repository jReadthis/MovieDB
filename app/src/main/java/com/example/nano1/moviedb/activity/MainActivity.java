package com.example.nano1.moviedb.activity;


import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nano1.moviedb.BuildConfig;
import com.example.nano1.moviedb.GenreEnum;
import com.example.nano1.moviedb.MovieDbService;
import com.example.nano1.moviedb.MyApp;
import com.example.nano1.moviedb.NetworkReceiver;
import com.example.nano1.moviedb.NetworkState;
import com.example.nano1.moviedb.R;
import com.example.nano1.moviedb.Utils;
import com.example.nano1.moviedb.pojos.Movie;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

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
    //private static final int PLACE_PICKER_REQUEST = 1;

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
    /**
     * The container {@link android.view.ViewGroup} for the minimal UI associated with this sample.
     */
    private RelativeLayout mContainer;



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

        mContainer = findViewById(R.id.main_activity_container);

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

//        /**
//         * Check Permissions
//         */
//        if (!havePermissions()) {
//            Log.i(TAG, "Requesting permissions needed for this app.");
//            requestPermissions();
//        }

    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode != PERMISSIONS_REQUEST_CODE){
//            return;
//        }
//        for (int i = 0; i < permissions.length; i++){
//            String permission = permissions[i];
//            if (grantResults[i] == PackageManager.PERMISSION_DENIED){
//                // There are states to watch when a user denies permission when presented with
//                // the Nearby permission dialog: 1) When the user pressed "Deny", but does not
//                // check the "Never ask again" option. In this case, we display a Snackbar which
//                // lets the user kick off the permissions flow again. 2) When the user pressed
//                // "Deny" and also checked the "Never ask again" option. In this case, the
//                // permission dialog will no longer be presented to the user. The user may still
//                // want to authorize location and use the app, and we present a Snackbar that
//                // directs them to go to Settings where they can grant the location permission.
//                if (shouldShowRequestPermissionRationale(permission)) {
//                    Log.i(TAG, "Permission denied without 'NEVER ASK AGAIN': " + permission);
//                    showRequestPermissionsSnackbar();
//                } else {
//                    Log.i(TAG, "Permission denied with 'NEVER ASK AGAIN': " + permission);
//                    showLinkToSettingsSnackbar();
//                }
//            }else{
//                Log.i(TAG, "Permission granted, building GoogleApiClient");
//                //buildGoogleApiClient();
//            }
//        }
//    }

    /**
     * Make KEY call to retrieve Movies then randomly select one
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
        } else if (id == R.id.nav_theaters) {
            Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_new) {
            Intent myIntent = new Intent(MainActivity.this, ListActivity.class);
            String[] myStrings = new String[]{"theaters"};
            myIntent.putExtra("list", myStrings);
            MainActivity.this.startActivity(myIntent);
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

//    /**
//     * Displays {@link Snackbar} instructing user to visit Settings to grant permissions required by
//     * this application.
//     */
//    private void showLinkToSettingsSnackbar() {
//        if (mContainer == null) {
//            return;
//        }
//        Snackbar.make(mContainer,
//                R.string.permission_denied_explanation,
//                Snackbar.LENGTH_INDEFINITE)
//                .setAction(R.string.settings, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // Build intent that displays the App settings screen.
//                        Intent intent = new Intent();
//                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                        Uri uri = Uri.fromParts("package",
//                                BuildConfig.APPLICATION_ID, null);
//                        intent.setData(uri);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                }).show();
//    }

//    /**
//     * Displays {@link Snackbar} with button for the user to re-initiate the permission workflow.
//     */
//    private void showRequestPermissionsSnackbar() {
//        if (mContainer == null) {
//            return;
//        }
//        Snackbar.make(mContainer, R.string.permission_rationale,
//                Snackbar.LENGTH_INDEFINITE)
//                .setAction(R.string.ok, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // Request permission.
//                        ActivityCompat.requestPermissions(MainActivity.this,
//                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                                PERMISSIONS_REQUEST_CODE);
//                    }
//                }).show();
//    }

}
