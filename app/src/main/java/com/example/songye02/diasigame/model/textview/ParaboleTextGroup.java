package com.example.songye02.diasigame.model.textview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/4/24.
 */

public class ParaboleTextGroup extends BaseShowableView{

    List<ParaboleTextView> mTextList = new ArrayList<>();
    int count = 0; // 计时，当前为多少帧
    int interval = 3; //多少帧生成一个新ParaboleTextView
    private boolean countEnough = false;
    private Random random = new Random(10);
    private float maxX;
    private float maxY;
    private long startTime;
    private long continueTime;

    public ParaboleTextGroup(float startX, float startY, float maxX, float maxY, long continueTime) {
        //ParaboleTextGroup是不动的
        super(startX, startY, 0, 0);
        this.collisionable = true;
        this.maxX = maxX;
        this.maxY = maxY;
        this.continueTime = continueTime;
        startTime = System.currentTimeMillis();
    }

    public void draw(Canvas canvas) {
        for (ParaboleTextView paraboleTextView : mTextList) {
            paraboleTextView.draw(canvas);
        }
    }

    public void logic() {
        //移除dead的ParaboleTextView
        Iterator<ParaboleTextView> iterator = mTextList.iterator();
        while (iterator.hasNext()) {
            ParaboleTextView paraboleTextView = iterator.next();
            if (paraboleTextView.isDead()) {
                iterator.remove();
            } else {
                paraboleTextView.logic();
            }
        }
        //判断group isDead；

        if (System.currentTimeMillis()-startTime>continueTime) {
            countEnough = true;
        }
        if (!countEnough) {
            //添加ParaboleTextView
            if (count % interval == 0) {
                ParaboleTextView paraboleTextView = new ParaboleTextView(currentX, currentY, "吔",
                        createSpeedX(), createSpeedY(),
                        createMaxX(), createMaxY(), createDirection(), NormalTextView
                        .TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
                mTextList.add(paraboleTextView);
            }
        }
        // 计数加一
        count++;
        // 判断是否dead
        if (mTextList.isEmpty()) {
            isDead = true;
        }
    }

    @Override
    public void collisionWith(HeartShapeView view) {
        for (ParaboleTextView paraboleTextView : mTextList) {
            paraboleTextView.collisionWith(view);
        }
    }

    private float createSpeedX() {
//        return DiaSiApplication.paraboleTextGroupFloatRandoms[count*4];
        return random.nextFloat() * DpiUtil.dipToPix(12.5f);
    }

    private float createSpeedY() {
//        return DiaSiApplication.paraboleTextGroupFloatRandoms[count*4+1];
        return random.nextFloat() * DpiUtil.dipToPix(0.5f) + DpiUtil.dipToPix(3.0f);
    }

    private float createMaxX() {
//        return DiaSiApplication.paraboleTextGroupFloatRandoms[count*4+2];
        return random.nextFloat() * maxX;
    }

    private float createMaxY() {
//        return DiaSiApplication.paraboleTextGroupFloatRandoms[count*4+3];
        return random.nextFloat() * maxY;
    }

    private boolean createDirection() {
//        return DiaSiApplication.paraboleTextGroupBooleanRandoms[count];
        return random.nextBoolean();
    }

}
