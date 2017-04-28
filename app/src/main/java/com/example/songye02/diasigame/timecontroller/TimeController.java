package com.example.songye02.diasigame.timecontroller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.GunGroup;
import com.example.songye02.diasigame.model.shapeview.GunView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.textview.CollisionNormalTextView;
import com.example.songye02.diasigame.model.textview.NormalTextView;
import com.example.songye02.diasigame.model.textview.NormalTextViewGroup;
import com.example.songye02.diasigame.model.textview.ParaboleTextGroup;
import com.example.songye02.diasigame.model.textview.ParaboleTextView;
import com.example.songye02.diasigame.utils.DpiUtil;

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

    public void setStartTime(Long startTime){
        this.startTime = startTime;
    }

    public int excute(long currentTime, HeartShapeView mHeartShapeView,
                      List<BaseShowableView> mMoveables) throws Exception {
        if (startTime == 0) {
            throw new Exception("have not set start time");
        }
        if(timerEvent == null){
            return NONE_TIME_EVENT;
        }
        long interval = currentTime - startTime;
        if (timerEvent.getTntervalTime() > interval) {
            return EXCUTE_TIME_EVENT_NOT_REACH;
        }
        //timerEvent执行动作,向mMoveables中添加事件
        timerEvent.addTimerEvent(mMoveables, mHeartShapeView);
        if (timerEvents.size()>0){
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
//                mMoveables.add(new ParaboleTextGroup(DiaSiApplication.getCanvasWidth() / 2, DpiUtil.dipToPix(50)));
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
//                mMoveables.add(new GunGroup(DiaSiApplication.getCanvasWidth()/2,DiaSiApplication.getCanvasHeight()/2,
//                        0,720));
//            }
//        });
        timerEvents.add(new TimerEvent() {
            @Override
            public long getTntervalTime() {
                return 2 * 1000;
            }

            @Override
            public void addTimerEvent(List<BaseShowableView> mMoveables, HeartShapeView mHeartShapeView) {
                List<String> list = new ArrayList<String>();
                list.add("梁非凡吔屎啦");list.add("梁非凡吔屎啦");list.add("梁非凡吔屎啦");list.add("梁非凡吔屎啦");list.add("梁非凡吔屎啦");list.add("梁非凡吔屎啦");
                NormalTextViewGroup normalTextViewGroup = new NormalTextViewGroup(2000,1000,500,1000,500,list,
                        NormalTextView.TEXT_ORIENTATION_VERTICAL_DOWNTOUP,100,false);
                mMoveables.add(normalTextViewGroup);
            }
        });
    }
}
