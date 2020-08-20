package com.appatstudio.epicdungeontactics2.global.managers.map;

import com.appatstudio.epicdungeontactics2.global.primitives.CoordsFloat;
import com.appatstudio.epicdungeontactics2.global.primitives.CoordsInt;
import com.badlogic.gdx.graphics.Color;

public class LightConfigObject {

    private final float radius;
    private final float softness;
    private final Color color;
    private final CoordsFloat offset;

    public LightConfigObject(float radius, float softness, Color color, CoordsFloat offset) {
        this.radius = radius;
        this.softness = softness;
        this.color = color;
        this.offset = offset;
    }

    public Color getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

    public float getSoftness() {
        return softness;
    }

    public CoordsFloat getOffset() {
        return offset;
    }
}
