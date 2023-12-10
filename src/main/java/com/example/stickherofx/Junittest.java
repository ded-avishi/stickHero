package com.example.stickherofx;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Junittest {
    @Test
    public void test1(){
        Stick stickk = new Stick();
        Point origin = new Point(0,0);
        Point A = new Point(2,2);
        stickk.setStartPoint(origin);
        stickk.setEndPoint(A);
        double b = ((origin.getX() - A.getX())*(origin.getX() - A.getX())) + ((origin.getY() - A.getY())*(origin.getY() - A.getY()));
        double ans = Math.sqrt(b);
        boolean flag = false;
        if (ans == origin.calculateLength(A)){
            flag = true;
        }
        assertTrue(flag);
    }


}
