package com.example.songye02.diasigame.test;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.model.shapeview.BottomMenuView;
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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by songye02 on 2017/5/11.
 */

public class MenuSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable, DirectionKeyCallBack,
        View.OnClickListener {

    private static final int MENU_STATE_1 = 0; // 梁非凡来袭
    private static final int MENU_STATE_2 = 1; // 选择选项 让他吔屎，打赏，查看
    private static final int MENU_STATE_3 = 2; // 我感觉你要吔点屎了
    private int menuState;

    private boolean flag;
    private SurfaceHolder surfaceHolder;
    private Paint rectPaint;
    private Canvas canvas;

    private DirectionKeyView directionKeyView;
    private HeartShapeView heartShapeView;
    private PortraitView portraitView;
    private BottomMenuView bottomMenuView;

    public MenuSurfaceView(Context context){
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        menuState = MENU_STATE_1;
    }

    public MenuSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        dealGlobalVariable();
        flag = true;
        // 初始化键盘
        directionKeyView = new DirectionKeyView(this);
        // 初始化主角View
        heartShapeView = new HeartShapeView(getWidth() / 2, getHeight() / 2, 15);
        heartShapeView.setBoundary(getWidth() / 2 - DpiUtil.dipToPix(400) / 2,
                DpiUtil.dipToPix(150),
                DpiUtil.dipToPix(400),
                getHeight() - DpiUtil.dipToPix(150 + 60));
        heartShapeView.setBloodMax(100);
        heartShapeView.setBloodCurrent(100);
        // 初始化任务画像
        portraitView = new PortraitView(getWidth() / 2 - DiaSiApplication.getPortraitWidth() / 2, DpiUtil.dipToPix(10));
        bottomMenuView = new BottomMenuView();
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
                myDraw();
                logic();
            } catch (Exception e) {

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
            bottomMenuView.draw(canvas);
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
        // rad -pi-pi
//        heartShapeView.setCurrentX();
    }

    //获取canvas的宽和高，并将其设置入Application成为全局的变量
    private void dealGlobalVariable() {
        DiaSiApplication.setCanvasWidth(getWidth());
        DiaSiApplication.setCanvasHeight(getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        directionKeyView.dealTouchEvent(event);
        return true;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(),"click",Toast.LENGTH_SHORT).show();
    }
}
