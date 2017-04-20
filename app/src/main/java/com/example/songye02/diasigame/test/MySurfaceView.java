package com.example.songye02.diasigame.test;

import com.example.songye02.diasigame.model.textview.NormalTextView;
import com.example.songye02.diasigame.model.textview.ParaboleTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by songye02 on 2017/4/13.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private boolean flag;

    private SurfaceHolder surfaceHolder;
    private Paint rectPaint;

    ParaboleTextView paraboleTextView;
    NormalTextView normalTextView;
    Canvas canvas;

    public MySurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
//        setZOrderOnTop(true);// 设置画布 背景透明
//        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        surfaceHolder.addCallback(this);
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        paraboleTextView = new ParaboleTextView(200,200,"吔",50,10,500,400,true);
        normalTextView = new NormalTextView(0,0,0,0,"梁非凡吔屎啦！");
        normalTextView.setTextOrientation(NormalTextView.TEXT_ORIENTATION_VERTICAL);
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

    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();
//            Log.d("time",""+(end - start));
            try {
                if (end - start < 16) {
                    Thread.sleep(16 - (end - start));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void myDraw() {
        try{
            canvas = surfaceHolder.lockCanvas();
            canvas.drawRect(0, 0, getWidth(), getHeight(), rectPaint);
            //        canvas.drawRect(0, 0, getRight(), getBottom(), rectPaint);

            //换行
            //        TextPaint textPaint = new TextPaint();
            //        textPaint.setColor(Color.WHITE);
            //        textPaint.setTextSize(50);
            //        textPaint.setAntiAlias(true);
            //        StringBuilder builder = new StringBuilder();
            //        String[] strings = s.split("");
            //        for(String string:strings){
            //            builder.append(string);
            //            builder.append("\n");
            //        }
            //        builder.deleteCharAt(builder.length()-1);
            //        String newString = builder.toString();
            //        StaticLayout layout = new StaticLayout(newString, textPaint, 300,
            //                Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
            //        canvas.save();
            //        canvas.translate(20, 20);//从20，20开始画
            //        layout.draw(canvas);
            //        canvas.restore();

            paraboleTextView.draw(canvas);
            normalTextView.draw(canvas);
        } catch (Exception e){

        } finally{
            if(flag){
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

    }

    private void logic() {
        paraboleTextView.logic();
        normalTextView.logic();
    }
}
