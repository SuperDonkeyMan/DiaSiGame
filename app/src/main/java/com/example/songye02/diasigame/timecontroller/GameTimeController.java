package com.example.songye02.diasigame.timecontroller;

import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.callback.ButtonVisibilityCallBack;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.EasyLongSpineGroupView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.LongSpineGroupView;
import com.example.songye02.diasigame.model.shapeview.LongSpineView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.model.shapeview.SinLongSpineGroupView;
import com.example.songye02.diasigame.model.shapeview.SpineView;
import com.example.songye02.diasigame.model.textview.CollisionNormalTextView;
import com.example.songye02.diasigame.model.textview.FollowInnerTextViewGroup;
import com.example.songye02.diasigame.model.textview.FollowTextView;
import com.example.songye02.diasigame.model.textview.NormalTextView;
import com.example.songye02.diasigame.model.textview.NormalTextViewGroup;
import com.example.songye02.diasigame.model.textview.ParaboleTextGroup;
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

    private ButtonVisibilityCallBack buttonVisibilityCallBack;

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
//                        mHeartShapeView,
//                                          PortraitView portraitView) {
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
//                    return 1750;
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
//                    return (long) 17.66 * 1000;
//                }
//
//                @Override
//                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
//                        mHeartShapeView,
//                                          PortraitView portraitView) {
//                    portraitView.setPortraitBmp(PortraitView.BMP_LIUXING);
//                    TimeDialogueParams[] paramses = new TimeDialogueParams[4];
//                    paramses[0] = new TimeDialogueParams("吔屎吔屎梁非凡", 100, 1100);
//                    paramses[1] = new TimeDialogueParams("你听不到我~我在讲", 1200, 2200);
//                    paramses[2] = new TimeDialogueParams("讲到你哥听到为止", 3300, 4300);
//                    paramses[3] = new TimeDialogueParams("吔屎啦梁非凡~", 4400, 5400);
//                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
//                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
//                            portraitView.getCurrentY() + DpiUtil.dipToPix(20),
//                            System.currentTimeMillis(), 6000);
//                    group.setPlaySound(false);
//                    mMoveables.add(group);
//
//                    List<String> list = string2List("吔屎吔屎梁非凡");
//                    FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
//                            mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW() - DpiUtil
//                                    .dipToPix(25),
//                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10),
//                            FollowInnerTextViewGroup.TEXT_ORIENTATION_VERTICAL,
//                            list, mHeartShapeView, 50, 10, 25, 18);
//                    mMoveables.add(followInnerTextViewGroup);
//
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
//                        mHeartShapeView,
//                                          PortraitView portraitView) {
//                    List<String> list = string2List("你听不到我");
//                    FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
//                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
//                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10),
//                            FollowInnerTextViewGroup.TEXT_ORIENTATION_VERTICAL,
//                            list, mHeartShapeView, 50, 10, 25);
//                    mMoveables.add(followInnerTextViewGroup);
//
//                }
//            });
//
//            timerEvents.add(new TimerEvent() {
//                @Override
//                public long getIntervalTime() {
//                    return (long) 22 * 1000;
//                }
//
//                @Override
//                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
//                        mHeartShapeView,
//                                          PortraitView portraitView) {
//                    List<String> list = string2List("我在讲");
//                    FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
//                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
//                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(10),
//                            FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
//                            list, mHeartShapeView, 50, 10, 25);
//                    mMoveables.add(followInnerTextViewGroup);
//
//                }
//            });
//
//            timerEvents.add(new TimerEvent() {
//                @Override
//                public long getIntervalTime() {
//                    return (long) 24 * 1000;
//                }
//
//                @Override
//                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
//                        mHeartShapeView,
//                                          PortraitView portraitView) {
//                    List<String> list = string2List("讲到你哥听到为止");
//                    FollowInnerTextViewGroup followInnerTextViewGroup = new FollowInnerTextViewGroup(
//                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
//                            mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() - DpiUtil
//                                    .dipToPix(25),
//                            FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
//                            list, mHeartShapeView, 50, 10, 25, 15);
//                    mMoveables.add(followInnerTextViewGroup);
//
//                }
//            });
//
//            timerEvents.add(new TimerEvent() {
//                @Override
//                public long getIntervalTime() {
//                    return (long) 26 * 1000;
//                }
//
//                @Override
//                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
//                        mHeartShapeView,
//                                          PortraitView portraitView) {
//                    List<String> list = string2List("吔屎啦梁非凡");
//                    FollowInnerTextViewGroup followInnerTextViewGroup1 = new FollowInnerTextViewGroup(
//                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
//                            mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH() - DpiUtil
//                                    .dipToPix(25),
//                            FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
//                            list, mHeartShapeView, 50, 10, 25, 18);
//
//                    FollowInnerTextViewGroup followInnerTextViewGroup2 = new FollowInnerTextViewGroup(
//                            mHeartShapeView.getBoundaryX() + DpiUtil.dipToPix(5),
//                            mHeartShapeView.getBoundaryY() + DpiUtil.dipToPix(5),
//                            FollowInnerTextViewGroup.TEXT_ORIENTATION_HORIZONTAL,
//                            list, mHeartShapeView, 50, 10, 25, 18);
//                    mMoveables.add(followInnerTextViewGroup1);
//                    mMoveables.add(followInnerTextViewGroup2);
//                }
//            });
//
//            timerEvents.add(new TimerEvent() {
//                @Override
//                public long getIntervalTime() {
//                    return (long) 28 * 1000;
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

            timerEvents.add(new TimerEvent() {
                @Override
                public long getIntervalTime() {
//                    return (long) 29 * 1000;
                    return (long) 1 * 1000;
                }

                @Override
                public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView
                        mHeartShapeView, PortraitView portraitView) {
                    portraitView.setPortraitBmp(PortraitView.BMP_FEIFANGE);
                    mHeartShapeView.setDismiss(false);
                    mHeartShapeView.setHeartMode(HeartShapeView.HEART_MODE_GRAVITY);
                    mHeartShapeView.changeBoundaryWithAmination(mHeartShapeView.getBoundaryX()-mHeartShapeView
                            .getBoundaryW()/2,mHeartShapeView.getBoundaryY(),mHeartShapeView.getBoundaryW()*2,
                            mHeartShapeView.getBoundaryH(),10);
                    buttonVisibilityCallBack.showButton();


                    TimeDialogueParams[] paramses = new TimeDialogueParams[4];
                    paramses[0] = new TimeDialogueParams("刘醒我是你上司", 100, 1100);
                    paramses[1] = new TimeDialogueParams("警署有规定不啵上司嘴", 1200, 2200);
                    paramses[2] = new TimeDialogueParams("我要艹你 我要艹你", 3300, 4300);
                    paramses[3] = new TimeDialogueParams("大声讲我艹你", 4400, 5400);
                    TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                            portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                            portraitView.getCurrentY() + DpiUtil.dipToPix(20),
                            System.currentTimeMillis(), 6000);
                    group.setPlaySound(false);
                    mMoveables.add(group);

                    List<String> list = new ArrayList<>();
                    list.add("刘醒我是你");
                    list.add("刘醒我是你");
                    NormalTextViewGroup normalTextViewGroup1 = new NormalTextViewGroup(mHeartShapeView.getBoundaryX()
                            + mHeartShapeView.getBoundaryW(), mHeartShapeView.getBoundaryY(),
                            mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                            50, list, NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN, 25, true);
                    normalTextViewGroup1.setTextSize(12);
                    mMoveables.add(normalTextViewGroup1);

                    List<String> list2 = new ArrayList<>();
                    list2.add("刘醒我是你");
                    list2.add("刘醒我是你");
                    NormalTextViewGroup normalTextViewGroup2 =
                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX(), mHeartShapeView.getBoundaryY(),
                                    mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW(),
                                    mHeartShapeView.getBoundaryY(),
                                    50, list2, NormalTextView.TEXT_ORIENTATION_VERTICAL_UPTODOWN, 25, true);
                    normalTextViewGroup2.setTextSize(12);
                    mMoveables.add(normalTextViewGroup2);

                    List<String> list3 = new ArrayList<>();
                    // 从下到上，因此要反过来
                    list3.add("司上");
                    list3.add("司上");
                    list3.add("定规有署警");
                    list3.add("嘴司上顶不");
                    list3.add("你艹要我");
                    list3.add("你艹要我");
                    list3.add("你艹我讲声大");
                    NormalTextViewGroup normalTextViewGroup3 =
                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW(),
                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                    mHeartShapeView.getBoundaryX(),
                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                    50, list3, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 25, true);
                    normalTextViewGroup3.setTextSize(12);
                    mMoveables.add(normalTextViewGroup3);

                    List<String> list4 = new ArrayList<>();
                    // 从下到上，因此要反过来
                    list4.add("司上");
                    list4.add("司上");
                    list4.add("定规有署警");
                    list4.add("嘴司上顶不");
                    list4.add("你艹要我");
                    list4.add("你艹要我");
                    list4.add("你艹我讲声大");
                    NormalTextViewGroup normalTextViewGroup4 =
                            new NormalTextViewGroup(mHeartShapeView.getBoundaryX(),
                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                    mHeartShapeView.getBoundaryX() + mHeartShapeView.getBoundaryW(),
                                    mHeartShapeView.getBoundaryY() + mHeartShapeView.getBoundaryH(),
                                    50, list4, NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP, 25, true);
                    normalTextViewGroup4.setTextSize(12);
                    mMoveables.add(normalTextViewGroup4);

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
