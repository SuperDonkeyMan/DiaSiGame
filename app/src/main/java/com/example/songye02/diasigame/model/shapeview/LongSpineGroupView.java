package com.example.songye02.diasigame.model.shapeview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/6/6.
 */

public class LongSpineGroupView extends BaseShowableView {

    private List<LongSpineView> spineViewList;

    private int numTotal; // 总共多少列
    private int departCount; // 每隔几帧出现一列
    private float viewHeight; // 子view高度
    private int viewDirection;
    private int count = 0; // 帧计数器
    private int spineCount = 0; // 子view计数器

    public LongSpineGroupView(float startX, float startY, float speedX, int numTotal, int departCount,
                              float viewHeight, int viewDirection) {
        super(startX, startY, speedX, 0);
        this.numTotal = numTotal;
        this.departCount = departCount;
        this.viewHeight = viewHeight;
        this.viewDirection = viewDirection;
        spineViewList = new ArrayList<>();
        collisionable = true;
    }


    @Override
    public void draw(Canvas canvas) {
        for (LongSpineView longSpineView : spineViewList) {
            longSpineView.draw(canvas);
        }
    }

    @Override
    public void logic() {

        if (spineCount >= numTotal && spineViewList.isEmpty()) {
            isDead = true;
            spineViewList.clear();
            return;
        }
        Iterator<LongSpineView> iterator = spineViewList.iterator();
        while (iterator.hasNext()) {
            LongSpineView longSpineView = iterator.next();
            if (longSpineView.isDead()) {
                iterator.remove();
            } else {
                longSpineView.logic();
            }
        }
        if (spineCount < numTotal && count % departCount == 0) {
            LongSpineView longSpineView1 = new LongSpineView(startX, startY, speedX,
                    viewDirection , viewHeight);
            spineViewList.add(longSpineView1);
            spineCount++;
        }
        count++;
    }

    @Override
    public void collisionWith(HeartShapeView view) {
        for (LongSpineView longSpineView : spineViewList) {
            longSpineView.collisionWith(view);
        }
    }
}
