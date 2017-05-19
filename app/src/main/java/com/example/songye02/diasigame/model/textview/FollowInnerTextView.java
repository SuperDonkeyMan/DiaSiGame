package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

/**
 * Created by songye02 on 2017/5/17.
 */

public class FollowInnerTextView extends FollowTextView {
    public FollowInnerTextView(float startX, float startY,
                               HeartShapeView heartShapeView, float speed,
                               int pauseBefore, String text, int textOrientation) {
        super(startX, startY, heartShapeView, speed, pauseBefore, text, textOrientation);
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
        // 出了边框边缘就消失
        if (currentX <= heartShapeView.getBoundaryX() ||
                currentX + getWidth() >= heartShapeView.getBoundaryX() + heartShapeView.getBoundaryW() ||
                currentY <= heartShapeView.getBoundaryY() ||
                currentY + getHeight() >= heartShapeView.getBoundaryY() + heartShapeView.getBoundaryH()) {
            isDead = true;
        }
    }
}
