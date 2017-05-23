package com.example.songye02.diasigame.model.textview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/5/17.
 */

public class FollowInnerTextViewGroup extends BaseShowableView implements Collisionable {

    public static final int TEXT_ORIENTATION_HORIZONTAL = 0;
    public static final int TEXT_ORIENTATION_VERTICAL = 1;
    protected int textOrientation;
    protected List<String> textList;
    protected List<FollowInnerTextView> followTextViewList;
    protected HeartShapeView heartShapeView;

    /**
     * pauseBefore 第一个字等多久后飞出去
     * pauseDepart 之后的字每等多久飞出去
     * pauseAfter 停住后等多久
     */
    public FollowInnerTextViewGroup(float startX, float startY, int textOrientation, List<String> textList,
                                    HeartShapeView heartShapeView, int pauseBefore, int pauseDepart, int speed) {
        super(startX, startY, 0, 0);
        this.textOrientation = textOrientation;
        this.textList = textList;
        this.heartShapeView = heartShapeView;
        followTextViewList = new ArrayList<>();
        float shiftDistance = 0;
        for (int i = 0; i < textList.size(); i++) {
            // 文字水平放置的时候
            if (textOrientation == TEXT_ORIENTATION_HORIZONTAL) {
                float textStartX = shiftDistance + startX;
                float textStartY = startY;
                FollowInnerTextView textView = new FollowInnerTextView(textStartX, textStartY, heartShapeView,
                        speed, pauseBefore + pauseDepart * i, textList.get(i), NormalTextView
                        .TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
                followTextViewList.add(textView);
                shiftDistance += textView.getWidth();
            } else {
                // 文字竖直放置的时候
                float textStartX = startX;
                float textStartY = shiftDistance + startY;
                FollowInnerTextView textView = new FollowInnerTextView(textStartX, textStartY, heartShapeView,
                        40, pauseBefore + pauseDepart * i, textList.get(i), NormalTextView
                        .TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
                followTextViewList.add(textView);
                shiftDistance += textView.getHeight();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (FollowInnerTextView followInnerTextView : followTextViewList) {
            followInnerTextView.draw(canvas);
        }
    }

    @Override
    public void logic() {
        Iterator<FollowInnerTextView> iterator = followTextViewList.iterator();
        while (iterator.hasNext()) {
            FollowInnerTextView followInnerTextView = iterator.next();
            if (followInnerTextView.isDead()) {
                iterator.remove();
            } else {
                followInnerTextView.logic();
            }
        }
        if (followTextViewList.size() == 0) {
            isDead = true;
        }

    }

    @Override
    public void collisionWith(HeartShapeView view) {
        for (FollowInnerTextView followInnerTextView : followTextViewList) {
            followInnerTextView.collisionWith(view);
        }
    }
}
