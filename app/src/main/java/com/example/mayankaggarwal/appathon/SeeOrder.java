package com.example.mayankaggarwal.appathon;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

public class SeeOrder extends AppCompatActivity {


    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_order);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d976b6")));
        getSupportActionBar().setTitle("Orders");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ProgressDialog pd = new ProgressDialog(SeeOrder.this);
        pd.setMessage("Uploading");

        pd.show();

        recyclerView=(RecyclerView)findViewById(R.id.orderrecyle);
        int numberOfColumns = 2;
//        recyclerView.setAdapter(new RVOrderList(null,null, this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerView.setAdapter(new RVOrderList(null,null, this));
        pd.dismiss();

    }
}
