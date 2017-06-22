package com.example.songye02.diasigame.utils;

import com.example.songye02.diasigame.model.shapeview.GunView;

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
        }else {
            return true;
        }

}



    public static boolean isCollisionWithBullet(float x1, float y1, float w, float h, float x2, float y2, float angle,
                                                float width) {
        float x = x1 + w / 2;
        float y = y1 + h / 2;

        float x3 = x2 - (float) Math.cos(MathUtil.angel2Radians(angle));
        float y3 = y2 - (float) Math.sin(MathUtil.angel2Radians(angle));

        float centerAngel = Math.abs(MathUtil.radians2Angel(MathUtil.getAngel(x2, y2, x, y, x3, y3)));
        if (centerAngel > 90) {
            return false;
        } else {
            if (MathUtil.DistanceFromPointToLine(x1, y1, x3, y3, x2, y2) > Math.min(w / 2, h / 2)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static boolean isCollisionWithGun(float x1, float y1, float w1, float h1, GunView gunView) {
        float x2 = gunView.getCurrentX();
        float y2 = gunView.getCurrentY();
        float w2 = gunView.getWidth();
        float h2 = gunView.getHeight();
        // 这里将枪等效成两个矩形
        boolean b1 = isCollisionWithRect(x1,y1,w1,h1,x2,y2,w2,h2*0.2f);
        boolean b2 = isCollisionWithRect(x1,y1,w1,h1,x2+0.8f*w2,y2,w2*0.2f,h2);
        return b1 && b2;

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
