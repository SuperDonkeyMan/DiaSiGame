package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.utils.CollisionUtil;

/**
 * Created by songye02 on 2017/4/21.
 */

public class CollisionNormalTextView extends NormalTextView {


    public CollisionNormalTextView(float startX, float startY, float endX, float endY, int frameCount,
                                   String text, int textOrientation) {
        super(startX, startY, endX, endY, frameCount, text, textOrientation);
        collisionable = true;
    }


    @Override
    protected boolean isCollisionWith(HeartShapeView heartShapeView) {
        boolean isCollision;
        if (textOrientation == TEXT_ORIENTATION_VERTICAL_DOWNTOUP) {
            isCollision = CollisionUtil.isCollisionWithRect(heartShapeView.getCurrentX(),
                    heartShapeView.getCurrentY(), heartShapeView.getWidth(), heartShapeView.getHeight(),
                    currentX, currentY - mHeight, mWidth, mHeight);
        } else if (textOrientation == TEXT_ORIENTATION_VERTICAL_UPTODOWN) {
            isCollision = CollisionUtil.isCollisionWithRect(heartShapeView.getCurrentX(),
                    heartShapeView.getCurrentY(), heartShapeView.getWidth(), heartShapeView.getHeight(),
                    currentX, currentY, mWidth, mHeight);
        } else if (textOrientation == TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT) {
            isCollision = CollisionUtil.isCollisionWithRect(heartShapeView.getCurrentX(),
                    heartShapeView.getCurrentY(), heartShapeView.getWidth(), heartShapeView.getHeight(),
                    currentX, currentY, mWidth, mHeight);
        } else {
            isCollision = CollisionUtil.isCollisionWithRect(heartShapeView.getCurrentX(),
                    heartShapeView.getCurrentY(), heartShapeView.getWidth(), heartShapeView.getHeight(),
                    currentX, currentY - mWidth, mWidth, mHeight);
        }
        return isCollision;
    }

    @Override
    protected void dealWithCollision(HeartShapeView heartShapeView) {
        heartShapeView.setBloodCurrent(heartShapeView.getBloodCurrent() - 1);
        heartShapeView.startTwinkle(15);
    }
}
