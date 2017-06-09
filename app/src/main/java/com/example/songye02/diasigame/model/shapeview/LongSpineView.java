package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.utils.CollisionUtil;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by songye02 on 2017/6/6.
 * 最后的长刺，方向为从左右运动，speedY无用
 */

public class LongSpineView extends BaseShowableView {
    public static final int SPINE_DIRECTION_UP = 1;
    public static final int SPINE_DIRECTION_DOWN = 2;
    private int spineDirection;
    private float spineLength; // 尖刺总共的长度
    private float spineTopHeight = DpiUtil.dipToPix(5); // 矩形上面刺尖的高度
    private float spineWidth = DpiUtil.dipToPix(4);

    public LongSpineView(float startX, float startY, float speedX,int spineDirection, float
            spineLength) {
        super(startX, startY, speedX, 0);
        this.spineDirection = spineDirection;
        this.spineLength = spineLength;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        this.collisionable = true;
    }

    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        switch (spineDirection) {
            case SPINE_DIRECTION_UP:
                canvas.drawRect(currentX, currentY - spineLength + spineTopHeight,
                        currentX + spineWidth, currentY, paint);
                path.moveTo(currentX, currentY - spineLength + spineTopHeight);
                path.lineTo(currentX + spineWidth, currentY - spineLength + spineTopHeight);
                path.lineTo(currentX + spineWidth / 2, currentY - spineLength);
                path.close();
                canvas.drawPath(path, paint);
                break;
            case SPINE_DIRECTION_DOWN:
                canvas.drawRect(currentX, currentY, currentX + spineWidth,
                        currentY + spineLength - spineTopHeight, paint);
                path.moveTo(currentX,currentY + spineLength - spineTopHeight);
                path.lineTo(currentX+spineWidth,currentY + spineLength - spineTopHeight);
                path.lineTo(currentX+spineWidth/2,currentY + spineLength);
                path.close();
                canvas.drawPath(path, paint);
                break;
            default:
                break;
        }
    }

    @Override
    public void logic() {
        currentX+=speedX;
        currentY+=speedY;
        if(currentX+spineWidth<0||currentX> DiaSiApplication.getCanvasWidth()+1){
            isDead = true;
        }
    }

    @Override
    protected boolean isCollisionWith(HeartShapeView heartShapeView) {
         boolean result = false;
         switch (spineDirection){
             case SPINE_DIRECTION_DOWN:
                 result =  CollisionUtil.isCollisionWithRect(heartShapeView.getCurrentX(),heartShapeView.getCurrentY(),
                         heartShapeView.getWidth(),heartShapeView.getHeight(),currentX,currentY,spineWidth,spineLength);
                 break;
             case SPINE_DIRECTION_UP:
                 result =  CollisionUtil.isCollisionWithRect(heartShapeView.getCurrentX(),heartShapeView.getCurrentY(),
                         heartShapeView.getWidth(),heartShapeView.getHeight(),currentX,currentY-spineLength,spineWidth,
                         spineLength);
                 break;
         }
         return result;
    }

    @Override
    protected void dealWithCollision(HeartShapeView heartShapeView) {
        heartShapeView.setBloodCurrent(heartShapeView.getBloodCurrent() - 1);
        heartShapeView.startTwinkle(15);
    }
}
