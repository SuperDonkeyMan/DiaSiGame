package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.model.CollisonView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

/**
 * Created by songye02 on 2017/4/19.
 * 最基础的弹幕，可以直接建立实例，也可以继承，丰富更多特性
 */


public class NormalTextView extends CollisonView {

    public static final int TEXT_ORIENTATION_VERTICAL = 0;
    public static final int TEXT_ORIENTATION_HORIZONTAL = 1;


    protected String text;
    protected int textOrientation;

    public NormalTextView(float startX, float startY, float speedX, float speedY, String text) {
        super(startX, startY, speedX, speedY);
        this.text = text;
        paint = new TextPaint();
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        textOrientation = TEXT_ORIENTATION_HORIZONTAL;
    }

    @Override
    public boolean collisonWith(HeartShapeView heartShapeView) {
        // TODO: 2017/4/19 添加判断碰撞的函数
        return false;
    }

    @Override
    public void draw(Canvas canvas) {

        switch (textOrientation){
            case TEXT_ORIENTATION_VERTICAL:
                StringBuilder builder = new StringBuilder();
                char strings[] = text.toCharArray();
                for (char string : strings) {
                    builder.append(string);
                    builder.append("\n");
                }
                builder.deleteCharAt(builder.length() - 1);
                String newString = builder.toString();
                StaticLayout layout = new StaticLayout(newString, paint, canvas.getWidth(),
                        Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                canvas.translate(currentX, currentY);
                layout.draw(canvas);
                break;
            case TEXT_ORIENTATION_HORIZONTAL:
                canvas.drawText(text, currentX, currentY, paint);
                break;
        }
    }

    @Override
    public void logic() {
        currentX += speedX;
        currentY += speedY;
    }

    public void setTextAlpha(int alpha){
        paint.setAlpha(alpha);
    }

    public void setTextColor(int color){
        paint.setColor(color);
    }

    public void setTextSize(float textSize){
        paint.setTextSize(textSize);
    }

    public void setTextOrientation(int textOrientation) {
        this.textOrientation = textOrientation;
    }

}
