package com.example.songye02.diasigame.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.songye02.diasigame.DiaSiApplication;
import com.example.songye02.diasigame.GameActivity;
import com.example.songye02.diasigame.R;
import com.example.songye02.diasigame.callback.DirectionKeyCallBack;
import com.example.songye02.diasigame.model.BaseShowableView;
import com.example.songye02.diasigame.model.Showable;
import com.example.songye02.diasigame.model.shapeview.DirectionKeyView;
import com.example.songye02.diasigame.model.shapeview.HeartShapeView;
import com.example.songye02.diasigame.model.shapeview.PortraitView;
import com.example.songye02.diasigame.model.textview.MenuView;
import com.example.songye02.diasigame.model.textview.TimeDialogueParams;
import com.example.songye02.diasigame.model.textview.TimeDialogueTextGroup;
import com.example.songye02.diasigame.model.textview.TriggerDialogueGroup;
import com.example.songye02.diasigame.timecontroller.MenuTimeController;
import com.example.songye02.diasigame.timecontroller.MenuViewHolder;
import com.example.songye02.diasigame.timecontroller.TimeController;
import com.example.songye02.diasigame.timecontroller.TimerEvent;
import com.example.songye02.diasigame.utils.DpiUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dell on 2017/6/19.
 */

public class MenuSurfaceView extends BaseSurfaceView<MenuViewHolder,BaseShowableView> implements DirectionKeyCallBack, View
        .OnClickListener {

    private static final int MENU_STATE_1 = 0; // 梁非凡来袭
    private static final int MENU_STATE_2 = 1; // 选择选项 让他吔屎，打赏，查看
    private static final int MENU_STATE_3 = 2; // 我感觉你要吔点屎了


    private List<String> menu1;
    private List<String> menu2;
    private List<String> menu3;
    private boolean isMenu2Clicked = false;

    private int menuState;

    private boolean flag;

    private Paint rectPaint;
    private DirectionKeyView directionKeyView;
    private HeartShapeView heartShapeView;
    private MenuView menuView;
    private PortraitView portraitView;

    // 声音相关变量
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    public MenuSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        menuState = MENU_STATE_1;
        menu1 = new ArrayList<>();
        menu1.add("梁 非 凡 袭 来!!");
        menu2 = new ArrayList<>();
        menu2.add("让 他 吔 屎");
        menu2.add("打 赏");
        menu2.add("查 看");
        menu3 = new ArrayList<>();
        menu3.add("我 感 觉 你 要 吔 点 屎 了!");
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.bgm);
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 100);
        soundPool.load(DiaSiApplication.getInstance(), R.raw.ye, 1);
    }

    @Override
    public void onClick(View v) {
        soundPool.play(1, 1, 1, 0, 0, 1);
        switch (v.getId()){
            case R.id.menu_button:
                switch (menuState) {
                    case MENU_STATE_1:
                        menuState = MENU_STATE_2;
                        menuView.setTexts(menu2);
                        mShowables.clear();
                        break;
                    case MENU_STATE_2:
                        if (menuView.getCurrentIndex() == 0) {
                            if (!isMenu2Clicked) {
                                portraitView.setPortraitBmp(PortraitView.BMP_LIUXING);
                                portraitView.startTwinkle(25);
                                // menuView在dismiss阶段允许heartShapeView自由活动
                                menuView.setDismiss(30);
                                heartShapeView.startMove(portraitView.getCenterX(), portraitView.getCenterY(), 5,
                                        heartShapeView1 -> heartShapeView1.setDismiss(true));
                                menuState = MENU_STATE_3;
                                menuView.setTexts(menu3);
                                //                    menuView.setIsDead(true);
                                // 添加新的对话
                                List<TimerEvent> list = new ArrayList<>();
                                list.add(new TimerEvent<MenuViewHolder>() {
                                    @Override
                                    public long getIntervalTime() {
                                        return 500;
                                    }

                                    @Override
                                    public void addTimerEvent(MenuViewHolder viewHolder) {
                                        HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                                        PortraitView portraitView = viewHolder.portraitView;
                                        List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                                        TimeDialogueParams[] paramses = new TimeDialogueParams[1];
                                        paramses[0] = new TimeDialogueParams("今天是多么美好的一天啊!", 100, 900);
                                        TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                                                portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                                                portraitView.getCurrentY() + DpiUtil.dipToPix(20), 2000);
                                        group.setPlaySound(true);
                                        mMoveables.add(group);
                                    }
                                });
                                list.add(new TimerEvent<MenuViewHolder>() {
                                    @Override
                                    public long getIntervalTime() {
                                        return 2500;
                                    }

                                    @Override
                                    public void addTimerEvent(MenuViewHolder viewHolder) {
                                        HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                                        PortraitView portraitView = viewHolder.portraitView;
                                        List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                                        TimeDialogueParams[] paramses = new TimeDialogueParams[2];
                                        paramses[0] = new TimeDialogueParams("鸟儿在歌唱，", 100, 600);
                                        paramses[1] = new TimeDialogueParams("鲜花在绽放...", 1000, 1500);
                                        TimeDialogueTextGroup group = new TimeDialogueTextGroup(paramses,
                                                portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                                                portraitView.getCurrentY() + DpiUtil.dipToPix(20), 2500);
                                        group.setPlaySound(true);
                                        mMoveables.add(group);
                                    }
                                });

                                list.add(new TimerEvent<MenuViewHolder>() {
                                    @Override
                                    public long getIntervalTime() {
                                        return 5000;
                                    }

                                    @Override
                                    public void addTimerEvent(MenuViewHolder viewHolder) {
                                        HeartShapeView mHeartShapeView = viewHolder.heartShapeView;
                                        PortraitView portraitView = viewHolder.portraitView;
                                        List<BaseShowableView> mMoveables = viewHolder.mMoveables;
                                        TimeDialogueParams[] paramses = new TimeDialogueParams[2];
                                        paramses[0] = new TimeDialogueParams("在这样的一天里，", 100, 600);
                                        paramses[1] = new TimeDialogueParams("像你这样的上司...", 1000, 1800);
                                        TriggerDialogueGroup group = new TriggerDialogueGroup(paramses,
                                                portraitView.getCurrentX() + portraitView.getWidth() + DpiUtil.dipToPix(20),
                                                portraitView.getCurrentY() + DpiUtil.dipToPix(20),
                                                System.currentTimeMillis());
                                        group.setPlaySound(true);
                                        mMoveables.add(group);
                                    }
                                });
                                timeController.clearAndAddNewTimerEvent(System.currentTimeMillis(), list);
                            }
                        } else if (menuView.getCurrentIndex() == 1) {
                            // TODO: 2017/5/15 添加打赏功能
                        } else {
                            // TODO: 2017/5/15 添加查看功能
                        }
                        break;
                    case MENU_STATE_3:
                        // 当最后一个动画播放完才能点击
                        if (mShowables.size() > 0 && mShowables.get(0) instanceof TriggerDialogueGroup &&
                                ((TriggerDialogueGroup) mShowables.get(0)).havaPlayedOk()) {
                            ((TriggerDialogueGroup) mShowables.get(0)).setIsDead(true);
                            // 停止音乐播放
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            soundPool.release();
                            mediaPlayer = null;
                            soundPool = null;
                            Intent intent = new Intent(getContext(), GameActivity.class);
                            getContext().startActivity(intent);
                            ((Activity) getContext()).finish();
                            // 设置无动画
                            ((Activity) getContext()).overridePendingTransition(0, 0);
                        }
                        break;
                }
                break;
        }
    }

    @Override
    public void dealDirectionKeyDown(float rad, float distance) {

    }

    @Override
    public void dealDirectionKeyUp(float rad, float distance) {
        // rad -pi-pi
        soundPool.play(1, 1, 1, 0, 0, 1);
        if (rad > Math.PI * 0.25 && rad < Math.PI * 0.75) {
            menuView.nextIndex();
        } else if (rad > -Math.PI * 0.75 && rad < -Math.PI * 0.25) {
            menuView.lastIndex();
        }
    }

    @Override
    void onSurfaceCreated() {
        dealGlobalVariable();
        // 初始化键盘
        if (directionKeyView == null) {
            directionKeyView = new DirectionKeyView(this);
        }
        // 初始化主角View
        if (heartShapeView == null) {
            heartShapeView = new HeartShapeView(getWidth() / 2, getHeight() / 2, 15, timeController);
            heartShapeView.setBoundary(getWidth() / 2 - DpiUtil.dipToPix(400) / 2,
                    DpiUtil.dipToPix(150),
                    DpiUtil.dipToPix(400),
                    getHeight() - DpiUtil.dipToPix(150 + 60));
            heartShapeView.setBloodMax(100);
            heartShapeView.setBloodCurrent(100);
        }

        // 初始化任务画像
        if (portraitView == null) {
            portraitView = new PortraitView(getWidth() / 2 - DiaSiApplication.getPortraitWidth() / 2, DpiUtil.dipToPix(10));
        }
        if (menuView == null) {
            menuView = new MenuView(menu1, getWidth() / 2 - DpiUtil.dipToPix(400) / 2,
                    DpiUtil.dipToPix(150),
                    DpiUtil.dipToPix(400),
                    getHeight() - DpiUtil.dipToPix(150 + 60), heartShapeView);
        }
        //开始播放声音
        if (getPauseStatus() == NOTPAUSED) {
            mediaPlayer.start();
        }
    }

    @Override
    void onPause() {
        mediaPlayer.pause();
    }

    @Override
    void onResume() {
        mediaPlayer.start();
    }

    @Override
    protected void myDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), rectPaint);
        //画可移动物
        for (Showable showable : mShowables) {
            showable.draw(canvas);
        }
        directionKeyView.draw(canvas);
        portraitView.draw(canvas);
        menuView.draw(canvas);
        heartShapeView.draw(canvas);
    }

    @Override
    protected void myLogic() {
        portraitView.logic();
        menuView.logic();
        heartShapeView.logic();
        Iterator<BaseShowableView> iterator = mShowables.iterator();
        while (iterator.hasNext()) {
            BaseShowableView baseMoveableView = iterator.next();
            if (baseMoveableView.isDead()) {
                mShowables.remove(baseMoveableView);
            } else {
                baseMoveableView.logic();
            }
        }
    }

    @Override
    protected MenuViewHolder intViewHolder() {
        return new MenuViewHolder(mShowables,heartShapeView,portraitView);
    }

    @Override
    protected TimeController<MenuViewHolder> initTimeController() {
        return new MenuTimeController();
    }

    //获取canvas的宽和高，并将其设置入Application成为全局的变量
    private void dealGlobalVariable() {
        DiaSiApplication.setCanvasWidth(getWidth());
        DiaSiApplication.setCanvasHeight(getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        directionKeyView.dealTouchEvent(event);
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
