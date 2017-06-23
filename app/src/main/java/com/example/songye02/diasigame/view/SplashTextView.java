package com.example.songye02.diasigame.view;

import com.example.songye02.diasigame.utils.MathUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by songye02 on 2017/6/23.
 */

public class SplashTextView extends android.support.v7.widget.AppCompatTextView {
    boolean flag = true;
    private int count;
    private int countNum = 100; // 50帧一循环
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 10010){
                SplashTextView.this.setAlpha(Math.abs((float) (Math.sin((float)count/countNum*Math.PI))));
            }
        }
    };
    public SplashTextView(Context context) {
        super(context);
        startSplash();
    }

    public SplashTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        startSplash();
    }

    public SplashTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        startSplash();
    }

    private void startSplash() {
        new  Thread(new Runnable() {
            @Override
            public void run() {
                while(flag){
                    long timeBefore = System.currentTimeMillis();
                    try {
                        count++;
                        handler.sendEmptyMessage(10010);
                        long timeAfter = System.currentTimeMillis();
                        if(timeAfter-timeBefore<20){
                            Thread.sleep(20-(timeAfter-timeBefore));
                        }
                    }catch (InterruptedException e){

                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        flag = false;
    }
}
