package com.example.songye02.diasigame.model.textview;

/**
 * Created by songye02 on 2017/5/11.
 * 事件触发才会消失的DialogueGroup
 */

public class TriggerDialogueGroup extends TimeDialogueTextGroup {

    public TriggerDialogueGroup(TimeDialogueParams[] paramses, float startX, float startY, long startTime) {
        super(paramses, startX, startY, startTime, Integer.MAX_VALUE);
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
        if(isDead){
            // 释放资源
            for(DialogueText dialogueText:dialogueTexts){
                dialogueText.releaseSoundPool();
            }
        }
    }

    public boolean havaPlayedOk() {
        long lastTime = paramses[paramses.length - 1].endTime + startTime;
        return System.currentTimeMillis() >= lastTime;
    }
}
