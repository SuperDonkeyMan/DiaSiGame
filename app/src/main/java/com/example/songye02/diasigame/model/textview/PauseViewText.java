package com.example.songye02.diasigame.model.textview;

import com.example.songye02.diasigame.callback.PauseTextViewDeadCallback;

/**
 * Created by songye02 on 2017/5/3.
 * 在文字运动前后发生暂停 frameCount是运动的帧数
 */

public class PauseViewText extends CollisionNormalTextView {

    protected int pauseBefore;// 运动前暂停多少帧
    protected int pauseAfter;// 运动后暂停多少帧
    private PauseTextViewDeadCallback callback = null;

    public PauseViewText(float startX, float startY, float endX, float endY, int pauseBefore, int frameCount,
                         int pauseAfter, String text, int textOrientation) {
        super(startX, startY, endX, endY, frameCount, text, textOrientation);
        this.pauseBefore = pauseBefore;
        this.pauseAfter = pauseAfter;
    }

    @Override
    public void logic() {
        if (count < pauseBefore) {
            currentX = startX;
            currentY = startY;
        } else if (count < pauseBefore + frameCount) {
            currentX += speedX;
            currentY += speedY;
        } else {
            currentX = endX;
            currentY = endY;
        }
        count++;
        if (count > pauseBefore + pauseAfter + frameCount) {
            isDead = true;
        }
    }

    public String getText(){
        return text;
    }
}
