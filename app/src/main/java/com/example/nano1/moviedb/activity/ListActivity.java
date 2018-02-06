package com.example.nano1.moviedb.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.example.nano1.moviedb.MovieDbService;
import com.example.nano1.moviedb.R;
import com.example.nano1.moviedb.adapters.MovieAdapter;
import com.example.nano1.moviedb.listeners.MovieItemClickListener;
import com.example.nano1.moviedb.pojos.Movie;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListActivity extends AppCompatActivity {

    private static final String TAG = ListActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private String name;
    private String searchTerm;
    private static final String POPULAR = "popular";
    private static final String SEARCH = "search";
    private static final String THEATERS = "theaters";
    private String today;
    private String twoWeeksBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Create handle for the RetrofitInstance interface
         */
        MovieDbService service = MovieDbService.Implementation.get();
        Call<Movie> call = null;
        String[] myArray = getIntent().getExtras().getStringArray("list");
        Log.i("LIST", myArray[0]);
        if (myArray.length > 0)
            name = myArray[0];

        /**
         * Retrieve Movie data
         */
        switch(name){
            case POPULAR:
                call = service.getPopularMovies();
                break;
            case SEARCH:
                if (myArray.length > 1)
                    searchTerm = myArray[1];
                Log.i(TAG, myArray[1]);
                call = service.searchMovies("en-US", searchTerm, false,1);
                break;
            case THEATERS:
                generateDates();
                call = service.getInTheaters(twoWeeksBack,today,1);
            default:

        }

        if (call != null)
            Log.i("TAG", call.request().url() + " ");

            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Movie results = response.body();
                        if (results != null){
                            generateMovieList(results.getResults());
                        }else{
                            Toast.makeText(getBaseContext(), "No Results", Toast.LENGTH_SHORT).show();
                        }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Service Call Failed", Toast.LENGTH_SHORT).show();
                }
            });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void generateDates() {
        Calendar t = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        today = dateFormatter.format(t.getTime());
        Calendar w = t;
        w.add(Calendar.WEEK_OF_MONTH, -2);
        twoWeeksBack = dateFormatter.format(w.getTime());
        Log.i(TAG, "START: " + twoWeeksBack + " END: " + today );
        System.out.println("START: " + twoWeeksBack + " END: " + today);
    }

    /*Method to generate List of Movies using RecyclerView with custom adapter*/
    private void generateMovieList(final List<Movie.ResultsEntity> movieList){
        recyclerView = findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter(movieList);
        recyclerView.setAdapter(movieAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new MovieItemClickListener(this, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), movieList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListActivity.this, MovieActivity.class);
                intent.putExtra("key", movieList.get(position));
                startActivity(intent);
            }
        }));

    }



}
