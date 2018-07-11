package com.example.songye02.diasigame.timecontroller;

/**
 * Created by songye02 on 2017/4/25.
 */

public interface TimerEvent<T extends BaseViewHolder> {
    long getIntervalTime();
    void addToViewHolder(T viewHolder);
}
