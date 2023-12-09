package com.example.stickherofx;

import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;

public class Player extends Point {
    private PlayerState state;
    private boolean flipState;
    private ImageView imageView;
    private int height;
    private int width;
    private double x;
    private double y;
    private double speed;
    private double fallSpeed;
    private int cherriesCount;
    private int Score = 0 ;

    public Player(ImageView imgvu) {
        this.imageView = imgvu;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void flip(){
        if (this.flipState){
            this.flipState = false;
            this.imageView.setY(315);
            this.imageView.setScaleY(-0.25);
            this.imageView.getTransforms().add(new Translate(0,0));
        } else{
            this.flipState = true;
            this.imageView.setY(280);
            this.imageView.setScaleY(0.25);
            this.imageView.getTransforms().add(new Translate(0,0));
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getFallSpeed() {
        return fallSpeed;
    }

    public void setFallSpeed(double fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public int getCherriesCount() {
        return cherriesCount;
    }

    public void setCherriesCount(int cherriesCount) {
        this.cherriesCount = cherriesCount;
    }
}
