package com.example.songye02.diasigame.timecontroller;

import com.example.songye02.diasigame.exception.NoStartTimeException;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by songye02 on 2017/4/25.
 * 这里，暂停只能暂停添加绘制view的那部分，每个子view不能实现时间控制暂停，因此建议子view不要用精确时间控制
 * 而是用帧数控制。
 */

public abstract class TimeController<T extends BaseViewHolder> {

    public static final int EXCUTE_TIME_EVENT_OK = 0;
    public static final int EXCUTE_TIME_EVENT_NOT_REACH = 1;
    public static final int NONE_TIME_EVENT = 2;

    public long startTime = 0;
    public long pauseTime = 0;
    protected volatile ArrayDeque<TimerEvent> timerEvents;
    protected List<TimerEvent> eventsList;
    protected TimerEvent timerEvent;

    public TimeController() {
        initTimerEvents();
        Collections.sort(eventsList, new Comparator<TimerEvent>() {
            public int compare(TimerEvent timeEvent1, TimerEvent timeEvent2) {
                if (timeEvent1.getIntervalTime() > timeEvent2.getIntervalTime()) {
                    return 1;
                } else if (timeEvent1.getIntervalTime() < timeEvent2.getIntervalTime()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        timerEvents = new ArrayDeque<>(eventsList);
        if (timerEvents.size() > 0) {
            timerEvent = timerEvents.pop();
        }
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public int excute(long currentTime, T viewHolder) throws NoStartTimeException {
        if (startTime == 0) {
            throw new NoStartTimeException("have not set start time");
        }
        if (timerEvent == null) {
            return NONE_TIME_EVENT;
        }
        long interval = currentTime - startTime - pauseTime;
        if (timerEvent.getIntervalTime() > interval) {
            return EXCUTE_TIME_EVENT_NOT_REACH;
        }
        //timerEvent执行动作,向mMoveables中添加事件
        timerEvent.addToViewHolder(viewHolder);
        if (timerEvents.size() > 0) {
            timerEvent = timerEvents.pop();
        } else {
            timerEvent = null;
        }

        return EXCUTE_TIME_EVENT_OK;
    }

    abstract void initTimerEvents();

    public void setPauseTime(long pauseTime){
        this.pauseTime = pauseTime;
    }

    public void clearTimerEvents(){
        timerEvents.clear();
        // 将当前正在缓存的任务也要置空
        timerEvent = null;
        pauseTime = 0;
    }

    public void addNewTimerEvent(List<TimerEvent> newTimerEvents) {
        eventsList.clear();
        while (!timerEvents.isEmpty()){
            eventsList.add(timerEvents.pop());
        }
        eventsList.addAll(newTimerEvents);
        Collections.sort(eventsList, new Comparator<TimerEvent>() {
            public int compare(TimerEvent timeEvent1, TimerEvent timeEvent2) {
                if (timeEvent1.getIntervalTime() > timeEvent2.getIntervalTime()) {
                    return 1;
                } else if (timeEvent1.getIntervalTime() < timeEvent2.getIntervalTime()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        timerEvents.addAll(eventsList);
        // 这里必须提前pop出一个，timerEvent为空的时候就跳出循环了，且timerEvent可能就是之前pop出来的任务
        if(timerEvent == null){
            timerEvent = timerEvents.pop();
        }
    }

    public void clearAndAddNewTimerEvent(Long startTime, List<TimerEvent> newTimerEvents){
        this.startTime = startTime;
        clearTimerEvents();
        addNewTimerEvent(newTimerEvents);
    }



}
