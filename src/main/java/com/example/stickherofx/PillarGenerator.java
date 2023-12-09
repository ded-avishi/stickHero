package com.example.stickherofx;

public class PillarGenerator {
    //  singleton class PillarGenerator is used to generate Pillar objects
    private static PillarGenerator gen = null;

    public static PillarGenerator getInstance() {
        if (gen == null) {
            gen = new PillarGenerator();
        }
        return gen;
    }

    public Pillar newPillar(){
        return new Pillar();
    }
}