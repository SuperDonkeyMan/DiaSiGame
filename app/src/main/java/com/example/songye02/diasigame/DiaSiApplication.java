package com.example.songye02.diasigame;

import java.util.Random;

import com.example.songye02.diasigame.utils.DpiUtil;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * Created by songye02 on 2017/4/21.
 */

public class DiaSiApplication extends Application {

    private static Context context;
    private static int canvasWidth;
    private static int canvasHeight;
    private static Bitmap gunBitmap;
    public static final int TIME_DELAYED = 20;
    public static float[] paraboleTextGroupFloatRandoms;
    public static boolean[] paraboleTextGroupBooleanRandoms;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // 得到图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        Bitmap temp = BitmapFactory.decodeResource(this.getResources(), R.drawable.gun);
        Matrix matrix = new Matrix();
        // 设置想要的大小
        int newWidth = 100;
        int newHeight = 50;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / temp.getWidth();
        float scaleHeight = ((float) newHeight) / temp.getHeight();
        matrix.postScale(Math.min(scaleWidth, scaleHeight), Math.min(scaleWidth, scaleHeight));
        gunBitmap = Bitmap.createBitmap(temp,0,0,temp.getWidth(),temp.getHeight(),matrix,true);

        paraboleTextGroupFloatRandoms = createFloatRandoms(300);
        paraboleTextGroupBooleanRandoms = createBooleanRandoms(300);
    }

    private float[] createFloatRandoms(int count) {
        Random random = new Random(10);
        float[] randoms = new float[count * 4];
        for (int i = 0; i < count; i++) {
            randoms[4 * i] = random.nextFloat() * DpiUtil.dipToPix(12.5f);
            randoms[4 * i + 1] = random.nextFloat() * DpiUtil.dipToPix(0.5f) + DpiUtil.dipToPix(2.0f);
            randoms[4 * i + 2] = random.nextFloat() * DpiUtil.dipToPix(125f);
            randoms[4 * i + 3] = random.nextFloat() * DpiUtil.dipToPix(100f);
        }
        return randoms;
    }

    private boolean[] createBooleanRandoms(int count) {
        Random random = new Random(10);
        boolean[] randoms = new boolean[count];
        for (int i = 0; i < count; i++) {
            randoms[i] = random.nextBoolean();
        }
        return randoms;
    }

    public static Context getContext() {
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

    public static Bitmap getGunBitmap() {
        return gunBitmap;
    }

}
