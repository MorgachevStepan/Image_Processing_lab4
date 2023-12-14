package com.stepanew.entities;

public class MyColor {
    private int Red;
    private int Green;
    private int Blue;

    public MyColor(int red, int green, int blue) {
        Red = red;
        Green = green;
        Blue = blue;
    }

    public int getRed() {
        return Red;
    }

    public int getGreen() {
        return Green;
    }

    public int getBlue() {
        return Blue;
    }

    public void setRed(int red) {
        Red = red;
    }

    public void setGreen(int green) {
        Green = green;
    }

    public void setBlue(int blue) {
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
