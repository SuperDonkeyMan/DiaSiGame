package com.example.songye02.diasigame.model.textview;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

import android.graphics.Canvas;

/**
 * Created by songye02 on 2017/4/27.
 */

public class NormalTextViewGroup extends BaseShowableView implements Showable, Collisionable {
    private List<String> stringList;
    private List<CollisionNormalTextView> viewList;
    private boolean collisionable;
    private int textOrientation;
    private int interval; // 两行文字的间隔帧数
    private float endX;
    private float endY;
    private int frameCount; // textView从出现到消失总共用多少帧
    private int count = 0; // 帧数计数
    private int textNum = 0; // textView数量计数
    private boolean countEnough = false;

    public
    NormalTextViewGroup(float startX, float startY, float endX, float endY, int frameCount,
                               List<String> stringList, int textOrientation, int interval, boolean collisionable) {
        super(startX, startY, 0, 0);
        this.stringList = stringList;
        this.textOrientation = textOrientation;
        this.interval = interval;
        this.collisionable = collisionable;
        this.endX = endX;
        this.endY = endY;
        this.frameCount = frameCount;
        viewList = new LinkedList<>();
    }

    @Override
    public void draw(Canvas canvas) {
        for(CollisionNormalTextView textView:viewList){
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
        if (textNum > 300) {
            countEnough = true;
        }
        if(!countEnough){
            if(count%interval == 0){
                CollisionNormalTextView collisionNormalTextView = new CollisionNormalTextView(startX,startY,endX,
                        endY,frameCount,stringList.get(textNum),textOrientation);
                viewList.add(collisionNormalTextView);
            }
        }
        count++;
        if(count>frameCount*stringList.size()){
            isDead = true;
        }
    }

    @Override
    public boolean collisonWith(HeartShapeView view) {
        if(!collisionable){
            return false;
        }else {
            for(CollisionNormalTextView textView:viewList){
                if(textView.collisonWith(view)){
                    return true;
                }
            }
        }
        return false;
    }
}
