package com.example.songye02.diasigame.model.textview;

/**
 * Created by songye02 on 2017/5/10.
 */

public class TimeDialogueParams {
    public String text;
    public long startTime;
    public int endTime;

    public TimeDialogueParams(String text, long startTime, int endTime) {
        this.text = text;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
