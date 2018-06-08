package com.example.admin.exercise.exercise1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.exercise.R;

public class OneOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_one);

        Shape shape1 = new Shape();
        Log.i("shape 1 color", shape1.getColor());
        Log.i("shape 1 fill", Boolean.toString(shape1.isFilled()));

        Shape shape2 = new Shape("green", false);
        Log.i("shape 2 color", shape2.getColor());
        Log.i("shape 2 fill", Boolean.toString(shape2.isFilled()));

        shape2.setColor("blue");
        Log.i("shape 2 color", shape2.getColor());
        Log.i("shape 2 fill", Boolean.toString(shape2.isFilled()));

        shape2.setFilled(true);
        Log.i("shape 2 color", shape2.getColor());
        Log.i("shape 2 fill", Boolean.toString(shape2.isFilled()));

    }
}
