package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.utils.CollisionUtil;
import com.example.songye02.diasigame.utils.DpiUtil;
import com.example.songye02.diasigame.utils.MathUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by songye02 on 2017/5/23.
 */

public class RotateTextView extends BaseShowableView {

    private int textNum;
    private float radius;
    private float angle;
    private String text;
    private Rect rect;
    private float radiusMax;

    public RotateTextView(float startX, float startY, String text) {
        super(startX, startY, 0, 0);
        radius = DpiUtil.dipToPix(5);
        radiusMax = DpiUtil.dipToPix(150);
        angle = 0;
        textNum = 8;
        this.text = text;
        //文字画笔
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(DpiUtil.spToPix(13));
        rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        this.collisionable = true;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, startX, startY);
        paint.setAlpha((int)(255*(radiusMax-radius)/radiusMax));
        for (int i = 0; i < textNum; i++) {
            canvas.drawText(text, startX - (rect.right - rect.left) / 2, startY - radius - paint.descent(), paint);
            canvas.rotate(360 / textNum, startX, startY);
        }
        canvas.restore();
    }

    @Override
    public void logic() {
        // 设置消失的边界为 200dp
        radius += 5;
        angle += 1;
        if (radius >= radiusMax) {
            isDead = true;
        }
    }

    // 由于矩形斜着，计算很麻烦，素以用原来代替
    @Override
    protected boolean isCollisionWith(HeartShapeView heartShapeView) {
        float smallR = radius;
        float bigR = radius + (paint.descent() - paint.ascent());
        // 找出心形四顶点到圆心最小的距离
        float d1 = MathUtil.getDistance(startX, startY, heartShapeView.getCurrentX(), heartShapeView
                .getCurrentY());
        float d2 = MathUtil.getDistance(startX, startY,
                heartShapeView.getCurrentX() + heartShapeView.getWidth(), heartShapeView.getCurrentY());
        float d3 = MathUtil.getDistance(startX, startY,
                heartShapeView.getCurrentX(), heartShapeView.getCurrentY() + heartShapeView.getHeight());
        float d4 = MathUtil.getDistance(startX, startY,
                heartShapeView.getCurrentX() + heartShapeView.getWidth(),
                heartShapeView.getCurrentY() + heartShapeView.getHeight());
        float minDistance = Math.min(Math.min(d1, d2), Math.min(d3, d4));
        // 不在碰撞范围内直接返回false
        if (minDistance < smallR || minDistance > bigR) {
            return false;
        }else {
            // 由于y坐标是上小下大，所以是startY - heartShapeView.getCurrentY()
            float heartAngel1 = MathUtil.getYAngel(heartShapeView.getCurrentX() - startX,
                    startY - heartShapeView.getCurrentY());
            float startAngel = angle % (360 / textNum);
            // 找到把heartView夹在中间的两个角度
            float targetAngel1 = 0;
            float targetAngel2 = 0;
            for (int i = 0; i < textNum; i++) {
                if (startAngel > heartAngel1) {
                    targetAngel1 = startAngel - (360 / textNum);
                    targetAngel2 = startAngel;
                    break;
                } else {
                    startAngel += 360 / textNum;
                }
            }
            float textCenterR = radius + (paint.descent() - paint.ascent()) / 2; // 单个字中心到大圈中信的距离
            float textRangeR = (rect.right - rect.left) / 2; // 单个字体模拟成圆的半径
            float x1 = (float) (startX + textCenterR * Math.sin(MathUtil.angel2Radians(targetAngel1)));
            float y1 = (float) (startY - textCenterR * Math.cos(MathUtil.angel2Radians(targetAngel1)));
            float x2 = (float) (startX + textCenterR * Math.sin(MathUtil.angel2Radians(targetAngel2)));
            float y2 = (float) (startY - textCenterR * Math.cos(MathUtil.angel2Radians(targetAngel2)));
            return CollisionUtil.isCollisionWithCircle(x1,y1,textRangeR,heartShapeView.getCurrentX(),heartShapeView
                    .getCurrentY(),heartShapeView.getWidth(),heartShapeView.getHeight()) ||
                    CollisionUtil.isCollisionWithCircle(x2,y2,textRangeR,heartShapeView.getCurrentX(),heartShapeView
                            .getCurrentY(),heartShapeView.getWidth(),heartShapeView.getHeight());
        }



    }

    @Override
    protected void dealWithCollision(HeartShapeView heartShapeView) {
        heartShapeView.setBloodCurrent(heartShapeView.getBloodCurrent() - 1);
        heartShapeView.startTwinkle(15);
    }
}
