package com.example.songye02.diasigame.timecontroller;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
            eventsList = new ArrayList<>();
            eventsList.add(new TimerEvent<MenuViewHolder>() {
                @Override
                public long getIntervalTime() {
                    return 500;
                }

                @Override
                public void addTimerEvent(MenuViewHolder viewHolder) {
                    HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                    PortraitView portraitView = viewHolder.portraitView;
                    List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                    TimeDialogueParams[] paramses = new TimeDialogueParams[1];
                    paramses[0] = new TimeDialogueParams("昨天干嘛不来上班啊？", 100, 600);
                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                            portraitView.getCurrentY() + DpiUtil.dipToPix(20), 1500);
                    mMoveables.add(group);
                }
            });

            eventsList.add(new TimerEvent<MenuViewHolder>() {
                @Override
                public long getIntervalTime() {
                    return 2000;
                }

                @Override
                public void addTimerEvent(MenuViewHolder viewHolder) {
                    HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                    PortraitView portraitView = viewHolder.portraitView;
                    List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                    TimeDialogueParams[] paramses = new TimeDialogueParams[1];
                    paramses[0] = new TimeDialogueParams("警署有规定", 100, 500);
                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                            portraitView.getCurrentY() + DpiUtil.dipToPix(20), 1500);
                    mMoveables.add(group);
                }
            });

            eventsList.add(new TimerEvent<MenuViewHolder>() {
                @Override
                public long getIntervalTime() {
                    return 3500;
                }

                @Override
                public void addTimerEvent(MenuViewHolder viewHolder) {
                    HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                    PortraitView portraitView = viewHolder.portraitView;
                    List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                    TimeDialogueParams[] paramses = new TimeDialogueParams[1];
                    paramses[0] = new TimeDialogueParams("下属不许顶上司嘴", 100, 600);
                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                            portraitView.getCurrentY() + DpiUtil.dipToPix(20), 1500);
                    mMoveables.add(group);
                }
            });
            eventsList.add(new TimerEvent<MenuViewHolder>() {
                @Override
                public long getIntervalTime() {
                    return 5000;
                }

                @Override
                public void addTimerEvent(MenuViewHolder viewHolder) {
                    HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                    PortraitView portraitView = viewHolder.portraitView;
                    List<BaseShowableView> mMoveables = viewHolder.mMoveables;
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
}
