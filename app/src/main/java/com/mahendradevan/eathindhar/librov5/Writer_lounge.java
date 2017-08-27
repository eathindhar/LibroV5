package com.mahendradevan.eathindhar.librov5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mahendradevan.eathindhar.librov5.Recycler.RecyclerAdapter;

import java.util.ArrayList;


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
        RecyclerView rv =(RecyclerView) view.findViewById(R.id.recwriter);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        RecyclerAdapter re=new RecyclerAdapter(R.layout.fragment_writer_lounge,getBooks());
        rv.setAdapter(re);

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

    private ArrayList<Books> getBooks() {
        ArrayList<Books> books =new ArrayList<>();
        Books book=new Books("Hello", "Hello", "Hello", R.drawable.image_placeholder);
        books.add(book);
        book=new Books("Hello", "Hello", "Hello", R.drawable.image_placeholder);
        books.add(book);
        book=new Books("Hello", "Hello", "Hello", R.drawable.image_placeholder);
        books.add(book);
        book=new Books("Hello", "Hello", "Hello", R.drawable.image_placeholder);
        books.add(book);
        return books;
    }

}
