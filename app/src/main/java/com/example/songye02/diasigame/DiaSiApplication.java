package com.example.songye02.diasigame;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by songye02 on 2017/4/21.
 */

public class DiaSiApplication extends Application {

    private static Context context;
    private static int canvasWidth;
    private static int canvasHeight;
    private static Bitmap gunBitmap;
    public static final int TIME_DELAYED = 20;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        gunBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.gun);

    }

    public static Context getContext(){
        return context;
    }

    public static int getCanvasWidth() {
        return canvasWidth;
    }

    public static int getCanvasHeight() {
        return canvasHeight;
    }

    public static void setCanvasWidth(int canvasWidth1) {
        canvasWidth = canvasWidth1;
    }

    public static void setCanvasHeight(int canvasHeight1) {
        canvasHeight = canvasHeight1;
    }

    public static Bitmap getGunBitmap(){
        return gunBitmap;
    }


}
