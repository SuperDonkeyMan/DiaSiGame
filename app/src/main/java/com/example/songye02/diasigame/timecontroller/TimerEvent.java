package com.example.songye02.diasigame.timecontroller;

import java.util.List;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;

/**
 * Created by songye02 on 2017/4/25.
 */

public interface TimerEvent {
    long getTntervalTime();
    void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView);
}
