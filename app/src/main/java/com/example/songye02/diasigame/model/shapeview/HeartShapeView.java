package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.timecontroller.TimeController;
import com.example.songye02.diasigame.utils.DpiUtil;
import com.example.songye02.diasigame.utils.GameStateUtil;
import com.example.songye02.diasigame.utils.MathUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by songye02 on 2017/4/19.
 */

public class HeartShapeView extends BaseShowableView {
    public static final int HEART_MODE_NORMAL = 0;
    public static final int HEART_MODE_GRAVITY = 1;
    public static final int HEART_MODE_MOVE = 2;
    public static final int GRAVITY_LEFT = 0;
    public static final int GRAVITY_RIGHT = 1;
    public static final int GRAVITY_TOP = 2;
    public static final int GRAVITY_BOTTOM = 3;
    private int heartMode = HEART_MODE_NORMAL;
    private int gravityOrientation = GRAVITY_BOTTOM;
    private boolean isDismiss = false;
    private boolean isInAir = false;
    private float g = 2;
    private float v1 = 15;
    private float v2 = 30;

    private boolean isHeartShowable = true;

    private float heartWidth = DpiUtil.dipToPix(12); // 心形的长宽
    private float heartHeight = DpiUtil.dipToPix(9);
    private float mWidth; // 实际的长宽 由于存在旋转的情况，可能存在长宽互换的情况
    private float mHeight;
    private int mColor;
    private float speedMax;
    //当前手柄的参数
    private float currentRad;
    private float currentDistance;

    private float boundaryX;
    private float boundaryY;
    private float boundaryW;
    private float boundaryH;
    private float boundaryStrokeWidth = 3;
    private float bottomSpace = 55;
    private Paint boundaryPaint;

    private int bloodMax;
    private int bloodCurrent;

    // HEART_MODE_MOVE模式需要的参数
    private int previousHeartMode = heartMode;
    private float endX;
    private float endY;
    private OnHeartMoveFinishListener onHeartMoveFinishListener;
    // 闪烁相关参数
    private boolean isTwikle = false;
    private int twikleCount = 0;
    private int twikleFrames = 25; // 持续闪烁的帧数
    // 边框扩大相关参数
    private boolean isBoundaryChanging = false;
    private float targetBoundaryX;
    private float targetBoundaryY;
    private float targetBoundaryW;
    private float targetBoundaryH;
    private float previousBoundaryX;
    private float previousBoundaryY;
    private float previousBoundaryW;
    private float previousBoundaryH;
    private int boundaryTotalCount = 0;
    private int boundaryCount = 0;

    public HeartShapeView(float startX, float startY, float speedMax) {
        super(startX, startY, 0, 0);
        mWidth = heartWidth;
        mHeight = heartHeight;
        mColor = Color.RED;
        this.speedMax = speedMax;

        paint = new Paint();
        paint.setColor(mColor);
        // 边框画笔
        boundaryPaint = new Paint();
        boundaryPaint.setColor(Color.WHITE);
        boundaryPaint.setStyle(Paint.Style.STROKE);
        boundaryPaint.setStrokeWidth(DpiUtil.dipToPix(boundaryStrokeWidth));

    }

    public void draw(Canvas canvas) {
        if (!isDismiss) {
            if (isHeartShowable) {
                if (isTwikle) {
                    if (twikleCount % 2 != 0) {
                        drawHeartShape(canvas);
                    }
                } else {
                    drawHeartShape(canvas);
                }
            }
        }

        // 画边界
        canvas.drawRect(boundaryX, boundaryY, boundaryX + boundaryW, boundaryY + boundaryH, boundaryPaint);

        // 画血条 血条长120dp，宽15，顶部距离画布底70
        float bloodY = DiaSiApplication.getCanvasHeight() - DpiUtil.dipToPix(bottomSpace);
        float bloodX = DiaSiApplication.getCanvasWidth() / 2 - DpiUtil.dipToPix(60);
        float bloodW = DpiUtil.dipToPix(120);
        float bloodH = DpiUtil.dipToPix(15);
        Paint bloodPaint = new Paint();
        bloodPaint.setColor(Color.YELLOW);
        canvas.drawRect(bloodX, bloodY, bloodX + bloodW, bloodY + bloodH, bloodPaint);
        bloodPaint.setColor(Color.RED);
        canvas.drawRect(bloodX + bloodW - (float) (bloodMax - bloodCurrent) / bloodMax * bloodW, bloodY,
                bloodX + bloodW, bloodY + bloodH, bloodPaint);
        // 画文字
        bloodPaint.setColor(Color.WHITE);
        bloodPaint.setTextSize(DpiUtil.spToPix(12));
        Paint.FontMetrics fontMetrics = bloodPaint.getFontMetrics();
        if (DiaSiApplication.gameState == GameStateUtil.GAME_STATE_MENU) {
            String name;
            // 菜单是写人名
            name = DiaSiApplication.currentPerson == GameStateUtil.PERSON_FEIFAN ? "FEIFAN" : "LIUXING";
            canvas.drawText(name, bloodX - DpiUtil.dipToPix(100), bloodY - fontMetrics.ascent, bloodPaint);
        } else if (DiaSiApplication.gameState == GameStateUtil.GAME_STATE_GAMING) {
            // 游戏中就写时间
            long timeMillis = System.currentTimeMillis() - TimeController.startTime - TimeController.pauseTime;
            long minute = timeMillis / 1000 / 60;
            long second = timeMillis / 1000 % 60;
            canvas.drawText("TIME " + minute + ":" + second,
                    bloodX - DpiUtil.dipToPix(100), bloodY - fontMetrics.ascent, bloodPaint);
        }
        canvas.drawText("HP", bloodX - DpiUtil.dipToPix(20), bloodY - fontMetrics.ascent, bloodPaint);
        canvas.drawText("KR " + bloodCurrent + " / " + bloodMax,
                bloodX + bloodW + DpiUtil.dipToPix(10), bloodY - fontMetrics.ascent, bloodPaint);
    }

    private void drawHeartShape(Canvas canvas) {
        if (heartMode == HEART_MODE_GRAVITY) {
            canvas.save();
            switch (gravityOrientation) {
                case GRAVITY_LEFT:
                    canvas.translate(currentX + heartHeight, currentY);
                    canvas.rotate(90);
                    break;
                case GRAVITY_TOP:
                    canvas.translate(currentX + heartWidth, currentY + heartHeight);
                    canvas.rotate(180);
                    break;
                case GRAVITY_RIGHT:
                    canvas.translate(currentX, currentY + heartWidth);
                    canvas.rotate(270);
                    break;
                default:
                    canvas.translate(currentX, currentY);
                    break;
            }
        } else {
            canvas.save();
            canvas.translate(currentX, currentY);
        }
        Path path = new Path();
        path.moveTo((float) (0.5 * heartWidth), (float) (0.17 * heartHeight));
        path.cubicTo((float) (0.15 * heartWidth), (float) (-0.35 * heartHeight), (float) (-0.4 * heartWidth),
                (float) (0.45 * heartHeight), (float) (0.5 * heartWidth), heartHeight);
        path.moveTo((float) (0.5 * heartWidth), heartHeight);
        path.cubicTo((float) (heartWidth + 0.4 * heartWidth), (float) (0.45 * heartHeight),
                (float) (heartWidth - 0.15 * heartWidth),
                (float) (-0.35 * heartHeight), (float) (0.5 * heartWidth), (float) (0.17 * heartHeight));
        path.close();
        canvas.drawPath(path, paint);
        //画边框
        Paint rangePaint = new Paint();
        rangePaint.setColor(Color.RED);
        rangePaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, heartWidth, heartHeight, rangePaint);
        canvas.restore();
    }

    @Override
    public void logic() {
        switch (heartMode) {
            case HEART_MODE_NORMAL:
                if (currentDistance != 0) {
                    speedX = (float) (speedMax * Math.cos(currentRad));
                    speedY = (float) (speedMax * Math.sin(currentRad));
                    currentX += speedX;
                    currentY += speedY;
                    // 设定范围
                    if (currentX < boundaryX + boundaryStrokeWidth) {
                        currentX = boundaryX + boundaryStrokeWidth;
                    }
                    if (currentX > boundaryX + boundaryW - boundaryStrokeWidth - mWidth) {
                        currentX = boundaryX + boundaryW - boundaryStrokeWidth - mWidth;
                    }
                    if (currentY < boundaryY + boundaryStrokeWidth) {
                        currentY = boundaryY + boundaryStrokeWidth;
                    }
                    if (currentY > boundaryY + boundaryH - boundaryStrokeWidth - mHeight) {
                        currentY = boundaryY + boundaryH - boundaryStrokeWidth - mHeight;
                    }
                }
                break;
            case HEART_MODE_GRAVITY:
                switch (gravityOrientation) {
                    case GRAVITY_BOTTOM:
                        // X不受影响
                        if (currentDistance != 0) {
                            speedX = (float) (speedMax * Math.cos(currentRad));
                            currentX += speedX;
                        }
                        // Y受影响
                        if (isInAir) {
                            speedY -= g;
                        }
                        currentY -= speedY;
                        // 设定范围
                        if (currentX < boundaryX + boundaryStrokeWidth) {
                            currentX = boundaryX + boundaryStrokeWidth;
                        }
                        if (currentX > boundaryX + boundaryW - boundaryStrokeWidth - mWidth) {
                            currentX = boundaryX + boundaryW - boundaryStrokeWidth - mWidth;
                        }
                        if (currentY < boundaryY + boundaryStrokeWidth) {
                            currentY = boundaryY + boundaryStrokeWidth;
                        }
                        if (currentY > boundaryY + boundaryH - boundaryStrokeWidth - mHeight) {
                            currentY = boundaryY + boundaryH - boundaryStrokeWidth - mHeight;
                            isInAir = false;
                        }
                        break;
                    case GRAVITY_TOP:
                        // X不受影响
                        if (currentDistance != 0) {
                            speedX = (float) (speedMax * Math.cos(currentRad));
                            currentX += speedX;
                        }
                        // Y受影响
                        if (isInAir) {
                            speedY -= g;
                        }
                        currentY += speedY;
                        // 设定范围
                        if (currentX < boundaryX + boundaryStrokeWidth) {
                            currentX = boundaryX + boundaryStrokeWidth;
                        }
                        if (currentX > boundaryX + boundaryW - boundaryStrokeWidth - mWidth) {
                            currentX = boundaryX + boundaryW - boundaryStrokeWidth - mWidth;
                        }
                        if (currentY < boundaryY + boundaryStrokeWidth) {
                            currentY = boundaryY + boundaryStrokeWidth;
                            isInAir = false;
                        }
                        if (currentY > boundaryY + boundaryH - boundaryStrokeWidth - mHeight) {
                            currentY = boundaryY + boundaryH - boundaryStrokeWidth - mHeight;
                        }
                        break;
                    case GRAVITY_LEFT:
                        // Y不受影响
                        if (currentDistance != 0) {
                            speedY = (float) (speedMax * Math.sin(currentRad));
                            currentY += speedY;
                        }
                        // X受影响
                        if (isInAir) {
                            speedX -= g;
                        }
                        currentX += speedX;
                        // 设定范围
                        if (currentX < boundaryX + boundaryStrokeWidth) {
                            currentX = boundaryX + boundaryStrokeWidth;
                            isInAir = false;
                        }
                        if (currentX > boundaryX + boundaryW - boundaryStrokeWidth - mWidth) {
                            currentX = boundaryX + boundaryW - boundaryStrokeWidth - mWidth;
                        }
                        if (currentY < boundaryY + boundaryStrokeWidth) {
                            currentY = boundaryY + boundaryStrokeWidth;
                        }
                        if (currentY > boundaryY + boundaryH - boundaryStrokeWidth - mHeight) {
                            currentY = boundaryY + boundaryH - boundaryStrokeWidth - mHeight;
                        }
                        break;
                    case GRAVITY_RIGHT:
                        // Y不受影响
                        if (currentDistance != 0) {
                            speedY = (float) (speedMax * Math.sin(currentRad));
                            currentY += speedY;
                        }
                        // X受影响
                        if (isInAir) {
                            speedX -= g;
                        }
                        currentX -= speedX;
                        // 设定范围
                        if (currentX < boundaryX + boundaryStrokeWidth) {
                            currentX = boundaryX + boundaryStrokeWidth;
                        }
                        if (currentX > boundaryX + boundaryW - boundaryStrokeWidth - mWidth) {
                            currentX = boundaryX + boundaryW - boundaryStrokeWidth - mWidth;
                            isInAir = false;
                        }
                        if (currentY < boundaryY + boundaryStrokeWidth) {
                            currentY = boundaryY + boundaryStrokeWidth;
                        }
                        if (currentY > boundaryY + boundaryH - boundaryStrokeWidth - mHeight) {
                            currentY = boundaryY + boundaryH - boundaryStrokeWidth - mHeight;
                        }
                        break;
                }
                break;
            case HEART_MODE_MOVE:
                currentX += speedX;
                currentY += speedY;
                if (MathUtil.pointEquals(currentX, currentY, endX, endY, 1f)) {
                    heartMode = previousHeartMode;
                    onHeartMoveFinishListener.action(this);
                }
                break;
        }

        if (isTwikle) {
            if (twikleCount > twikleFrames) {
                isTwikle = false;
            } else {
                twikleCount++;
            }
        }

        // 处理边框动画
        if (isBoundaryChanging) {
            if (boundaryCount < boundaryTotalCount) {
                float XSpeed = (targetBoundaryX - previousBoundaryX) / boundaryTotalCount;
                float YSpeed = (targetBoundaryY - previousBoundaryY) / boundaryTotalCount;
                float WSpeed = (targetBoundaryW - previousBoundaryW) / boundaryTotalCount;
                float HSpeed = (targetBoundaryH - previousBoundaryH) / boundaryTotalCount;
                boundaryX += XSpeed;
                boundaryY += YSpeed;
                boundaryW += WSpeed;
                boundaryH += HSpeed;
                boundaryCount++;
            } else {
                isBoundaryChanging = false;
                boundaryCount = 0;
                boundaryX = targetBoundaryX;
                boundaryY = targetBoundaryY;
                boundaryW = targetBoundaryW;
                boundaryH = targetBoundaryH;
            }
        }

    }

    public void setCurrentSpeed(float rad, float distance) {
        currentRad = rad;
        currentDistance = distance;
    }

    public void onSmallJumpClick() {
        if (heartMode == HEART_MODE_GRAVITY) {
            if (!isInAir) {
                isInAir = true;
                if (gravityOrientation == GRAVITY_TOP || gravityOrientation == GRAVITY_BOTTOM) {
                    speedY = v1;
                } else {
                    speedX = v1;
                }
            }
        }
    }

    public void onBigJumpClick() {
        if (heartMode == HEART_MODE_GRAVITY) {
            if (!isInAir) {
                isInAir = true;
                if (gravityOrientation == GRAVITY_TOP || gravityOrientation == GRAVITY_BOTTOM) {
                    speedY = v2;
                } else {
                    speedX = v2;
                }
            }
        }
    }
    // 这句话有重置重力状态的作用，因此转换重力方向，要重新调用
    public void setHeartMode(int heartMode) {
        this.heartMode = heartMode;
        if (heartMode == HEART_MODE_GRAVITY) {
            isInAir = true;
            speedX = 0;
            speedY = 0;
        }
    }

    public void setGravityOrientation(int gravityOrientation) {
        this.gravityOrientation = gravityOrientation;
        if (gravityOrientation == GRAVITY_LEFT || gravityOrientation == GRAVITY_RIGHT) {
            mWidth = heartHeight;
            mHeight = heartWidth;
        } else {
            mWidth = heartWidth;
            mHeight = heartHeight;
        }
    }

    public void setCurrentX(float currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(float currentY) {
        this.currentY = currentY;
    }

    public float getWidth() {
        return mWidth;
    }

    public float getHeight() {
        return mHeight;
    }

    public void setBoundary(float boundaryX, float boundaryY, float boundaryW, float boundaryH) {
        this.boundaryX = boundaryX;
        this.boundaryY = boundaryY;
        this.boundaryW = boundaryW;
        this.boundaryH = boundaryH;
    }

    // 动画方式改变边框大小
    public void changeBoundaryWithAmination(float targetBoundaryX, float targetBoundaryY,
                                            float targetBoundaryW, float targetBoundaryH, int boundaryTotalCount) {
        this.targetBoundaryX = targetBoundaryX;
        this.targetBoundaryY = targetBoundaryY;
        this.targetBoundaryW = targetBoundaryW;
        this.targetBoundaryH = targetBoundaryH;
        this.boundaryTotalCount = boundaryTotalCount;
        this.previousBoundaryX = boundaryX;
        this.previousBoundaryY = boundaryY;
        this.previousBoundaryW = boundaryW;
        this.previousBoundaryH = boundaryH;
        isBoundaryChanging = true;
    }

    public int getBloodMax() {
        return bloodMax;
    }

    public int getBloodCurrent() {
        return bloodCurrent;
    }

    public void setBloodMax(int bloodMax) {
        this.bloodMax = bloodMax;
    }

    public void setBloodCurrent(int bloodCurrent) {
        this.bloodCurrent = bloodCurrent;
    }

    public void setHeartShowable() {

    }

    public float getBoundaryX() {
        if(isBoundaryChanging){
            return targetBoundaryX;
        }else {
            return boundaryX;
        }
    }

    public float getBoundaryY() {
        if(isBoundaryChanging){
            return targetBoundaryY;
        }else {
            return boundaryY;
        }
    }

    public float getBoundaryW() {
        if(isBoundaryChanging){
            return targetBoundaryW;
        }else {
            return boundaryW;
        }
    }

    public float getBoundaryH() {
        if(isBoundaryChanging){
            return targetBoundaryH;
        }else {
            return boundaryH;
        }
    }

    public float getBoundaryR(){
        return getBoundaryX()+getBoundaryW();
    }

    public float getBoundaryB(){
        return getBoundaryY()+getBoundaryH();
    }

    public void startTwinkle(int twikleFrames) {
        twikleCount = 0;
        isTwikle = true;
        this.twikleFrames = twikleFrames;
    }

    public void startMove(float targetX, float targetY, int count) {
        previousHeartMode = heartMode;
        heartMode = HEART_MODE_MOVE;
        endX = targetX;
        endY = targetY;
        speedX = (targetX - currentX) / count;
        speedY = (targetY - currentY) / count;
    }

    public void startMove(float targetX, float targetY, int count, OnHeartMoveFinishListener listener) {
        startMove(targetX, targetY, count);
        this.onHeartMoveFinishListener = listener;
    }

    public void setDismiss(boolean dismiss) {
        this.isDismiss = dismiss;
    }

    public interface OnHeartMoveFinishListener {
        void action(HeartShapeView heartShapeView);
    }

    public void setHeartCenter() {
        setCurrentX(getBoundaryX() + getBoundaryW() / 2);
        setCurrentY(getBoundaryY() + getBoundaryH() / 2);
    }

}
