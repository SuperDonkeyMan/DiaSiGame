package com.example.songye02.diasigame.test;

import static com.example.songye02.diasigame.timecontroller.TimeController.NONE_TIME_EVENT;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.exception.NoStartTimeException;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.timecontroller.BaseViewHolder;
import com.example.songye02.diasigame.timecontroller.TimeController;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by songye02 on 2017/6/19.
 */

public abstract class BaseSurfaceView<T extends BaseViewHolder,K extends Showable> extends SurfaceView implements SurfaceHolder
        .Callback, Runnable{

    public static final boolean PAUSED = true;
    public static final boolean NOTPAUSED = false;

    // 当前所有的Showable都在mShowables中
    protected List<K> mShowables = new CopyOnWriteArrayList<>();
    protected boolean flag;
    protected long pausingTime = 0; // 总共暂停的时间
    protected long pausingStartTime = 0; // 开始暂停的时间
    protected long pausingStopTime = 0; // 暂停结束的时间
    private volatile boolean pauseStatus = NOTPAUSED;
    protected TimeController<T> timeController;
    protected SurfaceHolder surfaceHolder;
    protected T viewHolder;
    private Canvas canvas;


    public BaseSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        timeController = initTimeController();
        // 初始化timeController
        timeController.setStartTime(System.currentTimeMillis());
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        onSurfaceCreated();
        viewHolder = intViewHolder();
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
                if (!pauseStatus) {
                    dealWhileNotPause(currentStartTime);
                }else {
                    dealWhilePause(currentStartTime);
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

    // 暂停时调用
    protected void dealWhilePause(long currentStartTime) {

    }

    // 正常运行（没暂停）时调用
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
        if(this.pauseStatus != isPausing){
            this.pauseStatus = isPausing;
            if (isPausing) {
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

    public boolean getPauseStatus(){
        return pauseStatus;
    }

    // surfaceCreated调用时调用,一般做额外showable的初始化
    abstract void onSurfaceCreated();

    // 暂停时调用
    abstract void onPause();

    // 暂停结束调用
    abstract void onResume();

    // 将内容画到画布上
    protected abstract void myDraw(Canvas canvas);

    // 逻辑处理函数
    protected abstract void myLogic();

    // 初始化viewHolder
    protected abstract T intViewHolder();

    // 初始化viewHolder
    protected abstract TimeController<T> initTimeController();


    // TimeController中已经取完所有事件时调用
    protected void onNoEventInTimeController(){}
}
