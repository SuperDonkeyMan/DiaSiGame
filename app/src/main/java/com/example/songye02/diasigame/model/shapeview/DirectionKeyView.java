package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.model.Moveable;

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

    public DirectionKeyView(){
        smallCenterX = 120;
        smallCenterY = 120;
        smallR = 20;
        smallColor = Color.GRAY;
        smallAlpha = 150;

        bigCenterX = 120;
        bigCenterY = 120;
        bigR = 50;
        bigColor = Color.GRAY;
        bigAlpha = 100;

        smallPaint = new Paint();
        smallPaint.setColor(smallColor);
        smallPaint.setAlpha(smallAlpha);
        smallPaint.setStyle(Paint.Style.FILL);
        smallPaint.setAntiAlias(true);

        bigPaint = new Paint();
        bigPaint.setColor(smallColor);
        bigPaint.setAlpha(smallAlpha);
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
        }
        else
    }

}
