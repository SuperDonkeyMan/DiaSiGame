package com.example.songye02.diasigame.test;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.model.shapeview.DirectionKeyView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by songye02 on 2017/5/11.
 */

public class MenuSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable,DirectionKeyCallBack {

    private boolean flag;
    private SurfaceHolder surfaceHolder;
    private Paint rectPaint;
    private Canvas canvas;

    private DirectionKeyView directionKeyView;
    private HeartShapeView heartShapeView;
    private PortraitView portraitView;

    public MenuSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        // 初始化键盘
        directionKeyView = new DirectionKeyView(this);
        // 初始化主角View
        heartShapeView = new HeartShapeView(getWidth() / 2, getHeight() / 2, 15);
        heartShapeView
                .setBoundary(getWidth() / 2 - DpiUtil.dipToPix(200) / 2, getHeight() / 2 - DpiUtil.dipToPix(200) / 2,
                        DpiUtil.dipToPix(200), DpiUtil.dipToPix(200));
        heartShapeView.setBloodMax(100);
        heartShapeView.setBloodCurrent(100);
        // 初始化任务画像
        portraitView = new PortraitView(getWidth() / 2 - DiaSiApplication.getPortraitWidth() / 2, DpiUtil.dipToPix(20));
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
        while (flag){
            try {
                myDraw();
                logic();
            }catch (Exception e){

            }

        }
    }

    private void logic() {
        portraitView.logic();
    }

    private void myDraw() {
        try {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawRect(0, 0, getWidth(), getHeight(), rectPaint);
            //画可移动物
            directionKeyView.draw(canvas);
            heartShapeView.draw(canvas);
            portraitView.draw(canvas);
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        } finally {
            if (flag) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void dealDirectionKeyDown(float rad, float distance) {

    }

    @Override
    public void dealDirectionKeyUp(float rad, float distance) {

    }
}
