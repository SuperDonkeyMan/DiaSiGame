package com.example.songye02.diasigame.test;

import static com.example.songye02.diasigame.timecontroller.TimeController.NONE_TIME_EVENT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.R;
import com.example.songye02.diasigame.callback.ButtonVisibilityCallBack;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.exception.NoStartTimeException;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.model.shapeview.BottomMenuView;
import com.example.songye02.diasigame.model.shapeview.DirectionKeyView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.timecontroller.GameTimeController;
import com.example.songye02.diasigame.timecontroller.TimeController;
import com.example.songye02.diasigame.utils.DpiUtil;
import com.example.songye02.diasigame.utils.GameStateUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by songye02 on 2017/4/13.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable,
        DirectionKeyCallBack, View.OnClickListener {

//    private List<BaseShowableView> mShowables = Collections.synchronizedList(new ArrayList<>());
    private List<BaseShowableView> mShowables = new CopyOnWriteArrayList<>();

    private boolean flag;
    private long startTime;

    private SurfaceHolder surfaceHolder;
    private Paint rectPaint;

    //几个必须的组件
    private GameTimeController timeController;
    private DirectionKeyView directionKeyView;
    private HeartShapeView heartShapeView;
    private PortraitView portraitView;
    private BottomMenuView bottomMenuView;
    private ButtonVisibilityCallBack buttonVisibilityCallBack;
    private Canvas canvas;

    // 声音相关变量
    private MediaPlayer mediaPlayer;

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DiaSiApplication.gameState = GameStateUtil.GAME_STATE_GAMING;
        surfaceHolder = getHolder();
        //        setZOrderOnTop(true);// 设置画布 背景透明
        //        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        surfaceHolder.addCallback(this);
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        timeController = new GameTimeController();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        // 初始化键盘
        directionKeyView = new DirectionKeyView(this);
        // 初始化主角View
        heartShapeView = new HeartShapeView(getWidth() / 2, getHeight() / 2, DpiUtil.dipToPix(2));
        heartShapeView
                .setBoundary(getWidth() / 2 - (getHeight() - DpiUtil.dipToPix(150 + 60)) / 2,
                        DpiUtil.dipToPix(150),
                        getHeight() - DpiUtil.dipToPix(150 + 60),
                        getHeight() - DpiUtil.dipToPix(150 + 60));
        heartShapeView.setBloodMax(100);
        heartShapeView.setBloodCurrent(100);
//        heartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
//        heartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_LEFT);
//        buttonVisibilityCallBack.showButton();
        // 初始化任务画像
        portraitView = new PortraitView(getWidth() / 2 - DiaSiApplication.getPortraitWidth() / 2, DpiUtil.dipToPix(10));
        bottomMenuView = new BottomMenuView();
        Thread thread = new Thread(this);
        thread.start();
        // 初始化timeController
        startTime = System.currentTimeMillis();
        timeController.setStartTime(startTime);
        //开始播放声音
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.game_bgm);
        mediaPlayer.start();
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
                int state = timeController.excute(currentStartTime, heartShapeView, mShowables, portraitView);
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
            } catch (NoStartTimeException e) {
                e.printStackTrace();
            }
        }
    }

    private void myDraw() {
        try {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawRect(0, 0, getWidth(), getHeight(), rectPaint);
            portraitView.draw(canvas);
            //画可移动物
            for (Showable showable : mShowables) {
                showable.draw(canvas);
            }
            directionKeyView.draw(canvas);
            heartShapeView.draw(canvas);
            bottomMenuView.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (flag) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void logic() {
        // 处理portraitView
        bottomMenuView.logic();
        portraitView.logic();
        heartShapeView.logic();
        // 处理碰撞物
        Iterator<BaseShowableView> iterator = mShowables.iterator();
        while (iterator.hasNext()) {
            BaseShowableView baseMoveableView = iterator.next();
            if (baseMoveableView.isDead()) {
                /**
                 * 本来是iterator.remove的，但是由于多线程操作list，使用了CopyOnWriteArrayList
                 * 其不能使用iterator.remove，因此用了下面的方法
                 * */
                mShowables.remove(baseMoveableView);
            } else {
                baseMoveableView.logic();
                //如果是可碰撞的，就判断碰撞
                if (baseMoveableView.isCollisionable()) {
                    baseMoveableView.collisionWith(heartShapeView);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_jump_small:
                heartShapeView.onSmallJumpClick();
                break;
            case R.id.button_jump_big:
                heartShapeView.onBigJumpClick();
                break;
            default:
                break;
        }
    }

    public void setButtonVisibilityCallBack(ButtonVisibilityCallBack buttonVisibilityCallBack){
        this.buttonVisibilityCallBack = buttonVisibilityCallBack;
        timeController.setButtonVisibilityCallBack(buttonVisibilityCallBack);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
