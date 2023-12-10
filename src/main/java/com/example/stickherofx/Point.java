package com.example.stickherofx;

class Point {
    double x;
    double y;

    public Point(double v) {
    }

    public double getX() {
        return x;
    }

    public Point() {
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double calculateLength(Point A){
        double a = ((x - A.getX())*(x - A.getX())) + ((y - A.getY())*(y - A.getY()));
        return (Math.sqrt(a));
    }

    public void setY(double y) {
        this.y = y;
    }
}
