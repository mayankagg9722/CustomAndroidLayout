package com.example.mayankaggarwal.appathon;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayankaggarwal on 04/03/17.
 */

public class RVConatctList extends RecyclerView.Adapter<RVConatctList.MyViewHolder> {

    List<String> name;
    List<String> number;
    Context context;


    public RVConatctList(List<String> nameList, List<String> numberList, Context activity) {
        this.name = new ArrayList<>();
        this.number = new ArrayList<>();
        this.context = activity;

        for (String n : nameList) {
            this.name.add(n);
        }
        for (String n : numberList) {
            this.number.add(n);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ItemView itemView = new ItemView(context);

        public MyViewHolder(View view) {
            super(view);
            itemView = (ItemView) view.findViewById(R.id.myitem);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemsecondary, parent, false);

        return new RVConatctList.MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final String number = this.number.get(position);
        final String name = this.name.get(position);

        holder.itemView.setValues(false, name, number);

        holder.itemView.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.itemView.getCheck()) {
                    holder.itemView.setImageClick(false);
                    Globals.nameList.remove(name);
                    Globals.numberList.remove(number);
                    AddContact.addButton.setText("Add "+ String.valueOf(Globals.nameList.size())+" People");
                } else {
                    holder.itemView.setImageClick(true);
                    Globals.nameList.add(name);
                    Globals.numberList.add(number);
                    AddContact.addButton.setText("Add "+ String.valueOf(Globals.nameList.size())+" People");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.name.size();
    }


}
