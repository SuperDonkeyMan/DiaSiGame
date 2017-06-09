package com.example.songye02.diasigame.model.textview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/5/25.
 * 一大堆从左向右跑的吔
 */

public class RandomTextViewGroup extends BaseShowableView {
    private List<CollisionNormalTextView> list = new ArrayList<>();
    private float width;
    private float height;
    private int frameCount;
    private int count;
    private Random random;
    private float threshold = 0.1f;
    private String text;

    public RandomTextViewGroup(float startX, float startY, float width, float height, float speed, int frameCount,
                               String text) {
        super(startX, startY, speed, 0);
        this.width = width;
        this.height = height;
        this.frameCount = frameCount;
        this.collisionable = true;
        this.text = text;
        random = new Random(31);
    }

    @Override
    public void draw(Canvas canvas) {
        for (CollisionNormalTextView collisionNormalTextView : list) {
            collisionNormalTextView.draw(canvas);
        }
    }

    @Override
    public void logic() {
        // 最后一个飞出屏幕
        if (count > frameCount && list.get(list.size()-1).getCurrentX()<0) {
            isDead = true;
            list.clear();
            return;
        }
        Iterator<CollisionNormalTextView> iterator = list.iterator();
        while (iterator.hasNext()) {
            CollisionNormalTextView collisionNormalTextView = iterator.next();
            if (collisionNormalTextView.isDead()) {
                iterator.remove();
            } else {
                collisionNormalTextView.logic();
            }
        }
        // 在阈值内则添加一个CollisionNormalTextView
        if (count <= frameCount && random.nextFloat() < threshold) {
            float viewStartX = startX;
            float viewStartY = startY + height * random.nextFloat();
            float viewEndX = startX - width;
            float viewEndY = viewStartY;
            int viewFramecount = (int) (width / speedX);
            CollisionNormalTextView view = new CollisionNormalTextView(viewStartX, viewStartY, viewEndX, viewEndY,
                    viewFramecount, text, NormalTextView.TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
            list.add(view);
        }
        count++;
    }

    @Override
    public void collisionWith(HeartShapeView view) {
        for (CollisionNormalTextView collisionNormalTextView : list) {
            collisionNormalTextView.collisionWith(view);
        }
    }
}
