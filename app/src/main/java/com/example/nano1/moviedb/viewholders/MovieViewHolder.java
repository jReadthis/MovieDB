package com.example.nano1.moviedb.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nano1.moviedb.R;

/**
 * Created by nano1 on 1/28/2018.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public TextView txtTitle, txtReleaseDate, txtDesc;
    public ImageView imgPoster;

    public MovieViewHolder(View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.txt_Title);
        txtReleaseDate = itemView.findViewById(R.id.txt_ReleaseDate);
        txtDesc = itemView.findViewById(R.id.txt_Desc);
        imgPoster = itemView.findViewById(R.id.img_Poster);
    }
}
