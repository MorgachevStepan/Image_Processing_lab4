package com.stepanew.entities;

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

    public int getRed(){
        return color.getRed();
    }

    public int getGreen(){
        return color.getGreen();
    }

    public int getBlue(){
        return color.getBlue();
    }

    public int getX(){
        return coord.getxCoord();
    }

    public int getY(){
        return coord.getyCoord();
    }

    public void setRed(int r){
        color.setRed(r);
    }

    public void setGreen(int g){
        color.setGreen(g);
    }

    public void setBlue(int b){
        color.setBlue(b);
    }

    public void setX(int x){
        coord.setxCoord(x);
    }

    public void setY(int y){
        coord.setyCoord(y);
    }

    @Override
    public String toString() {
        return "ColorWithCoord{" +
                "color=" + color +
                ", coord=" + coord +
                '}';
    }
}
