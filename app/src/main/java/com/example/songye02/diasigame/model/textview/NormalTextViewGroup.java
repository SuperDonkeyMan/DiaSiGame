package com.example.songye02.diasigame.model.textview;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/4/27.
 */

public class NormalTextViewGroup extends BaseShowableView {

    public static class NormalTextViewGroupParams {
        public String text;
        public float textSize;
        public int textColor;

        public NormalTextViewGroupParams(String text, float textSize) {
            this.text = text;
            this.textSize = textSize;
            this.textColor = 0xFFFFFFFF;
        }

        public NormalTextViewGroupParams(String text, float textSize, int textColor) {
            this.text = text;
            this.textSize = textSize;
            this.textColor = textColor;
        }
    }

    private List<NormalTextViewGroupParams> stringList;
    private List<CollisionNormalTextView> viewList;
    private int textOrientation;
    private int interval; // 两行文字的间隔帧数
    private float endX;
    private float endY;
    private int frameCount; // 单个textView从出现到消失总共用多少帧
    private int count = 0; // 帧数计数
    private int textNum = 0; // textView数量计数
    private boolean countEnough = false;

    public NormalTextViewGroup(float startX, float startY, float endX, float endY, int frameCount,
                               List<NormalTextViewGroupParams> stringList, int textOrientation, int interval, boolean collisionable) {
        super(startX, startY, 0, 0);
        this.stringList = stringList;
        this.textOrientation = textOrientation;
        this.interval = interval;
        this.endX = endX;
        this.endY = endY;
        this.frameCount = frameCount;
        viewList = new LinkedList<>();
        this.collisionable = collisionable;
    }

    @Override
    public void draw(Canvas canvas) {
        for (CollisionNormalTextView textView : viewList) {
            textView.draw(canvas);
        }
    }

    @Override
    public void logic() {
        Iterator<CollisionNormalTextView> iterator = viewList.iterator();
        while (iterator.hasNext()) {
            CollisionNormalTextView textView = iterator.next();
            if (textView.isDead()) {
                iterator.remove();
            } else {
                textView.logic();
            }
        }
        textNum = count / interval;
        if (textNum >= stringList.size()) {
            countEnough = true;
        }
        if (!countEnough) {
            if (count % interval == 0) {
                CollisionNormalTextView collisionNormalTextView = new CollisionNormalTextView(startX, startY, endX,
                        endY, frameCount, stringList.get(textNum).text, textOrientation);
                collisionNormalTextView.setTextSize(stringList.get(textNum).textSize);
                collisionNormalTextView.setTextColor(stringList.get(textNum).textColor);
                viewList.add(collisionNormalTextView);
            }
        }
        count++;
        if (count > frameCount * stringList.size()) {
            isDead = true;
        }
    }

    @Override
    public void collisionWith(HeartShapeView view) {
        for (CollisionNormalTextView textView : viewList) {
            textView.collisionWith(view);
        }
    }
}
