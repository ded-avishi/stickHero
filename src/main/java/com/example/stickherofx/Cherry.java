package com.example.stickherofx;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Cherry{
    private double x;
    private double y;
    private ImageView img;

    public void setImg(ImageView img) {
        this.img = img;
    }

    public ImageView getImg() {
        return img;
    }

    public Cherry(ImageView img) {
        this.img = img;
    }

    public Cherry(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Node getImageView() {
        return this.img;
    }
}
