package com.example.songye02.diasigame.timecontroller;

import java.util.ArrayDeque;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.model.textview.TimeDialogueParams;
import com.example.songye02.diasigame.model.textview.TimeDialogueTextGroup;
import com.example.songye02.diasigame.model.textview.TriggerDialogueGroup;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.util.Log;

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
                    return 500;
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView,
                                          PortraitView portraitView) {
                    TimeDialogueParams[] paramses = new TimeDialogueParams[1];
                    paramses[0] = new TimeDialogueParams("昨天干嘛不来上班啊？", 100, 600);
                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                            portraitView.getCurrentY() + DpiUtil.dipToPix(20), 1500);
                    mMoveables.add(group);
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return 2000;
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView,
                                          PortraitView portraitView) {
                    TimeDialogueParams[] paramses = new TimeDialogueParams[1];
                    paramses[0] = new TimeDialogueParams("警署有规定", 100, 500);
                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                            portraitView.getCurrentY() + DpiUtil.dipToPix(20), 1500);
                    mMoveables.add(group);
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return 3500;
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView,
                                          PortraitView portraitView) {
                    TimeDialogueParams[] paramses = new TimeDialogueParams[1];
                    paramses[0] = new TimeDialogueParams("下属不许顶上司嘴", 100, 600);
                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                            portraitView.getCurrentY() + DpiUtil.dipToPix(20), 1500);
                    mMoveables.add(group);
                }
            });
            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return 5000;
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView,
                                          PortraitView portraitView) {
                    TimeDialogueParams[] paramses = new TimeDialogueParams[1];
                    paramses[0] = new TimeDialogueParams("我要开除你啊", 100, 600);
                    TriggerDialogueGroup group = new TriggerDialogueGroup(paramses,
                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                            portraitView.getCurrentY() + DpiUtil.dipToPix(20),
                            System.currentTimeMillis());
                    mMoveables.add(group);
                }
            });
        }
    }

    public void removePreviousTimerEvents(List<BaseShowableView> mMoveables) {
        timerEvents.clear();
        mMoveables.clear();
        // 将当前正在缓存的任务也要置空
        timerEvent = null;
    }

    public void addNewTimerEvent(Long startTime, List<TimerEvent> newTimerEvents) {
        this.startTime = startTime;
        timerEvents.addAll(newTimerEvents);
        // 这里必须提前pop出一个，因为当父类发现timerEvent为空的时候就跳出循环了，且timerEvent可能就是之前pop出来的任务
        timerEvent = timerEvents.pop();
        Log.d("timeController", "" + timerEvents.size());
    }

    // 重新设定开始时间，以重新设定的时间为新的基准
    public void clearAndAddNewTimerEvents(Long startTime, List<BaseShowableView> mMoveables, List<TimerEvent>
            newTimerEvents) {
        removePreviousTimerEvents(mMoveables);
        addNewTimerEvent(startTime, newTimerEvents);
    }
}
