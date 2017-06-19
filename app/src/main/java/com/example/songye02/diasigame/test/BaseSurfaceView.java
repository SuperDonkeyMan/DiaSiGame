package com.example.songye02.diasigame.test;

import static com.example.songye02.diasigame.timecontroller.TimeController.NONE_TIME_EVENT;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.exception.NoStartTimeException;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.timecontroller.BaseViewHolder;
import com.example.songye02.diasigame.timecontroller.TimeController;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by songye02 on 2017/6/19.
 */

public abstract class BaseSurfaceView<T extends BaseViewHolder,K extends Showable> extends SurfaceView implements SurfaceHolder
        .Callback, Runnable{

    // 当前所有的Showable都在mShowables中
    protected List<K> mShowables = new CopyOnWriteArrayList<>();
    protected boolean flag;
    protected long pausingTime = 0; // 总共暂停的时间
    protected long pausingStartTime = 0; // 开始暂停的时间
    protected long pausingStopTime = 0; // 暂停结束的时间
    protected volatile boolean isPausing = false;
    protected TimeController timeController;
    private SurfaceHolder surfaceHolder;
    protected T viewHolder;
    private Canvas canvas;


    public BaseSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        viewHolder = intViewHolder();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        onSurfaceCreated();
        intViewHolder();
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
            try {
                long currentStartTime = System.currentTimeMillis();
                // 暂停
                if (!isPausing) {
                    dealWhileNotPause(currentStartTime);
                }
                long currentEndTime = System.currentTimeMillis();
                if (currentEndTime - currentStartTime < DiaSiApplication.TIME_DELAYED) {
                    Thread.sleep(DiaSiApplication.TIME_DELAYED - (currentEndTime - currentStartTime));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void dealWhileNotPause(long currentStartTime){
        try {
            int state = timeController.excute(currentStartTime, viewHolder);
            if (state == NONE_TIME_EVENT) {
                onNoEventInTimeController();
            }
            draw();
            myLogic();
        }catch (NoStartTimeException e){
            e.printStackTrace();
        }
    }

    private void draw(){
        try {
            canvas = surfaceHolder.lockCanvas();
            myDraw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (flag) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }


    // 处理暂停的函数
    public void dealWithPauseEvent(boolean isPausing) {
        if(this.isPausing != isPausing){
            this.isPausing = isPausing;
            if (!isPausing) {
                pausingStartTime = System.currentTimeMillis();
                onPause();
            } else {
                pausingStopTime = System.currentTimeMillis();
                pausingTime += pausingStopTime - pausingStartTime;
                if(timeController!=null){
                    timeController.setPauseTime(pausingTime);
                }
                onResume();
            }
        }
    }

    // surfaceCreated调用时调用
    abstract void onSurfaceCreated();

    // 暂停时调用
    abstract void onPause();

    // 暂停结束调用
    abstract void onResume();

    // 将内容画到画布上
    protected abstract void myDraw(Canvas canvas);

    // 逻辑处理函数
    protected abstract void myLogic();

    // TimeController中已经取完所有事件时调用
    protected abstract void onNoEventInTimeController();

    // 初始化viewHolder
    protected abstract T intViewHolder();
}
