package com.example.mayankaggarwal.appathon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.Random;

/**
 * Created by mayankaggarwal on 05/03/17.
 */

public class RVOrderList extends RecyclerView.Adapter<RVOrderList.MyViewHolder>  {

    Context context;

    String[] name={"Mayank Aggarwal","Navdeesh Ahuja","Sunny Chakroboraty","Ramesh","Suresh"};
    String[] id={"G56D","D6ER","KMB9","K97DR","UNSHG"};
    String[] cat={"Shoe","Clothes","Watch","Glass","Tank"};


    public RVOrderList(List<String> nameList, List<String> numberList, Context activity) {
        this.context=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView orderid;
        TextView desc;

        public MyViewHolder(View view) {
            super(view);
            name=(TextView)view.findViewById(R.id.nameo);
            orderid=(TextView)view.findViewById(R.id.orderido);
            desc=(TextView)view.findViewById(R.id.desco);

        }
    }


    @Override
    public RVOrderList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item_layout, parent, false);

        return new RVOrderList.MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Random rand = new Random();

        int  n = rand.nextInt(5);

        holder.orderid.setText("Order Id: "+id[n]);
        holder.name.setText(name[n]);
        holder.desc.setText(cat[n]);
    }


    @Override
    public int getItemCount() {
        return 16;
    }
}
