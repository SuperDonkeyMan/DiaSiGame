package com.example.songye02.diasigame.timecontroller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.model.textview.FollowTextView;
import com.example.songye02.diasigame.model.textview.NormalTextView;
import com.example.songye02.diasigame.model.textview.NormalTextViewGroup;
import com.example.songye02.diasigame.model.textview.ParaboleTextView;
import com.example.songye02.diasigame.model.textview.PauseSpeedUpTextView;
import com.example.songye02.diasigame.model.textview.PauseViewText;

/**
 * Created by songye02 on 2017/4/25.
 */

public class TimeController {

    public static final int EXCUTE_TIME_EVENT_OK = 0;
    public static final int EXCUTE_TIME_EVENT_NOT_REACH = 1;
    public static final int NONE_TIME_EVENT = 2;

    public static long startTime = 0;
    private ArrayDeque<TimerEvent> timerEvents;
    private TimerEvent timerEvent;

    public TimeController() {
        initTimerEvents();
        timerEvent = timerEvents.pop();
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public int excute(long currentTime, HeartShapeView mHeartShapeView,
                      List<BaseShowableView> mMoveables, PortraitView portraitView) throws Exception {
        if (startTime == 0) {
            throw new Exception("have not set start time");
        }
        if (timerEvent == null) {
            return NONE_TIME_EVENT;
        }
        long interval = currentTime - startTime;
        if (timerEvent.getIntervalTime() > interval) {
            return EXCUTE_TIME_EVENT_NOT_REACH;
        }
        //timerEvent执行动作,向mMoveables中添加事件
        timerEvent.addTimerEvent(mMoveables, mHeartShapeView, portraitView);
        if (timerEvents.size() > 0) {
            timerEvent = timerEvents.pop();
        } else {
            timerEvent = null;
        }

        return EXCUTE_TIME_EVENT_OK;
    }

    private void initTimerEvents() {
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
        timerEvents.add(new TimerEvent() {
            @Override
            public long getIntervalTime() {
                return 1 * 1000;
            }

            @Override
            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView,
                                      PortraitView portraitView) {
                PauseSpeedUpTextView pauseSpeedUpTextView =
                        new PauseSpeedUpTextView(1000, 1000, 100, 100, 20, 500, 20, "吔",
                                NormalTextView
                                        .TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
                mMoveables.add(pauseSpeedUpTextView);

                portraitView.move(portraitView.getCurrentX(),portraitView.getCurrentY(),0,0,100);
            }
        });

        timerEvents.add(new TimerEvent() {
            @Override
            public long getIntervalTime() {
                return 1 * 1000;
            }

            @Override
            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView,
                                      PortraitView portraitView) {
                FollowTextView followTextView = new FollowTextView(1000, 1000, mHeartShapeView, 10, 50, 50, "吔",
                        NormalTextView
                                .TEXT_ORIENTATION_HORIZONTAL_LEFTTORIGHT);
                mMoveables.add(followTextView);
            }
        });
    }
}
