package com.example.songye02.diasigame.model;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by songye02 on 2017/4/19.
 */

public abstract class BaseView {

    protected boolean isDead;

    protected float startX, startY;
    protected float currentX, currentY;
    protected float speedX, speedY;
    protected Paint paint;


    public BaseView(float startX, float startY, float speedX, float speedY) {
        this.startX = startX;
        this.startY = startY;
        this.speedX = speedX;
        this.speedY = speedY;

        currentX = startX;
        currentY = startY;
    }

    public abstract void draw(Canvas canvas);
    public abstract void logic();
}
