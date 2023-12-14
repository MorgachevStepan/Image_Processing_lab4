package com.stepanew.entities;

public class MyColor {
    public int Red;
    public int Green;
    public int Blue;

    public MyColor(int red, int green, int blue) {
        Red = red;
        Green = green;
        Blue = blue;
    }

    @Override
    public String toString() {
        return "Color{" +
                "Red=" + Red +
                ", Green=" + Green +
                ", Blue=" + Blue +
                '}';
    }
}
