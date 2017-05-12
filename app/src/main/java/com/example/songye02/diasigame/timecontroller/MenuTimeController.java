package com.example.songye02.diasigame.timecontroller;

import java.util.ArrayDeque;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;

/**
 * Created by songye02 on 2017/5/12.
 */

public class MenuTimeController extends TimeController {
    @Override
    void initTimerEvents() {
        {
            timerEvents = new ArrayDeque();
            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return 1 * 1000;
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView, PortraitView portraitView) {

                }
            });
        }
    }
}
