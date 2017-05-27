package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by songye02 on 2017/5/25.
 */

public class SpineView extends BaseShowableView {

    public static final int SPINE_DIRECTION_DOWN = 0;
    public static final int SPINE_DIRECTION_RIGHT = 1;
    public static final int SPINE_DIRECTION_LEFT = 2;
    public static final int SPINE_DIRECTION_UP = 3;


    private float spineHeight = DpiUtil.dipToPix(20);
    private float spineWidth = DpiUtil.dipToPix(10);
    private float spineNum ;
    private int spineDirection;

    private int count=0;
    private int finishCount = 20; // 刺到最长的时候需要的帧数
    private float currentLength = 0; // 当前刺的长度

    public SpineView(float startX, float startY, float totalLength, int spineDirection) {
        super(startX, startY, 0, 0);
        this.spineDirection = spineDirection;
        spineNum = totalLength/spineWidth;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        this.collisionable = true;
    }

    @Override
    public void draw(Canvas canvas) {
        float start = spineWidth/2;
        float shift1;
        float shift2;
        float shift3 = 0;
        Path path = new Path();

        for(int i = 0;i<spineNum;i++){
            // 刺伸出的过程
            if(count<finishCount){
                shift1 = start+i*spineWidth-((float) count)/finishCount*spineWidth/2;
                shift2 = start+i*spineWidth+((float) count)/finishCount*spineWidth/2;
                shift3 = ((float) count)/finishCount*spineHeight;
            }else {
                // 刺缩回去的过程
                shift1 = start+i*spineWidth-((float) (2*finishCount-count))/finishCount*spineWidth/2;
                shift2 = start+i*spineWidth+((float) (2*finishCount-count))/finishCount*spineWidth/2;
                shift3 = ((float) (2*finishCount-count))/finishCount*spineHeight;
            }
            switch (spineDirection){
                case SPINE_DIRECTION_DOWN:
                    path.moveTo(startX+shift1,startY);
                    path.lineTo(startX+shift2,startY);
                    path.lineTo(startX+start+i*spineWidth,startY+shift3);
                    path.close();
                    canvas.drawPath(path,paint);
                    break;
                case SPINE_DIRECTION_LEFT:
                    path.moveTo(startX,startY+shift1);
                    path.lineTo(startX,startY+shift2);
                    path.lineTo(startX+shift3,startY+start+i*spineWidth);
                    path.close();
                    canvas.drawPath(path,paint);
                    break;
                case SPINE_DIRECTION_RIGHT:
                    path.moveTo(startX,startY+shift1);
                    path.lineTo(startX,startY+shift2);
                    path.lineTo(startX+shift3,startY-start+i*spineWidth);
                    path.close();
                    canvas.drawPath(path,paint);
                    break;
                case SPINE_DIRECTION_UP:
                    path.moveTo(startX+shift1,startY);
                    path.lineTo(startX+shift2,startY);
                    path.lineTo(startX+start+i*spineWidth,startY-shift3);
                    path.close();
                    canvas.drawPath(path,paint);
                    break;
                default:
                    break;
            }
        }
        currentLength = shift3;
    }

    @Override
    public void logic() {
        if(count>2*finishCount){
            isDead = true;
            return;
        }
        count++;
    }

    @Override
    protected boolean isCollisionWith(HeartShapeView heartShapeView) {
        boolean result = false;
        switch (spineDirection){
            case SPINE_DIRECTION_DOWN:
                result = heartShapeView.getCurrentY()<startY+currentLength;
                break;
            case SPINE_DIRECTION_LEFT:
                result = heartShapeView.getBoundaryX()<startX+currentLength;
                break;
            case SPINE_DIRECTION_RIGHT:
                result = heartShapeView.getCurrentX()+heartShapeView.getWidth()>startX-currentLength;
                break;
            case SPINE_DIRECTION_UP:
                result = heartShapeView.getCurrentY()+heartShapeView.getHeight()>startY-currentLength;
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void dealWithCollision(HeartShapeView heartShapeView) {
        heartShapeView.setBloodCurrent(heartShapeView.getBloodCurrent() - 1);
        heartShapeView.startTwinkle(15);
    }
}
