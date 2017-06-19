package com.example.songye02.diasigame.model.textview;

import java.util.ArrayList;
import java.util.List;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.timecontroller.TimeController;
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
    protected int continueCount; // 从开始，到对话框消失，总共要多久/ms
    protected float rowSpacing; // 行距
    protected int textIndex = 0; // 当前位置的索引
    protected Paint backgroudPaint;
    protected boolean isPlaySound = false;
    private int count = 0;

    public TimeDialogueTextGroup(TimeDialogueParams[] paramses, float startX, float startY,
                             int continueTime) {
        super(startX, startY, 0, 0);
        this.paramses = paramses;
        this.continueCount = continueTime/ DiaSiApplication.TIME_DELAYED;
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
        // 时间超过了，就dead
        if (count >= continueCount) {
            // 释放资源
            for(DialogueText dialogueText:dialogueTexts){
                dialogueText.releaseSoundPool();
            }
            isDead = true;
        }
        // 下标越界，不进行添加新的文字行
        if (textIndex < paramses.length) {
            if (paramses[textIndex].startTime <= count*DiaSiApplication.TIME_DELAYED) {
                int displayCount = (int)((paramses[textIndex].endTime - paramses[textIndex].startTime)/DiaSiApplication
                        .TIME_DELAYED);
                DialogueText dialogueText = new DialogueText(startX + DpiUtil.dipToPix(10),
                                textIndex * rowSpacing + DpiUtil.dipToPix(20) + startY,
                                paramses[textIndex].text, displayCount);
                dialogueText.setPlaySound(isPlaySound);
                dialogueTexts.add(dialogueText);
                textIndex++;
            }
        }
        for (DialogueText dialogueText : dialogueTexts) {
            dialogueText.logic();
        }
        count++;
    }

    public void setPlaySound(boolean playSound){
        this.isPlaySound = playSound;
    }
}
