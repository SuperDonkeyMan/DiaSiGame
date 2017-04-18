package com.example.songye02.diasigame.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by songye02 on 2017/4/14.
 */

public class RotateTextView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private boolean flag;
    private boolean dead;

    private float centerX;
    private float centerY;
    private int textNum;
    private float radius;
    private float angle;
    private String text;

    private Canvas canvas;
    private Paint paint;
    private Paint rectPaint;
    private SurfaceHolder surfaceHolder;

    public RotateTextView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        radius = 200;
        angle = 0;
        textNum = 12;
        text = "吔";
        //文字画笔
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        //背景画笔
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);

        flag = true;
        dead = false;

        Thread t = new Thread(this);
        t.start();
    }

    private void myDraw() {
        try{
            canvas = surfaceHolder.lockCanvas();
            // TODO: 2017/4/18 将来注意这里，要是大家都绘制一个黑背景的话只有最后一个能显示出来，要在总逻辑中画
            canvas.drawRect(0,0,getWidth(),getHeight(),rectPaint);
            Rect rect = new Rect();
            paint.getTextBounds(text, 0, text.length(), rect);
            canvas.save();
            canvas.rotate(angle, centerX, centerY);
            for (int i = 0; i < textNum; i++) {
                canvas.drawText(text, centerX - (rect.right - rect.left) / 2, centerY - radius - paint.descent(), paint);
                canvas.rotate(360 / textNum, centerX, centerY);
            }
            canvas.restore();
        }catch (Exception e){

        }finally {
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    public void logic() {
        radius+=5;
        angle+=1;
        if(radius>=800){
            dead = true;
        }
    }

    @Override
    public void run() {
        while (flag&&!dead){
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();
            try {
                if(end - start < 30) {
                    Thread.sleep(30 - (end-start));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(dead){
            // TODO: 2017/4/18 将来注意这里，要是大家都绘制一个黑背景的话只有最后一个能显示出来，要在总逻辑中画
            canvas = surfaceHolder.lockCanvas();
            canvas.drawRect(0,0,getWidth(),getHeight(),rectPaint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
