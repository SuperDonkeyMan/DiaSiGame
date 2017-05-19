package com.example.songye02.diasigame.model.shapeview;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.R;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

/**
 * Created by songye02 on 2017/5/11.
 */

public class BottomMenuView implements Showable {

    private float textSize = DpiUtil.dipToPix(20);
    private float recWidth = DpiUtil.dipToPix(75);
    private float recHeight = DpiUtil.dipToPix(25);
    private float bottomSpace = DpiUtil.dipToPix(5); // 距离底部距离
    private Bitmap bmpFight;
    private Bitmap bmpAction;
    private Bitmap bmpThings;
    private Bitmap bmpMercy;
    private Paint paint;
    private Paint textPaint;

    public BottomMenuView() {
        bmpFight = getBmp(R.drawable.fight);
        bmpAction = getBmp(R.drawable.action);
        bmpThings = getBmp(R.drawable.things);
        bmpMercy = getBmp(R.drawable.mercy);

        paint = new Paint();
        paint.setColor(Color.rgb(255, 128, 0));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(7);

        textPaint = new TextPaint();
        textPaint.setTextSize(textSize);
        textPaint.setColor(Color.rgb(255, 128, 0));
        Typeface font = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD);
        textPaint.setTypeface(font);
        textPaint.setFakeBoldText(true);

    }

    private Bitmap getBmp(int resultId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        Bitmap gunTemp = BitmapFactory.decodeResource(DiaSiApplication.getInstance().getResources(), resultId);
        Matrix matrix = new Matrix();
        // 设置想要的大小
        int newWidth = (int) DpiUtil.dipToPix(25);
        int newHeight = (int) DpiUtil.dipToPix(25);
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / gunTemp.getWidth();
        float scaleHeight = ((float) newHeight) / gunTemp.getHeight();
        matrix.postScale(Math.min(scaleWidth, scaleHeight), Math.min(scaleWidth, scaleHeight));
        return Bitmap.createBitmap(gunTemp, 0, 0, gunTemp.getWidth(), gunTemp.getHeight(), matrix, true);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        // translate是为了视图居中
        canvas.translate((DiaSiApplication.getCanvasWidth() / 6 - recWidth) / 2, 0);
        // 画一个方形块
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        canvas.drawRect(DiaSiApplication.getCanvasWidth() / 6,
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight,
                DiaSiApplication.getCanvasWidth() / 6 + recWidth,
                DiaSiApplication.getCanvasHeight() - bottomSpace, paint);
        canvas.drawBitmap(bmpFight, DiaSiApplication.getCanvasWidth() / 6,
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight, paint);
        canvas.drawText("战斗", DiaSiApplication.getCanvasWidth() / 6 + bmpFight.getWidth(),
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight - fontMetrics.top, textPaint);
        // 画一个方形块
        canvas.drawRect(DiaSiApplication.getCanvasWidth() / 6 * 2,
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight,
                DiaSiApplication.getCanvasWidth() / 6 * 2 + recWidth,
                DiaSiApplication.getCanvasHeight() - bottomSpace, paint);
        canvas.drawBitmap(bmpAction, DiaSiApplication.getCanvasWidth() / 6 * 2,
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight, paint);
        canvas.drawText("行动", DiaSiApplication.getCanvasWidth() / 6 * 2 + bmpAction.getWidth(),
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight - fontMetrics.top, textPaint);
        // 画一个方形块
        canvas.drawRect(DiaSiApplication.getCanvasWidth() / 6 * 3,
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight,
                DiaSiApplication.getCanvasWidth() / 6 * 3 + recWidth,
                DiaSiApplication.getCanvasHeight() - bottomSpace, paint);
        canvas.drawBitmap(bmpThings, DiaSiApplication.getCanvasWidth() / 6 * 3,
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight, paint);
        canvas.drawText("物品", DiaSiApplication.getCanvasWidth() / 6 * 3 + bmpThings.getWidth(),
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight - fontMetrics.top, textPaint);
        // 画一个方形块
        canvas.drawRect(DiaSiApplication.getCanvasWidth() / 6 * 4,
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight,
                DiaSiApplication.getCanvasWidth() / 6 * 4 + recWidth,
                DiaSiApplication.getCanvasHeight() - bottomSpace, paint);
        canvas.drawBitmap(bmpMercy, DiaSiApplication.getCanvasWidth() / 6 * 4,
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight, paint);
        canvas.drawText("仁慈", DiaSiApplication.getCanvasWidth() / 6 * 4 + bmpMercy.getWidth(),
                DiaSiApplication.getCanvasHeight() - bottomSpace - recHeight - fontMetrics.top, textPaint);
        canvas.restore();
    }

    @Override
    public void logic() {

    }

}
