package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/4/19.
 */

public class ParaboleTextView extends CollisionNormalTextView {

    private float speedXMax;
    private float speedYMax;
    private float lengthX;
    private float lengthY;
    private boolean isRightDirection;

    public ParaboleTextView(float startX, float startY, String text,
                            float speedXMax, float speedYMax, float lengthX, float lengthY, boolean isRightDirection,
                            int textOrientation) {
        super(startX, startY, 0, 0,Integer.MAX_VALUE, text, textOrientation);
        this.speedXMax = speedXMax;
        this.speedYMax = speedYMax;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.isRightDirection = isRightDirection;
        speedX = speedXMax - speedXMax / lengthX * Math.abs(currentX - startX);
        speedY = 1 + speedYMax / lengthY * Math.abs(currentY - startY);
    }

    @Override
    public void draw(Canvas canvas) {
        textPaint.setTextSize((1 + (speedXMax-speedX) / speedXMax) / 2 * 50);
        textPaint.setAlpha((int)((1 + (speedXMax-speedX) / speedXMax) / 2 * 255));
        canvas.drawText(text, currentX, currentY - textPaint.descent(), textPaint);
    }

    @Override
    public void logic() {
        speedX = speedXMax - speedXMax / lengthX * Math.abs(currentX - startX);
        if (speedX < 0) {
            speedX = 0;
        }
        if(isRightDirection){
            currentX += speedX;
        }else {
            currentX -= speedX;
        }


        speedY = 1 + speedYMax / lengthY * Math.abs(currentY - startY);
        if (speedY > speedYMax) {
            speedY = speedYMax;
        }
        currentY += speedY;

        //todo 这里改为dp
        if (currentY > DpiUtil.dipToPix(250)) {
            isDead = true;
        }
    }
}
