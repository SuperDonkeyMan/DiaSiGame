package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.utils.DpiUtil;

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

public class NormalTextView extends BaseShowableView {

    public static final int TEXT_ORIENTATION_VERTICAL = 0;
    public static final int TEXT_ORIENTATION_HORIZONTAL = 1;

    protected String text;
    protected int textOrientation;

    protected TextPaint textPaint;
    protected StaticLayout layout;
    protected float mWidth;
    protected float mHeight;

    public NormalTextView(float startX, float startY, float speedX, float speedY, String text, int textOrientation) {
        super(startX, startY, speedX, speedY);
        textPaint = new TextPaint();
        textPaint.setTextSize(DpiUtil.spToPix(20));
        textPaint.setColor(Color.WHITE);
        this.textOrientation = textOrientation;
        this.text = dealTextOrientation(text);
        //layout是用来画竖直字体的
        layout = new StaticLayout(text, textPaint, (int) textPaint.measureText("吔"),
                Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        mWidth = getWidth();
        mHeight = getHeight();
    }


    @Override
    public void draw(Canvas canvas) {

        switch (textOrientation) {
            case TEXT_ORIENTATION_VERTICAL:
                canvas.save();
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                canvas.translate(currentX, currentY);
                layout.draw(canvas);
                //画边框
                Paint rangePaint = new Paint();
                rangePaint.setColor(Color.RED);
                rangePaint.setStyle(Paint.Style.STROKE);
                int topPadding = layout.getTopPadding();
                //这个框就是判断碰撞的范围
                canvas.drawRect(0, -topPadding + (fontMetrics.ascent - fontMetrics.top), mWidth,
                        mHeight - topPadding + (fontMetrics.ascent - fontMetrics.top), rangePaint);
                canvas.restore();
                break;
            case TEXT_ORIENTATION_HORIZONTAL:
                Paint rangePaint1 = new Paint();
                rangePaint1.setColor(Color.RED);
                rangePaint1.setStyle(Paint.Style.STROKE);
                canvas.drawText(text, currentX, currentY, textPaint);
                Paint.FontMetrics fontMetrics1 = textPaint.getFontMetrics();
                //这个框就是判断碰撞的范围
                canvas.drawRect(currentX, currentY + fontMetrics1.ascent, currentX + mWidth, currentY +
                        fontMetrics1.ascent + mHeight, rangePaint1);
                break;
        }
    }

    @Override
    public void logic() {
        currentX += speedX;
        currentY += speedY;
    }

    public void setTextAlpha(int alpha) {
        textPaint.setAlpha(alpha);
    }

    public void setTextColor(int color) {
        textPaint.setColor(color);
    }

    public void setTextSize(float textSize) {
        textPaint.setTextSize(textSize);
    }

    public float getWidth() {
        if (textOrientation == TEXT_ORIENTATION_VERTICAL) {
            return textPaint.measureText("吔");
        } else {
            return textPaint.measureText(text);
        }
    }

    public float getHeight() {
        if (textOrientation == TEXT_ORIENTATION_VERTICAL) {
            int topPadding = layout.getTopPadding();
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float topTextSpace = fontMetrics.ascent - fontMetrics.top;
            return layout.getHeight() + topPadding - topTextSpace;
        } else {
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            return fontMetrics.descent - fontMetrics.ascent;
        }
    }

    private String dealTextOrientation(String text) {
        //当字体为竖直方向时才处理
        if (textOrientation == TEXT_ORIENTATION_VERTICAL) {
            //得到数值字符串
            StringBuilder builder = new StringBuilder();
            char strings[] = text.toCharArray();
            for (char string : strings) {
                builder.append(string);
                builder.append("\n");
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        } else {
            return text;
        }
    }

}
