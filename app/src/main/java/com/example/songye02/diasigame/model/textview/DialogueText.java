package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.Deadable;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

/**
 * Created by songye02 on 2017/5/9.
 * 一行逐字出现的文字
 * 此text绝不独自出现，因为其不能管理自身的生命周期，dead是靠别人管理的
 */

public class DialogueText implements Showable {

    //    public static final int DISMISS_BY_TOUCH = 0;
    //    public static final int DISMISS_BY_TIME = 1;
    //    protected int dismissMode;

    protected int displayCount; // 一个一个字显示时总共的持续帧数
    //    protected int dismissCount; // 最后一个字显示完的持续帧数
    protected int pauseCount; // 每个字出现后下一个字的出现帧数
    protected float startX;
    protected float startY;

    protected String text;
    protected String currentText;
    protected Paint paint;
    protected int count = 0; // 帧数计时
    protected int textIndex = 0; // 当前显示字数的计数

    public DialogueText(float startX, float startY, String text, long displayTime) {
        this.startX = startX;
        this.startY = startY;
        this.text = text;
        this.displayCount = (int) displayTime / DiaSiApplication.TIME_DELAYED;
        this.pauseCount = displayCount / text.length();
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(DpiUtil.spToPix(10));
        Typeface font = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD);
        paint.setTypeface(font);
        paint.setFakeBoldText(true);
    }

    //    // 单位为毫秒
    //    public void setShowTime(int dismissTime) {
    //        if (dismissMode == DISMISS_BY_TIME) {
    //            dismissCount = dismissTime / DiaSiApplication.TIME_DELAYED;
    //        }
    //    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(currentText, startX, startY, paint);
    }

    @Override
    public void logic() {
        // 字数填充满时，不再增长字符串
        if (count <= displayCount && count % pauseCount == 0) {
            if (textIndex <= text.length()) {
                currentText = text.substring(0, textIndex);
                if(currentText.equals("警署有规")){
                    int p = 3;
                    p++;
                }
                textIndex++;
            }
        }
        count++;
    }

    public float getHeight() {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.bottom - fontMetrics.top;
    }

}
