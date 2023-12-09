package com.example.stickherofx;

import javafx.scene.shape.Rectangle;

public class Pillar extends Rectangle {
    private double y;
    private double speed;
    private int height;
    private int minWidth;
    private int maxWidth;
    private double acceleration;

    public Pillar() {
    }

//    public double getY() {
//        return y;
//    }
//
//    public void setY(double y) {
//        this.y = y;
//    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

//    public int getHeight() {
//        return height;
//    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }
}
