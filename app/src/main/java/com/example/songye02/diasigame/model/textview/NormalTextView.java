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

    public static final int TEXT_ORIENTATION_VERTICAL_UPTODOWN = 0;
    public static final int TEXT_ORIENTATION_VERTICAL_DOWNTOUP = 1;
    public static final int TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT = 2;
    public static final int TEXT_ORIENTATION_HORIZONTAL_RIGHTTOLEFT = 3;

    protected String text;
    protected int textOrientation;
    protected TextPaint textPaint;
    protected StaticLayout layout;
    protected float mWidth;
    protected float mHeight;
    private float endX;
    private float endY;
    private int frameCount; // textView从出现到消失总共用多少帧
    private int count;

    public NormalTextView(float startX, float startY, float endX, float endY, int frameCount,
                          String text, int textOrientation) {
        super(startX, startY, (endX - startX) / frameCount, (endY - startY) / frameCount);
        this.frameCount = frameCount;
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
        int topPadding;
        Paint.FontMetrics fontMetrics;
        Paint rangePaint;
        switch (textOrientation) {
            case TEXT_ORIENTATION_VERTICAL_UPTODOWN:
                canvas.save();
                fontMetrics = textPaint.getFontMetrics();
                canvas.translate(currentX, currentY);
                layout.draw(canvas);
                //画边框
                rangePaint = new Paint();
                rangePaint.setColor(Color.RED);
                rangePaint.setStyle(Paint.Style.STROKE);
                topPadding = layout.getTopPadding();
                //这个框就是判断碰撞的范围
                canvas.drawRect(0, -topPadding + (fontMetrics.ascent - fontMetrics.top), mWidth,
                        mHeight - topPadding + (fontMetrics.ascent - fontMetrics.top), rangePaint);
                canvas.restore();
                break;
            case TEXT_ORIENTATION_VERTICAL_DOWNTOUP:
                canvas.save();
                topPadding = layout.getTopPadding();
                fontMetrics = textPaint.getFontMetrics();
                canvas.translate(currentX, currentY - mHeight);
                layout.draw(canvas);
                //画边框
                rangePaint = new Paint();
                rangePaint.setColor(Color.RED);
                rangePaint.setStyle(Paint.Style.STROKE);
                //这个框就是判断碰撞的范围
                canvas.drawRect(0, -topPadding + (fontMetrics.ascent - fontMetrics.top), mWidth,
                        mHeight - topPadding + (fontMetrics.ascent - fontMetrics.top), rangePaint);
                canvas.restore();
                break;
            case TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT:
                rangePaint = new Paint();
                rangePaint.setColor(Color.RED);
                rangePaint.setStyle(Paint.Style.STROKE);
                canvas.drawText(text, currentX, currentY, textPaint);
                fontMetrics = textPaint.getFontMetrics();
                //这个框就是判断碰撞的范围
                canvas.drawRect(currentX, currentY + fontMetrics.ascent, currentX + mWidth, currentY +
                        fontMetrics.ascent + mHeight, rangePaint);
                break;
            case TEXT_ORIENTATION_HORIZONTAL_RIGHTTOLEFT:
                rangePaint = new Paint();
                rangePaint.setColor(Color.RED);
                rangePaint.setStyle(Paint.Style.STROKE);
                textPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(text, currentX, currentY, textPaint);
                fontMetrics = textPaint.getFontMetrics();
                //这个框就是判断碰撞的范围
                canvas.drawRect(currentX, currentY + fontMetrics.ascent, currentX + mWidth, currentY +
                        fontMetrics.ascent + mHeight, rangePaint);
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
        textPaint.setTextSize(textSize);
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
            int topPadding = layout.getTopPadding();
            int bottomPadding = layout.getBottomPadding();
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float topTextSpace = fontMetrics.ascent - fontMetrics.top;
            float bottomTextSpace = fontMetrics.bottom;
            return layout.getHeight() + topPadding - bottomPadding - topTextSpace - bottomTextSpace;
        } else {
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            return fontMetrics.descent - fontMetrics.ascent;
        }
    }

    private String dealTextOrientation(String text) {
        //当字体为竖直方向时才处理
        if (textOrientation == TEXT_ORIENTATION_VERTICAL_DOWNTOUP
                || textOrientation == TEXT_ORIENTATION_VERTICAL_UPTODOWN) {
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
