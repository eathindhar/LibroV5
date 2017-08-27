package com.mahendradevan.eathindhar.librov5;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahendradevan.eathindhar.librov5.Recycler.RecyclerAdapter;

import java.util.ArrayList;


public class Account extends Fragment {

    public Account() {
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        RecyclerView rv =(RecyclerView) view.findViewById(R.id.recacc);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        RecyclerAdapter re=new RecyclerAdapter(R.layout.fragment_account,getBooks());
        rv.setAdapter(re);
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
