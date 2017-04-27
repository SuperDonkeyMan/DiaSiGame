package com.example.songye02.diasigame.utils;

/**
 * Created by songye02 on 2017/4/19.
 */

public class CollisionUtil {
    public static boolean isCollisonWithRect(float x1, float y1, float w1, float h1, float x2, float y2, float w2,
                                             float h2) {
        if (x1 >= x2 + w2) {
            return false;
        } else if (x1 + w1 <= x2) {
            return false;
        } else if (y1 >= y2 + h2) {
            return false;
        } else if (y1 + h1 <= y2) {
            return false;
        }
        return true;
    }

    public static boolean isCollisionWithBullet(float x1, float y1, float w, float h, float x2, float y2, float angle,
                                                float width) {

        if (angle == 90) {
            if (y1 > y2 || Math.abs(x2 - x1) > w / 2 || Math.abs(x2 - (x1 + w)) < w / 2){
                return false;
            }else {
                return true;
            }
        }else if(angle == 270){
            if (y1 + h < y2 || Math.abs(x2 - x1) > w / 2 || Math.abs(x2 - (x1 + w)) < w / 2){
                return false;
            }else {
                return true;
            }
        }

        if (angle < 90) {
            if (x1 + w < x2 && y1 + h < y2) {
                double k = Math.tan(MathUtil.angel2Radians(180 - angle));
                if (Math.pow(k * x1 - (y1 + h) + (y2 - k * x2), 2) / (Math.pow(k, 2) + 1) <= width * width / 4
                        || Math.pow(k * (x1 + w) - y1 + (y2 - k * x2), 2) / (Math.pow(k, 2) + 1) <= width * width / 4) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (angle < 180) {
            if (x1 > x2 && y1 + h < y2) {
                double k = Math.tan(MathUtil.angel2Radians(180 - angle));
                if (Math.pow(k * x1 - y1 + (y2 - k * x2), 2) / (Math.pow(k, 2) + 1) <= width * width / 4
                        || Math.pow(k * (x1 + w) - (y1 + h) + (y2 - k * x2), 2) / (Math.pow(k, 2) + 1)
                        <= width * width / 4) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (angle < 270) {
            if (x1 > x2 && y1 > y2) {
                double k = -Math.tan(MathUtil.angel2Radians(angle - 180));
                if (Math.pow(k * x1 - (y1 + h) + (y2 - k * x2), 2) / (Math.pow(k, 2) + 1) <= width * width / 4
                        || Math.pow(k * (x1 + w) - y1 + (y2 - k * x2), 2) / (Math.pow(k, 2) + 1) <= width * width / 4) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (x1 + w < x2 && y1 + h < y2) {
                double k = Math.tan(MathUtil.angel2Radians(angle - 180));
                if (Math.pow(k * x1 - y1 + (y2 - k * x2), 2) / (Math.pow(k, 2) + 1) <= width * width / 4
                        || Math.pow(k * (x1 + w) - (y1 + h) + (y2 - k * x2), 2) / (Math.pow(k, 2) + 1)
                        <= width * width / 4) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

    }
}
