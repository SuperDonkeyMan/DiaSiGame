package com.example.songye02.diasigame.model.textview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.example.songye02.diasigame.model.BaseMoveableView;
import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/4/24.
 */

public class ParaboleTextGroup extends BaseMoveableView implements Collisionable {

    List<ParaboleTextView> mTextList = new ArrayList<>();
    int count = 0; // 计时，当前为多少帧
    int interval = 5; //多少帧生成一个新ParaboleTextView
    Random random = new Random(10);
    private boolean countEnouth = false;

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
            if (paraboleTextView.isDead) {
                iterator.remove();
            } else {
                paraboleTextView.logic();
            }
        }
        //判断group isDead；
        if(count/interval>300){
            countEnouth = true;
        }
        if(!countEnouth){
            //添加ParaboleTextView
            if (count % interval == 0) {
                ParaboleTextView paraboleTextView = new ParaboleTextView(currentX, currentY, "吔",
                        createSpeedX(), createSpeedY(),
                        createMaxX(), createMaxY(), createDirection(), NormalTextView.TEXT_ORIENTATION_HORIZONTAL);
                mTextList.add(paraboleTextView);
            }
        }
        // 计数加一
        count++;
        // 判断是否dead
        if(mTextList.isEmpty()){
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

    private float createSpeedX() {
        return random.nextFloat() * DpiUtil.dipToPix(12.5f); // 12.5dp为最大
    }

    private float createSpeedY() {
        return random.nextFloat() * DpiUtil.dipToPix(0.5f)+DpiUtil.dipToPix(2.0f);
    }

    private float createMaxX() {
        return random.nextFloat() * DpiUtil.dipToPix(125f);
    }

    private float createMaxY() {
        return random.nextFloat() * DpiUtil.dipToPix(100f);
    }

    private boolean createDirection() {
        return random.nextBoolean();
    }

}
