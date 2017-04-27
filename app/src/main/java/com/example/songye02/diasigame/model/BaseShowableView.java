package com.example.songye02.diasigame.model;

import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;

/**
 * Created by songye02 on 2017/4/19.
 */

public abstract class BaseShowableView implements Showable, Deadable {

    protected boolean isDead;

    protected float startX, startY;
    protected float currentX, currentY;
    protected float speedX, speedY;
    protected Paint paint;


    public BaseShowableView(float startX, float startY, float speedX, float speedY) {
        this.startX = startX;
        this.startY = startY;
        this.speedX = speedX;
        this.speedY = speedY;

        currentX = this.startX;
        currentY = this.startY;
    }

    public float getCurrentX() {
        return currentX;
    }

    public float getCurrentY() {
        return currentY;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }
}
