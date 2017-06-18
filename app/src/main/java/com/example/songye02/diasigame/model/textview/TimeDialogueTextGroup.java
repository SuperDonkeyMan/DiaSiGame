package com.example.songye02.diasigame.model.textview;

import java.util.ArrayList;
import java.util.List;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by songye02 on 2017/5/10.
 * <p>
 * 一段文字，每行为一个DialogueText，按时间出现消失
 * <p>
 * 每行文字不能单独dead，是与整个group一起dead的
 */

public class TimeDialogueTextGroup extends BaseShowableView {
    protected TimeDialogueParams[] paramses;
    protected List<DialogueText> dialogueTexts;
    protected Long startTime; // surfaceView初始化时的时间戳
    protected int continueTime; // 从开始，到对话框消失，总共要多久/ms
    protected float rowSpacing; // 行距
    protected int textIndex = 0; // 当前位置的索引
    protected Paint backgroudPaint;
    protected boolean isPlaySound = false;

    public TimeDialogueTextGroup(TimeDialogueParams[] paramses, float startX, float startY,
                                 long startTime, int continueTime) {
        super(startX, startY, 0, 0);
        this.paramses = paramses;
        this.startTime = startTime;
        this.continueTime = continueTime;
        dialogueTexts = new ArrayList<>();
        rowSpacing = DpiUtil.dipToPix(15);
        backgroudPaint = new Paint();
        backgroudPaint.setColor(Color.WHITE);
    }

    @Override
    public void draw(Canvas canvas) {
        // 外边框的大小
        canvas.drawRect(startX, startY, startX + DpiUtil.dipToPix(150), startY + DpiUtil.dipToPix(100), backgroudPaint);
        for (DialogueText dialogueText : dialogueTexts) {
            dialogueText.draw(canvas);
        }
    }

    @Override
    public void logic() {
        long currentTime = System.currentTimeMillis() - startTime;
        // 时间超过了，就dead
        if (currentTime >= continueTime) {
            // 释放资源
            for(DialogueText dialogueText:dialogueTexts){
                dialogueText.releaseSoundPool();
            }
            isDead = true;
        }
        // 下标越界，不进行添加新的文字行
        if (textIndex < paramses.length) {
            if (paramses[textIndex].startTime <= currentTime) {
                long displayTime = paramses[textIndex].endTime - paramses[textIndex].startTime;
                DialogueText dialogueText =
                        new DialogueText(startX + DpiUtil.dipToPix(10),
                                textIndex * rowSpacing + DpiUtil.dipToPix(20) + startY,
                                paramses[textIndex].text, displayTime);
                dialogueText.setPlaySound(isPlaySound);
                dialogueTexts.add(dialogueText);
                textIndex++;
            }
        }
        for (DialogueText dialogueText : dialogueTexts) {
            dialogueText.logic();
        }
    }

    public void setPlaySound(boolean playSound){
        this.isPlaySound = playSound;
    }
}
