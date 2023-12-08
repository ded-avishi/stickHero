package com.example.stickherofx;

public class Stick{
    private double length;
    private Point startPoint;
    private Point endPoint;
    private double width;
    private double angle;
    private int fallAngleSpeed;

    public Stick() {
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getFallAngleSpeed() {
        return fallAngleSpeed;
    }

    public void setFallAngleSpeed(int fallAngleSpeed) {
        this.fallAngleSpeed = fallAngleSpeed;
    }
}
