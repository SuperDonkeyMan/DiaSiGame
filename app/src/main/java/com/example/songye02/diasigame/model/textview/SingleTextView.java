package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.model.CollisonView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

import android.graphics.Paint;

/**
 * Created by songye02 on 2017/4/19.
 */

public abstract class SingleTextView extends CollisonView {

    protected String text;
    protected float textSize;

    public SingleTextView(float startX, float startY, float speedX, float speedY, String text, float textSize) {
        super(startX, startY, speedX, speedY);
        this.text = text;
        this.textSize = textSize;
        paint = new Paint();
        paint.setTextSize(textSize);
    }

    public void setTextAlpha(int alpha){
        paint.setAlpha(alpha);
    }

    public void setTextColor(int color){
        paint.setColor(color);
    }

    @Override
    public boolean collisonWith(HeartShapeView heartShapeView) {
        // TODO: 2017/4/19 添加判断碰撞的函数
        return false;
    }
}
