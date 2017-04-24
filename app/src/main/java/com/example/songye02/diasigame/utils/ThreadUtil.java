package com.example.songye02.diasigame.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by songye02 on 2017/4/24.
 */

public class ThreadUtil {
    public static void runOnUiThread(Runnable runnable){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }
}
