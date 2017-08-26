package com.mahendradevan.eathindhar.librov5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustAdapter extends BaseAdapter {
    Context c;
    ArrayList<imagepickeradapter> imgpkr;

    public CustAdapter(Context c, ArrayList<imagepickeradapter> imgpkr){
        this.c=c;
        this.imgpkr=imgpkr;
    }

    @Override
    public int getCount(){
        return imgpkr.size();
    }

    @Override
    public Object getItem(int i){
        return imgpkr.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        if(view==null){
            //Inflate Custom Layout
            view= LayoutInflater.from(c).inflate(R.layout.model, viewGroup, false);
        }

        final imagepickeradapter imgp = (imagepickeradapter)this.getItem(i);

        TextView nametxt=(TextView)view.findViewById(R.id.nametxt);
        ImageView img = (ImageView)view.findViewById(R.id.img);

        //Bind Data
        nametxt.setText(imgp.getName());
        Picasso.with(c).load(imgp.getUri()).placeholder(R.drawable.image_placeholder).into(img);

        //View ITEM CLICK
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,imgp.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
