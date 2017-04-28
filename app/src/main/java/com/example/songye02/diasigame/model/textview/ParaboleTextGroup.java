package com.example.songye02.diasigame.model.textview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/4/24.
 */

public class ParaboleTextGroup extends BaseShowableView implements Collisionable {

    List<ParaboleTextView> mTextList = new ArrayList<>();
    int count = 0; // 计时，当前为多少帧
    int interval = 5; //多少帧生成一个新ParaboleTextView
    private boolean countEnough = false;

    public ParaboleTextGroup(float startX, float startY) {
        //ParaboleTextGroup是不动的
        super(startX, startY, 0, 0);
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
        int textNum = count / interval;
        if (textNum > 300) {
            countEnough = true;
        }
        if (!countEnough) {
            //添加ParaboleTextView
            if (count % interval == 0) {
                ParaboleTextView paraboleTextView = new ParaboleTextView(currentX, currentY, "吔",
                        createSpeedX(textNum), createSpeedY(textNum),
                        createMaxX(textNum), createMaxY(textNum), createDirection(textNum), NormalTextView
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
    public boolean collisonWith(HeartShapeView view) {
        for (ParaboleTextView paraboleTextView : mTextList) {
            if (paraboleTextView.collisonWith(view)) {
                return true;
            }
        }
        return false;
    }

    private float createSpeedX(int count) {
        return DiaSiApplication.paraboleTextGroupFloatRandoms[count*4];
    }

    private float createSpeedY(int count) {
        return DiaSiApplication.paraboleTextGroupFloatRandoms[count*4+1];
    }

    private float createMaxX(int count) {
        return DiaSiApplication.paraboleTextGroupFloatRandoms[count*4+2];
    }

    private float createMaxY(int count) {
        return DiaSiApplication.paraboleTextGroupFloatRandoms[count*4+3];
    }

    private boolean createDirection(int count) {
        return DiaSiApplication.paraboleTextGroupBooleanRandoms[count];
    }

}
