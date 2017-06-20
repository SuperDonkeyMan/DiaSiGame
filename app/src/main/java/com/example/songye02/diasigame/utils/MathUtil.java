package com.example.songye02.diasigame.utils;

/**
 * Created by songye02 on 2017/4/26.
 */

public class MathUtil {
    public static float angel2Radians(float angel) {
        return (float) (Math.PI * angel / 180);
    }

    public static float radians2Angel(float radians) {
        return (float) (radians / Math.PI * 180);
    }

    public static float getDistance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * 得到一个点的相对坐标与Y轴夹角（0~360）
     */
    public static float getYAngel(float x, float y) {
        // 先处理x=0的情况
        if (x >= 0) {
            return radians2Angel((float) Math.atan2(x, y));
        } else {
            return 360 + radians2Angel((float) Math.atan2(x, y));
        }
    }

    public static boolean floatEquals(float x1, float x2, float accuracy) {
        return Math.abs(x1 - x2) <= accuracy;
    }

    public static boolean pointEquals(float x1, float y1, float x2, float y2, float accuracy) {
        return floatEquals(x1, x2, accuracy) && floatEquals(y1, y2, accuracy);
    }

    public static float getXieBianLength(float x1, float x2) {
        return (float) Math.sqrt(Math.pow(x1, 2) + Math.pow(x2, 2));
    }

    // 计算点(x, y)到经过两点(x1, y1)和(x2, y2)的直线的距离
    public static float DistanceFromPointToLine(float x, float y, float x1, float y1, float x2, float y2) {
        float a = y2 - y1;
        float b = x1 - x2;
        float c = x2 * y1 - x1 * y2;

        if (Math.abs(a) > 0.0001 || Math.abs(b) > 0.0001) {
            return (float) Math.abs((a * x + b * y + c) / Math.sqrt(a * a + b * b));
        } else {
            return getDistance(x, y, x1, y1);
        }
    }

    public static float getAngel(float x1, float y1, float x2, float y2, float x3, float y3) {

        float d1 = getDistance(x1, y1, x2, y2);
        float d2 = getDistance(x1, y1, x3, y3);
        float d3 = getDistance(x2, y2, x3, y3);

        return (float)Math.acos((d1 * d1 + d2 * d2 - d3 * d3) / 2f / (d1 * d2));
    }




}
