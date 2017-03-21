package com.example.mayankaggarwal.appathon;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class SuccessProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_product);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.bg);
        getSupportActionBar().setBackgroundDrawable(drawable);
        getSupportActionBar().setTitle("Added Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
