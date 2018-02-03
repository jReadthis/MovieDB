package com.example.nano1.moviedb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nano1.moviedb.R;

public class SearchActivity extends AppCompatActivity {

    EditText searchTerm;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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


        searchTerm = findViewById(R.id.et_Search);
        searchButton = findViewById(R.id.btn_Search);
    }


    public void onClick(View view) {
        Intent myIntent = new Intent(SearchActivity.this, ListActivity.class);
        String[] myStrings = new String[]{"search", searchTerm.getText().toString()};
        myIntent.putExtra("list", myStrings);
        SearchActivity.this.startActivity(myIntent);
    }
}
