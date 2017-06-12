package com.example.songye02.diasigame.timecontroller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.example.songye02.diasigame.exception.NoStartTimeException;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.model.textview.FollowTextView;
import com.example.songye02.diasigame.model.textview.NormalTextView;
import com.example.songye02.diasigame.model.textview.NormalTextViewGroup;
import com.example.songye02.diasigame.model.textview.ParaboleTextView;
import com.example.songye02.diasigame.model.textview.PauseSpeedUpTextView;
import com.example.songye02.diasigame.model.textview.PauseViewText;
import com.example.songye02.diasigame.model.textview.TimeDialogueParams;
import com.example.songye02.diasigame.model.textview.TimeDialogueTextGroup;

/**
 * Created by songye02 on 2017/4/25.
 */

public abstract class TimeController {

    public static final int EXCUTE_TIME_EVENT_OK = 0;
    public static final int EXCUTE_TIME_EVENT_NOT_REACH = 1;
    public static final int NONE_TIME_EVENT = 2;

    public static long startTime = 0;
    protected volatile ArrayDeque<TimerEvent> timerEvents;
    protected TimerEvent timerEvent;

    public TimeController() {
        initTimerEvents();
        if (timerEvents.size() > 0) {
            timerEvent = timerEvents.pop();
        }
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public int excute(long currentTime, HeartShapeView mHeartShapeView,
                      List<BaseShowableView> mMoveables, PortraitView portraitView) throws NoStartTimeException {
        if (startTime == 0) {
            throw new NoStartTimeException("have not set start time");
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

    abstract void initTimerEvents();

}
