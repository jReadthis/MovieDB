package com.example.nano1.moviedb.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nano1.moviedb.GenreEnum;
import com.example.nano1.moviedb.MyApp;
import com.example.nano1.moviedb.NetworkReceiver;
import com.example.nano1.moviedb.NetworkState;
import com.example.nano1.moviedb.R;
import com.example.nano1.moviedb.pojos.Movie;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MovieActivity extends AppCompatActivity {

    Movie.ResultsEntity movie;
    NetworkReceiver mReciever;
    EventBus bus = MyApp.getBus();
    private static final String TAG = MovieActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                // Add data to the intent, the receiving app will decide
                // what to do with it.
                share.putExtra(Intent.EXTRA_SUBJECT, "Lets Watch This!!");
                share.putExtra(Intent.EXTRA_TEXT, movie.getTitle() );

                startActivity(Intent.createChooser(share, "Share link!"));

            }
        });

        TextView title = findViewById(R.id.textView7);
        TextView year = findViewById(R.id.textView8);
        TextView rating = findViewById(R.id.textView6);
        TextView details = findViewById(R.id.textView9);
        ImageView mImageView = findViewById(R.id.imageView);

        if (getIntent().getExtras() != null)
            movie = getIntent().getExtras().getParcelable("key");

        if (movie != null) {
            //setTitle(movie.getTitle());
            StringBuilder sb = new StringBuilder();
            sb.append(movie.getVote_average()).append("/10 (").append(movie.getVote_count()).append(")");
            assert title != null;
            Log.i(TAG, String.valueOf(movie.getTitle()));
            title.setText(movie.getTitle());
            assert year != null;
            year.setText(movie.getRelease_date().split("-")[0]);
            assert rating != null;
            rating.setText(sb.toString());
            assert details != null;
            details.setText(movie.getOverview());

            String POSTER_BASE_URL = "https://image.tmdb.org/t/p/original";
            Picasso.with(this)
                .load(POSTER_BASE_URL +movie.getPoster_path())
                .into(mImageView);
        }
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

    // method that will be called when someone posts an event NetworkStateChanged
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NetworkState event) {
        if (!event.isInternetConnected()) {
            Toast.makeText(this, "No Internet connection!", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, event.getNetworkType() + " Connection", Toast.LENGTH_SHORT).show();}
        }
    }

}
