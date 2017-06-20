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
    static private int intervalBeforeShoot = 30; // 枪移动到位置后等几帧才开枪
    private int intervalShoot = 20; //开枪持续的时间
    private int currentShootFrame = 0; // 当前处于开枪的第几帧
    private int state = COMING_IN; // 当前枪的状态
    private float bulletMax; // 子弹最粗 dp
    private float bulletMin; // 子弹最细 dp
    private float bulletLength; // 子弹长度 dp
    private boolean ifShoot; // 是否射击
    private Paint bulletPaint;


    public GunView(float startX, float startY, float targetX, float targetY, float angle) {
        this(startX, startY, targetX, targetY, angle, true);
    }

    public GunView(float startX, float startY, float targetX, float targetY, float angle, boolean ifShoot) {
        // 枪的运动时间固定为0.5s
        super(startX, startY, (targetX - startX) / 25,
                (targetY - startY) / 25);
        this.targetX = targetX;
        this.targetY = targetY;
        this.angle = angle;
        this.ifShoot = ifShoot;
        bulletMax = DpiUtil.dipToPix(30);
        bulletMin = DpiUtil.dipToPix(10);
        bulletLength = DpiUtil.dipToPix(5000);
        paint = new Paint();
        bulletPaint = new Paint();
        bulletPaint.setColor(Color.WHITE);
        bulletPaint.setStyle(Paint.Style.FILL);
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
                if (!ifShoot && currentShootFrame < intervalShoot) {
                    paint.setAlpha(255 - (int) (((float) (currentShootFrame)) / intervalShoot * 100));
                }
                canvas.drawBitmap(DiaSiApplication.getGunBitmap(), 0, 0, paint);
                // 画子弹
                if (ifShoot && currentShootFrame < intervalShoot) {
                    Path path = new Path();
                    float temp = (1f - (float) currentShootFrame / intervalShoot) * (bulletMax - bulletMin) / 2;
                    path.moveTo(0, -temp);
                    path.lineTo(0, bulletMin + temp);
                    // 这是画三角形的子弹，但由于碰撞编写难度较大，先用矩形代替
                    path.lineTo(-bulletLength, bulletMin / 2);
                    path.close();
                    bulletPaint.setAlpha(255 - (int) (((float) (currentShootFrame)) / intervalShoot * 100));
                    canvas.drawPath(path, bulletPaint);
                }
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
        } else {
            if (state != SHOOTING) {
                // 第一次进入射击状态
                state = SHOOTING;
                float speed = MathUtil.getXieBianLength(speedX, speedY);
                speedX = (float) (-Math.cos(MathUtil.angel2Radians(angle)) * speed);
                speedY = (float) (-Math.sin(MathUtil.angel2Radians(angle)) * speed);
            }
            if(ifShoot){
                currentX -= speedX;
                currentY -= speedY;
                if ((currentX > DiaSiApplication.getCanvasWidth() || currentX < 0
                             || currentY > DiaSiApplication.getCanvasHeight() || currentY < 0)
                        && currentShootFrame >= intervalShoot) {
                    isDead = true;
                }
            }else {
                if(currentShootFrame >= intervalShoot){
                    isDead = true;
                }
            }

            count++;
            currentShootFrame++;
        }

    }

    @Override
    protected boolean isCollisionWith(HeartShapeView heartShapeView) {

        // TODO: 2017/4/24 判断枪体的collision
        if(ifShoot){
            if(currentShootFrame>0){
                return CollisionUtil
                        .isCollisionWithBullet(heartShapeView.getCurrentX(), heartShapeView.getCurrentY(),
                                heartShapeView.getWidth(), heartShapeView.getHeight(), currentX, currentY, angle,
                                bulletMin + (bulletMax - bulletMin) /
                                        intervalShoot * (intervalBeforeShoot + intervalShoot - count));
            }else {
                return false;
            }

        }else {
            return CollisionUtil.isCollisionWithGun(heartShapeView.getCurrentX(), heartShapeView.getCurrentY(),
                    heartShapeView.getWidth(), heartShapeView.getHeight(),this);
        }


    }

    @Override
    protected void dealWithCollision(HeartShapeView heartShapeView) {
        heartShapeView.setBloodCurrent(heartShapeView.getBloodCurrent() - 1);
        heartShapeView.startTwinkle(15);
    }

    public static long getTimeBeforeShoot() {
        return (intervalBeforeShoot + 25) * DiaSiApplication.TIME_DELAYED;
    }

    public float getWidth(){
        return DiaSiApplication.getGunBitmap().getWidth();
    }

    public float getHeight(){
        return DiaSiApplication.getGunBitmap().getHeight();
    }
}
