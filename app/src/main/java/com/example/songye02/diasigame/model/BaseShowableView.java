package com.example.songye02.diasigame.model;

import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

import android.graphics.Paint;

/**
 * Created by songye02 on 2017/4/19.
 * 对于Collisionable，单个的view重写isCollisionWith和dealWithCollision。
 * group直接重写collisionWith分发collison即可
 *
 */

public abstract class BaseShowableView implements Showable, Deadable, Collisionable {

    protected boolean isDead;

    protected float startX, startY;
    protected float currentX, currentY;
    protected float speedX, speedY;
    protected Paint paint;

    protected boolean collisionable = false;
    protected long startCollisonTime = 0;
    protected long collisionTimeThreshold = 500; // 碰撞的阈值为500ms

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

    @Override
    public void collisionWith(HeartShapeView view) {
        if(!collisionable||isDead||view.isHeartDead()){
            return;
        }
        boolean isCollision;
        // 如果在阈值区间外再次发生碰撞，进行处理
        if (System.currentTimeMillis() - startCollisonTime >= collisionTimeThreshold) {
            isCollision = isCollisionWith(view);
            // 刚刚发生碰撞时的处理
            if (isCollision) {
                startCollisonTime = System.currentTimeMillis();
                dealWithCollision(view);
            }
        }
    }

    protected boolean isCollisionWith(HeartShapeView heartShapeView) {return false;}
    protected void dealWithCollision(HeartShapeView heartShapeView){}

    public void setCollisionable(boolean collisionable){
        this.collisionable = collisionable;
    }

    public boolean isCollisionable(){
       return  collisionable;
    }

}
