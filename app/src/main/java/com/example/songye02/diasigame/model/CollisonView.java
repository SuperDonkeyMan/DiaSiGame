package com.example.songye02.diasigame.model;

import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

/**
 * Created by songye02 on 2017/4/19.
 */

public abstract class CollisonView extends BaseView {

    public CollisonView(float startX, float startY, float speedX, float speedY) {
        super(startX, startY, speedX, speedY);
    }

    public abstract boolean collisonWith(HeartShapeView view);
}
