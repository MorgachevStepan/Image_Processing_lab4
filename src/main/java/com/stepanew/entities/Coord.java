package com.stepanew.entities;

public class Coord {
    public int xCoord;
    public int yCoord;

    public Coord(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                '}';
    }
}
