package com.example.songye02.diasigame.utils;

/**
 * Created by songye02 on 2017/4/19.
 */

public class CollisionUtil {
    public static boolean isCollisonWithRect(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
        if(x1>=x2+w2){
            return false;
        }else if(x1+w1<=x2){
            return false;
        }else if(y1>=y2+h2){
            return false;
        }else if(y1+h1<=y2){
            return false;
        }
        return true;
    }

}
