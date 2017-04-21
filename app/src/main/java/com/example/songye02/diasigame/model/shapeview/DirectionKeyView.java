package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.model.Moveable;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by songye02 on 2017/4/20.
 */

public class DirectionKeyView implements Moveable {

    //小圈的参数
    private float smallCenterX;
    private float smallCenterY;
    private float smallR;
    private int smallColor;
    private int smallAlpha;

    //大圈的参数
    private float bigCenterX;
    private float bigCenterY;
    private float bigR;
    private int bigColor;
    private int bigAlpha;

    private Paint smallPaint;
    private Paint bigPaint;

    private DirectionKeyCallBack directionKeyCallBack;

    public DirectionKeyView(DirectionKeyCallBack directionKeyCallBack){
        this.directionKeyCallBack = directionKeyCallBack;
        int canvasWidth = DiaSiApplication.getCanvasWidth();
        int canvasHeight = DiaSiApplication.getCanvasHeight();

        smallCenterX = DpiUtil.dipToPix(90);
        smallCenterY = canvasHeight - DpiUtil.dipToPix(90);
        smallR = DpiUtil.dipToPix(20);
        smallColor = Color.GRAY;
        smallAlpha = 150;

        bigCenterX = DpiUtil.dipToPix(90);
        bigCenterY = canvasHeight - DpiUtil.dipToPix(90);
        bigR = DpiUtil.dipToPix(40);
        bigColor = Color.GRAY;
        bigAlpha = 100;

        smallPaint = new Paint();
        smallPaint.setColor(smallColor);
        smallPaint.setAlpha(smallAlpha);
        smallPaint.setStyle(Paint.Style.FILL);
        smallPaint.setAntiAlias(true);

        bigPaint = new Paint();
        bigPaint.setColor(bigColor);
        bigPaint.setAlpha(bigAlpha);
        bigPaint.setStyle(Paint.Style.FILL);
        bigPaint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(smallCenterX,smallCenterY,smallR,smallPaint);
        canvas.drawCircle(bigCenterX,bigCenterY,bigR,bigPaint);
    }

    @Override
    public void logic() {

    }

    public void dealTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            smallCenterX = bigCenterX;
            smallCenterY = bigCenterY;
            directionKeyCallBack.dealDirectionKeyUp(0,0);
        }
        else if(event.getAction() == MotionEvent.ACTION_DOWN ||
                event.getAction() == MotionEvent.ACTION_MOVE){
            if(Math.sqrt(Math.pow((bigCenterX - (int) event.getX()), 2)
                    + Math.pow((bigCenterY - (int) event.getY()), 2)) >= bigR){
                float tempRad = getRad(bigCenterX, bigCenterY, event.getX(), event.getY());
                getXY(bigCenterX, bigCenterY, bigR, tempRad);
                directionKeyCallBack.dealDirectionKeyDown(tempRad,bigR);
            }else {
                smallCenterX = (int) event.getX();
                smallCenterY = (int) event.getY();
                float tempRad = getRad(bigCenterX, bigCenterY, event.getX(), event.getY());
                float tempR = (float) Math.sqrt(Math.pow((bigCenterX - (int) event.getX()), 2)
                        + Math.pow((bigCenterY - (int) event.getY()), 2));
                directionKeyCallBack.dealDirectionKeyDown(tempRad,tempR);
            }
        }
    }

    /***
     * 得到两点之间的弧度
     */
    public float getRad(float px1, float py1, float px2, float py2) {
        //得到两点X的距离
        float x = px2 - px1;
        //得到两点Y的距离
        float y = py1 - py2;
        //算出斜边长
        float xie = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        //得到这个角度的余弦值（通过三角函数中的定理 ：邻边/斜边=角度余弦值）
        float cosAngle = x / xie;
        //通过反余弦定理获取到其角度的弧度
        float rad = (float) Math.acos(cosAngle);
        //注意：当触屏的位置Y坐标<摇杆的Y坐标我们要取反值-0~-180
        if (py2 < py1) {
            rad = -rad;
        }
        return rad;
    }

    public void getXY(float centerX, float centerY, float R, double rad) {
        //获取圆周运动的X坐标
        smallCenterX = (float) (R * Math.cos(rad)) + centerX;
        //获取圆周运动的Y坐标
        smallCenterY = (float) (R * Math.sin(rad)) + centerY;
    }

}
