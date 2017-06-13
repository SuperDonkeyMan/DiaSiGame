package com.example.songye02.diasigame.model.textview;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.callback.PauseTextViewDeadCallback;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.utils.DpiUtil;
import com.example.songye02.diasigame.utils.MathUtil;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/6/13.
 */

public class PauseViewTextGroup extends BaseShowableView {

    public class PauseViewTextParams{
        public float endX;
        public float endY;
        public String text;

        public PauseViewTextParams(float endX, float endY, String text) {
            this.endX = endX;
            this.endY = endY;
            this.text = text;
        }
    }

    public static final int APPEAR_DIRECTION_RIGHT = 0;
    public static final int APPEAR_DIRECTION_LEFT = 1;

    public static final int PAUSE_INCREMENT_DIRECTION_RIGHT = 0;
    public static final int PAUSE_INCREMENT_DIRECTION_LEFT = 1;
    private float textSize;
    private List<PauseViewTextParams> textList;
    private int appearDirection; // 逐字出现的方向
    private int pauseIncrementDirection; // 每个字幕暂停帧数的增量方向
    private int pauseBeforeAppear; // 每个字幕逐自出现间隔的时间
    private int pauseBefore; // 第一个字运动前暂停多少帧
    private int pauseAfter; // 停止运动停留多少帧消失
    private int frameCount;
    private int pauseBeforeIncrement; // 之后每个字幕暂停帧数的增量

    private List<PauseViewText> list;
    private int textCount = 0;
    private int count = 0;
    private PauseTextViewDeadCallback callback = null;

    public PauseViewTextGroup(float startX, float startY, float textSize, int frameCount,
                              List<PauseViewTextParams> textList, int appearDirection, int pauseIncrementDirection,
                              int pauseBeforeAppear, int pauseBefore, int pauseAfter, int pauseBeforeIncrement) {
        super(startX, startY, 0, 0);
        this.textSize = textSize;
        this.textList = textList;
        this.frameCount = frameCount;
        this.appearDirection = appearDirection;
        this.pauseIncrementDirection = pauseIncrementDirection;
        this.pauseBeforeAppear = pauseBeforeAppear;
        this.pauseBefore = pauseBefore;
        this.pauseAfter = pauseAfter;
        this.pauseBeforeIncrement = pauseBeforeIncrement;

        collisionable = true;
        list = new LinkedList<>();
    }

    @Override
    public void draw(Canvas canvas) {
        for (PauseViewText pauseViewText : list) {
            pauseViewText.draw(canvas);
        }
    }

    @Override
    public void logic() {
        if (textCount >= textList.size() && list.size() == 0) {
            isDead = true;
            list.clear();
            return;
        }
        Iterator<PauseViewText> iterator = list.iterator();
        while (iterator.hasNext()) {
            PauseViewText pauseViewText = iterator.next();
            if (pauseViewText.isDead()) {
                callback.onDead(pauseViewText,getTextIndex(pauseViewText),textList.size());
                iterator.remove();
            } else {
                pauseViewText.logic();
            }
        }

        if (textCount < textList.size() && count % pauseBeforeAppear == 0) {
            String text = "";
            float textStartX = 0;
            int textPauseBefore = 0;
            // 从左向右出现
            switch (appearDirection) {
                case APPEAR_DIRECTION_RIGHT:
                    text = textList.get(textCount).text;
                    textStartX = startX + textCount * DpiUtil.spToPix(textSize);
                    if (pauseIncrementDirection == PAUSE_INCREMENT_DIRECTION_RIGHT) {
                        textPauseBefore = pauseBefore + textCount * pauseBeforeIncrement;
                    } else {
                        textPauseBefore = pauseBefore + (textList.size() - textCount - 1) * pauseBeforeIncrement;
                    }
                    break;
                case APPEAR_DIRECTION_LEFT:
                    text = textList.get(textList.size() - 1 - textCount).text;
                    textStartX = startX + (textList.size() - 1 - textCount) * DpiUtil.spToPix(textSize);
                    if (pauseIncrementDirection == PAUSE_INCREMENT_DIRECTION_RIGHT) {
                        textPauseBefore = pauseBefore + textCount * pauseBeforeIncrement;
                    } else {
                        textPauseBefore = pauseBefore + (textList.size() - textCount - 1) * pauseBeforeIncrement;
                    }
                    break;
            }
            PauseViewText view = new PauseViewText(textStartX,startY,textList.get(textCount).endX,textList.get
                    (textCount).endY,textPauseBefore,frameCount,pauseAfter,textList.get(textCount).text,
                    NormalTextView.TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
            view.setTextSize(textSize);
            list.add(view);
            textCount++;
        }
        count++;
    }

    public void setTextViewDeadCallback(PauseTextViewDeadCallback callback){
        this.callback = callback;
    }

    private int getTextIndex(PauseViewText pauseViewText){
        int index = 0;
        for(int i = 0;i<textList.size();i++){
            PauseViewTextParams param = textList.get(i);
            if (param.text.equals(pauseViewText.getText())
                    && MathUtil.floatEquals(param.endX,pauseViewText.getCurrentX(),0.1f)
                    && MathUtil.floatEquals(param.endY,pauseViewText.getCurrentY(),0.1f)){
                index = i;
                break;
            }
        }
        return index;
    }
}
