package com.example.stickherofx;

public class StickGenerator {
    //  singleton class StickGenerator is used to generate Stick objects
    private static StickGenerator gen = null;
    public static StickGenerator getInstance(){
        if (gen==null) {
            gen = new StickGenerator();
        }
        return gen;
    }
    public Stick newStick(){
        return new Stick();
    }

}
