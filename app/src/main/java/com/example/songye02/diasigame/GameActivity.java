package com.example.songye02.diasigame;

import com.example.songye02.diasigame.callback.ButtonVisibilityCallBack;
import com.example.songye02.diasigame.test.MySurfaceView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GameActivity extends AppCompatActivity implements ButtonVisibilityCallBack {

    private MySurfaceView mySurfaceView;
    private Button btnSmallJump;
    private Button btnBigJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.acitvity_game);
        mySurfaceView = (MySurfaceView)findViewById(R.id.my_surface_view);
        mySurfaceView.setButtonVisibilityCallBack(this);
        btnSmallJump = (Button)findViewById(R.id.button_jump_small);
        btnBigJump = (Button)findViewById(R.id.button_jump_big);
        btnSmallJump.setOnClickListener(mySurfaceView);
        btnBigJump.setOnClickListener(mySurfaceView);
        hideButton();
    }

    @Override
    public void showButton() {
        runOnUiThread(()->{btnSmallJump.setVisibility(View.VISIBLE);
            btnBigJump.setVisibility(View.VISIBLE);});

    }

    @Override
    public void hideButton() {
        runOnUiThread(()->{btnSmallJump.setVisibility(View.GONE);
            btnBigJump.setVisibility(View.GONE);});

    }
}
