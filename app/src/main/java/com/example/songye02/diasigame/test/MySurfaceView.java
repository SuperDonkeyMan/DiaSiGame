package com.example.songye02.diasigame.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.model.BaseMoveableView;
import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.model.Moveable;
import com.example.songye02.diasigame.model.shapeview.DirectionKeyView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.textview.CollisionNormalTextView;
import com.example.songye02.diasigame.model.textview.NormalTextView;
import com.example.songye02.diasigame.model.textview.ParaboleTextGroup;
import com.example.songye02.diasigame.model.textview.ParaboleTextView;
import com.example.songye02.diasigame.utils.DpiUtil;
import com.example.songye02.diasigame.utils.ThreadUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
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
    List<Collisionable> mCollisionables = new ArrayList<>();
    List<BaseMoveableView> mMoveables = new ArrayList<>();

    private boolean flag;

    private SurfaceHolder surfaceHolder;
    private Paint rectPaint;

    ParaboleTextView paraboleTextView;
    ParaboleTextGroup paraboleTextGroup;
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
        //初始化碰撞物
        paraboleTextView =
                new ParaboleTextView(200, 200, "吔",
                        //                        50,
                        //                        10,
                        //                        500,
                        //                        400,
                        DpiUtil.dipToPix(12.5f),
                        DpiUtil.dipToPix(2.5f),
                        DpiUtil.dipToPix(125f),
                        DpiUtil.dipToPix(100f),
                        true, NormalTextView.TEXT_ORIENTATION_HORIZONTAL);

        paraboleTextGroup = new ParaboleTextGroup(getWidth() / 2, DpiUtil.dipToPix(50));

        normalTextView =
                new CollisionNormalTextView(200, 200, 0, 0, "梁非凡吔屎啦！", NormalTextView.TEXT_ORIENTATION_VERTICAL);

        directionKeyView = new DirectionKeyView(this);

        heartShapeView = new HeartShapeView(getWidth() / 2, getHeight() / 2, 15);
        //将碰撞物添加入list
        mCollisionables.add(paraboleTextGroup);
        mCollisionables.add(normalTextView);
        mCollisionables.add(paraboleTextView);
        //将可移动物加入list
        mMoveables.add(paraboleTextGroup);
        mMoveables.add(normalTextView);
        mMoveables.add(paraboleTextView);

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
                if (end - start < 20) {
                    Thread.sleep(20 - (end - start));
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
            //画可移动物
            for (Moveable moveable : mMoveables) {
                moveable.draw(canvas);
            }
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
        Iterator<BaseMoveableView> iterator = mMoveables.iterator();
        while (iterator.hasNext()) {
            BaseMoveableView baseMoveableView = iterator.next();
            if (baseMoveableView.isDead) {
                iterator.remove();
            } else {
                baseMoveableView.logic();
                //如果是可碰撞的，就判断碰撞
                if (baseMoveableView instanceof Collisionable) {
                    if (((Collisionable) baseMoveableView).collisonWith(heartShapeView)) {
                        ThreadUtil.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "Collision", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
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

    //获取canvas的宽和高，并将其设置入Application成为全局的变量
    private void dealGlobalVariable() {
        DiaSiApplication.setCanvasWidth(getWidth());
        DiaSiApplication.setCanvasHeight(getHeight());
    }

}
