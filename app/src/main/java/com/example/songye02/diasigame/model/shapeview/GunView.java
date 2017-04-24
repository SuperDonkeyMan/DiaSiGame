package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.model.BaseMoveableView;
import com.example.songye02.diasigame.model.Collisionable;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by songye02 on 2017/4/24.
 */

public class GunView extends BaseMoveableView implements Collisionable {

    public static final int COMING_IN = 0;
    public static final int SHOOTING = 1;
    public static final int COMING_OUT = 2;

    private float targetX;
    private float targetY;
    private float angle;
    private int count = 0; // 计时
    private int intervalStop = 50; //枪移动到指定位置停留的时间帧数
    private int intervalBeforeShoot = 10; // 枪移动到位置后等几帧才开枪
    private int intervalShoot = 30; //开枪持续的时间
    private int state = COMING_IN; // 当前枪的状态
    private float bulletMax; // 子弹最粗 dp
    private float bulletMin; // 子弹最细 dp
    private float bulletLength; // 子弹长度 dp

    public GunView(float startX, float startY, float targetX, float targetY, float angle) {
        // 枪的运动时间固定为1s
        super(startX, startY, (targetX - startX) / 1000 * DiaSiApplication.TIME_DELAYED,
                (targetY - startY) / 1000 * DiaSiApplication.TIME_DELAYED);
        this.targetX = targetX;
        this.targetY = targetY;
        this.angle = angle;
        bulletMax = DpiUtil.dipToPix(30);
        bulletMin = DpiUtil.dipToPix(20);
        bulletLength = DpiUtil.dipToPix(800);
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    @Override
    public void draw(Canvas canvas) {
        switch (state) {
            case COMING_IN:
            case COMING_OUT:
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
                if (count >= intervalBeforeShoot && count < intervalBeforeShoot + intervalShoot) {
                    Path path = new Path();
                    float temp =
                            ((bulletMax - bulletMin) / intervalShoot * (intervalBeforeShoot + intervalShoot - count))
                                    / 2;
                    path.moveTo(0, -temp);
                    path.lineTo(0, bulletMin + temp);
                    path.lineTo(-bulletLength, bulletMin / 2);
                    path.close();
                    canvas.drawPath(path, paint);
                }
                canvas.restore();
                break;
        }
    }

    @Override
    public void logic() {
        // 进入的状态 float不能用==，因此认为(currentX-targetX)>speedX时为运动状态
        if (Math.abs(currentX - targetX) > speedX && count == 0) {
            currentX += speedX;
            currentY += speedY;
        }
        // 射击的状态，枪的位置不动
        else if (currentX - targetX <= speedX && count < intervalStop) {
            state = SHOOTING;
            count++;
        }
        // 退出的状态
        else {
            currentX -= speedX;
            currentY -= speedY;
            state = COMING_OUT;
            //回到原处就死亡
            if(Math.abs(currentX-startX)<=speedX){
                isDead = true;
            }
        }
    }

    @Override
    public boolean collisonWith(HeartShapeView view) {
        return false;
    }
}
