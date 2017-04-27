package com.example.songye02.diasigame.model.shapeview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.model.textview.ParaboleTextView;
import com.example.songye02.diasigame.utils.MathUtil;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/4/24.
 */

public class GunGroup extends BaseShowableView implements Collisionable {

    private float centerX;
    private float centerY;
    private float startAngel;
    private float endAngel;
    int count = 0; // 计时，当前为多少帧
    int interval = 15; //多少帧生成一个新gun
    private boolean countEnough;
    private float intercalAngel = 15; // 相邻两枪间隔角度
    private float gunRadiusMax = 500; // 最大半径
    private float gunRadiusMin = 200; // 最小半径

    private List<GunView> gunViewList;

    public GunGroup(float centerX, float centerY, float startAngel, float endAngel) {
        super(centerX, centerY, 0, 0);
        this.centerX = centerX;
        this.centerY = centerY;
        this.startAngel = startAngel;
        this.endAngel = endAngel;
        gunViewList = new ArrayList<>();
    }

    @Override
    public void draw(Canvas canvas) {
        if (gunViewList != null) {
            for (GunView gunView : gunViewList) {
                if (!gunView.isDead()) {
                    gunView.draw(canvas);
                }
            }
        }
    }

    @Override
    public void logic() {
        //移除dead的GunView
        Iterator<GunView> iterator = gunViewList.iterator();
        while (iterator.hasNext()) {
            GunView gunView = iterator.next();
            if (gunView.isDead()) {
                iterator.remove();
            } else {
                gunView.logic();
            }
        }

        int gunNum = count / interval; // 从零开始
        if (gunNum * intercalAngel + startAngel > endAngel) {
            countEnough = true;
        }
        if (!countEnough && count % interval == 0) {
            float angel = intercalAngel * gunNum + startAngel;
            float startX = (float) (centerX + gunRadiusMax * Math.cos(MathUtil.angel2Radians(-angel)));
            float endX = (float) (centerX + gunRadiusMin * Math.cos(MathUtil.angel2Radians(-angel)));
            float startY = (float) (centerY + gunRadiusMax * Math.sin(MathUtil.angel2Radians(-angel)));
            float endY = (float) (centerY + gunRadiusMin * Math.sin(MathUtil.angel2Radians(-angel)));
            GunView gunView = new GunView(startX, startY, endX, endY, -angel);
            gunView.setIsGunOutside(true);
            gunViewList.add(gunView);
        }
        count++;
        if (countEnough && gunViewList.size() == 0) {
            isDead = true;
        }
    }

    @Override
    public boolean collisonWith(HeartShapeView view) {
        for (GunView gunView : gunViewList) {
            if (gunView.collisonWith(view)) {
                return true;
            }
        }
        return false;
    }
}
