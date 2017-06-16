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
}
