package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

/**
 * Created by songye02 on 2017/5/4.
 */

public class FollowTextView extends PauseViewText {
    protected HeartShapeView heartShapeView;
    protected float speed;

    public FollowTextView(float startX, float startY, HeartShapeView heartShapeView, float speed, int pauseBefore,
                          String text, int textOrientation) {

        // 这里0都是后面计算的，构造函数不需要
        super(startX, startY, 0, 0, pauseBefore, Integer.MAX_VALUE, 0, text, textOrientation);
        this.heartShapeView = heartShapeView;
        this.speed = speed;
    }


    protected static int getFrameCount(float startX, float startY, HeartShapeView heartShapeView, float speed) {
        float endX = heartShapeView.getCurrentX();
        float endY = heartShapeView.getBoundaryY();
        return (int) (Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2)) / speed);
    }

    @Override
    public void logic() {
        if (count < pauseBefore) {
            currentX = startX;
            currentY = startY;
            if (count == pauseBefore - 1) {
                //                endX = getEndX(startX, startY, heartShapeView);
                //                endY = getEndY(startX, startY, heartShapeView);
                endX = heartShapeView.getCurrentX();
                endY = heartShapeView.getCurrentY();
                frameCount = getFrameCount(startX, startY, heartShapeView, speed);
                speedX = (endX - startX) * speed /
                        (float) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
                speedY = (endY - startY) * speed /
                        (float) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
            }
        } else {
            currentX += speedX;
            currentY += speedY;
        }
        count++;
        // 出屏幕了就消失
        if (Math.abs(DiaSiApplication.getCanvasWidth() - currentX) <= Math.abs(speedX) ||
                Math.abs(currentX) <= Math.abs(speedX) ||
                Math.abs(DiaSiApplication.getCanvasHeight() - currentY) <= Math.abs(speedY) ||
                Math.abs(currentY) < Math.abs(speedY)) {
            isDead = true;
        }
    }
}
