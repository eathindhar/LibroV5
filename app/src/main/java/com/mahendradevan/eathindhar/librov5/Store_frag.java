package com.mahendradevan.eathindhar.librov5;

import android.content.Intent;
import android.media.Image;
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


public class Store_frag extends Fragment {

    ImageView pstbk, cart, filter;
    public Store_frag() {
        // Required empty public constructor
        //Glide.with(getApplicationContext()).load(url).into(image);

    }
    //ArrayList<Books> aa=new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_frag, container, false);
        pstbk=(ImageView)view.findViewById(R.id.post_bk);
        cart=(ImageView) view.findViewById(R.id.cart);
        filter=(ImageView)view.findViewById(R.id.filter_cat);
        RecyclerView rv =(RecyclerView) view.findViewById(R.id.recstore);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        RecyclerAdapter re=new RecyclerAdapter(R.layout.fragment_store_frag,getBooks());
        rv.setAdapter(re);

        pstbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostBook.class);
                startActivity(intent);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Cart.class);
                startActivity(intent);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Filter.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
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
