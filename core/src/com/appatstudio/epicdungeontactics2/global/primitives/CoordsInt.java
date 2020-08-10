package com.appatstudio.epicdungeontactics2.global.primitives;

public class CoordsInt {
    public int x;
    public int y;

    public CoordsInt() {
        this.x = 0;
        this.y = 0;
    }

    public CoordsInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(CoordsInt compare) {
        return compare.x == this.x && compare.y == this.y;
    }
}
