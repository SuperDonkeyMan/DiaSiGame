package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

/**
 * Created by songye02 on 2017/5/4.
 */

public class FollowTextView extends PauseViewText {
    private HeartShapeView heartShapeView;
    private float speed;

    public FollowTextView(float startX, float startY, HeartShapeView heartShapeView, float speed, int pauseBefore,
                          int pauseAfter, String text, int textOrientation) {
        //        super(startX, startY,
        //                getEndX(startX, startY, heartShapeView),
        //                getEndY(startX, startY, heartShapeView), pauseBefore,
        //                getFrameCount(startX, startY, heartShapeView, speed),
        //                pauseAfter, text, textOrientation);

        // 这里0都是后面计算的，构造函数不需要
        super(startX, startY, 0, 0, pauseBefore, 1000000, pauseAfter, text, textOrientation);
        this.heartShapeView = heartShapeView;
        this.speed = speed;
    }

    private static float getEndX(float startX, float startY, HeartShapeView heartShapeView) {
        if (heartShapeView.getCurrentY() >= startY) {
            return startX + (heartShapeView.getCurrentX() - startX) * (DiaSiApplication.getCanvasHeight() - startX)
                    / (heartShapeView.getCurrentY() - startY);
        } else {
            return startX + (heartShapeView.getCurrentX() - startX) * startX
                    / (startY - heartShapeView.getCurrentY());
        }
    }

    private static float getEndY(float startX, float startY, HeartShapeView heartShapeView) {
        if (heartShapeView.getCurrentY() >= startY) {
            return DiaSiApplication.getCanvasHeight();
        } else {
            return 0;
        }
    }

    private static int getFrameCount(float startX, float startY, HeartShapeView heartShapeView, float speed) {
        float endX = getEndX(startX, startY, heartShapeView);
        float endY = getEndY(startX, startY, heartShapeView);
        return (int) (Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2)) / speed);
    }

    @Override
    public void logic() {
        if (count < pauseBefore) {
            currentX = startX;
            currentY = startY;
            if (count == pauseBefore - 1) {
                endX = getEndX(startX, startY, heartShapeView);
                endY = getEndY(startX, startY, heartShapeView);
                frameCount = getFrameCount(startX, startY, heartShapeView, speed);
                speedX = (endX - startX) / frameCount;
                speedY = (endY - startY) / frameCount;
            }
        } else if (count < pauseBefore + frameCount) {
            currentX += speedX;
            currentY += speedY;
        } else {
            currentX = endX;
            currentY = endY;
        }
        count++;
        if (Math.abs(DiaSiApplication.getCanvasWidth() - currentX) <= Math.abs(speedX) ||
                Math.abs(currentX) <= Math.abs(speedX) ||
                Math.abs(DiaSiApplication.getCanvasHeight() - currentY) <= Math.abs(speedY) ||
                Math.abs(currentY) < Math.abs(speedY)) {
            isDead = true;
        }
    }
}
