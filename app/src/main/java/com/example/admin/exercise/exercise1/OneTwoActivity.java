package com.example.admin.exercise.exercise1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.exercise.R;

public class OneTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_two);

        Circle shape1 = new Circle();
        Log.i("shape 1 color", shape1.getColor());
        Log.i("shape 1 fill", Boolean.toString(shape1.isFilled()));
        Log.i("shape 1 radius", Double.toString(shape1.getRadius()));
        Log.i("shape 1 Area", Double.toString(shape1.getArea()));
        Log.i("shape 1 Perimeter", Double.toString(shape1.getPerimeter()));

        shape1.setRadius(2.0);
        Log.i("shape 1 Area", Double.toString(shape1.getArea()));

        Rectangle shape2 = new Rectangle(1.0, 2.0, "green", false);
        Log.i("shape 2 color", shape2.getColor());
        Log.i("shape 2 fill", Boolean.toString(shape2.isFilled()));
        Log.i("shape 2 Area", Double.toString(shape2.getArea()));

        shape2.setWidth(5.0);
        Log.i("shape 2 Perimeter", Double.toString(shape2.getPerimeter()));

        Shape shape3 = (Shape) shape2;
        Log.i("shape 3 color", shape3.getColor());
        Log.i("shape 3 fill", Boolean.toString(shape3.isFilled()));
    }
}
