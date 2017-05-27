package com.example.songye02.diasigame.utils;

/**
 * Created by songye02 on 2017/4/19.
 */

public class CollisionUtil {
    public static boolean isCollisionWithRect(float x1, float y1, float w1, float h1, float x2, float y2, float w2,
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
            if (y1 > y2 || Math.abs(x2 - x1) > w / 2 || Math.abs(x2 - (x1 + w)) < w / 2) {
                return false;
            } else {
                return true;
            }
        } else if (angle == 270) {
            if (y1 + h < y2 || Math.abs(x2 - x1) > w / 2 || Math.abs(x2 - (x1 + w)) < w / 2) {
                return false;
            } else {
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

    public static float getPoint2PointDistance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public static boolean isPointInRec(float px, float py, float x, float y, float w, float h) {
        return px > x && px < (x + w) && py > y && py < (y + h);
    }

    public static boolean isPointInCircle(float cx, float cy, float cr, float x, float y) {
        return MathUtil.getDistance(cx, cy, x, y) < cr;
    }

    /**
     * 判断矩形与圆形碰撞的方法
     * http://www.cnblogs.com/JensenCat/p/4973382.html
     */
    public static boolean isCollisionWithCircle(float cx, float cy, float cr, float x, float y, float w, float h) {
        return isPointInRec(cx, cy, x - cr, y, cr, h) ||
                isPointInRec(cx, cy, x, y - cr, w, cr) ||
                isPointInRec(cx, cy, x + w, y, cr, h) ||
                isPointInRec(cx, cy, x, y + h, w, cr) ||
                isPointInRec(cx, cy, x, y, w, h) ||

                isPointInCircle(cx, cy, cr, x, y) ||
                isPointInCircle(cx, cy, cr, x + w, y) ||
                isPointInCircle(cx, cy, cr, x, y + h) ||
                isPointInCircle(cx, cy, cr, x + w, y + h);
    }
}
