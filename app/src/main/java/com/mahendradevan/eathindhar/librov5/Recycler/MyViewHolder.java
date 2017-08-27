package com.mahendradevan.eathindhar.librov5.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahendradevan.eathindhar.librov5.R;

import org.w3c.dom.Text;

/**
 * Created by Eathindhar on 27-08-2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView img;
    TextView bktit, bkauth, bkdesc;
    ItemClickListner itcl;
    public MyViewHolder(View itemView){
        super(itemView);

        bktit=(TextView) itemView.findViewById(R.id.bktit);
        bkauth=(TextView) itemView.findViewById(R.id.bkauth);
        bkdesc=(TextView)itemView.findViewById(R.id.desc);
        img=(ImageView)itemView.findViewById(R.id.thumbnail);
        itemView.setOnClickListener(this);
    }
    public void setItemclicklistener(ItemClickListner ic){
        this.itcl=ic;
    }

    @Override
    public void onClick(View v) {
        this.itcl.onItemClick(v,getLayoutPosition());
    }
}
