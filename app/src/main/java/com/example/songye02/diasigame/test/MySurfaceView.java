package com.example.songye02.diasigame.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

/**
 * Created by songye02 on 2017/4/13.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private float X;
    private float Y;
    private String s;
    private Paint rectPaint;

    public MySurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
//        setZOrderOnTop(true);// 设置画布 背景透明
//        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);

        s = "我是好人";

        rectPaint = new Paint();
        rectPaint.setColor(Color.RED);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        float fontHeight = -fm.ascent;
        X = 0;
        Y = fontHeight;
        myDraw();
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
        canvas.drawText(s, X, Y, paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Rect rect = new Rect();
//        paint.getTextBounds(s, 0, s.length(), rect);
//        int x = (rect.right - rect.left);
//        int y = (int)(paint.getFontMetrics().descent-paint.getFontMetrics().ascent+0.5);// 四舍五入
//        setMeasuredDimension(x,y);
//    }

}
