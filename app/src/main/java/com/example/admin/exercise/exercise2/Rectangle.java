package com.example.admin.exercise.exercise2;

public class Rectangle implements Shape {

    private double length = 1.0;
    private double width = 1.0;
    @Override
    public double getArea() {
        return length * width;
    }
}
