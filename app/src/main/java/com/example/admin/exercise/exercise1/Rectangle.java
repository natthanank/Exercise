package com.example.admin.exercise.exercise1;

public class Rectangle extends Shape {

    private double width = 1.0;
    private double length = 1.0;

    public Rectangle() {

    }

    public Rectangle(double width, double length, String color, boolean filled) {
        setLength(length);
        setWidth(width);
        setColor(color);
        setFilled(filled);
    }

    public double getArea() {
        return width * length;
    }

    public double getPerimeter() {
        return  2 * width + 2 * length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
