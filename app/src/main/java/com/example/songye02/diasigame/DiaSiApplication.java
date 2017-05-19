package com.example.songye02.diasigame;

import java.io.IOException;
import java.util.Random;

import com.example.songye02.diasigame.utils.DpiUtil;
import com.example.songye02.diasigame.utils.GameStateUtil;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaPlayer;

/**
 * Created by songye02 on 2017/4/21.
 */

public class DiaSiApplication extends Application {

    private static DiaSiApplication context;
    private static int canvasWidth;
    private static int canvasHeight;

    private static Bitmap gunBitmap;
    private static Bitmap liuxingBitmap;
    private static Bitmap feifanBitmap;

    public static final int TIME_DELAYED = 20;
    public static float[] paraboleTextGroupFloatRandoms;
    public static boolean[] paraboleTextGroupBooleanRandoms;
    public static int gameState = GameStateUtil.GAME_STATE_MENU;
    public static int currentPerson = GameStateUtil.PERSON_FEIFAN;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        /**
         *得到枪图片
         * */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        Bitmap gunTemp = BitmapFactory.decodeResource(this.getResources(), R.drawable.gun);
        Matrix matrix = new Matrix();
        // 设置想要的大小
        int newWidth = (int) DpiUtil.dipToPix(50);
        int newHeight = (int) DpiUtil.dipToPix(25);
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / gunTemp.getWidth();
        float scaleHeight = ((float) newHeight) / gunTemp.getHeight();
        matrix.postScale(Math.min(scaleWidth, scaleHeight), Math.min(scaleWidth, scaleHeight));
        gunBitmap = Bitmap.createBitmap(gunTemp, 0, 0, gunTemp.getWidth(), gunTemp.getHeight(), matrix, true);

        /**
         *得到刘醒图片
         * */
        Bitmap liuxingTemp = BitmapFactory.decodeResource(this.getResources(), R.drawable.liuxing);
        // 设置想要的大小 比例 2:5.1
        newWidth = (int) DpiUtil.dipToPix(50);
        newHeight = (int) DpiUtil.dipToPix(127);
        // 计算缩放比例
        scaleWidth = ((float) newWidth) / liuxingTemp.getWidth();
        scaleHeight = ((float) newHeight) / liuxingTemp.getHeight();
        Matrix matrix1 = new Matrix();
        matrix1.postScale(Math.min(scaleWidth, scaleHeight), Math.min(scaleWidth, scaleHeight));
        liuxingBitmap =
                Bitmap.createBitmap(liuxingTemp, 0, 0, liuxingTemp.getWidth(), liuxingTemp.getHeight(), matrix1, true);

        /**
         *得到非凡哥图片
         * */
        Bitmap feifanTemp = BitmapFactory.decodeResource(this.getResources(), R.drawable.feihan);
        // 设置想要的大小 比例 2:5.1
        newWidth = (int) DpiUtil.dipToPix(50);
        newHeight = (int) DpiUtil.dipToPix(127);
        // 计算缩放比例
        scaleWidth = ((float) newWidth) / liuxingTemp.getWidth();
        scaleHeight = ((float) newHeight) / liuxingTemp.getHeight();
        Matrix matrix2 = new Matrix();
        matrix2.postScale(Math.min(scaleWidth, scaleHeight), Math.min(scaleWidth, scaleHeight));
        int i = feifanTemp.getWidth();
        int p = feifanTemp.getHeight();
        feifanBitmap = Bitmap.createBitmap(feifanTemp, 0, 0, i, p, matrix2, true);

        // 创建随机数
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

    public static DiaSiApplication getInstance() {
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

    public static Bitmap getLiuxingBitmap() {
        return liuxingBitmap;
    }

    public static Bitmap getFeifanBitmap() {
        return feifanBitmap;
    }

    public static int getPortraitWidth() {
        return liuxingBitmap.getWidth();
    }

    public static int getPortraitHeight() {
        return liuxingBitmap.getHeight();
    }

}
