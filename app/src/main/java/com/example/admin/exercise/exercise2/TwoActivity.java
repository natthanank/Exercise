package com.example.admin.exercise.exercise2;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.exercise.R;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Rectangle shape1 = new Rectangle();
        Log.i("shape 1 Area", Double.toString(shape1.getArea()));

        Triangle shape2 = new Triangle();
        Log.i("shape 2 Area", Double.toString(shape2.getArea()));

        Shape shape3 = (Shape) shape1;
        Log.i("shape 3 Area", Double.toString(shape3.getArea()));

        shape3 = (Shape) shape2;
        Log.i("shape 3 Area", Double.toString(shape3.getArea()));
    }
}
