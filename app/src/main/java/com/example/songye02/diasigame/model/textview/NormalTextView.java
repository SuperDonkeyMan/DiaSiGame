package com.example.songye02.diasigame.model.textview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.utils.DpiUtil;

/**
 * Created by songye02 on 2017/4/19.
 * 最基础的弹幕，可以直接建立实例，也可以继承，丰富更多特性
 */

public class NormalTextView extends BaseShowableView {

    public static final int TEXT_ORIENTATION_VERTICAL_UPTODOWN = 0;
    public static final int TEXT_ORIENTATION_VERTICAL_DOWNTOUP = 1;
    public static final int TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT = 2;
    public static final int TEXT_ORIENTATION_HORIZONTAL_RIGHTTOLEFT = 3;

    protected String text;
    protected int textOrientation;
    protected TextPaint textPaint;
    protected float mWidth;
    protected float mHeight;
    protected float endX;
    protected float endY;
    protected int frameCount; // textView从出现到消失总共用多少帧
    protected int count;

    public NormalTextView(float startX, float startY, float endX, float endY, int frameCount,
                          String text, int textOrientation) {
        super(startX, startY, (endX - startX) / frameCount, (endY - startY) / frameCount);
        this.endX = endX;
        this.endY = endY;
        this.frameCount = frameCount;
        textPaint = new TextPaint();
        textPaint.setTextSize(DpiUtil.spToPix(20));
        textPaint.setColor(Color.WHITE);
        this.textOrientation = textOrientation;
        this.text = text;
        mWidth = getWidth();
        mHeight = getHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        Paint.FontMetrics fontMetrics;
//        Paint rangePaint;
        switch (textOrientation) {
            case TEXT_ORIENTATION_VERTICAL_UPTODOWN:
                fontMetrics = textPaint.getFontMetrics();
                String[] singleTexts1 = text.split("");
                float shiftDistance1 = 0;
                // 从1开始，因为第分割后第一个为空字符串
                for (int i = 1; i < singleTexts1.length; i++) {
                    float startX = currentX;
                    float startY = currentY + shiftDistance1;
                    canvas.drawText(singleTexts1[i], startX, startY - fontMetrics.ascent, textPaint);
                    shiftDistance1 += fontMetrics.bottom - fontMetrics.top;
                }
//                rangePaint = new Paint();
//                rangePaint.setColor(Color.RED);
//                rangePaint.setStyle(Paint.Style.STROKE);
//                canvas.drawLine(0, currentY, 10000, currentY, rangePaint);
//                canvas.drawRect(currentX, currentY, currentX + mWidth,
//                        currentY + shiftDistance1 - (fontMetrics.bottom - fontMetrics.descent) -
//                                (fontMetrics.ascent - fontMetrics.top), rangePaint);
                break;
            case TEXT_ORIENTATION_VERTICAL_DOWNTOUP:
                fontMetrics = textPaint.getFontMetrics();
                String[] singleTexts2 = text.split("");
                float shiftDistance2 = 0;
                for (int i = 1; i < singleTexts2.length; i++) {
                    float startX = currentX;
                    float startY = currentY - shiftDistance2;
                    canvas.drawText(singleTexts2[i], startX, startY - fontMetrics.descent, textPaint);
                    shiftDistance2 += fontMetrics.bottom - fontMetrics.top;
                }
//                rangePaint = new Paint();
//                rangePaint.setColor(Color.RED);
//                rangePaint.setStyle(Paint.Style.STROKE);
//                canvas.drawRect(currentX, currentY - shiftDistance2 + (fontMetrics.bottom - fontMetrics.descent) +
//                                (fontMetrics.ascent - fontMetrics.top), currentX + mWidth, currentY
//                        , rangePaint);
                break;
            case TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT:
                fontMetrics = textPaint.getFontMetrics();
//                rangePaint = new Paint();
//                rangePaint.setColor(Color.RED);
//                rangePaint.setStyle(Paint.Style.STROKE);
                canvas.drawText(text, currentX, currentY - fontMetrics.ascent, textPaint);
                //这个框就是判断碰撞的范围
//                canvas.drawRect(currentX, currentY, currentX + mWidth, currentY + mHeight, rangePaint);
                break;
            case TEXT_ORIENTATION_HORIZONTAL_RIGHTTOLEFT:
                fontMetrics = textPaint.getFontMetrics();
//                rangePaint = new Paint();
//                rangePaint.setColor(Color.RED);
//                rangePaint.setStyle(Paint.Style.STROKE);
                textPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(text, currentX, currentY - fontMetrics.ascent, textPaint);
                //这个框就是判断碰撞的范围
//                canvas.drawRect(currentX, currentY, currentX + mWidth, currentY + mHeight, rangePaint);
                break;
        }
    }

    @Override
    public void logic() {
        currentX += speedX;
        currentY += speedY;
        // 到达目的地就死亡
        if (count >= frameCount) {
            isDead = true;
        } else {
            count++;
        }
    }

    public void setTextAlpha(int alpha) {
        textPaint.setAlpha(alpha);
    }

    public void setTextColor(int color) {
        textPaint.setColor(color);
    }

    public void setTextSize(float textSize) {
        textPaint.setTextSize(DpiUtil.spToPix(textSize));
        // 更新长宽
        mWidth = getWidth();
        mHeight = getHeight();
    }

    public float getWidth() {
        if (textOrientation == TEXT_ORIENTATION_VERTICAL_UPTODOWN
                || textOrientation == TEXT_ORIENTATION_VERTICAL_DOWNTOUP) {
            return textPaint.measureText("吔");
        } else {
            return textPaint.measureText(text);
        }
    }

    public float getHeight() {
        if (textOrientation == TEXT_ORIENTATION_VERTICAL_DOWNTOUP
                || textOrientation == TEXT_ORIENTATION_VERTICAL_UPTODOWN) {
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float heightTemp = (fontMetrics.bottom - fontMetrics.top) * text.length();
            float topTextSpace = fontMetrics.ascent - fontMetrics.top;
            float bottomTextSpace = fontMetrics.bottom - fontMetrics.descent;
            return heightTemp - topTextSpace - bottomTextSpace;
        } else {
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            return fontMetrics.descent - fontMetrics.ascent;
        }
    }

    // 得到不刨除上下padding的整体高度
    //    public float getFullHeight(){
    //        if (textOrientation == TEXT_ORIENTATION_VERTICAL_DOWNTOUP
    //                || textOrientation == TEXT_ORIENTATION_VERTICAL_UPTODOWN) {
    //            int topPadding = layout.getTopPadding();
    //            int bottomPadding = layout.getBottomPadding();
    //            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
    //            float topTextSpace = fontMetrics.ascent - fontMetrics.top;
    //            float bottomTextSpace = fontMetrics.bottom;
    //            return layout.getHeight() + topPadding - bottomPadding - topTextSpace - bottomTextSpace;
    //        } else {
    //            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
    //            return fontMetrics.bottom - fontMetrics.top;
    //        }
    //    }

    public float getBaseLineHeight() {
        Paint.FontMetrics metrics = textPaint.getFontMetrics();
        return -metrics.top;
    }

}
