package com.example.mayankaggarwal.appathon;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mayankaggarwal on 05/03/17.
 */

public class ItemView extends LinearLayout {

    ImageView checkbox;
    TextView name;
    TextView number;
    Boolean checking;

    public void intialize() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.item_view, this, true);

        checkbox = (ImageView) findViewById(R.id.tickimage);
        name = (TextView) findViewById(R.id.name);
        number = (TextView) findViewById(R.id.phone);

    }


    public void setValues(Boolean checked, String nameString, String phoneString) {
        setCheck(checked);
        name.setText(nameString);
        number.setText(phoneString);
    }

    public void setImageClick(Boolean checked){
        setCheck(checked);
    }

    public ImageView getCheckButton() {
        return checkbox;
    }

    public void setCheck(Boolean checked) {
        if (checked != null) {
            if (checked) {
                checking=true;
                checkbox.setImageResource(R.drawable.tick);
            } else {
                checking=false;
                checkbox.setImageResource(R.drawable.untick);
            }
        } else {
            checkbox.setImageResource(R.drawable.untick);
        }
    }

    public Boolean getCheck() {
        return checking;
    }

    public ItemView(Context context) {
        super(context);
        intialize();
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        intialize();
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intialize();
    }


}
