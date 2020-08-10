package com.appatstudio.epicdungeontactics2.global.primitives;

public class CoordsFloat {
    public float x;
    public float y;

    public CoordsFloat() {
        this.x = 0;
        this.y = 0;
    }

    public CoordsFloat(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(CoordsFloat compare) {
        return compare.x == this.x && compare.y == this.y;
    }
}
