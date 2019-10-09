package com.example.mayankaggarwal.appathon;

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Prefs.setPrefs("storenamelist",Globals.nameList.toString(),this);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.bg);
        getSupportActionBar().setBackgroundDrawable(drawable);
        getSupportActionBar().setTitle("Added People");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView=(TextView)findViewById(R.id.successtext);
        textView.setText("Succeefully Added ("+Globals.nameList.size()+") people");

    }
}
