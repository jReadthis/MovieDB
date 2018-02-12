package com.example.nano1.moviedb.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nano1.moviedb.R;
import com.example.nano1.moviedb.pojos.Result;
import com.example.nano1.moviedb.pojos.Theater;
import com.example.nano1.moviedb.service.PlacesService;

import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapDataFragment extends Fragment {

    private static final String TAG = MapDataFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "place";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Theater mParam1;
    private String mParam2;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;

    private OnFragmentInteractionListener mListener;

    public MapDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapDataFragment newInstance(Theater param1, String param2) {
        MapDataFragment fragment = new MapDataFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_data,container,false);
        textView1 = view.findViewById(R.id.theater1);
        textView2 = view.findViewById(R.id.theater2);
        textView3 = view.findViewById(R.id.theater3);
        textView4 = view.findViewById(R.id.theater4);
        textView5 = view.findViewById(R.id.theater5);

        if (mParam1 != null){
            List<Result> place = mParam1.getResults();
            textView1.setText(place.get(0).getName());
            textView2.setText(place.get(1).getName());
            textView3.setText(place.get(2).getName());
            textView4.setText(place.get(3).getName());
            textView5.setText(place.get(4).getName());
            Log.i(TAG, place.toString());
            place.get(0).getOpeningHours().isOpenNow();
        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateText() {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
