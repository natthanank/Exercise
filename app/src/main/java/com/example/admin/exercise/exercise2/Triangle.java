package com.example.admin.exercise.exercise2;

public class Triangle implements Shape {

    private double base = 1.0;
    private double height = 1.0;

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }
}
