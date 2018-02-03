package com.example.nano1.moviedb.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by nano1 on 1/29/2018.
 */

public class MovieItemClickListener implements RecyclerView.OnItemTouchListener {

    private AdapterView.OnItemClickListener mListener;
    private GestureDetector mGestureDetector;
    private AdapterView adapterView;

    public MovieItemClickListener(Context context,AdapterView.OnItemClickListener mListener) {
        this.mListener = mListener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)){
            mListener.onItemClick(adapterView, childView, rv.getChildAdapterPosition(childView), rv.getId());
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
