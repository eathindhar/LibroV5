package com.mahendradevan.eathindhar.librov5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Writer_lounge extends Fragment {

    public Writer_lounge() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_writer_lounge, container, false);

        ImageView fav = (ImageView) view.findViewById(R.id.fav_img);
        ImageView postart = (ImageView) view.findViewById(R.id.postart_img);
        ImageView filter = (ImageView) view.findViewById(R.id.filter_img);

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Favourites.class);
                startActivity(i);
            }
        });

        postart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),PostArticle.class);
                startActivity(i);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Filter.class);
                startActivity(i);
            }
        });
        return view;
    }

}
