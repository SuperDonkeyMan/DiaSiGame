package com.example.songye02.diasigame.timecontroller;

import java.util.ArrayDeque;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.model.textview.TimeDialogueParams;
import com.example.songye02.diasigame.model.textview.TimeDialogueTextGroup;
import com.example.songye02.diasigame.utils.DpiUtil;

/**
 * Created by songye02 on 2017/5/12.
 */

public class GameTimeController extends TimeController {
    @Override
    void initTimerEvents() {
        {
            timerEvents = new ArrayDeque();
            //        timerEvents.add(new TimerEvent() {
            //            @Override
            //            public long getTntervalTime() {
            //                return 4 * 1000;
            //            }
            //
            //            @Override
            //            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView) {
            //                mMoveables.add(new ParaboleTextGroup(DiaSiApplication.getCanvasWidth() / 2, DpiUtil
            // .dipToPix(50)));
            //            }
            //        });
            //        timerEvents.push(new TimerEvent() {
            //            @Override
            //            public long getTntervalTime() {
            //                return 0 * 1000;
            //            }
            //
            //            @Override
            //            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView) {
            //                mMoveables.add(new CollisionNormalTextView(1000, 1000, 500, 1000, 100, "梁非凡吔屎啦",
            //                        NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP));
            //            }
            //        });
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
            //        timerEvents.add(new TimerEvent() {
            //            @Override
            //            public long getTntervalTime() {
            //                return 1 * 1000;
            //            }
            //
            //            @Override
            //            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView) {
            //                List<String> list = new ArrayList<String>();
            //                list.add("qwe");list.add("wer");list.add("ert");list.add("rty");list.add("tyu");list.add
            //                        ("yui");
            //                NormalTextViewGroup normalTextViewGroup = new NormalTextViewGroup(2000,1000,500,1000,200,list,
            //                        NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP,100,false);
            //                mMoveables.add(normalTextViewGroup);
            //
            //                List<String> list2 = new ArrayList<String>();
            //                list2.add("123");list2.add("234");list2.add("345");list2.add("456");list2.add("567");
            //                list2.add("678");
            //                NormalTextViewGroup normalTextViewGroup2 = new NormalTextViewGroup(2000,1500,500,1500,200,
            // list2,
            //                        NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP,100,false);
            //                mMoveables.add(normalTextViewGroup2);
            //            }
            //        });
            //        timerEvents.add(new TimerEvent() {
            //            @Override
            //            public long getTntervalTime() {
            //                return 1 * 1000;
            //            }
            //
            //            @Override
            //            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView) {
            //                PauseViewText pauseViewText = new PauseViewText(1000,1000,500,500,20,100,20,"吔",NormalTextView
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

            //        timerEvents.add(new TimerEvent() {
            //            @Override
            //            public long getIntervalTime() {
            //                return 1 * 1000;
            //            }
            //
            //            @Override
            //            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView,
            //                                      PortraitView portraitView) {
            //                FollowTextView followTextView = new FollowTextView(1000, 1000, mHeartShapeView, 10, 50, 50,
            // "吔",
            //                        NormalTextView
            //                                .TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
            //                mMoveables.add(followTextView);
            //            }
            //        });
                    timerEvents.add(new TimerEvent() {
                        @Override
                        public long getIntervalTime() {
                            return 1 * 1000;
                        }

                        @Override
                        public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView,
                                                  PortraitView portraitView) {
//                            TimeDialogueParams[] paramses = new TimeDialogueParams[2];
//                            paramses[0] = new TimeDialogueParams("我是好人", 2000, 4000);
//                            paramses[1] = new TimeDialogueParams("我是大好人", 5000, 6000);
//                            TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,500,500,startTime,100000000);
                            TimeDialogueParams[] paramses = new TimeDialogueParams[2];
                            paramses[0] = new TimeDialogueParams("鸟儿在歌唱，", 100, 600);
                            paramses[1] = new TimeDialogueParams("鲜花在绽放...", 700, 1200);
                            TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                                    portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                                    portraitView.getCurrentY() + DpiUtil.dipToPix(20),
                                    startTime,2500);
                            mMoveables.add(group);
                        }
                    });
        }
    }
}
