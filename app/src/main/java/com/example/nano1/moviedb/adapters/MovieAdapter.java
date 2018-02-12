package com.example.nano1.moviedb.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nano1.moviedb.MyApp;
import com.example.nano1.moviedb.R;
import com.example.nano1.moviedb.pojos.Movie;
import com.example.nano1.moviedb.viewholders.MovieViewHolder;
import java.util.List;


/**
 * Created by nano1 on 1/28/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List movieList;


    public MovieAdapter(List movieList) {
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        assert movieList != null;
        Movie.ResultsEntity m = (Movie.ResultsEntity) movieList.get(position);

        assert holder.txtTitle != null;
        holder.txtTitle.setText(m.getTitle());

        assert  holder.txtReleaseDate != null;
        holder.txtReleaseDate.setText(m.getRelease_date().split("-")[0]);

        assert holder.txtDesc != null;
        holder.txtDesc.setText(m.getOverview());

        String POSTER_BASE_URL = "https://image.tmdb.org/t/p/original";
        MyApp.picassoWithCache.with(holder.imgPoster.getContext())
                .load(POSTER_BASE_URL + m.getPoster_path())
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
