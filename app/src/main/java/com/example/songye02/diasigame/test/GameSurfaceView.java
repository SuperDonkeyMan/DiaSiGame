package com.example.songye02.diasigame.test;

import com.example.songye02.diasigame.callback.ButtonVisibilityCallBack;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.shapeview.BottomMenuView;
import com.example.songye02.diasigame.model.shapeview.DirectionKeyView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.timecontroller.BaseViewHolder;
import com.example.songye02.diasigame.timecontroller.GameTimeController;
import com.example.songye02.diasigame.timecontroller.GameViewHolder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by songye02 on 2017/6/19.
 */

public class GameSurfaceView extends BaseSurfaceView<GameViewHolder,BaseShowableView> implements DirectionKeyCallBack, View
        .OnClickListener {
    //几个必须的组件
    private GameTimeController timeController;
    private DirectionKeyView directionKeyView;
    private HeartShapeView heartShapeView;
    private PortraitView portraitView;
    private BottomMenuView bottomMenuView;
    private ButtonVisibilityCallBack buttonVisibilityCallBack;
    private Canvas canvas;
    private Paint rectPaint;

    // 声音相关变量
    private MediaPlayer mediaPlayer;


    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void dealDirectionKeyDown(float rad, float distance) {

    }

    @Override
    public void dealDirectionKeyUp(float rad, float distance) {

    }

    @Override
    void onSurfaceCreated() {

    }

    @Override
    void onPause() {

    }

    @Override
    void onResume() {

    }

    @Override
    protected void myDraw(Canvas canvas) {

    }

    @Override
    protected void myLogic() {

    }

    @Override
    protected void onNoEventInTimeController() {

    }

    @Override
    protected GameViewHolder intViewHolder() {
        return new GameViewHolder<>(mShowables,heartShapeView,portraitView);
    }

}
