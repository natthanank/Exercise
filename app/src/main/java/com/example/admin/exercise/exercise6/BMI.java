package com.example.admin.exercise.exercise6;

import android.os.Parcel;
import android.os.Parcelable;

public class BMI implements Parcelable{

    private double weight;
    private double height;
    private boolean isMale;
    private boolean isAdult;

    protected BMI(Parcel in) {
        weight = in.readInt();
        height = in.readInt();
        isMale = in.readByte() != 0;
        isAdult = in.readByte() != 0;
    }

    public static final Creator<BMI> CREATOR = new Creator<BMI>() {
        @Override
        public BMI createFromParcel(Parcel in) {
            return new BMI(in);
        }

        @Override
        public BMI[] newArray(int size) {
            return new BMI[size];
        }
    };

    public BMI() {

    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(weight);
        parcel.writeDouble(height);
        parcel.writeByte((byte) (isMale ? 1 : 0));
        parcel.writeByte((byte) (isAdult ? 1 : 0));
    }
}
