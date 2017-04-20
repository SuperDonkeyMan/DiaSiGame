package com.example.songye02.diasigame.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by songye02 on 2017/4/18.
 */

public class ParabolaText extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private boolean flag;
    private boolean dead;

    private String text;
    private float speedX;
    private float speedY;
    private float speedXMax;
    private float speedYMax;
    private float startX;
    private float startY;
    private float lengthX;
    private float lengthY;
    private float currentX;
    private float currentY;

    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Paint rectPaint;
    private Canvas canvas;

    public ParabolaText(Context context) {
        super(context);
        flag = true;
        dead = false;

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        speedXMax = 50;
        speedYMax = 10;
        startX = 200;
        startY = 200;
        lengthX = 500;
        lengthY = 400;
        text = "吔";
        currentX = startX;
        currentY = startY;

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);

        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    private void myDraw() {
        try {
            canvas = surfaceHolder.lockCanvas();
            // TODO: 2017/4/18 将来注意这里，要是大家都绘制一个黑背景的话只有最后一个能显示出来，要在总逻辑中画
            canvas.drawRect(0, 0, getWidth(), getHeight(), rectPaint);
            paint.setTextSize((1 + (speedXMax-speedX) / speedXMax) / 2 * 50);
            paint.setAlpha((int)((1 + (speedXMax-speedX) / speedXMax) / 2 * 255));
            canvas.drawText(text, currentX, currentY - paint.descent(), paint);
        } catch (Exception e) {

        } finally {
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void run() {
        while (flag && !dead) {
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();
            try {
                if (end - start < 30) {
                    Thread.sleep(30 - (end - start));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (dead) {

        }
    }

    private void logic() {

        speedX = speedXMax - speedXMax / lengthX * Math.abs(currentX - startX);
        if (speedX < 0) {
            speedX = 0;
        }
        currentX += speedX;

        speedY = 1 + speedYMax / lengthY * Math.abs(currentY - startY);
        if (speedY > speedYMax) {
            speedY = speedYMax;
        }
        currentY += speedY;

        if (currentY > 1900) {
            dead = true;
        }

    }
}
