package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.utils.DpiUtil;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by songye02 on 2017/4/19.
 */

public class HeartShapeView extends BaseShowableView {
    private float mWidth;
    private float mHeight;
    private int mColor;
    private float speedMax;
    //当前手柄的参数
    private float currentRad;
    private float currentDistance;

    private float boundaryX;
    private float boundaryY;
    private float boundaryW;
    private float boundaryH;

    public HeartShapeView(float startX, float startY, float speedMax) {
        super(startX, startY, 0, 0);
        mWidth = DpiUtil.dipToPix(12);
        mHeight = DpiUtil.dipToPix(9);
        mColor = Color.RED;
        this.speedMax = speedMax;

        paint = new Paint();
        paint.setColor(mColor);
        paint.setAntiAlias(true);
    }

    public void draw(Canvas canvas) {
        if (currentDistance != 0) {
            speedX = (float) (speedMax * Math.cos(currentRad));
            speedY = (float) (speedMax * Math.sin(currentRad));
            currentX += speedX;
            currentY += speedY;
        }
        canvas.save();
        canvas.translate(currentX, currentY);
        Path path = new Path();
        path.moveTo((float) (0.5 * mWidth), (float) (0.17 * mHeight));
        path.cubicTo((float) (0.15 * mWidth), (float) (-0.35 * mHeight), (float) (-0.4 * mWidth),
                (float) (0.45 * mHeight), (float) (0.5 * mWidth), mHeight);
        path.moveTo((float) (0.5 * mWidth), mHeight);
        path.cubicTo((float) (mWidth + 0.4 * mWidth), (float) (0.45 * mHeight), (float) (mWidth - 0.15 * mWidth),
                (float) (-0.35 * mHeight), (float) (0.5 * mWidth), (float) (0.17 * mHeight));
        path.close();
        canvas.drawPath(path, paint);
        //画边框
        Paint rangePaint = new Paint();
        rangePaint.setColor(Color.RED);
        rangePaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, mWidth, mHeight, rangePaint);
        canvas.restore();
    }

    @Override
    public void logic() {

    }

    public void setCurrentSpeed(float rad, float distance) {
        currentRad = rad;
        currentDistance = distance;
    }

    public float getWidth() {
        return mWidth;
    }

    public float getHeight() {
        return mHeight;
    }
}
