package com.example.admin.exercise.exercise1;

public class Circle extends Shape {
    private double radius = 1.0;

    public Circle() {

    }

    public Circle(double radius) {
        setRadius(radius);
    }

    public Circle(double radius, String color, boolean filled) {
        setRadius(radius);
        setColor(color);
        setFilled(filled);
    }

    public double getArea() {
        return 3.1416 * getRadius() * getRadius();
    }

    public double getPerimeter() {
        return 2 * 3.1416 * getRadius();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
