package com.example.songye02.diasigame.model.textview;

import java.util.List;

import com.example.songye02.diasigame.model.Deadable;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by songye02 on 2017/5/15.
 */

public class MenuView implements Showable, Deadable {

    private boolean isDead;
    private boolean isDismiss = false;
    private int dismissFrameCount = 25;
    private int dismissFrameIndex = 0;
    private int currentIndex = 0; // 当前位置指针 从零开始
    private int rows = 3; // 一列多少行 默认三行
    private int columns; // 一行多少列
    private float paddingVertical; // 竖直方向与边框的距离
    private float paddingHorizontal;// 水平方向与边框的距离
    private float heightText;
    private float widthText;

    private List<String> texts;
    private float boundaryX;
    private float boundaryY;
    private float boundaryW;
    private float boundaryH;
    private HeartShapeView heartShapeView;
    private Paint paint;
    private Paint.FontMetrics metrics;

    public MenuView(List<String> texts, float boundaryX, float boundaryY, float boundaryW, float boundaryH,
                    HeartShapeView heartShapeView) {
        this.texts = texts;
        this.boundaryX = boundaryX;
        this.boundaryY = boundaryY;
        this.boundaryW = boundaryW;
        this.boundaryH = boundaryH;
        this.heartShapeView = heartShapeView;

        columns = texts.size() / rows;
        if (texts.size() % rows != 0) {
            columns++;
        }
        paddingVertical = DpiUtil.dipToPix(20);
        paddingHorizontal = DpiUtil.dipToPix(50);

        paint = new Paint();
        paint.setTextSize(DpiUtil.dipToPix(20));
        paint.setColor(Color.WHITE);
        metrics = paint.getFontMetrics();

        heightText = (boundaryH - 2 * paddingVertical) / rows;
        widthText = (boundaryW - 2 * paddingHorizontal) / columns;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void draw(Canvas canvas) {
        if(!isDismiss){
            for (int i = 0; i < texts.size(); i++) {
                int rowIndex = i % rows;
                int columnIndex = i / rows;
                float x = boundaryX + paddingHorizontal + columnIndex * widthText;
                float y = boundaryY + paddingVertical + rowIndex * heightText;
                canvas.drawText(texts.get(i), x, y - metrics.top, paint);
                if (i == currentIndex) {
                    heartShapeView.setCurrentX(x - heartShapeView.getWidth() - DpiUtil.dipToPix(10));
                    heartShapeView.setCurrentY(y + (metrics.bottom - metrics.top - heartShapeView.getHeight()) / 2);
                }
            }
        }
    }

    @Override
    public void logic() {
        if(isDismiss){
            if(dismissFrameIndex<dismissFrameCount){
                dismissFrameIndex++;
            }else {
                isDismiss = false;
                heartShapeView.setDismiss(false);
            }
        }
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
        columns = texts.size() / rows;
        if (texts.size() % rows != 0) {
            columns++;
        }
        heightText = (boundaryH - 2 * paddingVertical) / rows;
        widthText = (boundaryW - 2 * paddingHorizontal) / columns;
    }

    public void setDismiss(int dismissFrameCount){
        this.dismissFrameCount = dismissFrameCount;
        dismissFrameIndex = 0;
        isDismiss = true;
    }

    public void lastIndex() {
        if (currentIndex > 0) {
            currentIndex--;
        }
    }

    public void nextIndex() {
        if (currentIndex < texts.size() - 1) {
            currentIndex++;
        }
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

}
