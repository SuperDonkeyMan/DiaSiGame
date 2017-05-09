package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseShowableView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by songye02 on 2017/5/8.
 */

public class PortraitView extends BaseShowableView {

    public static final int BMP_LIUXING = 0; // 刘醒
    public static final int BMP_FEIFANGE = 1; // 非凡哥

    private static final int STATE_ISMOVING = 0; // 正在位移
    private static final int STATE_MOVESTART = 1;// 刚刚开始位移
    private static final int STATE_MOVEFINISH = 2;// 刚刚开始位移
    private int count;
    private int frameCount;

    private Bitmap portraitBmp;
    private boolean isDismiss;
    private boolean isMoving;
    private int moveState;

    public PortraitView(float startX, float startY) {
        super(startX, startY, 0, 0);
        portraitBmp = DiaSiApplication.getFeifanBitmap();
        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        if(!isDismiss){
            canvas.drawBitmap(portraitBmp,currentX,currentY,paint);
        }
    }

    @Override
    public void logic() {
        switch (moveState){
            case STATE_MOVESTART:
                count = 0;
                moveState = STATE_ISMOVING;
                break;
            case STATE_ISMOVING:
                currentX += speedX;
                currentY += speedY;
                count++;
                if(count == frameCount){
                    moveState = STATE_MOVEFINISH;
                }
                break;
            case STATE_MOVEFINISH:
                break;
            default:
                break;
        }
    }

    public void move(float startX, float startY, float endX, float endY, int frameCount){
        moveState = STATE_MOVESTART;
        this.currentX = startX;
        this.currentY = startY;
        this.frameCount = frameCount;
        speedX = (endX - startX) / frameCount;
        speedY = (endY - startY) / frameCount;
    }

    public void setPortraitBmp(int index){
        switch (index){
            case BMP_LIUXING:
                portraitBmp = DiaSiApplication.getLiuxingBitmap();
                break;
            case BMP_FEIFANGE:
                portraitBmp = DiaSiApplication.getFeifanBitmap();
                break;
            default:
                break;
        }
    }

}
