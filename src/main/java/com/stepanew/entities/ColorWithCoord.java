package com.stepanew.entities;

import java.awt.*;

public class ColorWithCoord {
    public MyColor color;
    public Coord coord;

    public ColorWithCoord(MyColor color, Coord coord) {
        this.color = color;
        this.coord = coord;
    }

    public ColorWithCoord(int r, int g, int b, int x, int y){
        this.color = new MyColor(r, g, b);
        this.coord = new Coord(x, y);
    }

    @Override
    public String toString() {
        return "ColorWithCoord{" +
                "color=" + color +
                ", coord=" + coord +
                '}';
    }
}
