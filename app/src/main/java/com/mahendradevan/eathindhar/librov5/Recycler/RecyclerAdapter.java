package com.mahendradevan.eathindhar.librov5.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.core.Context;
import com.mahendradevan.eathindhar.librov5.Books;
import com.mahendradevan.eathindhar.librov5.R;

import java.util.ArrayList;

/**
 * Created by Eathindhar on 27-08-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>{
    int c;
    ArrayList<Books> books;

    public RecyclerAdapter(int c, ArrayList<Books> books) {
        this.c = c;
        this.books = books;
    }

    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, null);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.bktit.setText(books.get(position).getBktit());
        holder.bkauth.setText(books.get(position).getBkauth());
        holder.bkdesc.setText(books.get(position).getBkdesc());
        holder.img.setImageResource(books.get(position).getImage());

        holder.setItemclicklistener(new ItemClickListner() {
            @Override
            public void onItemClick(View v, int pos) {
                System.out.println(books.get(pos).getBktit());
            }
        });
    }

    public int getItemCount(){
        return books.size();
    }

}
