package com.example.songye02.diasigame.model.shapeview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/6/6.
 * 正弦形出现的guroup
 * startXY是最上面的子View的左上角
 */

public class SinLongSpineGroupView extends BaseShowableView {

    private List<LongSpineView> spineViewList;

    private int numTotal; // 总共多少列
    private int departCount; // 每隔几帧出现一列
    private float maxShiftHeight; // 每列最大的偏移高度
    private float viewHeight; // 子view高度
    private float upDownDepartHeight; // 上下两个子view的相隔距离
    private int count = 0; // 帧计数器
    private int spineCount = 0; // 子view计数器

    public SinLongSpineGroupView(float startX, float startY, float speedX, int numTotal,
                                 int departCount, float viewHeight, float upDownDepartHeight, float maxShiftHeight) {
        super(startX, startY, speedX, 0);
        spineViewList = new ArrayList<>();
        this.numTotal = numTotal;
        this.departCount = departCount;
        this.maxShiftHeight = maxShiftHeight;
        this.viewHeight = viewHeight;
        this.upDownDepartHeight = upDownDepartHeight;
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
            float shiftHeight = maxShiftHeight * (float) Math.sin(((float) spineCount / numTotal * Math.PI));
            // 上面的view
            LongSpineView longSpineView1 = new LongSpineView(startX, startY - shiftHeight, speedX,
                    LongSpineView.SPINE_DIRECTION_DOWN, viewHeight);
            // 下面的view
            LongSpineView longSpineView2 =
                    new LongSpineView(startX, startY - shiftHeight + 2 * viewHeight + upDownDepartHeight,
                            speedX, LongSpineView.SPINE_DIRECTION_UP, viewHeight);
            spineViewList.add(longSpineView1);
            spineViewList.add(longSpineView2);
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
