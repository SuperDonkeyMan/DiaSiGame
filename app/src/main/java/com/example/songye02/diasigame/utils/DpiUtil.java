package com.example.songye02.diasigame.utils;

import com.example.songye02.diasigame.DiaSiApplication;

import android.util.TypedValue;

/**
 * Created by songye02 on 2017/4/21.
 */

public class DpiUtil {
    public static float dipToPix(float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                DiaSiApplication.getInstance()
                        .getResources()
                        .getDisplayMetrics());
        return px;
    }

    public static float spToPix(float sp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                DiaSiApplication.getInstance()
                        .getResources()
                        .getDisplayMetrics());
        return px;
    }
}
