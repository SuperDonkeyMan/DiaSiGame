package com.example.songye02.diasigame.test;

import static com.example.songye02.diasigame.timecontroller.TimeController.NONE_TIME_EVENT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.model.shapeview.DirectionKeyView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.timecontroller.TimeController;
import com.example.songye02.diasigame.utils.DpiUtil;
import com.example.songye02.diasigame.utils.ThreadUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private List<BaseShowableView> mShowables = new ArrayList<>();

    private boolean flag;

    private SurfaceHolder surfaceHolder;
    private Paint rectPaint;

    //三个必须的组件
    private TimeController timeController;
    private DirectionKeyView directionKeyView;
    private HeartShapeView heartShapeView;
    private Canvas canvas;

    public MySurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        //        setZOrderOnTop(true);// 设置画布 背景透明
        //        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        surfaceHolder.addCallback(this);
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        timeController = new TimeController();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        dealGlobalVariable();
        flag = true;
        // 初始化键盘
        directionKeyView = new DirectionKeyView(this);
        // 初始化主角View
        heartShapeView = new HeartShapeView(getWidth() / 2, getHeight() / 2, 15);
        heartShapeView.setBoundary(getWidth()/2- DpiUtil.dipToPix(200)/2,getHeight()/2- DpiUtil.dipToPix(200)/2,
                DpiUtil.dipToPix(200),DpiUtil.dipToPix(200));
        heartShapeView.setBloodMax(100);
        Thread thread = new Thread(this);
        thread.start();
        // 初始化timeController
        timeController.setStartTime(System.currentTimeMillis());
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
            try {
                long currentStartTime = System.currentTimeMillis();
                int state = timeController.excute(currentStartTime, heartShapeView, mShowables);
                if (state == NONE_TIME_EVENT) {
                    // TODO: 2017/4/25 所有事件执行完毕了
                }
                myDraw();
                logic();
                long currentEndTime = System.currentTimeMillis();
                Log.d("time", "" + (currentEndTime - currentStartTime));
                if (currentEndTime - currentStartTime < DiaSiApplication.TIME_DELAYED) {
                    Thread.sleep(DiaSiApplication.TIME_DELAYED - (currentEndTime - currentStartTime));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {

            }
        }
    }

    private void myDraw() {
        try {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawRect(0, 0, getWidth(), getHeight(), rectPaint);
            //画可移动物
            for (Showable showable : mShowables) {
                showable.draw(canvas);
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
        Iterator<BaseShowableView> iterator = mShowables.iterator();
        while (iterator.hasNext()) {
            BaseShowableView baseMoveableView = iterator.next();
            if (baseMoveableView.isDead()) {
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
                                heartShapeView.setBloodCurrent(heartShapeView.getBloodCurrent()-1);
                                // 游戏结束
                                if(heartShapeView.getBloodCurrent() == 0){
                                    Toast.makeText(getContext(), "GAME OVER", Toast.LENGTH_SHORT).show();
                                }
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
