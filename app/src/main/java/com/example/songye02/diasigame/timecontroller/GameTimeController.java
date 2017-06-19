package com.example.songye02.diasigame.timecontroller;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.R;
import com.example.songye02.diasigame.callback.ButtonVisibilityCallBack;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.ArrowView;
import com.example.songye02.diasigame.model.shapeview.EasyLongSpineGroupView;
import com.example.songye02.diasigame.model.shapeview.GunGroup;
import com.example.songye02.diasigame.model.shapeview.GunView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.model.shapeview.SinLongSpineGroupView;
import com.example.songye02.diasigame.model.shapeview.SpineView;
import com.example.songye02.diasigame.model.textview.FollowInnerTextViewGroup;
import com.example.songye02.diasigame.model.textview.FollowTextViewGroup;
import com.example.songye02.diasigame.model.textview.NormalTextView;
import com.example.songye02.diasigame.model.textview.NormalTextViewGroup;
import com.example.songye02.diasigame.model.textview.NormalTextViewGroup.NormalTextViewGroupParams;
import com.example.songye02.diasigame.model.textview.ParaboleTextGroup;
import com.example.songye02.diasigame.model.textview.PauseSpeedUpTextViewGroup;
import com.example.songye02.diasigame.model.textview.PauseViewText;
import com.example.songye02.diasigame.model.textview.PauseViewTextGroup;
import com.example.songye02.diasigame.model.textview.PauseViewTextGroup.PauseViewTextParams;
import com.example.songye02.diasigame.model.textview.RandomTextViewGroup;
import com.example.songye02.diasigame.model.textview.RotateTextView;
import com.example.songye02.diasigame.model.textview.TimeDialogueParams;
import com.example.songye02.diasigame.model.textview.TimeDialogueTextGroup;
import com.example.songye02.diasigame.utils.DpiUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by songye02 on 2017/5/12.
 */

public class GameTimeController extends TimeController {

    private ButtonVisibilityCallBack buttonVisibilityCallBack;  // 按键决定重力方向
    public boolean ifFinish;

    @Override
    void initTimerEvents() {
        ifFinish = false;
        eventsList = new ArrayList<>();
        eventsList.add(new TimerEvent<GameViewHolder>() {
            @Override
            public long getIntervalTime() {
                return 0;
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                TimeDialogueParams[] paramses = new TimeDialogueParams[3];
                paramses[0] = new TimeDialogueParams("应该在", 0, 540);
                paramses[1] = new TimeDialogueParams("地狱里", 640, 930);
                paramses[2] = new TimeDialogueParams("吔屎", 1100, 1400);
                TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                        portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                        portraitView.getCurrentY() + DpiUtil.dipToPix(20), 1750);
                group.setPlaySound(false);
                mMoveables.add(group);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (1.902 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new ParaboleTextGroup(DiaSiApplication.getCanvasWidth() / 2,
                        portraitView.getCurrentY() + portraitView.getHeight() * 0.2f, mHeartShapeView
                        .getBoundaryW() / 2, portraitView.getHeight() * 0.4f, 14300));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (17.520 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<String> list = string2List("吔屎吔屎梁非凡");
                FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
                        mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() - DpiUtil
                                .dipToPix(25), mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10),
                        FollowInnerTextViewGroup.TEXT_ORIENTATION_VERTICAL, list, mHeartShapeView,
                        18, 12, 15, 15);
                mMoveables.add(followInnerTextViewGroup);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (17.84 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.setPortraitBmp(PortraitView.BMP_LIUXING);
                TimeDialogueParams[] paramses = new TimeDialogueParams[4];
                paramses[0] = new TimeDialogueParams("吔屎吔屎梁非凡", 16, 1200);
                paramses[1] = new TimeDialogueParams("你听不到我~我在讲", 2000, 3600);
                paramses[2] = new TimeDialogueParams("讲到你哥听到为止", 4000, 5240);
                paramses[3] = new TimeDialogueParams("吔屎啦梁非凡~", 6000, 6600);
                TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                        portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                        portraitView.getCurrentY() + DpiUtil.dipToPix(20), 7720);
                group.setPlaySound(false);
                mMoveables.add(group);

            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (19.520 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<String> list = string2List("你听不到我");
                FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10),
                        FollowInnerTextViewGroup.TEXT_ORIENTATION_VERTICAL,
                        list, mHeartShapeView, 16, 10, 15, 15);
                mMoveables.add(followInnerTextViewGroup);

            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (21 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<String> list = string2List("我在讲");
                FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10),
                        FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
                        list, mHeartShapeView, 14, 5, 15, 15);
                mMoveables.add(followInnerTextViewGroup);

            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (21.6 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<String> list = string2List("讲到你哥听到为止");
                FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() - DpiUtil
                                .dipToPix(25),
                        FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
                        list, mHeartShapeView, 16, 11, 15, 15);
                mMoveables.add(followInnerTextViewGroup);

            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (23.720 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<String> list = string2List("吔屎啦梁非凡");
                FollowInnerTextViewGroup followInnerTextViewGroup1 = new
                        FollowInnerTextViewGroup(
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(25),
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH()
                                - DpiUtil
                                .dipToPix(25),
                        FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
                        list, mHeartShapeView, 10, 10, 15, 15);

                FollowInnerTextViewGroup followInnerTextViewGroup2 = new
                        FollowInnerTextViewGroup(
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(25),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(5),
                        FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
                        list, mHeartShapeView, 10, 10, 15, 15);
                mMoveables.add(followInnerTextViewGroup1);
                mMoveables.add(followInnerTextViewGroup2);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (25.6 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.startTwinkle(25);
                mHeartShapeView.startMove(portraitView.getCenterX(), portraitView
                                .getCenterY(), 5,
                        heartShapeView1 -> {
                            heartShapeView1.setDismiss(true);
                            heartShapeView1.setHeartMode(HeartShapeView
                                    .HEART_MODE_NORMAL);
                        });
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (26.00 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.setPortraitBmp(PortraitView.BMP_FEIFANGE);
                mHeartShapeView.setDismiss(false);
                mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                mHeartShapeView.changeBoundaryWithAmination(mHeartShapeView
                                .getBoundaryX() -
                                mHeartShapeView
                                        .getBoundaryW() / 2, mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryW
                                () * 2,
                        mHeartShapeView.getBoundaryH(), 10);
                buttonVisibilityCallBack.showButton();

                TimeDialogueParams[] paramses = new TimeDialogueParams[4];
                paramses[0] = new TimeDialogueParams("刘醒我是你上司", 0, 1320);
                paramses[1] = new TimeDialogueParams("警署有规定不啵上司嘴", 2040, 3640);
                paramses[2] = new TimeDialogueParams("我要艹你 我要艹你", 3960, 5240);
                paramses[3] = new TimeDialogueParams("大声讲我艹你", 5960, 6760);
                TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                        portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                        portraitView.getCurrentY() + DpiUtil.dipToPix(20), 8360);
                group.setPlaySound(false);
                mMoveables.add(group);

                List<NormalTextViewGroupParams> list = new ArrayList<>();
                list.add(new NormalTextViewGroupParams("刘醒我是你", 12));
                list.add(new NormalTextViewGroupParams("刘醒我是你", 12));
                NormalTextViewGroup normalTextViewGroup1 = new NormalTextViewGroup(mHeartShapeView
                        .getBoundaryX()
                        + mHeartShapeView.getBoundaryW() - DpiUtil.spToPix(12), mHeartShapeView
                        .getBoundaryY(),
                        mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        96, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN, 50, true);
                mMoveables.add(normalTextViewGroup1);

                List<NormalTextViewGroupParams> list2 = new ArrayList<>();
                list2.add(new NormalTextViewGroupParams("刘醒我是你", 12));
                list2.add(new NormalTextViewGroupParams("刘醒我是你", 12));
                NormalTextViewGroup normalTextViewGroup2 =
                        new NormalTextViewGroup(mHeartShapeView.getBoundaryX(),
                                mHeartShapeView.getBoundaryY(),
                                mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() -
                                        DpiUtil.spToPix(12),
                                mHeartShapeView.getBoundaryY(), 96, list2, NormalTextView
                                .TEXT_ORIENTATION_VERTICAL_UPTODOWN, 50, true);
                mMoveables.add(normalTextViewGroup2);

                List<NormalTextViewGroupParams> list3 = new ArrayList<>();
                // 从下到上，因此要反过来
                list3.add(new NormalTextViewGroupParams("司上", 12));
                list3.add(new NormalTextViewGroupParams("司上", 12));
                list3.add(new NormalTextViewGroupParams("定规有署警", 12));
                list3.add(new NormalTextViewGroupParams("嘴司上顶不", 12));
                list3.add(new NormalTextViewGroupParams("你艹要我", 12));
                list3.add(new NormalTextViewGroupParams("你艹要我", 12));
                list3.add(new NormalTextViewGroupParams("你艹我讲声大", 12));
                NormalTextViewGroup normalTextViewGroup3 =
                        new NormalTextViewGroup(mHeartShapeView.getBoundaryX() +
                                mHeartShapeView.getBoundaryW() - DpiUtil.spToPix(12),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                mHeartShapeView.getBoundaryX(),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                96, list3, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 50,
                                true);
                mMoveables.add(normalTextViewGroup3);

                List<NormalTextViewGroupParams> list4 = new ArrayList<>();
                // 从下到上，因此要反过来
                list4.add(new NormalTextViewGroupParams("司上", 12));
                list4.add(new NormalTextViewGroupParams("司上", 12));
                list4.add(new NormalTextViewGroupParams("定规有署警", 12));
                list4.add(new NormalTextViewGroupParams("嘴司上顶不", 12));
                list4.add(new NormalTextViewGroupParams("你艹要我", 12));
                list4.add(new NormalTextViewGroupParams("你艹要我", 12));
                list4.add(new NormalTextViewGroupParams("你艹我讲声大", 12));
                NormalTextViewGroup normalTextViewGroup4 =
                        new NormalTextViewGroup(mHeartShapeView.getBoundaryX(),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() -
                                        DpiUtil.spToPix(12),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                96, list4, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 50,
                                true);
                mMoveables.add(normalTextViewGroup4);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (33.880 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.startTwinkle(25);
                mHeartShapeView.startMove(portraitView.getCenterX(), portraitView.getCenterY(), 5,
                        heartShapeView1 -> {
                            heartShapeView1.setDismiss(true);
                            heartShapeView1.setHeartMode(HeartShapeView.HEART_MODE_NORMAL);
                        });
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (34.6 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.setPortraitBmp(PortraitView.BMP_LIUXING);
                portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
                        mHeartShapeView.getBoundaryX(), portraitView.getCurrentY(), 10);
                mHeartShapeView.setDismiss(false);
                mHeartShapeView.changeBoundaryWithAmination(DiaSiApplication.getCanvasWidth() / 2 -
                                (DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150
                                        + 60)) / 2, DpiUtil.dipToPix(150),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150 + 60),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150 + 60), 10);
                mHeartShapeView.setHeartCenter();
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (34.800 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                TimeDialogueParams[] paramses = new TimeDialogueParams[5];
                paramses[0] = new TimeDialogueParams("报告非凡哥警署有规定的", 160, 1480);
                paramses[1] = new TimeDialogueParams("警员有权请事假的", 2840, 4000);
                paramses[2] = new TimeDialogueParams("我规定讲你再啵啵你嘴", 4160, 5400);
                paramses[3] = new TimeDialogueParams("都是六个字", 7200, 8160);
                paramses[4] = new TimeDialogueParams("吔屎啦梁非凡", 8840, 10920);
                TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                        mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() +
                                portraitView.getWidth() + DpiUtil.dipToPix(20),
                        portraitView.getCurrentY() + DpiUtil.dipToPix(20), 11960);
                group.setPlaySound(false);
                mMoveables.add(group);
                portraitView.move(mHeartShapeView.getBoundaryX(), portraitView.getCurrentY(),
                        mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW(),
                        portraitView.getCurrentY(), 56);

                PauseSpeedUpTextViewGroup group1 = new PauseSpeedUpTextViewGroup(mHeartShapeView
                        .getBoundaryX(), portraitView.getCenterY() + DpiUtil.dipToPix(30), 17,
                        string2List("警署就有规定的"),
                        PauseSpeedUpTextViewGroup.APPEAR_DIRECTION_RIGHT, PauseSpeedUpTextViewGroup
                        .PAUSE_INCREMENT_DIRECTION_RIGHT, 8, 50, 5, 100000);
                PauseSpeedUpTextViewGroup group2 = new PauseSpeedUpTextViewGroup(mHeartShapeView
                        .getBoundaryX(), portraitView.getCenterY(), 17, string2List("报告梁非凡哥哥"),
                        PauseSpeedUpTextViewGroup.APPEAR_DIRECTION_RIGHT, PauseSpeedUpTextViewGroup
                        .PAUSE_INCREMENT_DIRECTION_RIGHT, 8, 50, 5, 100000);
                mMoveables.add(group1);
                mMoveables.add(group2);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (37.480 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
                        mHeartShapeView.getBoundaryX() - portraitView.getWidth(), portraitView.getCurrentY(), 25);
                FollowTextViewGroup group1 =
                        new FollowTextViewGroup(mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(30),
                                portraitView.getCenterY() + DpiUtil.dipToPix(30), 17, string2List("请 事 假 的"),
                                FollowTextViewGroup.APPEAR_DIRECTION_LEFT, FollowTextViewGroup
                                .PAUSE_INCREMENT_DIRECTION_RIGHT, 5, 10, 0, 20, mHeartShapeView);
                FollowTextViewGroup group2 =
                        new FollowTextViewGroup(mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(30),
                                portraitView.getCenterY(), 17, string2List("警 有 员 权"), FollowTextViewGroup
                                .APPEAR_DIRECTION_LEFT, FollowTextViewGroup.PAUSE_INCREMENT_DIRECTION_LEFT, 5,
                                15, 8, 20, mHeartShapeView);
                mMoveables.add(group1);
                mMoveables.add(group2);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (38.920 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
                        mHeartShapeView.getBoundaryR() + portraitView.getWidth(),
                        portraitView.getCurrentY(), 25);
                FollowTextViewGroup group1 =
                        new FollowTextViewGroup(mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(50),
                                portraitView.getCenterY() + DpiUtil.dipToPix(30), 17, string2List("再啵啵你嘴"),
                                FollowTextViewGroup.APPEAR_DIRECTION_RIGHT, FollowTextViewGroup
                                .PAUSE_INCREMENT_DIRECTION_RIGHT, 5, 10, 3, 20, mHeartShapeView);
                FollowTextViewGroup group2 =
                        new FollowTextViewGroup(mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(50),
                                portraitView.getCenterY(), 17, string2List("我规定讲你"), FollowTextViewGroup
                                .APPEAR_DIRECTION_RIGHT, FollowTextViewGroup
                                .PAUSE_INCREMENT_DIRECTION_LEFT, 5, 25, 15, 20, mHeartShapeView);
                mMoveables.add(group1);
                mMoveables.add(group2);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (41.6 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
                        mHeartShapeView.getBoundaryX() - portraitView.getWidth(), portraitView.getCurrentY(), 25);
                List<PauseViewTextGroup.PauseViewTextParams> list = new LinkedList<>();
                list.add(new PauseViewTextParams(mHeartShapeView.getBoundaryX() + mHeartShapeView
                        .getBoundaryW() - DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(50), "都"));
                list.add(new PauseViewTextParams(mHeartShapeView.getBoundaryX() + mHeartShapeView
                        .getBoundaryW() - DpiUtil.dipToPix(30),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(70), "是"));
                list.add(new PauseViewTextParams(mHeartShapeView.getBoundaryX() + mHeartShapeView
                        .getBoundaryW() - DpiUtil.dipToPix(50),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(90), "六"));
                list.add(new PauseViewTextParams(mHeartShapeView.getBoundaryX() + mHeartShapeView
                        .getBoundaryW() - DpiUtil.dipToPix(70),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(110), "个"));
                list.add(new PauseViewTextParams(mHeartShapeView.getBoundaryX() + mHeartShapeView
                        .getBoundaryW() - DpiUtil.dipToPix(80),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(130), "字"));
                PauseViewTextGroup group = new PauseViewTextGroup(mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(25),
                        portraitView.getCenterY(), 17, 36, list, PauseViewTextGroup
                        .APPEAR_DIRECTION_LEFT, PauseViewTextGroup.PAUSE_INCREMENT_DIRECTION_LEFT, 5, 25, 30, 25);
                List<String> textList = string2List("吔屎啦梁非凡");
                group.setTextViewDeadCallback((view, index, totalNum) -> {
                    mMoveables.add(new RotateTextView(view.getCurrentX(), view.getCurrentY(),
                            textList.get(index)));
                    if (index == totalNum - 1) {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                mMoveables.add(new RotateTextView(mHeartShapeView.getBoundaryX()
                                        + mHeartShapeView.getBoundaryW() / 2, mHeartShapeView
                                        .getBoundaryY() + mHeartShapeView
                                        .getBoundaryH() / 2, textList.get(index + 1)));
                            }
                        }, 20 * 25);
                        // 一帧20ms，22帧
                    }
                });
                mMoveables.add(group);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (43.760 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
                        DiaSiApplication.getPortraitWidth() / 2 - portraitView.getWidth() / 2,
                        portraitView.getCurrentY(), 10);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (46.840 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
                        mHeartShapeView.getBoundaryX(), portraitView.getCurrentY(), 20);

                mMoveables.add(new RandomTextViewGroup(DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryY(), DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryH(), 20, 60, "吔"));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (50.360 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.startTwinkle(25);
                mHeartShapeView.startMove(portraitView.getCenterX(), portraitView.getCenterY(), 5,
                        heartShapeView1 -> {
                            heartShapeView1.setDismiss(true);
                            heartShapeView1.setHeartMode(HeartShapeView.HEART_MODE_NORMAL);
                        });
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (50.920 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.setPortraitBmp(PortraitView.BMP_FEIFANGE);
                mHeartShapeView.setDismiss(false);
                mHeartShapeView.setHeartCenter();
                mHeartShapeView.changeBoundaryWithAmination(mHeartShapeView.getBoundaryX() -
                                mHeartShapeView.getBoundaryW() / 2, mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryW() * 2, mHeartShapeView.getBoundaryH(), 10);
                mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_BOTTOM);
                buttonVisibilityCallBack.showButton();
                TimeDialogueParams[] paramses = new TimeDialogueParams[5];
                paramses[0] = new TimeDialogueParams("你再大声讲对不起非凡哥", 0, 1080);
                paramses[1] = new TimeDialogueParams("我听不到你在讲", 2480, 3600);
                paramses[2] = new TimeDialogueParams("再讲讲到我听到为止", 3760, 5040);
                paramses[3] = new TimeDialogueParams("警署有规定", 6840, 7720);
                paramses[4] = new TimeDialogueParams("刘醒讲非凡哥", 8400, 10480);
                TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                        portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                        portraitView.getCurrentY() + DpiUtil.dipToPix(20), 11840);
                group.setPlaySound(false);
                mMoveables.add(group);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (50.960 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<NormalTextViewGroupParams> list = new ArrayList<>();
                // 从下到上，因此要反过来
                int blueColor = DiaSiApplication.getInstance().getResources().getColor(R.color
                        .textBlue);
                int orangeColor = DiaSiApplication.getInstance().getResources().getColor(R.color
                        .textOrange);
                list.add(new NormalTextViewGroupParams("你你你你你你你你", 14, blueColor));
                list.add(new NormalTextViewGroupParams("再再再再再再再再", 14, blueColor));
                list.add(new NormalTextViewGroupParams("大大大大大大大大", 14, orangeColor));
                list.add(new NormalTextViewGroupParams("声声声声声声声声", 14, orangeColor));
                list.add(new NormalTextViewGroupParams("讲讲讲讲讲讲讲讲", 14, blueColor));
                list.add(new NormalTextViewGroupParams("对对对对对对对对", 14, blueColor));
                list.add(new NormalTextViewGroupParams("不不不不不不不不", 14, orangeColor));
                list.add(new NormalTextViewGroupParams("起起起起起起起起", 14, orangeColor));
                list.add(new NormalTextViewGroupParams("非非非非非非非非", 14, blueColor));
                NormalTextViewGroup normalTextViewGroup4 =
                        new NormalTextViewGroup(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                                mHeartShapeView.getBoundaryY(),
                                mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(14),
                                mHeartShapeView.getBoundaryY(),
                                84, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN, 8,
                                false);
                mMoveables.add(normalTextViewGroup4);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (52.480 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<NormalTextViewGroupParams> list = new ArrayList<>();
                // 从下到上，因此要反过来
                list.add(new NormalTextViewGroupParams("凡凡", 14));
                list.add(new NormalTextViewGroupParams("哥哥哥", 14));
                NormalTextViewGroup normalTextViewGroup4 =
                        new NormalTextViewGroup(mHeartShapeView.getBoundaryX() + mHeartShapeView
                                .getBoundaryW(),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                mHeartShapeView.getBoundaryX(),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                84, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 10,
                                true);
                mMoveables.add(normalTextViewGroup4);
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (53.640 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<NormalTextViewGroupParams> list = new ArrayList<>();
                // 从下到上，因此要反过来
                list.add(new NormalTextViewGroupParams("我", 14));
                list.add(new NormalTextViewGroupParams("听", 14));
                list.add(new NormalTextViewGroupParams("不", 14));
                list.add(new NormalTextViewGroupParams("到", 14));
                list.add(new NormalTextViewGroupParams("在", 14));
                list.add(new NormalTextViewGroupParams("讲", 14));
                list.add(new NormalTextViewGroupParams("再", 14));
                list.add(new NormalTextViewGroupParams("听", 14));
                list.add(new NormalTextViewGroupParams("不", 14));
                list.add(new NormalTextViewGroupParams("到", 14));
                list.add(new NormalTextViewGroupParams("继", 14));
                list.add(new NormalTextViewGroupParams("续", 14));
                list.add(new NormalTextViewGroupParams("讲", 14));
                NormalTextViewGroup normalTextViewGroup4 =
                        new NormalTextViewGroup(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                mHeartShapeView.getBoundaryX(),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                100, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 25,
                                true);
                mMoveables.add(normalTextViewGroup4);

            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (53.840 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<NormalTextViewGroupParams> list = new ArrayList<>();
                // 从下到上，因此要反过来
                list.add(new NormalTextViewGroupParams("到到到到到到到", 14));
                list.add(new NormalTextViewGroupParams("我我我我我我我", 14));
                list.add(new NormalTextViewGroupParams("听听听听听听听", 14));
                list.add(new NormalTextViewGroupParams("到到到到到到到", 14));
                list.add(new NormalTextViewGroupParams("为为为为为为为", 14));
                list.add(new NormalTextViewGroupParams("止止止止止止止", 14));
                NormalTextViewGroup normalTextViewGroup4 =
                        new NormalTextViewGroup(mHeartShapeView.getBoundaryX(),
                                mHeartShapeView.getBoundaryY(),
                                mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                                mHeartShapeView.getBoundaryY(),
                                100, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN, 45,
                                true);
                mMoveables.add(normalTextViewGroup4);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (61 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<NormalTextViewGroupParams> list = new ArrayList<>();
                // 从下到上，因此要反过来
                list.add(new NormalTextViewGroupParams("警警", 14));
                list.add(new NormalTextViewGroupParams("署署署", 14));
                list.add(new NormalTextViewGroupParams("有有有有", 14));
                list.add(new NormalTextViewGroupParams("规规规", 14));
                list.add(new NormalTextViewGroupParams("定定", 14));
                NormalTextViewGroup normalTextViewGroup4 =
                        new NormalTextViewGroup(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                mHeartShapeView.getBoundaryX(),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                100, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 12,
                                true);
                mMoveables.add(normalTextViewGroup4);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (59.4 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                List<NormalTextViewGroupParams> list = new ArrayList<>();
                // 从下到上，因此要反过来
                int blueColor = DiaSiApplication.getInstance().getResources().getColor(R.color
                        .textBlue);
                int orangeColor = DiaSiApplication.getInstance().getResources().getColor(R.color
                        .textOrange);
                for (int i = 0; i < 7; i++) {
                    list.add(new NormalTextViewGroupParams("刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘", 14,
                            blueColor));
                }
                list.add(new NormalTextViewGroupParams(" ", 14));
                for (int i = 0; i < 7; i++) {
                    list.add(new NormalTextViewGroupParams("醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 ",
                            14, orangeColor));
                }
                list.add(new NormalTextViewGroupParams(" ", 14));
                for (int i = 0; i < 7; i++) {
                    list.add(new NormalTextViewGroupParams("讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 ",
                            14, blueColor));
                }
                list.add(new NormalTextViewGroupParams(" ", 14));
                for (int i = 0; i < 7; i++) {
                    list.add(new NormalTextViewGroupParams("非 非 非 非 非 非 非 非 非 非 非 非 非 非 非 非 非 ",
                            14, orangeColor));
                }
                list.add(new NormalTextViewGroupParams(" ", 14));
                for (int i = 0; i < 7; i++) {
                    list.add(new NormalTextViewGroupParams("凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 ",
                            14, blueColor));
                }
                list.add(new NormalTextViewGroupParams(" ", 14));
                for (int i = 0; i < 7; i++) {
                    list.add(new NormalTextViewGroupParams("哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 ",
                            14, orangeColor));
                }
                NormalTextViewGroup normalTextViewGroup4 =
                        new NormalTextViewGroup(mHeartShapeView.getBoundaryX(),
                                mHeartShapeView.getBoundaryY(),
                                mHeartShapeView.getBoundaryX(),
                                mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() - DpiUtil.spToPix(14),
                                32, list, NormalTextView.TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT,
                                5, false);
                mMoveables.add(normalTextViewGroup4);
            }
        });

        /**
         * fengexian GunvView
         * */

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (65.4 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.startTwinkle(25);
                mHeartShapeView.startMove(portraitView.getCenterX(), portraitView.getCenterY(), 5,
                        heartShapeView1 -> {
                            heartShapeView1.setDismiss(true);
                            heartShapeView1.setHeartMode(HeartShapeView.HEART_MODE_NORMAL);
                        });
                buttonVisibilityCallBack.hideButton();
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (65.8 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.setPortraitBmp(PortraitView.BMP_LIUXING);
                portraitView.setPositon(DiaSiApplication.getCanvasWidth() / 2 - portraitView.getWidth() / 2,
                        DpiUtil.dipToPix(10));
                mHeartShapeView.setDismiss(false);
                mHeartShapeView.setHeartCenter();
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (66.44f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(), DiaSiApplication
                        .getCanvasHeight() / 2, mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() -
                        DpiUtil.dipToPix(20), mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(20), -30));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (67.12f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DpiUtil.dipToPix(100), DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(20),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(30), 135));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (67.44f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(0, 0,
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(50),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(40), -160));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (67.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(), DiaSiApplication
                        .getCanvasHeight(),
                        mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() + DpiUtil.dipToPix(50),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(50), -10));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (67.96f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(30),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (68.4f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR(),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(30),
                        mHeartShapeView.getBoundaryB() - DpiUtil.dipToPix(40), 45));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (69.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DpiUtil.dipToPix(100),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(20), 135));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (69.68f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(30), 0,
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(100),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(30), -80));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (69.84f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(20),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(30), 40));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (69.96f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX(),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(50),
                        mHeartShapeView.getBoundaryX(),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (70.52f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DpiUtil.dipToPix(150),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryB(), -50));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (71f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DpiUtil.dipToPix(150),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(50),
                        0, -105));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (71.52f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DpiUtil.dipToPix(200),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(10),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(20), 135));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (71.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryB(),
                        mHeartShapeView.getBoundaryR() + DpiUtil.dipToPix(10),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(50), -15));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (71.96f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (72.44f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(
                        mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() / 2 - DpiUtil.dipToPix(25),
                        0,
                        mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() / 2 - DpiUtil.dipToPix(25),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(40), -130));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (73.04f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(130),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(130),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (73.44f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX(),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryX(),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (73.6f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(45), 0));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (73.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(0,
                        DpiUtil.dipToPix(200),
                        mHeartShapeView.getBoundaryX() - DpiUtil.dipToPix(60),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(50), 180));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (74.520f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(140),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryX(),
                        mHeartShapeView.getBoundaryY(), -150));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (75f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(150),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(5), 45));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (75.52f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DpiUtil.dipToPix(100),
                        0,
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(70),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(50), -160));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (75.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() - DpiUtil.dipToPix(100),
                        0,
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(120),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(5), -80));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (75.8f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(110),
                        mHeartShapeView.getBoundaryB(), 80));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (75.96f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (76.52f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(110),
                        mHeartShapeView.getBoundaryB(), 80));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (77.04f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(20),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (76.08f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() - DpiUtil.dipToPix(100),
                        0,
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(10),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(35), 75));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (77.52f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2,
                        0,
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(100),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(150), -110));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (77.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(0,
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(100),
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(10),
                        mHeartShapeView.getBoundaryB() - DpiUtil.dipToPix(15), -125));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (77.84f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryY(),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(30), -30));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (78.52f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DpiUtil.dipToPix(300),
                        0,
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryY(), -135));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (79.08f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2,
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(20), 50));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (79.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(100),
                        0,
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryY(), -110));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (79.92f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() - DpiUtil.dipToPix(85),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() - DpiUtil.dipToPix(85),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (80.52f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        0,
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(75),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(10), -70));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (81.04f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(40),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(40),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (81.04f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(50), 90));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (81.52f * 1000) - GunView.getTimeBeforeShoot();
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX(),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryX(),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (81.6f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(100),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(120), -20));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (81.64f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (81.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(40),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(40),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(50), 30));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (82.44f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(100),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(40), -25));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (83.16f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(0,
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(100),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(10),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(40), 150));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (83.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DpiUtil.dipToPix(50),
                        0,
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(100),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(75), 170));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (83.68f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(50),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(30),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(40), -10));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (83.92f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX(),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryX(),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (84.4f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(100),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(110),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(130), 45));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (85.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(0,
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(50),
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(20), 135));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (85.68f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR(),
                        0,
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(140),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10), -85));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (85.84f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2,
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryB() - DpiUtil.dipToPix(20), 45));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (86.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DpiUtil.dipToPix(130),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(75),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(30), -45));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (87.04f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(50),
                        0,
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(10),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(30), -100));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (87.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX(),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(15), 120));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (87.68f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryB(),
                        mHeartShapeView.getBoundaryR(),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(30), -20));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (87.92f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (88.4f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(40),
                        0,
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(30),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(20), -135));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (89.12f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(130),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(130),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (89.44f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX(),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryX(),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (89.56f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(50), 0));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (89.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(0,
                        0,
                        mHeartShapeView.getBoundaryX() - DpiUtil.dipToPix(50),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(20), -160));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (89.8f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() - DpiUtil.dipToPix(150),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR(),
                        mHeartShapeView.getBoundaryB() - DpiUtil.dipToPix(20), 20));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (90.4f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(45),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(45),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (90.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DpiUtil.dipToPix(150),
                        0,
                        mHeartShapeView.getBoundaryX(),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(5), -140));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (90.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(20),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(40),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(5), 50));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (91.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(0,
                        0,
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(100),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(50), -170));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (91.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() - DpiUtil.dipToPix(100),
                        0,
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(150),
                        mHeartShapeView.getBoundaryY(), -75));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (92f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (92.44f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(50),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(75), 10));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (93.08f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() - DpiUtil.dipToPix(100),
                        0,
                        DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(15), -80));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (93.12f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(30),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (93.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() - DpiUtil.dipToPix(100),
                        0,
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(75),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(75), -105));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (93.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(0,
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(100),
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(10),
                        mHeartShapeView.getBoundaryB() - DpiUtil.dipToPix(20), 135));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (93.88f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DiaSiApplication.getCanvasHeight() / 2,
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(20), -30));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (93.96f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX(),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryX(),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (94.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(40),
                        0,
                        mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(40),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10), -135));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (95.08f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(10),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(10),
                        mHeartShapeView.getBoundaryB(), 65));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (95.72f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(50),
                        0,
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(20),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(5), -110));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (96f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(85),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (96.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(70),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(5), -65));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (97.08f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(40),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(40),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (97.12f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(10),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(10),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(40), 45));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (97.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryX(),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryX(),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (97.48f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(60),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(120),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(60), -45));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (97.6f * 1000) - GunView.getTimeBeforeShoot();
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(0,
                        DpiUtil.dipToPix(150),
                        mHeartShapeView.getBoundaryX(),
                        mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(50), 180));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (97.76f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(75),
                        DiaSiApplication.getCanvasHeight(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(75),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(75), 30));
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (98f * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(45),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(45),
                        mHeartShapeView.getBoundaryY(), 0, false));
            }
        });
        /**
         * GunView结束
         * */

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (81.48 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                PauseViewText text1 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryY(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        26, 94, 0, "吔屎啦吔吔吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN);
                text1.setTextSize(14);
                PauseViewText text2 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryB(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        26, 94, 0, "吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP);
                text2.setTextSize(14);
                mMoveables.add(text1);
                mMoveables.add(text2);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (83.48 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                PauseViewText text1 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryY(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        26, 94, 0, "吔屎啦吔吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN);
                text1.setTextSize(14);
                PauseViewText text2 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryB(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        26, 94, 0, "吔吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP);
                text2.setTextSize(14);
                mMoveables.add(text1);
                mMoveables.add(text2);
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (85.48 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                PauseViewText text1 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryY(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        26, 94, 0, "吔屎啦吔吔吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN);
                text1.setTextSize(14);
                PauseViewText text2 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryB(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        26, 94, 0, "吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP);
                text2.setTextSize(14);
                mMoveables.add(text1);
                mMoveables.add(text2);
            }
        });
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (87.48 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                PauseViewText text1 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryY(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        26, 94, 0, "吔屎", NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN);
                text1.setTextSize(14);
                PauseViewText text2 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryB(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        26, 94, 0, "啦吔吔吔吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP);
                text2.setTextSize(14);
                mMoveables.add(text1);
                mMoveables.add(text2);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (89.48 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                PauseViewText text1 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryY(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        26, 94, 0, "吔屎啦吔吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN);
                text1.setTextSize(14);
                PauseViewText text2 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryB(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        26, 94, 0, "吔吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP);
                text2.setTextSize(14);
                mMoveables.add(text1);
                mMoveables.add(text2);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (91.48 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                PauseViewText text1 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryY(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        26, 94, 0, "吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN);
                text1.setTextSize(14);
                PauseViewText text2 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryB(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        26, 94, 0, "屎啦吔吔吔吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP);
                text2.setTextSize(14);
                mMoveables.add(text1);
                mMoveables.add(text2);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (93.68 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                PauseViewText text1 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryY(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        26, 94, 0, "吔屎梁非", NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN);
                text1.setTextSize(14);
                PauseViewText text2 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryB(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        26, 94, 0, "凡吔吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP);
                text2.setTextSize(14);
                PauseViewText text3 = new PauseViewText(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14), mHeartShapeView.getBoundaryY(),
                        26, 94, 0, "吔屎梁非", NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN);
                text3.setTextSize(14);
                PauseViewText text4 = new PauseViewText(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14), mHeartShapeView.getBoundaryB(),
                        26, 94, 0, "凡吔吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP);
                text4.setTextSize(14);
                mMoveables.add(text1);
                mMoveables.add(text2);
                mMoveables.add(text3);
                mMoveables.add(text4);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (95.68 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                PauseViewText text1 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryY(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        26, 94, 0, "吔屎梁非凡吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN);
                text1.setTextSize(14);
                PauseViewText text2 = new PauseViewText(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14),
                        mHeartShapeView.getBoundaryB(), mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        26, 94, 0, "吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP);
                text2.setTextSize(14);
                PauseViewText text3 = new PauseViewText(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14), mHeartShapeView.getBoundaryY(),
                        26, 94, 0, "吔屎梁非凡吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN);
                text3.setTextSize(14);
                PauseViewText text4 = new PauseViewText(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(14), mHeartShapeView.getBoundaryB(),
                        26, 94, 0, "吔", NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP);
                text4.setTextSize(14);
                mMoveables.add(text1);
                mMoveables.add(text2);
                mMoveables.add(text3);
                mMoveables.add(text4);
            }
        });

        /**
         * fengexian gunGroup
         * */

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (97.96 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mHeartShapeView.changeBoundaryWithAmination(DiaSiApplication.getCanvasWidth() / 2 -
                                (DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150
                                        + 60)) / 2, DpiUtil.dipToPix(150),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150 + 60),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150 + 60), 10);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (98.36 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, 0, 1125));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (98.36 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, 0, 1125));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (106.56 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, 180, 225));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (107.36 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, 100, 145));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (108.16 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, -10, 35));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (109 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, 225, 270));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (109.8 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, 100, 145));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (110.6 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, 30, 75));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (111.16 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, -50, -5));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (112 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, 80, 125));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (112.8 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, 15, 60));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (113.6 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth() / 2,
                        mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() / 2, -50, -5));
            }
        });

        /**
         * fengexian SpineView
         * */

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (114.12 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY); // 这句话有重置重力状态的作用，因此转换重力方向，要重新调用
                mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_RIGHT);
                mHeartShapeView.changeBoundaryWithAmination(DiaSiApplication.getCanvasWidth() / 2 -
                                (DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150
                                        + 60)) / 2, DpiUtil.dipToPix(150),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150 + 60),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150 + 60), 10);
                mMoveables.add(new ArrowView(0, mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_RIGHT));
                buttonVisibilityCallBack.showButton();
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (114.84 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new SpineView(mHeartShapeView.getBoundaryR(), mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryH(), SpineView.SPINE_DIRECTION_LEFT));
            }
        });
        //
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (115.12 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_TOP);
                mMoveables.add(new ArrowView(mHeartShapeView.getBoundaryX() - DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasHeight(), ArrowView.DIRECTION_UP));
            }
        });
        //
        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (115.84 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryW(), SpineView.SPINE_DIRECTION_DOWN));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (115.96 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_TOP);
                mMoveables.add(new ArrowView(mHeartShapeView.getBoundaryX() - DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasHeight(),
                        DiaSiApplication.getCanvasHeight(), ArrowView.DIRECTION_UP));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (116.56 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryW(), SpineView.SPINE_DIRECTION_DOWN));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (116.72 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_LEFT);
                mMoveables.add(new ArrowView(DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryB() - DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_LEFT));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (117.44 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryH(), SpineView.SPINE_DIRECTION_RIGHT));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (117.6 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_BOTTOM);
                mMoveables.add(new ArrowView(mHeartShapeView.getBoundaryR() + DpiUtil.dipToPix(30),
                        0,
                        DiaSiApplication.getCanvasHeight(), ArrowView.DIRECTION_DOWN));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (118.24 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                        mHeartShapeView.getBoundaryW(), SpineView.SPINE_DIRECTION_UP));

            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (118.6 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_LEFT);
                mMoveables.add(new ArrowView(DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_LEFT));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (119.08 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryH(), SpineView.SPINE_DIRECTION_RIGHT));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (119.4 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_RIGHT);
                mMoveables.add(new ArrowView(0,
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_RIGHT));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (119.88 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new SpineView(mHeartShapeView.getBoundaryR(), mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryH(), SpineView.SPINE_DIRECTION_LEFT));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (120.2 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_LEFT);
                mMoveables.add(new ArrowView(DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_LEFT));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (120.72 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryH(), SpineView.SPINE_DIRECTION_RIGHT));
            }
        });

        /**
         *fengexian SinLongSpineGroupView
         * */

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (120.92 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new ArrowView(0, mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(30),
                        DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_RIGHT));
                mHeartShapeView.changeBoundaryWithAmination(DpiUtil.dipToPix(100), mHeartShapeView.getBoundaryY() +
                                DpiUtil.dipToPix(10), DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryH() - DpiUtil.dipToPix(20), 20);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (121.88 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
                        -portraitView.getWidth(), portraitView.getCurrentY(), 44);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (121.92 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new SinLongSpineGroupView(DiaSiApplication
                        .getCanvasWidth(), mHeartShapeView.getBoundaryY(), -mHeartShapeView.getBoundaryW() / 50, 30, 3,
                        mHeartShapeView.getBoundaryH() * 0.5f,
                        mHeartShapeView.getBoundaryH() * 0.25f, mHeartShapeView.getBoundaryH() * 0.25f));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (122.96 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.move(DiaSiApplication.getCanvasWidth() + portraitView.getWidth(),
                        portraitView.getCurrentY(), -portraitView.getWidth(), portraitView.getCurrentY(), 94);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (124.4 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new EasyLongSpineGroupView(DiaSiApplication.getCanvasWidth(),
                        mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(20), mHeartShapeView.getBoundaryY() +
                        mHeartShapeView.getBoundaryH() + DpiUtil.dipToPix(20), -mHeartShapeView.getBoundaryW() / 50, 4,
                        3, 5, 40,
                        mHeartShapeView.getBoundaryH() * 0.6f));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (125.04 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.move(DiaSiApplication.getCanvasWidth() + portraitView.getWidth(),
                        portraitView.getCurrentY(), -portraitView.getWidth(), portraitView.getCurrentY(), 94);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (127.04 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                portraitView.move(DiaSiApplication.getCanvasWidth() + portraitView.getWidth(),
                        portraitView.getCurrentY(),
                        DiaSiApplication.getCanvasWidth() / 2 - portraitView.getWidth() / 2, portraitView.getCurrentY(),
                        94);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (128.84 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mHeartShapeView.changeBoundaryWithAmination(DiaSiApplication.getCanvasWidth() / 2 -
                                (DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150
                                        + 60)) / 2, DpiUtil.dipToPix(150),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150 + 60),
                        DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150 + 60), 10);
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (129.92 * 1000 - GunView.getTimeBeforeShoot());
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                mMoveables.add(new GunView(mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(30),
                        mHeartShapeView.getBoundaryY(),
                        mHeartShapeView.getBoundaryR() - DpiUtil.dipToPix(30),
                        mHeartShapeView.getBoundaryB(), 180, false));
            }
        });

        eventsList.add(new TimerEvent<GameViewHolder<BaseShowableView>>() {
            @Override
            public long getIntervalTime() {
                return (long) (135 * 1000);
            }

            @Override
            public void addTimerEvent(GameViewHolder viewHolder) {
                HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                PortraitView portraitView = viewHolder.portraitView;
                List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                ifFinish = true;
            }
        });
    }

    private List<String> string2List(String string) {
        String[] strings = string.split("");
        List<String> list = new LinkedList<>(java.util.Arrays.asList(strings));
        list.remove(0);
        return list;
    }

    public void setButtonVisibilityCallBack(ButtonVisibilityCallBack buttonVisibilityCallBack) {
        this.buttonVisibilityCallBack = buttonVisibilityCallBack;
    }

    public boolean getIfFinish() {
        return ifFinish;
    }

}
