package com.example.nano1.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    Movie.ResultsEntity movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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

        TextView title = (TextView) findViewById(R.id.textView7);
        TextView year = (TextView) findViewById(R.id.textView8);
        TextView rating = (TextView) findViewById(R.id.textView6);
        TextView details = (TextView) findViewById(R.id.textView9);
        ImageView mImageView = (ImageView) findViewById(R.id.imageView);

        movie = getIntent().getExtras().getParcelable("key");

        if (movie != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(movie.getVote_average()).append("/10 (").append(movie.getVote_count()).append(")");
            assert title != null;
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

}
