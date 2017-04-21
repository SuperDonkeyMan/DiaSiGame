package com.example.songye02.diasigame.test;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.model.shapeview.DirectionKeyView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.textview.CollisionNormalTextView;
import com.example.songye02.diasigame.model.textview.NormalTextView;
import com.example.songye02.diasigame.model.textview.ParaboleTextView;

import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by songye02 on 2017/4/13.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable,
        DirectionKeyCallBack {

    private boolean flag;

    private SurfaceHolder surfaceHolder;
    private Paint rectPaint;

    ParaboleTextView paraboleTextView;
    CollisionNormalTextView normalTextView;
    DirectionKeyView directionKeyView;
    HeartShapeView heartShapeView;
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
        dealGlobalVariable();
        flag = true;

        paraboleTextView =
                new ParaboleTextView(200, 200, "吔", 50, 10, 500, 400, true, NormalTextView.TEXT_ORIENTATION_HORIZONTAL);

        normalTextView =
                new CollisionNormalTextView(200, 200, 0, 0, "梁非凡吔屎啦！", NormalTextView.TEXT_ORIENTATION_VERTICAL);

        directionKeyView = new DirectionKeyView(this);

        heartShapeView = new HeartShapeView(getWidth() / 2, getHeight() / 2, 15);
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
            Log.d("time", "" + (end - start));
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
        try {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawRect(0, 0, getWidth(), getHeight(), rectPaint);
            paraboleTextView.draw(canvas);
            normalTextView.draw(canvas);
            directionKeyView.draw(canvas);
            heartShapeView.draw(canvas);
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        } finally {
            if (flag) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void logic() {
        paraboleTextView.logic();
        normalTextView.logic();
        if (paraboleTextView.collisonWith(heartShapeView) || normalTextView.collisonWith(heartShapeView)) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "Collision", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    @Override
    public void dealDirectionKeyDown(float rad, float distance) {
        heartShapeView.setCurrentSpeed(rad, distance);
    }

    @Override
    public void dealDirectionKeyUp(float rad, float distance) {
        heartShapeView.setCurrentSpeed(0, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        directionKeyView.dealTouchEvent(event);
        return true;
    }

    private void dealGlobalVariable() {
        DiaSiApplication.setCanvasWidth(getWidth());
        DiaSiApplication.setCanvasHeight(getHeight());
    }
}
