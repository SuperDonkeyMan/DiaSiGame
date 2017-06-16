package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by songye02 on 2017/6/16.
 */

public class ArrowView extends BaseShowableView {

    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;

    private float speedMax = DpiUtil.dipToPix(50); // 快的速度
    private float speedMin = DpiUtil.dipToPix(5); // 慢的速度
    private float distance; // 运动的距离
    private int direction;

    public ArrowView(float startX, float startY, float distance, int direction) {
        super(startX, startY, 0, 0);
        this.direction = direction;
        this.distance = distance;
        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(currentX, currentY);
        float angle = 0;
        switch (direction) {
            case DIRECTION_LEFT:
                angle = 0;
                break;
            case DIRECTION_RIGHT:
                angle = 180;
                break;
            case DIRECTION_DOWN:
                angle = -90;
                break;
            default:
                angle = 90;
        }
        canvas.rotate(angle);
        canvas.drawBitmap(DiaSiApplication.getArrowBitmap(), 0, 0, paint);
        canvas.restore();
    }

    @Override
    public void logic() {
        float distanceNow;
        switch (direction) {
            case DIRECTION_LEFT:
            case DIRECTION_RIGHT:
                distanceNow = Math.abs(currentX - startX);
                break;
            default:
                distanceNow = Math.abs(currentY - startY);
                break;
        }

        if (distanceNow > distance) {
            isDead = true;
        }
        if (distanceNow > distance / 2 - DpiUtil.dipToPix(50) && distanceNow < distance / 2 + DpiUtil.dipToPix(50)) {
            switch (direction) {
                case DIRECTION_LEFT:
                    speedX = -speedMin;
                    speedY = 0;
                    break;
                case DIRECTION_RIGHT:
                    speedX = speedMin;
                    speedY = 0;
                    break;
                case DIRECTION_DOWN:
                    speedX = 0;
                    speedY = speedMin;
                    break;
                default:
                    speedX = 0;
                    speedY = -speedMin;
                    break;
            }
        } else {
            switch (direction) {
                case DIRECTION_LEFT:
                    speedX = -speedMax;
                    speedY = 0;
                    break;
                case DIRECTION_RIGHT:
                    speedX = speedMax;
                    speedY = 0;
                    break;
                case DIRECTION_DOWN:
                    speedX = 0;
                    speedY = speedMax;
                    break;
                default:
                    speedX = 0;
                    speedY = -speedMax;
                    break;
            }
        }
        currentX += speedX;
        currentY += speedY;
    }
}
