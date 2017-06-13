package com.example.songye02.diasigame.model.textview;

/**
 * Created by songye02 on 2017/5/3.
 * <p>
 * 这里的frameCount只是用来计算初始速度的，并不是实际运动的帧数
 */

public class PauseSpeedUpTextView extends PauseViewText {

    private float speedUp = 8;
    private float speedXTemp;
    private float speedYTemp;
    private int countAfter = 0;

    public PauseSpeedUpTextView(float startX, float startY, float endX, float endY, int pauseBefore, int frameCount,
                                int pauseAfter, String text, int textOrientation) {
        super(startX, startY, endX, endY, pauseBefore, frameCount, pauseAfter, text, textOrientation);
        speedXTemp = speedX;
        speedYTemp = speedY;
    }

    // 设置加速度的
    public void setSpeedUp(float speedUp) {
        this.speedUp = speedUp;
    }

    @Override
    public void logic() {
        if (count < pauseBefore) {
            currentX = startX;
            currentY = startY;
        } else if ((int) Math.abs(currentX - endX) > (int) Math.abs(speedX) ||
                (int) Math.abs(currentY - endY) > (int) Math.abs(speedY)) {
            // 每5帧加速一次
            if ((count - pauseBefore) % 5 == 0) {
                speedX += speedXTemp / (Math.abs(speedXTemp) + Math.abs(speedY)) * speedUp;
                speedY += speedYTemp / (Math.abs(speedXTemp) + Math.abs(speedY)) * speedUp;
                speedXTemp = speedX;
                speedYTemp = speedY;
            }
            currentX += speedX;
            currentY += speedY;
        } else {
            currentX = endX;
            currentY = endY;
        }
        count++;
        if ((int) Math.abs(currentX - endX) <= (int) Math.abs(speedX) &&
                (int) Math.abs(currentY - endY) <= (int) Math.abs(speedY)) {
            if (countAfter >= pauseAfter) {
                isDead = true;
            }
            countAfter++;
        }
    }


}
