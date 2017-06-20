package com.example.songye02.diasigame;

import com.example.songye02.diasigame.callback.BottomViewClickCallback;
import com.example.songye02.diasigame.callback.ButtonVisibilityCallBack;
import com.example.songye02.diasigame.test.GameSurfaceView;
import com.example.songye02.diasigame.view.JumpButton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity
        implements ButtonVisibilityCallBack, View.OnClickListener, BottomViewClickCallback {

    private GameSurfaceView mySurfaceView;
    private JumpButton btnSmallJump;
    private JumpButton btnBigJump;
    private boolean isPause = false;
    private View pauseBackground;
    private ImageView btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.acitvity_game);
        mySurfaceView = (GameSurfaceView) findViewById(R.id.my_surface_view);
        mySurfaceView.setButtonVisibilityCallBack(this);
        mySurfaceView.setBottomViewClickCallback(this);
        btnSmallJump = (JumpButton) findViewById(R.id.button_jump_small);
        btnBigJump = (JumpButton) findViewById(R.id.button_jump_big);
        btnSmallJump.setOnClickListener(mySurfaceView);
        btnBigJump.setOnClickListener(mySurfaceView);
        pauseBackground = findViewById(R.id.continue_layout);
        btnContinue = (ImageView) findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(mySurfaceView);
        findViewById(R.id.btn_fight).setOnClickListener(mySurfaceView);
        findViewById(R.id.btn_pause).setOnClickListener(mySurfaceView);
        findViewById(R.id.btn_things).setOnClickListener(mySurfaceView);
        findViewById(R.id.btn_mercy).setOnClickListener(mySurfaceView);
        hideButton();
    }

    @Override
    public void showButton() {
        runOnUiThread(() -> {
            btnSmallJump.setVisibility(View.VISIBLE);
            btnBigJump.setVisibility(View.VISIBLE);
        });

    }

    @Override
    public void hideButton() {
        runOnUiThread(() -> {
            btnSmallJump.setVisibility(View.GONE);
            btnBigJump.setVisibility(View.GONE);
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pause:
                isPause = !isPause;
                mySurfaceView.dealWithPauseEvent(isPause);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!isFinishing()) {
            super.onPause();
            isPause = true;
            mySurfaceView.dealWithPauseEvent(true);
            pauseBackground.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActionClick() {

    }

    @Override
    public void onPauseClick() {
        pauseBackground.setVisibility(View.VISIBLE);
    }

    @Override
    public void onContinueClick() {
        pauseBackground.setVisibility(View.GONE);

    }

    @Override
    public void onThingsClick() {

    }

    @Override
    public void onMercyClick() {

    }
}
