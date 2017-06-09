package com.example.songye02.diasigame.model.shapeview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/6/6.
 * 正弦形出现的guroup后面出现的那个
 */

public class EasyLongSpineGroupView extends BaseShowableView {

    private List<LongSpineGroupView> spineViewList;

    private int numGroup; // 多少组
    private int numPerGroup; // 每组多少列
    private int departCount; // 每隔几帧出现一列
    private int departGroupCount; // 每隔几帧出现一组
    private float viewHeight; // 子view高度
    private float startY1; // 上面一列的位置
    private float startY2; // 下面一列的位置
    private int count = 0; // 帧计数器
    private int spineCount = 0; // 子view计数器

    public EasyLongSpineGroupView(float startX, float startY1, float startY2, float speedX, int numGroup,
                                  int numPerGroup, int departCount, int departGroupCount, float viewHeight) {
        super(startX, 0, speedX, 0);
        this.numGroup = numGroup;
        this.numPerGroup = numPerGroup;
        this.departCount = departCount;
        this.departGroupCount = departGroupCount;
        this.viewHeight = viewHeight;
        this.startY1 = startY1;
        this.startY2 = startY2;

        spineViewList = new ArrayList<>();
        this.collisionable = true;
    }

    @Override
    public void draw(Canvas canvas) {
        for (LongSpineGroupView longSpineGroupView : spineViewList) {
            longSpineGroupView.draw(canvas);
        }
    }

    @Override
    public void logic() {
        if (spineCount >= numGroup && spineViewList.isEmpty()) {
            isDead = true;
            spineViewList.clear();
            return;
        }
        Iterator<LongSpineGroupView> iterator = spineViewList.iterator();
        while (iterator.hasNext()) {
            LongSpineGroupView longSpineGroupView = iterator.next();
            if (longSpineGroupView.isDead()) {
                iterator.remove();
            } else {
                longSpineGroupView.logic();
            }
        }

        if (spineCount < numGroup && count % departGroupCount == 0) {
            LongSpineGroupView longSpineGroupView = new LongSpineGroupView(startX,
                    spineCount % 2 == 0 ? startY1 : startY2,
                    speedX, numPerGroup, departCount, viewHeight,
                    spineCount % 2 == 0 ? LongSpineView.SPINE_DIRECTION_DOWN : LongSpineView.SPINE_DIRECTION_UP);
            spineViewList.add(longSpineGroupView);
            spineCount++;
        }
        count++;
    }

    @Override
    public void collisionWith(HeartShapeView view) {
        for (LongSpineGroupView longSpineGroupView : spineViewList) {
            longSpineGroupView.collisionWith(view);
        }
    }
}
