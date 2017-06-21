package com.example.songye02.diasigame.timecontroller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.model.textview.TimeDialogueParams;
import com.example.songye02.diasigame.model.textview.TimeDialogueTextGroup;
import com.example.songye02.diasigame.model.textview.TriggerDialogueGroup;
import com.example.songye02.diasigame.test.MenuSurfaceView;
import com.example.songye02.diasigame.utils.DpiUtil;
import com.example.songye02.diasigame.utils.GameStateUtil;

import android.util.Log;

/**
 * Created by songye02 on 2017/5/12.
 */

public class MenuTimeController extends TimeController {

    @Override
    void initTimerEvents() {
        eventsList = new ArrayList<>();
        switch (DiaSiApplication.gameState){
            case GameStateUtil.GAME_STATE_MENU:
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
                break;
            case GameStateUtil.GAME_STATE_OVER:
                eventsList.add(new TimerEvent<MenuViewHolder>() {
                    @Override
                    public long getIntervalTime() {
                        return 200;
                    }

                    @Override
                    public void addTimerEvent(MenuViewHolder viewHolder) {
                        HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                        PortraitView portraitView = viewHolder.portraitView;
                        List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                        TimeDialogueParams[] paramses = new TimeDialogueParams[3];
                        paramses[0] = new TimeDialogueParams("刘醒！", 100, 200);
                        paramses[1] = new TimeDialogueParams("警署有规定，下属不可跟上司顶嘴", 400, 1000);
                        paramses[2] = new TimeDialogueParams("去操场玩够一百下，去！", 1100, 1500);
                        TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                                portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                                portraitView.getCurrentY() + DpiUtil.dipToPix(20), 1000000000);
                        mMoveables.add(group);
                    }
                });
                break;
        }

    }
}
