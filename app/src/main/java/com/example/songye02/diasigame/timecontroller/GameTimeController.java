package com.example.songye02.diasigame.timecontroller;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.MemoryHandler;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.R;
import com.example.songye02.diasigame.callback.ButtonVisibilityCallBack;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.ArrowView;
import com.example.songye02.diasigame.model.shapeview.EasyLongSpineGroupView;
import com.example.songye02.diasigame.model.shapeview.GunView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.LongSpineGroupView;
import com.example.songye02.diasigame.model.shapeview.LongSpineView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.model.shapeview.SinLongSpineGroupView;
import com.example.songye02.diasigame.model.shapeview.SpineView;
import com.example.songye02.diasigame.model.textview.CollisionNormalTextView;
import com.example.songye02.diasigame.model.textview.FollowInnerTextView;
import com.example.songye02.diasigame.model.textview.FollowInnerTextViewGroup;
import com.example.songye02.diasigame.model.textview.FollowTextView;
import com.example.songye02.diasigame.model.textview.FollowTextViewGroup;
import com.example.songye02.diasigame.model.textview.NormalTextView;
import com.example.songye02.diasigame.model.textview.NormalTextViewGroup;
import com.example.songye02.diasigame.model.textview.NormalTextViewGroup.NormalTextViewGroupParams;
import com.example.songye02.diasigame.model.textview.ParaboleTextGroup;
import com.example.songye02.diasigame.model.textview.PauseSpeedUpTextViewGroup;
import com.example.songye02.diasigame.model.textview.PauseViewTextGroup;
import com.example.songye02.diasigame.model.textview.PauseViewTextGroup.PauseViewTextParams;
import com.example.songye02.diasigame.model.textview.RandomTextViewGroup;
import com.example.songye02.diasigame.model.textview.RotateTextView;
import com.example.songye02.diasigame.model.textview.TimeDialogueParams;
import com.example.songye02.diasigame.model.textview.TimeDialogueTextGroup;
import com.example.songye02.diasigame.model.textview.TriggerDialogueGroup;
import com.example.songye02.diasigame.test.MySurfaceView;
import com.example.songye02.diasigame.utils.DpiUtil;

/**
 * Created by songye02 on 2017/5/12.
 */

public class GameTimeController extends TimeController {

    private ButtonVisibilityCallBack buttonVisibilityCallBack;  // 按键决定重力方向

    @Override
    void initTimerEvents() {
        {
            timerEvents = new ArrayDeque();
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 0;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    TimeDialogueParams[] paramses = new TimeDialogueParams[3];
            //                    paramses[0] = new TimeDialogueParams("应该在", 0, 540);
            //                    paramses[1] = new TimeDialogueParams("地狱里", 640, 930);
            //                    paramses[2] = new TimeDialogueParams("吔屎", 1100, 1400);
            //                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
            //                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
            //                            portraitView.getCurrentY() + DpiUtil.dipToPix(20),
            //                            System.currentTimeMillis(), 1750);
            //                    group.setPlaySound(false);
            //                    mMoveables.add(group);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (1.902 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new ParaboleTextGroup(DiaSiApplication.getCanvasWidth() / 2,
            //                            portraitView.getCurrentY() + portraitView.getHeight() * 0.2f, mHeartShapeView
            //                            .getBoundaryW() / 2, portraitView.getHeight() * 0.4f, 14300));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (17.520 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<String> list = string2List("吔屎吔屎梁非凡");
            //                    FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
            //                            mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() - DpiUtil
            //                                    .dipToPix(25), mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10),
            //                            FollowInnerTextViewGroup.TEXT_ORIENTATION_VERTICAL, list, mHeartShapeView,
            // 18, 12, 15, 15);
            //                    mMoveables.add(followInnerTextViewGroup);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (17.84 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.setPortraitBmp(PortraitView.BMP_LIUXING);
            //                    TimeDialogueParams[] paramses = new TimeDialogueParams[4];
            //                    paramses[0] = new TimeDialogueParams("吔屎吔屎梁非凡", 16, 1200);
            //                    paramses[1] = new TimeDialogueParams("你听不到我~我在讲", 2000, 3600);
            //                    paramses[2] = new TimeDialogueParams("讲到你哥听到为止", 4000, 5240);
            //                    paramses[3] = new TimeDialogueParams("吔屎啦梁非凡~", 6000, 6600);
            //                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
            //                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
            //                            portraitView.getCurrentY() + DpiUtil.dipToPix(20),
            //                            System.currentTimeMillis(), 7720);
            //                    group.setPlaySound(false);
            //                    mMoveables.add(group);
            //
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (19.520 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<String> list = string2List("你听不到我");
            //                    FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
            //                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10),
            //                            FollowInnerTextViewGroup.TEXT_ORIENTATION_VERTICAL,
            //                            list, mHeartShapeView, 16, 10, 15, 15);
            //                    mMoveables.add(followInnerTextViewGroup);
            //
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (21 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<String> list = string2List("我在讲");
            //                    FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
            //                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10),
            //                            FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
            //                            list, mHeartShapeView, 14, 5, 15, 15);
            //                    mMoveables.add(followInnerTextViewGroup);
            //
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (21.6 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<String> list = string2List("讲到你哥听到为止");
            //                    FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
            //                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
            //                            mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() - DpiUtil
            //                                    .dipToPix(25),
            //                            FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
            //                            list, mHeartShapeView, 16, 11, 15, 15);
            //                    mMoveables.add(followInnerTextViewGroup);
            //
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (23.720 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<String> list = string2List("吔屎啦梁非凡");
            //                    FollowInnerTextViewGroup followInnerTextViewGroup1 = new
            //                            FollowInnerTextViewGroup(
            //                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(25),
            //                            mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH()
            //                                    - DpiUtil
            //                                    .dipToPix(25),
            //                            FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
            //                            list, mHeartShapeView, 10, 10, 15, 15);
            //
            //                    FollowInnerTextViewGroup followInnerTextViewGroup2 = new
            //                            FollowInnerTextViewGroup(
            //                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(25),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(5),
            //                            FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
            //                            list, mHeartShapeView, 10, 10, 15, 15);
            //                    mMoveables.add(followInnerTextViewGroup1);
            //                    mMoveables.add(followInnerTextViewGroup2);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (25.6 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.startTwinkle(25);
            //                    mHeartShapeView.startMove(portraitView.getCenterX(), portraitView
            //                                    .getCenterY(), 5,
            //                            heartShapeView1 -> {
            //                                heartShapeView1.setDismiss(true);
            //                                heartShapeView1.setHeartMode(HeartShapeView
            //                                        .HEART_MODE_NORMAL);
            //                            });
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (26.00 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.setPortraitBmp(PortraitView.BMP_FEIFANGE);
            //                    mHeartShapeView.setDismiss(false);
            //                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
            //                    mHeartShapeView.changeBoundaryWithAmination(mHeartShapeView
            //                                    .getBoundaryX() -
            //                                    mHeartShapeView
            //                                            .getBoundaryW() / 2, mHeartShapeView.getBoundaryY(),
            //                            mHeartShapeView.getBoundaryW
            //                                    () * 2,
            //                            mHeartShapeView.getBoundaryH(), 10);
            //                    buttonVisibilityCallBack.showButton();
            //
            //                    TimeDialogueParams[] paramses = new TimeDialogueParams[4];
            //                    paramses[0] = new TimeDialogueParams("刘醒我是你上司", 0, 1320);
            //                    paramses[1] = new TimeDialogueParams("警署有规定不啵上司嘴", 2040, 3640);
            //                    paramses[2] = new TimeDialogueParams("我要艹你 我要艹你", 3960, 5240);
            //                    paramses[3] = new TimeDialogueParams("大声讲我艹你", 5960, 6760);
            //                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
            //                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
            //                            portraitView.getCurrentY() + DpiUtil.dipToPix(20),
            //                            System.currentTimeMillis(), 8360);
            //                    group.setPlaySound(false);
            //                    mMoveables.add(group);
            //
            //                    List<NormalTextViewGroupParams> list = new ArrayList<>();
            //                    list.add(new NormalTextViewGroupParams("刘醒我是你", 12));
            //                    list.add(new NormalTextViewGroupParams("刘醒我是你", 12));
            //                    NormalTextViewGroup normalTextViewGroup1 = new NormalTextViewGroup(mHeartShapeView
            // .getBoundaryX()
            //                            + mHeartShapeView.getBoundaryW() - DpiUtil.spToPix(12), mHeartShapeView
            // .getBoundaryY(),
            //                            mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
            //                            96, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN, 50, true);
            //                    mMoveables.add(normalTextViewGroup1);
            //
            //                    List<NormalTextViewGroupParams> list2 = new ArrayList<>();
            //                    list2.add(new NormalTextViewGroupParams("刘醒我是你", 12));
            //                    list2.add(new NormalTextViewGroupParams("刘醒我是你", 12));
            //                    NormalTextViewGroup normalTextViewGroup2 =
            //                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX(),
            //                                    mHeartShapeView.getBoundaryY(),
            //                                    mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() -
            // DpiUtil.spToPix(12),
            //                                    mHeartShapeView.getBoundaryY(), 96, list2, NormalTextView
            //                                    .TEXT_ORIENTATION_VERTICAL_UPTODOWN, 50, true);
            //                    mMoveables.add(normalTextViewGroup2);
            //
            //                    List<NormalTextViewGroupParams> list3 = new ArrayList<>();
            //                    // 从下到上，因此要反过来
            //                    list3.add(new NormalTextViewGroupParams("司上", 12));
            //                    list3.add(new NormalTextViewGroupParams("司上", 12));
            //                    list3.add(new NormalTextViewGroupParams("定规有署警", 12));
            //                    list3.add(new NormalTextViewGroupParams("嘴司上顶不", 12));
            //                    list3.add(new NormalTextViewGroupParams("你艹要我", 12));
            //                    list3.add(new NormalTextViewGroupParams("你艹要我", 12));
            //                    list3.add(new NormalTextViewGroupParams("你艹我讲声大", 12));
            //                    NormalTextViewGroup normalTextViewGroup3 =
            //                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX() +
            //                                    mHeartShapeView.getBoundaryW() - DpiUtil.spToPix(12),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
            //                                    mHeartShapeView.getBoundaryX(),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
            //                                    96, list3, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 50,
            // true);
            //                    mMoveables.add(normalTextViewGroup3);
            //
            //                    List<NormalTextViewGroupParams> list4 = new ArrayList<>();
            //                    // 从下到上，因此要反过来
            //                    list4.add(new NormalTextViewGroupParams("司上", 12));
            //                    list4.add(new NormalTextViewGroupParams("司上", 12));
            //                    list4.add(new NormalTextViewGroupParams("定规有署警", 12));
            //                    list4.add(new NormalTextViewGroupParams("嘴司上顶不", 12));
            //                    list4.add(new NormalTextViewGroupParams("你艹要我", 12));
            //                    list4.add(new NormalTextViewGroupParams("你艹要我", 12));
            //                    list4.add(new NormalTextViewGroupParams("你艹我讲声大", 12));
            //                    NormalTextViewGroup normalTextViewGroup4 =
            //                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX(),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
            //                                    mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() -
            // DpiUtil.spToPix(12),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
            //                                    96, list4, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 50,
            // true);
            //                    mMoveables.add(normalTextViewGroup4);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (33.880 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.startTwinkle(25);
            //                    mHeartShapeView.startMove(portraitView.getCenterX(), portraitView.getCenterY(), 5,
            //                            heartShapeView1 -> {
            //                                heartShapeView1.setDismiss(true);
            //                                heartShapeView1.setHeartMode(HeartShapeView.HEART_MODE_NORMAL);
            //                            });
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (34.6 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.setPortraitBmp(PortraitView.BMP_LIUXING);
            //                    portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
            //                            mHeartShapeView.getBoundaryX(), portraitView.getCurrentY(), 10);
            //                    mHeartShapeView.setDismiss(false);
            //                    mHeartShapeView.changeBoundaryWithAmination(DiaSiApplication.getCanvasWidth() / 2 -
            //                                    (DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150
            //                                            + 60)) / 2, DpiUtil.dipToPix(150),
            //                            DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150 + 60),
            //                            DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(150 + 60), 10);
            //                    mHeartShapeView.setHeartCenter();
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (34.800 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    TimeDialogueParams[] paramses = new TimeDialogueParams[5];
            //                    paramses[0] = new TimeDialogueParams("报告非凡哥警署有规定的", 160, 1480);
            //                    paramses[1] = new TimeDialogueParams("警员有权请事假的", 2840, 4000);
            //                    paramses[2] = new TimeDialogueParams("我规定讲你再啵啵你嘴", 4160, 5400);
            //                    paramses[3] = new TimeDialogueParams("都是六个字", 7200, 8160);
            //                    paramses[4] = new TimeDialogueParams("吔屎啦梁非凡", 8840, 10920);
            //                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
            //                            mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() +
            // portraitView.getWidth() + DpiUtil.dipToPix(20),
            //                            portraitView.getCurrentY() + DpiUtil.dipToPix(20),
            //                            System.currentTimeMillis(), 11960);
            //                    group.setPlaySound(false);
            //                    mMoveables.add(group);
            //                    portraitView.move(mHeartShapeView.getBoundaryX(), portraitView.getCurrentY(),
            //                            mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW(),
            // portraitView.getCurrentY(), 56);
            //
            //                    PauseSpeedUpTextViewGroup group1 = new PauseSpeedUpTextViewGroup(mHeartShapeView
            //                            .getBoundaryX(), portraitView.getCenterY() + DpiUtil.dipToPix(30), 20,
            //                            string2List("警署就有规定的"),
            //                            PauseSpeedUpTextViewGroup.APPEAR_DIRECTION_RIGHT, PauseSpeedUpTextViewGroup
            //                            .PAUSE_INCREMENT_DIRECTION_RIGHT, 8, 70, 9, 100000);
            //                    PauseSpeedUpTextViewGroup group2 = new PauseSpeedUpTextViewGroup(mHeartShapeView
            //                            .getBoundaryX(), portraitView.getCenterY(), 20, string2List("报告梁非凡哥哥"),
            //                            PauseSpeedUpTextViewGroup.APPEAR_DIRECTION_RIGHT, PauseSpeedUpTextViewGroup
            //                            .PAUSE_INCREMENT_DIRECTION_RIGHT, 8, 70, 9, 100000);
            //                    mMoveables.add(group1);
            //                    mMoveables.add(group2);
            //                }
            //            });

            /**
             *gunView
             * */

            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    //                    return (long) 34.5 * 1000;
            //                    return 8 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
            // mHeartShapeView
            //                            .getBoundaryX(), portraitView.getCurrentY(), 20);
            //                    FollowTextViewGroup group1 = new FollowTextViewGroup(mHeartShapeView.getBoundaryX(),
            //                            portraitView.getCenterY() + DpiUtil.dipToPix(30), 20, string2List("请 事 假 的"),
            //                            FollowTextViewGroup.APPEAR_DIRECTION_LEFT, FollowTextViewGroup
            //                            .PAUSE_INCREMENT_DIRECTION_RIGHT, 3, 50,
            //                            10, 20, mHeartShapeView);
            //                    FollowTextViewGroup group2 = new FollowTextViewGroup(mHeartShapeView.getBoundaryX(),
            //                            portraitView.getCenterY(), 20, string2List("警 有 员 权"), FollowTextViewGroup
            //                            .APPEAR_DIRECTION_LEFT, FollowTextViewGroup.PAUSE_INCREMENT_DIRECTION_LEFT, 3,
            //                            100, 25,
            //                            20, mHeartShapeView);
            //                    mMoveables.add(group1);
            //                    mMoveables.add(group2);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    //                    return (long) 34.5 * 1000;
            //                    return 14 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
            //                            mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW(),
            // portraitView.getCurrentY(),
            //                            20);
            //                    FollowTextViewGroup group1 = new FollowTextViewGroup(mHeartShapeView.getBoundaryX(),
            //                            portraitView.getCenterY() + DpiUtil.dipToPix(30), 20, string2List("再啵啵你嘴"),
            //                            FollowTextViewGroup.APPEAR_DIRECTION_RIGHT, FollowTextViewGroup
            //                            .PAUSE_INCREMENT_DIRECTION_RIGHT, 3, 50, 10, 20, mHeartShapeView);
            //                    FollowTextViewGroup group2 = new FollowTextViewGroup(mHeartShapeView.getBoundaryX(),
            //                            portraitView.getCenterY(), 20, string2List("我规定讲你"), FollowTextViewGroup
            //                            .APPEAR_DIRECTION_RIGHT, FollowTextViewGroup
            //                            .PAUSE_INCREMENT_DIRECTION_LEFT, 3, 100, 25, 20, mHeartShapeView);
            //                    mMoveables.add(group1);
            //                    mMoveables.add(group2);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    //                    return (long) 34.5 * 1000;
            //                    return 1 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
            //                            mHeartShapeView.getBoundaryX(), portraitView.getCurrentY(), 20);
            //                    List<PauseViewTextGroup.PauseViewTextParams> list = new LinkedList<>();
            //                    list.add(new PauseViewTextParams(mHeartShapeView.getBoundaryX() + mHeartShapeView
            //                            .getBoundaryW() - DpiUtil.dipToPix(20),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(50), "都"));
            //                    list.add(new PauseViewTextParams(mHeartShapeView.getBoundaryX() + mHeartShapeView
            //                            .getBoundaryW() - DpiUtil.dipToPix(30),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(70), "是"));
            //                    list.add(new PauseViewTextParams(mHeartShapeView.getBoundaryX() + mHeartShapeView
            //                            .getBoundaryW() - DpiUtil.dipToPix(50),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(90), "六"));
            //                    list.add(new PauseViewTextParams(mHeartShapeView.getBoundaryX() + mHeartShapeView
            //                            .getBoundaryW() - DpiUtil.dipToPix(70),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(110), "个"));
            //                    list.add(new PauseViewTextParams(mHeartShapeView.getBoundaryX() + mHeartShapeView
            //                            .getBoundaryW() - DpiUtil.dipToPix(80),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(130), "字"));
            //                    PauseViewTextGroup group = new PauseViewTextGroup(mHeartShapeView.getBoundaryX(),
            //                            portraitView.getCenterY(), 20, 50, list, PauseViewTextGroup
            //                            .APPEAR_DIRECTION_LEFT,
            //                            PauseViewTextGroup.PAUSE_INCREMENT_DIRECTION_LEFT, 3, 50, 50, 50);
            //                    List<String> textList = string2List("吔屎啦梁非凡");
            //                    group.setTextViewDeadCallback((view, index, totalNum) -> {
            //                        mMoveables.add(new RotateTextView(view.getCurrentX(), view.getCurrentY(),
            //                                textList.get(index)));
            //                        if (index == totalNum - 1) {
            //                            new Timer().schedule(new TimerTask() {
            //                                @Override
            //                                public void run() {
            //                                    mMoveables.add(new RotateTextView(mHeartShapeView.getBoundaryX()
            //                                            + mHeartShapeView.getBoundaryW() / 2, mHeartShapeView
            //                                            .getBoundaryY() + mHeartShapeView
            //                                            .getBoundaryH() / 2, textList.get(index + 1)));
            //                                }
            //                            }, 20 * 50);
            //                            // 一帧20ms，50帧
            //                        }
            //                    });
            //                    mMoveables.add(group);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) 34.5 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
            //                            mHeartShapeView.getBoundaryX(), portraitView.getCurrentY(), 20);
            //
            //                    mMoveables.add(new RandomTextViewGroup(DiaSiApplication.getCanvasWidth(),
            //                            mHeartShapeView.getBoundaryY(), DiaSiApplication.getCanvasWidth(),
            //                            mHeartShapeView.getBoundaryH(), 20, 200, "吔"));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) 34.5 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
            //                            mHeartShapeView
            //                                    .getBoundaryX(), portraitView.getCurrentY(), 20);
            //
            //                    mMoveables.add(new RandomTextViewGroup(DiaSiApplication.getCanvasWidth(),
            //                            mHeartShapeView.getBoundaryY(), DiaSiApplication.getCanvasWidth(),
            //                            mHeartShapeView
            //                                    .getBoundaryH(), 20, 200, "吔"));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) 1 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.startTwinkle(25);
            //                    mHeartShapeView.startMove(portraitView.getCenterX(), portraitView.getCenterY(), 5,
            //                            heartShapeView1 -> {
            //                                heartShapeView1.setDismiss(true);
            //                                heartShapeView1.setHeartMode(HeartShapeView.HEART_MODE_NORMAL);
            //                            });
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) 2 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.setPortraitBmp(PortraitView.BMP_LIUXING);
            //                    mHeartShapeView.setDismiss(false);
            //                    mHeartShapeView.setHeartCenter();
            //                    mHeartShapeView.changeBoundaryWithAmination(mHeartShapeView.getBoundaryX() -
            //                                    mHeartShapeView.getBoundaryW() / 2, mHeartShapeView.getBoundaryY(),
            //                            mHeartShapeView.getBoundaryW() * 2, mHeartShapeView.getBoundaryH(), 10);
            //                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
            //                    mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_BOTTOM);
            //                    buttonVisibilityCallBack.showButton();
            //                    TimeDialogueParams[] paramses = new TimeDialogueParams[5];
            //                    paramses[0] = new TimeDialogueParams("你再大声讲对不起非凡哥", 100, 1100);
            //                    paramses[1] = new TimeDialogueParams("我听不到你在讲", 1200, 2200);
            //                    paramses[2] = new TimeDialogueParams("再讲讲到我听到位置", 3300, 4300);
            //                    paramses[3] = new TimeDialogueParams("警署有规定", 4400, 5400);
            //                    paramses[4] = new TimeDialogueParams("刘醒讲非凡哥", 4400, 5400);
            //                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
            //                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
            //                            portraitView.getCurrentY() + DpiUtil.dipToPix(20),
            //                            System.currentTimeMillis(), 6000);
            //                    group.setPlaySound(false);
            //                    mMoveables.add(group);
            //                    portraitView.move(portraitView.getCurrentX(), portraitView.getCurrentY(),
            //                            DiaSiApplication.getCanvasWidth() / 2 - DiaSiApplication.getPortraitWidth()
            // / 2,
            //                            portraitView.getCurrentY(), 10);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) 5 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<NormalTextViewGroupParams> list = new ArrayList<>();
            //                    // 从下到上，因此要反过来
            //                    int blueColor = DiaSiApplication.getInstance().getResources().getColor(R.color
            // .textBlue);
            //                    int orangeColor = DiaSiApplication.getInstance().getResources().getColor(R.color
            // .textOrange);
            //                    list.add(new NormalTextViewGroupParams("你你你你你你你你", 14, blueColor));
            //                    list.add(new NormalTextViewGroupParams("再再再再再再再再", 14, blueColor));
            //                    list.add(new NormalTextViewGroupParams("大大大大大大大大", 14, orangeColor));
            //                    list.add(new NormalTextViewGroupParams("声声声声声声声声", 14, orangeColor));
            //                    list.add(new NormalTextViewGroupParams("讲讲讲讲讲讲讲讲", 14, blueColor));
            //                    list.add(new NormalTextViewGroupParams("对对对对对对对对", 14, blueColor));
            //                    list.add(new NormalTextViewGroupParams("不不不不不不不不", 14, orangeColor));
            //                    list.add(new NormalTextViewGroupParams("起起起起起起起起", 14, orangeColor));
            //                    list.add(new NormalTextViewGroupParams("非非非非非非非非", 14, blueColor));
            //                    NormalTextViewGroup normalTextViewGroup4 =
            //                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX(),
            //                                    mHeartShapeView.getBoundaryY(),
            //                                    mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW(),
            //                                    mHeartShapeView.getBoundaryY(),
            //                                    100, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN, 15,
            // false);
            //                    mMoveables.add(normalTextViewGroup4);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) 8 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<NormalTextViewGroupParams> list = new ArrayList<>();
            //                    // 从下到上，因此要反过来
            //                    list.add(new NormalTextViewGroupParams("凡凡", 14));
            //                    list.add(new NormalTextViewGroupParams("哥哥哥", 14));
            //                    NormalTextViewGroup normalTextViewGroup4 =
            //                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX() + mHeartShapeView
            // .getBoundaryW(),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
            //                                    mHeartShapeView.getBoundaryX(),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
            //                                    100, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 25,
            // true);
            //                    mMoveables.add(normalTextViewGroup4);
            //                }
            //            });
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) 11 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<NormalTextViewGroupParams> list = new ArrayList<>();
            //                    // 从下到上，因此要反过来
            //                    list.add(new NormalTextViewGroupParams("我", 14));
            //                    list.add(new NormalTextViewGroupParams("听", 14));
            //                    list.add(new NormalTextViewGroupParams("不", 14));
            //                    list.add(new NormalTextViewGroupParams("到", 14));
            //                    list.add(new NormalTextViewGroupParams("在", 14));
            //                    list.add(new NormalTextViewGroupParams("讲", 14));
            //                    list.add(new NormalTextViewGroupParams("再", 14));
            //                    list.add(new NormalTextViewGroupParams("听", 14));
            //                    list.add(new NormalTextViewGroupParams("不", 14));
            //                    list.add(new NormalTextViewGroupParams("到", 14));
            //                    list.add(new NormalTextViewGroupParams("继", 14));
            //                    list.add(new NormalTextViewGroupParams("续", 14));
            //                    list.add(new NormalTextViewGroupParams("讲", 14));
            //                    NormalTextViewGroup normalTextViewGroup4 =
            //                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX() + mHeartShapeView
            // .getBoundaryW(),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
            //                                    mHeartShapeView.getBoundaryX(),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
            //                                    100, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 50,
            // true);
            //                    mMoveables.add(normalTextViewGroup4);
            //
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) 12 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<NormalTextViewGroupParams> list = new ArrayList<>();
            //                    // 从下到上，因此要反过来
            //                    list.add(new NormalTextViewGroupParams("到到到到到到到", 14));
            //                    list.add(new NormalTextViewGroupParams("我我我我我我我", 14));
            //                    list.add(new NormalTextViewGroupParams("听听听听听听听", 14));
            //                    list.add(new NormalTextViewGroupParams("到到到到到到到", 14));
            //                    list.add(new NormalTextViewGroupParams("为为为为为为为", 14));
            //                    list.add(new NormalTextViewGroupParams("止止止止止止止", 14));
            //                    NormalTextViewGroup normalTextViewGroup4 =
            //                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX(),
            //                                    mHeartShapeView.getBoundaryY(),
            //                                    mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW(),
            //                                    mHeartShapeView.getBoundaryY(),
            //                                    100, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN, 75,
            // true);
            //                    mMoveables.add(normalTextViewGroup4);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) 20 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<NormalTextViewGroupParams> list = new ArrayList<>();
            //                    // 从下到上，因此要反过来
            //                    list.add(new NormalTextViewGroupParams("警警", 14));
            //                    list.add(new NormalTextViewGroupParams("署署署", 14));
            //                    list.add(new NormalTextViewGroupParams("有有有有", 14));
            //                    list.add(new NormalTextViewGroupParams("规规规", 14));
            //                    list.add(new NormalTextViewGroupParams("定定", 14));
            //                    NormalTextViewGroup normalTextViewGroup4 =
            //                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX() + mHeartShapeView
            // .getBoundaryW(),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
            //                                    mHeartShapeView.getBoundaryX(),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
            //                                    100, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 25,
            // true);
            //                    mMoveables.add(normalTextViewGroup4);
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) 3 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    List<NormalTextViewGroupParams> list = new ArrayList<>();
            //                    // 从下到上，因此要反过来
            //                    int blueColor = DiaSiApplication.getInstance().getResources().getColor(R.color
            // .textBlue);
            //                    int orangeColor = DiaSiApplication.getInstance().getResources().getColor(R.color
            // .textOrange);
            //                    for (int i = 0; i < 7; i++) {
            //                        list.add(new NormalTextViewGroupParams("刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘 刘", 14,
            // blueColor));
            //                    }
            //                    list.add(new NormalTextViewGroupParams(" ", 14));
            //                    for (int i = 0; i < 7; i++) {
            //                        list.add(new NormalTextViewGroupParams("醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 醒 ",
            // 14, orangeColor));
            //                    }
            //                    list.add(new NormalTextViewGroupParams(" ", 14));
            //                    for (int i = 0; i < 7; i++) {
            //                        list.add(new NormalTextViewGroupParams("讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 讲 ",
            // 14, blueColor));
            //                    }
            //                    list.add(new NormalTextViewGroupParams(" ", 14));
            //                    for (int i = 0; i < 7; i++) {
            //                        list.add(new NormalTextViewGroupParams("非 非 非 非 非 非 非 非 非 非 非 非 非 非 非 非 非 ",
            // 14, orangeColor));
            //                    }
            //                    list.add(new NormalTextViewGroupParams(" ", 14));
            //                    for (int i = 0; i < 7; i++) {
            //                        list.add(new NormalTextViewGroupParams("凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 凡 ",
            // 14, blueColor));
            //                    }
            //                    list.add(new NormalTextViewGroupParams(" ", 14));
            //                    for (int i = 0; i < 7; i++) {
            //                        list.add(new NormalTextViewGroupParams("哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 哥 ",
            // 14, orangeColor));
            //                    }
            //                    NormalTextViewGroup normalTextViewGroup4 =
            //                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX(),
            //                                    mHeartShapeView.getBoundaryY(),
            //                                    mHeartShapeView.getBoundaryX(),
            //                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() -
            // DpiUtil
            //                                            .spToPix(14),
            //                                    30, list, NormalTextView.TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT,
            // 5, false);
            //                    mMoveables.add(normalTextViewGroup4);
            //                }
            //            });

            /**
             * fengexian
             * */

            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (1 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.startTwinkle(25);
            //                    mHeartShapeView.startMove(portraitView.getCenterX(), portraitView.getCenterY(), 5,
            //                            heartShapeView1 -> {
            //                                heartShapeView1.setDismiss(true);
            //                                heartShapeView1.setHeartMode(HeartShapeView.HEART_MODE_NORMAL);
            //                            });
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (3 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    portraitView.setPortraitBmp(PortraitView.BMP_LIUXING);
            //                    mHeartShapeView.setDismiss(false);
            //                    mHeartShapeView.setHeartCenter();
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (3.5 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(), DiaSiApplication
            // .getCanvasHeight()
            //                            / 2, mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() -
            // DpiUtil.dipToPix(20),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(20), -30));
            //                }
            //            });
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (4 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(DpiUtil.dipToPix(100), DiaSiApplication.getCanvasHeight(),
            //                            DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(20),
            //                            DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(30), 135));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (4.5 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(0, 0,
            //                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(50),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(40), -160));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (5 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(), DiaSiApplication
            // .getCanvasHeight(),
            //                            mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() + DpiUtil
            // .dipToPix(50),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(50), -10));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (5.1 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix
            // (30),
            //                            DiaSiApplication.getCanvasHeight(),
            //                            DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(30),
            //                            mHeartShapeView.getBoundaryY(), 0));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (5.5 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(mHeartShapeView.getBoundaryR(),
            //                            DiaSiApplication.getCanvasHeight(),
            //                            DiaSiApplication.getCanvasWidth() / 2 + DpiUtil.dipToPix(30),
            //                            mHeartShapeView.getBoundaryB()-DpiUtil.dipToPix(40), 0));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (5.8 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(DpiUtil.dipToPix(100),
            //                            DiaSiApplication.getCanvasHeight(),
            //                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(20),
            //                            mHeartShapeView.getBoundaryB()+DpiUtil.dipToPix(20), 135));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (6.0 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(mHeartShapeView.getBoundaryX(),
            //                            DiaSiApplication.getCanvasHeight()-DpiUtil.dipToPix(50),
            //                            mHeartShapeView.getBoundaryX(),
            //                            mHeartShapeView.getBoundaryY(), 0));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (6.0 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(mHeartShapeView.getBoundaryX(),
            //                            DiaSiApplication.getCanvasHeight()-DpiUtil.dipToPix(50),
            //                            mHeartShapeView.getBoundaryX(),
            //                            mHeartShapeView.getBoundaryY(), 0));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (6.1 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(mHeartShapeView.getBoundaryR()-DpiUtil.dipToPix(30),
            //                           0,
            //                            mHeartShapeView.getBoundaryX()+DpiUtil.dipToPix(100),
            //                            mHeartShapeView.getBoundaryY()+DpiUtil.dipToPix(30), -80));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (6.3 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth()/2+DpiUtil.dipToPix(20),
            //                            DiaSiApplication.getCanvasHeight(),
            //                            DiaSiApplication.getCanvasWidth()/2,
            //                            mHeartShapeView.getBoundaryB()+DpiUtil.dipToPix(30), 40));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (6.5 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth(),
            //                            0,
            //                            DiaSiApplication.getCanvasWidth()/2+Dl.dipToPix(30),
            //                            mHeartShapeView.getBoundaryY()+DpiUtil.dipToPix(50), -45));
            //                }
            //            });
            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return (long) (6.7 * 1000);
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView, PortraitView portraitView) {
            //                    mMoveables.add(new GunView(DiaSiApplication.getCanvasWidth()/2+DpiUtil.dipToPix(100),
            //                            0,
            //                            DiaSiApplication.getCanvasWidth()/2,
            //                            mHeartShapeView.getBoundaryY()+DpiUtil.dipToPix(50), -100));
            //                }
            //            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (1 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY); // 这句话有重置重力状态的作用，因此转换重力方向，要重新调用
                    mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_RIGHT);
                    mMoveables.add(new ArrowView(0, mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(30),
                            DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_RIGHT));
                    buttonVisibilityCallBack.showButton();
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (1.5 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mMoveables.add(new SpineView(mHeartShapeView.getBoundaryR(), mHeartShapeView.getBoundaryY(),
                            mHeartShapeView.getBoundaryH(), SpineView.SPINE_DIRECTION_LEFT));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (2 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                    mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_TOP);
                    mMoveables.add(new ArrowView(mHeartShapeView.getBoundaryX() - DpiUtil.dipToPix(30),
                            DiaSiApplication.getCanvasHeight(),
                            DiaSiApplication.getCanvasHeight(), ArrowView.DIRECTION_UP));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (2.5 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                            mHeartShapeView.getBoundaryW(), SpineView.SPINE_DIRECTION_DOWN));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (3 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                    mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_TOP);
                    mMoveables.add(new ArrowView(mHeartShapeView.getBoundaryX() - DpiUtil.dipToPix(30),
                            DiaSiApplication.getCanvasHeight(),
                            DiaSiApplication.getCanvasHeight(), ArrowView.DIRECTION_UP));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (3.5 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                            mHeartShapeView.getBoundaryW(), SpineView.SPINE_DIRECTION_DOWN));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (4 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                    mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_LEFT);
                    mMoveables.add(new ArrowView(DiaSiApplication.getCanvasWidth(),
                            mHeartShapeView.getBoundaryB() - DpiUtil.dipToPix(30),
                            DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_LEFT));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (4.5 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                            mHeartShapeView.getBoundaryH(), SpineView.SPINE_DIRECTION_RIGHT));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (5 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                    mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_BOTTOM);
                    mMoveables.add(new ArrowView(mHeartShapeView.getBoundaryR() + DpiUtil.dipToPix(30),
                            0,
                            DiaSiApplication.getCanvasHeight(), ArrowView.DIRECTION_DOWN));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (5.5 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryB(),
                            mHeartShapeView.getBoundaryW(), SpineView.SPINE_DIRECTION_UP));

                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (6 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                    mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_LEFT);
                    mMoveables.add(new ArrowView(DiaSiApplication.getCanvasWidth(),
                            mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(30),
                            DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_LEFT));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (6.5 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                            mHeartShapeView.getBoundaryH(), SpineView.SPINE_DIRECTION_RIGHT));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (7 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                    mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_RIGHT);
                    mMoveables.add(new ArrowView(0,
                            mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(30),
                            DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_RIGHT));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (7.5 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mMoveables.add(new SpineView(mHeartShapeView.getBoundaryR(), mHeartShapeView.getBoundaryY(),
                            mHeartShapeView.getBoundaryH(), SpineView.SPINE_DIRECTION_LEFT));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (8 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                    mHeartShapeView.setGravityOrientation(HeartShapeView.GRAVITY_LEFT);
                    mMoveables.add(new ArrowView(DiaSiApplication.getCanvasWidth(),
                            mHeartShapeView.getBoundaryB() + DpiUtil.dipToPix(30),
                            DiaSiApplication.getCanvasWidth(), ArrowView.DIRECTION_LEFT));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (8.5 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                            mHeartShapeView.getBoundaryH(), SpineView.SPINE_DIRECTION_RIGHT));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (10 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mHeartShapeView.changeBoundaryWithAmination(DpiUtil.dipToPix(100), mHeartShapeView.getBoundaryY() +
                                    DpiUtil.dipToPix(10)
                            , DiaSiApplication.getCanvasWidth(), mHeartShapeView.getBoundaryH() - DpiUtil.dipToPix(20),
                            10);
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (10.5 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mMoveables.add(new SinLongSpineGroupView(DiaSiApplication
                            .getCanvasWidth(), mHeartShapeView.getBoundaryY(), -30, 30, 3,
                            mHeartShapeView.getBoundaryH() * 0.5f,
                            mHeartShapeView.getBoundaryH() * 0.25f, mHeartShapeView.getBoundaryH() * 0.25f));
                }
            });

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
                    return (long) (15 * 1000);
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    mMoveables.add(new EasyLongSpineGroupView(DiaSiApplication.getCanvasWidth(),
                            mHeartShapeView.getBoundaryY() - DpiUtil.dipToPix(20), mHeartShapeView.getBoundaryY() +
                            mHeartShapeView.getBoundaryH() + DpiUtil.dipToPix(20), -30, 5, 5, 3, 35,
                            mHeartShapeView.getBoundaryH() * 0.6f));
                }
            });



            //
            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 9 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //                        mHeartShapeView,
            //                                          PortraitView portraitView) {
            //                    List<String> list = new ArrayList<>();
            //                    list.add("吔");
            //                    list.add("屎");
            //                    list.add("啦");
            //                    list.add("傻");
            //                    list.add("屌");
            //                    FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
            //                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
            //                            mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH()-DpiUtil
            //                                    .dipToPix(30),
            //                            FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
            //                            list, mHeartShapeView, 50, 25, 20);
            //                    mMoveables.add(followInnerTextViewGroup);
            //                }
            //            });

            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 2 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                                          PortraitView portraitView) {
            //                    mMoveables.add(new RotateTextView(1000,500,"吔"));
            //                }
            //            });

            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 2 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                                          PortraitView portraitView) {
            //                    mMoveables.add(new SpineView(mHeartShapeView.getBoundaryX(),mHeartShapeView
            // .getBoundaryY(),
            //                            mHeartShapeView.getBoundaryW(),SpineView.SPINE_DIRECTION_DOWN));
            //                }
            //            });

            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 1 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                                          PortraitView portraitView) {
            //                    mMoveables.add(new CollisionNormalTextView(1974, 635, 0, 635, 358, "吔",
            //                            NormalTextView.TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT));
            //                }
            //            });

            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 1 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                                          PortraitView portraitView) {
            //                    mMoveables.add(new RandomTextViewGroup(DiaSiApplication.getCanvasWidth(),
            // mHeartShapeView
            //                            .getBoundaryY(), DiaSiApplication.getCanvasWidth(), mHeartShapeView
            // .getBoundaryH(), 20, 500,
            //                            "吔"));
            //                }
            //            });

            //                        timerEvents.add(new TimerEvent() {
            //                            @Override
            //                            public long getIntervalTime() {
            //                                return 1 * 1000;
            //                            }
            //
            //                            @Override
            //                            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            //             mHeartShapeView,
            //                                                      PortraitView portraitView) {
            //                                mMoveables.add(new SinLongSpineGroupView(DiaSiApplication
            // .getCanvasWidth(),
            //             mHeartShapeView
            //                                        .getBoundaryY(), -30,30,3,mHeartShapeView.getBoundaryH()*0.5f,
            // mHeartShapeView
            //                                        .getBoundaryH()*0.25f,mHeartShapeView.getBoundaryH()*0.25f));
            //                            }
            //                        });

            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 1 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                                          PortraitView portraitView) {
            //                    //                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
            //                    //                    mHeartShapeView.setGravityOrientation(HeartShapeView
            // .GRAVITY_BOTTOM);
            //                    mMoveables.add(new EasyLongSpineGroupView(DiaSiApplication.getCanvasWidth(),
            // mHeartShapeView
            //                            .getBoundaryY() - DpiUtil.dipToPix(20), mHeartShapeView.getBoundaryY() +
            // mHeartShapeView
            //                            .getBoundaryH() + DpiUtil.dipToPix(20), -30, 5, 5, 3, 35,
            //                            mHeartShapeView.getBoundaryH() * 0.6f));
            //                }
            //            });

            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 1 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                                          PortraitView portraitView) {
            //                    mMoveables.add(new LongSpineView(500,500,5,LongSpineView.SPINE_DIRECTION_DOWN,
            // DpiUtil.dipToPix
            //                            (50)));
            //                }
            //            });

            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 1 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                                          PortraitView portraitView) {
            //                    mHeartShapeView.changeBoundaryWithAmination(0,mHeartShapeView.getBoundaryY(),
            // DiaSiApplication
            //                            .getCanvasWidth(),mHeartShapeView.getBoundaryH(),15);
            //                }
            //            });

            //                    timerEvents.add(new TimerEvent() {
            //                        @Override
            //                        public long getIntervalTime() {
            //                            return 1 * 1000;
            //                        }
            //
            //                        @Override
            //                        public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView, PortraitView portraitView) {
            //                            mMoveables.add(new CollisionNormalTextView(1000, 1000, 500, 1000, 10000,
            // "梁非凡吔屎啦",
            //                                    NormalTextView.TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT));
            //                        }
            //                    });
            //        timerEvents.push(new TimerEvent() {
            //            @Override
            //            public long getTntervalTime() {
            //                return 1 * 1000;
            //            }
            //
            //            @Override
            //            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView) {
            //                mMoveables.add(new GunView(1720, 720, 1480,
            //                        720, -720));
            //            }
            //        });
            //        timerEvents.add(new TimerEvent() {
            //            @Override
            //            public long getTntervalTime() {
            //                return 2 * 1000;
            //            }
            //
            //            @Override
            //            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView) {
            //                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth()/2,DiaSiApplication
            // .getCanvasHeight()/2,
            //                        0,720));
            //            }
            //        });
            //                    timerEvents.add(new TimerEvent() {
            //                        @Override
            //                        public long getIntervalTime() {
            //                            return 1 * 1000;
            //                        }
            //
            //                        @Override
            //                        public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                PortraitView portraitView) {
            //                            List<String> list = new ArrayList<>();
            //                            list.add("大好人好好");list.add("大好人好好");list.add("大好人好好");list.add("大好人好好");
            // list.add("大好人好好");list
            //                                    .add("大好人好好");
            //                            NormalTextViewGroup normalTextViewGroup = new NormalTextViewGroup(2000,500,
            // 500,500,
            //             200,list,
            //                                    NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN,100,false);
            //                            mMoveables.add(normalTextViewGroup);

            //                            List<String> list2 = new ArrayList<String>();
            //                            list2.add("大好人");list2.add("大好人");list2.add("大好人");list2.add("大好人");
            // list2.add("大好人");
            //                            list2.add("大好人");
            //                            NormalTextViewGroup normalTextViewGroup2 = new NormalTextViewGroup(2000,
            // 1000,500,1000,200,
            //             list2,
            //                                    NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP,100,false);
            //                            mMoveables.add(normalTextViewGroup2);
            //                        }
            //                    });
            //        timerEvents.add(new TimerEvent() {
            //            @Override
            //            public long getTntervalTime() {
            //                return 1 * 1000;
            //            }
            //
            //            @Override
            //            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView) {
            //                PauseViewText pauseViewText = new PauseViewText(1000,1000,500,500,20,100,20,"吔",
            // NormalTextView
            //                        .TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
            //                mMoveables.add(pauseViewText);
            //            }
            //        });
            //        timerEvents.add(new TimerEvent() {
            //            @Override
            //            public long getIntervalTime() {
            //                return 1 * 1000;
            //            }
            //
            //            @Override
            //            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView,
            //                                      PortraitView portraitView) {
            //                PauseSpeedUpTextView pauseSpeedUpTextView =
            //                        new PauseSpeedUpTextView(1000, 1000, 100, 100, 20, 500, 20, "吔",
            //                                NormalTextView
            //                                        .TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
            //
            //                mMoveables.add(pauseSpeedUpTextView);
            //
            //                portraitView.move(portraitView.getCurrentX(),portraitView.getCurrentY(),0,0,100);
            //            }
            //        });

            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 1 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                                          PortraitView portraitView) {
            //                    FollowTextView followTextView = new FollowTextView(1000, 1000, mHeartShapeView, 10,
            // 50,
            //                            "吔",
            //                            NormalTextView
            //                                    .TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
            //                    mMoveables.add(followTextView);
            //                }
            //            });

            //            timerEvents.add(new TimerEvent() {
            //                @Override
            //                public long getIntervalTime() {
            //                    return 1 * 1000;
            //                }
            //
            //                @Override
            //                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                                          PortraitView portraitView) {
            //                    List<String> list = new ArrayList<>();
            //                    list.add("吔");
            //                    list.add("屎");
            //                    list.add("啦");
            //                    list.add("傻");
            //                    list.add("屌");
            //                    FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
            //                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
            //                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(5),
            //                            FollowInnerTextViewGroup.TEXT_ORIENTATION_VERTICAL,
            //                            list, mHeartShapeView, 50, 25, 20);
            //                    mMoveables.add(followInnerTextViewGroup);
            //
            //                }
            //            });
            //

            //                    timerEvents.add(new TimerEvent() {
            //                        @Override
            //                        public long getIntervalTime() {
            //                            return 1 * 1000;
            //                        }
            //
            //                        @Override
            //                        public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
            // mHeartShapeView,
            //                                                  PortraitView portraitView) {
            ////                            TimeDialogueParams[] paramses = new TimeDialogueParams[2];
            ////                            paramses[0] = new TimeDialogueParams("我是好人", 2000, 4000);
            ////                            paramses[1] = new TimeDialogueParams("我是大好人", 5000, 6000);
            ////                            TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,500,500,
            /// startTime,100000000);
            //                            TimeDialogueParams[] paramses = new TimeDialogueParams[2];
            //                            paramses[0] = new TimeDialogueParams("鸟儿在歌唱，", 100, 600);
            //                            paramses[1] = new TimeDialogueParams("鲜花在绽放...", 700, 1200);
            //                            TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
            //                                    portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil
            // .dipToPix(20),
            //                                    portraitView.getCurrentY() + DpiUtil.dipToPix(20),
            //                                    startTime,2500);
            //                            mMoveables.add(group);
            //                        }
            //                    });
        }
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

}
