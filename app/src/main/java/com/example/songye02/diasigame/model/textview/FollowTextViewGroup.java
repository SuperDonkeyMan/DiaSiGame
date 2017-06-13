package com.example.songye02.diasigame.model.textview;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by songye02 on 2017/6/13.
 * 有一个逐字出现的过程
 * 实际使用中只有横向，因此偷懒了，没有写竖直方向的
 */

public class FollowTextViewGroup extends BaseShowableView {

    public static final int APPEAR_DIRECTION_RIGHT = 0;
    public static final int APPEAR_DIRECTION_LEFT = 1;

    public static final int PAUSE_INCREMENT_DIRECTION_RIGHT = 0;
    public static final int PAUSE_INCREMENT_DIRECTION_LEFT = 1;
    private float textSize;
    private List<String> textList;
    private int appearDirection; // 逐字出现的方向
    private int pauseIncrementDirection; // 每个字幕暂停帧数的增量方向
    private int pauseBeforeAppear; // 每个字幕逐自出现间隔的时间
    private int pauseBefore; // 第一个字运动前暂停多少帧
    private int pauseBeforeIncrement; // 之后每个字幕暂停帧数的增量
    private int speed; // 设定初始速度的
    private HeartShapeView heartShapeView;

    private int count = 0;
    private int textCount = 0; // 加入的字的计数
    private List<FollowTextView> list;

    public FollowTextViewGroup(float startX, float startY, float textSize,
                               List<String> textList, int appearDirection, int pauseIncrementDirection,
                               int pauseBeforeAppear, int pauseBefore, int pauseBeforeIncrement, int speed,
                               HeartShapeView heartShapeView) {
        super(startX, startY, 0, 0);
        this.textSize = textSize;
        this.textList = textList;
        this.appearDirection = appearDirection;
        this.pauseIncrementDirection = pauseIncrementDirection;
        this.pauseBeforeAppear = pauseBeforeAppear;
        this.pauseBefore = pauseBefore;
        this.pauseBeforeIncrement = pauseBeforeIncrement;
        this.speed = speed;
        this.heartShapeView = heartShapeView;

        list = new LinkedList<>();
        collisionable = true;
    }

    @Override
    public void draw(Canvas canvas) {
        for (FollowTextView followTextView : list) {
            followTextView.draw(canvas);
        }
    }

    @Override
    public void logic() {
        if (textCount >= textList.size() && list.size() == 0) {
            isDead = true;
            list.clear();
            return;
        }
        Iterator<FollowTextView> iterator = list.iterator();
        while (iterator.hasNext()) {
            FollowTextView followTextView = iterator.next();
            if (followTextView.isDead()) {
                iterator.remove();
            } else {
                followTextView.logic();
            }
        }

        if (textCount < textList.size() && count % pauseBeforeAppear == 0) {
            String text = "";
            float textStartX = 0;
            int textPauseBefore = 0;
            // 从左向右出现
            switch (appearDirection) {
                case APPEAR_DIRECTION_RIGHT:
                    text = textList.get(textCount);
                    textStartX = startX + textCount * DpiUtil.spToPix(textSize);
                    if (pauseIncrementDirection == PAUSE_INCREMENT_DIRECTION_RIGHT) {
                        textPauseBefore = pauseBefore + textCount * pauseBeforeIncrement;
                    } else {
                        textPauseBefore = pauseBefore + (textList.size() - textCount - 1) * pauseBeforeIncrement;
                    }
                    break;
                case APPEAR_DIRECTION_LEFT:
                    text = textList.get(textList.size() - 1 - textCount);
                    textStartX = startX + (textList.size() - 1 - textCount) * DpiUtil.spToPix(textSize);
                    if (pauseIncrementDirection == PAUSE_INCREMENT_DIRECTION_RIGHT) {
                        textPauseBefore = pauseBefore + textCount * pauseBeforeIncrement;
                    } else {
                        textPauseBefore = pauseBefore + (textList.size() - textCount - 1) * pauseBeforeIncrement;
                    }
                    break;
            }
            if(text.equals(" ")){
                textCount++;
                return;
            }
            FollowTextView view = new FollowTextView(textStartX, startY, heartShapeView, speed, textPauseBefore, text,
                    NormalTextView.TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
            view.setTextSize(textSize);
            list.add(view);
            textCount++;
        }
        count++;
    }

    @Override
    public void collisionWith(HeartShapeView view) {
        for (FollowTextView followTextView : list) {
            followTextView.collisionWith(view);
        }
    }

}
