package com.example.songye02.diasigame.test;

import com.example.songye02.diasigame.model.textview.ParaboleTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by songye02 on 2017/4/13.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private float X;
    private float Y;
    private String s;
    private Paint rectPaint;
    private Path path;

    public MySurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
//        setZOrderOnTop(true);// 设置画布 背景透明
//        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        surfaceHolder.addCallback(this);
        rectPaint = new Paint();
        rectPaint.setColor(Color.RED);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        ParaboleTextView paraboleTextView = new ParaboleTextView(200,200,0,0,)
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void myDraw() {
        Canvas canvas = surfaceHolder.lockCanvas();
//        canvas.drawRect(0, 0, getRight(), getBottom(), rectPaint);

        //换行
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);
        textPaint.setAntiAlias(true);
        StringBuilder builder = new StringBuilder();
        String[] strings = s.split("");
        for(String string:strings){
            builder.append(string);
            builder.append("\n");
        }
        builder.deleteCharAt(builder.length()-1);
        String newString = builder.toString();
        StaticLayout layout = new StaticLayout(newString, textPaint, 300,
                Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        canvas.save();
        canvas.translate(20, 20);//从20，20开始画
        layout.draw(canvas);
        canvas.restore();

        canvas.drawText(s, X, Y, paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void run() {

    }
}
