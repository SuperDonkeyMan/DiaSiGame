package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.utils.CollisionUtil;

import android.app.ActivityManager;
import android.graphics.Paint;

/**
 * Created by songye02 on 2017/4/21.
 */

public class CollisionNormalTextView extends NormalTextView implements Collisionable {

    public boolean isCollision;

    public CollisionNormalTextView(float startX, float startY, float endX, float endY, int frameCount,
                                   String text, int textOrientation) {
        super(startX, startY, endX, endY, frameCount, text, textOrientation);
    }

    @Override
    public boolean collisonWith(HeartShapeView heartShapeView) {
        // TODO: 2017/4/21 目前先用方块碰撞模拟
        if(isDead){
            return false;
        }
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        if (textOrientation == TEXT_ORIENTATION_VERTICAL_DOWNTOUP) {
            int topPadding = layout.getTopPadding();
            return CollisionUtil.isCollisonWithRect(heartShapeView.getCurrentX(), heartShapeView.getCurrentY(),
                    heartShapeView.getWidth(), heartShapeView.getHeight(),
                    currentX, currentY - topPadding + (fontMetrics.ascent - fontMetrics.top),
                    mWidth, mHeight);
        } else {
            return CollisionUtil.isCollisonWithRect(heartShapeView.getCurrentX(), heartShapeView.getCurrentY(),
                    heartShapeView.getWidth(), heartShapeView.getHeight(),
                    currentX, currentY + fontMetrics.ascent, mWidth, mHeight);
        }
    }

}
