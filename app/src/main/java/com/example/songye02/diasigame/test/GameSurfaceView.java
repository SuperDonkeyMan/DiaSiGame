package com.example.songye02.diasigame.test;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.MenuActivity;
import com.example.songye02.diasigame.R;
import com.example.songye02.diasigame.callback.BottomViewClickCallback;
import com.example.songye02.diasigame.callback.ButtonVisibilityCallBack;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.callback.HeartViewDeadCallback;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.model.shapeview.DirectionKeyView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.model.textview.*;
import com.example.songye02.diasigame.timecontroller.GameTimeController;
import com.example.songye02.diasigame.timecontroller.GameViewHolder;
import com.example.songye02.diasigame.timecontroller.TimeController;
import com.example.songye02.diasigame.utils.DpiUtil;
import com.example.songye02.diasigame.utils.GameStateUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by songye02 on 2017/6/19.
 */

public class GameSurfaceView extends BaseSurfaceView<GameViewHolder, BaseShowableView>
        implements DirectionKeyCallBack, View
        .OnClickListener, HeartViewDeadCallback {
    //几个必须的组件
    private DirectionKeyView directionKeyView;
    private HeartShapeView heartShapeView;
    private PortraitView portraitView;
    private ButtonVisibilityCallBack buttonVisibilityCallBack;
    private BottomViewClickCallback bottomViewClickCallback;
    private Paint rectPaint;

    // 声音相关变量
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.game_bgm);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
        soundPool.load(DiaSiApplication.getInstance(), R.raw.dead, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_jump_small:
                heartShapeView.onSmallJumpClick();
                break;
            case R.id.button_jump_big:
                heartShapeView.onBigJumpClick();
                break;
            case R.id.btn_pause:
                dealWithPauseEvent(PAUSED);
                bottomViewClickCallback.onPauseClick();
                break;
            case R.id.btn_continue:
                dealWithPauseEvent(NOTPAUSED);
                bottomViewClickCallback.onContinueClick();
                break;
            default:
                break;
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
    void onSurfaceCreated() {
        // 初始化键盘
        if (directionKeyView == null) {
            directionKeyView = new DirectionKeyView(this);
        }
        // 初始化主角View
        if (heartShapeView == null) {
            heartShapeView = new HeartShapeView(getWidth() / 2, getHeight() / 2, DpiUtil.dipToPix(2), timeController);
            heartShapeView
                    .setBoundary(getWidth() / 2 - (getHeight() - DpiUtil.dipToPix(150 + 60)) / 2,
                            DpiUtil.dipToPix(150),
                            getHeight() - DpiUtil.dipToPix(150 + 60),
                            getHeight() - DpiUtil.dipToPix(150 + 60));
            heartShapeView.setBloodMax(1);
            heartShapeView.setBloodCurrent(1);
            heartShapeView.setHeartViewDeadCallback(this);
        }
        // 初始化任务画像
        if (portraitView == null) {
            portraitView =
                    new PortraitView(getWidth() / 2 - DiaSiApplication.getPortraitWidth() / 2, DpiUtil.dipToPix(10));
        }
        //开始播放声音
        if (getPauseStatus() == NOTPAUSED) {
            mediaPlayer.start();
        }
    }

    @Override
    void onPause() {
        mediaPlayer.pause();
    }

    @Override
    void onResume() {
        mediaPlayer.start();
    }

    @Override
    protected void myDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), rectPaint);
        portraitView.draw(canvas);
        //画可移动物
        for (Showable showable : mShowables) {
            showable.draw(canvas);
        }
        directionKeyView.draw(canvas);
        heartShapeView.draw(canvas);

    }

    @Override
    protected void myLogic() {
        // 处理portraitView
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
    protected void onNoEventInTimeController() {
        if (mShowables.isEmpty()) {
            // 已经通关
            DiaSiApplication.gameState = GameStateUtil.GAME_STATE_FINISHED;
            mediaPlayer.stop();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getContext(), MenuActivity.class);
                    getContext().startActivity(intent);
                    ((Activity) getContext()).finish();
                    // 设置无动画
                    ((Activity) getContext()).overridePendingTransition(0, 0);
                }
            }, 2000);
        }
    }


    @Override
    protected GameViewHolder intViewHolder() {
        GameViewHolder viewHolder = new GameViewHolder(mShowables, heartShapeView, portraitView);
        viewHolder.setButtonVisibilityCallBack(buttonVisibilityCallBack);
        return viewHolder;
    }

    @Override
    protected TimeController<GameViewHolder> initTimeController() {
        return new GameTimeController();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        directionKeyView.dealTouchEvent(event);
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }

    public void setButtonVisibilityCallBack(ButtonVisibilityCallBack buttonVisibilityCallBack) {
        this.buttonVisibilityCallBack = buttonVisibilityCallBack;
    }

    public void setBottomViewClickCallback(BottomViewClickCallback bottomViewClickCallback) {
        this.bottomViewClickCallback = bottomViewClickCallback;
    }

    @Override
    public void onDead() {
        DiaSiApplication.gameState = GameStateUtil.GAME_STATE_OVER;
        soundPool.play(1, 1, 1, 0, 0, 1);
        mediaPlayer.stop();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getContext(), MenuActivity.class);
                getContext().startActivity(intent);
                ((Activity) getContext()).finish();
                // 设置无动画
                ((Activity) getContext()).overridePendingTransition(0, 0);
            }
        }, 2000);
    }
}
