package com.example.songye02.diasigame.utils;

/**
 * Created by songye02 on 2017/4/26.
 */

public class MathUtil {
    public static float angel2Radians(float angel) {
        return (float) (Math.PI * angel / 180);
    }

    public static float getDistance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
