package com.example.songye02.diasigame.timecontroller;

import java.util.List;

import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;

/**
 * Created by songye02 on 2017/6/19.
 */

public class GameViewHolder<T extends Showable> extends BaseViewHolder {
    public GameViewHolder(List<T> mMoveables,
                          HeartShapeView heartShapeView,
                          PortraitView portraitView) {
        super(mMoveables);
        this.heartShapeView = heartShapeView;
        this.portraitView = portraitView;
    }

    public HeartShapeView heartShapeView;
    public PortraitView portraitView;
}
