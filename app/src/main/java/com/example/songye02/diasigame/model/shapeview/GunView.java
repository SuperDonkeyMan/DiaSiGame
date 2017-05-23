package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.utils.CollisionUtil;
import com.example.songye02.diasigame.utils.DpiUtil;
import com.example.songye02.diasigame.utils.MathUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by songye02 on 2017/4/24.
 */

public class GunView extends BaseShowableView {

    public static final int COMING_IN = 0;
    public static final int SHOOTING = 1;
    public static final int WAIING = 2;

    private float targetX;
    private float targetY;
    private float angle;
    private int count = 0; // 计时
    private int intervalBeforeShoot = 30; // 枪移动到位置后等几帧才开枪
    private int intervalShoot = 15; //开枪持续的时间
    private int state = COMING_IN; // 当前枪的状态
    private float bulletMax; // 子弹最粗 dp
    private float bulletMin; // 子弹最细 dp
    private float bulletLength; // 子弹长度 dp

    private boolean isGunOutSide; // 如果枪在外面，则省去了判断枪对heartView的判断

    public GunView(float startX, float startY, float targetX, float targetY, float angle) {
        // 枪的运动时间固定为1s
        super(startX, startY, (targetX - startX) / 500 * DiaSiApplication.TIME_DELAYED,
                (targetY - startY) / 500 * DiaSiApplication.TIME_DELAYED);
        this.targetX = targetX;
        this.targetY = targetY;
        this.angle = angle;
        bulletMax = DpiUtil.dipToPix(30);
        bulletMin = DpiUtil.dipToPix(15);
        bulletLength = DpiUtil.dipToPix(800);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        this.collisionable = true;
    }

    @Override
    public void draw(Canvas canvas) {
        switch (state) {
            case COMING_IN:
            case WAIING:
                canvas.save();
                canvas.translate(currentX, currentY);
                canvas.rotate(angle);
                canvas.drawBitmap(DiaSiApplication.getGunBitmap(), 0, 0, paint);
                canvas.restore();
                break;
            case SHOOTING:
                canvas.save();
                canvas.translate(currentX, currentY);
                canvas.rotate(angle);
                canvas.drawBitmap(DiaSiApplication.getGunBitmap(), 0, 0, paint);
                // 画子弹,先设子弹最粗8dp,最细6dp

                Path path = new Path();
                float temp =
                        ((bulletMax - bulletMin) / intervalShoot * (intervalBeforeShoot + intervalShoot - count))
                                / 2;
                path.moveTo(0, -temp);
                path.lineTo(0, bulletMin + temp);
                // 这是画三角形的子弹，但由于碰撞编写难度较大，先用矩形代替
                path.lineTo(-bulletLength, bulletMin / 2);
                //                    path.lineTo(-bulletLength, bulletMin + temp);
                //                    path.lineTo(-bulletLength, -temp);
                path.close();
                paint.setAlpha(
                        255 - (int) (MathUtil.getDistance(currentX, currentY, targetX, targetY) / MathUtil.getDistance
                                (startX, startY, targetX, targetY) * 200));
                canvas.drawPath(path, paint);

                canvas.restore();
                break;
        }
    }

    @Override
    public void logic() {
        // 进入的状态 float不能用==，因此认为(currentX-targetX)>speedX时为运动状态
        if (((int) Math.abs(currentX - targetX) > (int) Math.abs(speedX) ||
                     (int) Math.abs(currentY - targetY) > (int) Math.abs(speedY)) && count == 0) {
            currentX += speedX;
            currentY += speedY;
        } else if (count < intervalBeforeShoot) {
            state = WAIING;
            count++;
        }
        // 射击的状态，枪的位置不动
        else {
            state = SHOOTING;
            currentX -= speedX;
            currentY -= speedY;
            //回到原处就死亡
            if ((int) Math.abs(currentX - startX) <= (int) Math.abs(speedX) &&
                    (int) Math.abs(currentY - startY) <= (int) Math.abs(speedY)) {
                isDead = true;
            }
            count++;
        }

    }


    @Override
    protected boolean isCollisionWith(HeartShapeView heartShapeView) {


        // TODO: 2017/4/24 判断枪体的collision

        return CollisionUtil
                .isCollisionWithBullet(heartShapeView.getCurrentX(), heartShapeView.getCurrentY(),
                        heartShapeView.getWidth(), heartShapeView.getHeight(), currentX, currentY, angle,
                        bulletMin + (bulletMax - bulletMin) /
                                intervalShoot * (intervalBeforeShoot + intervalShoot - count));
    }

    @Override
    protected void dealWithCollision(HeartShapeView heartShapeView) {
        heartShapeView.setBloodCurrent(heartShapeView.getBloodCurrent() - 1);
    }

    public void setIsGunOutside(boolean isGunOutSide) {
        this.isGunOutSide = isGunOutSide;
    }
}
