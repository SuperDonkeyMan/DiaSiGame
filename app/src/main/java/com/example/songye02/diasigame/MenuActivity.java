package com.example.songye02.diasigame;

import com.example.songye02.diasigame.test.MenuSurfaceView;
import com.example.songye02.diasigame.utils.GameStateUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    private ImageButton button;
    private MenuSurfaceView menuSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        menuSurfaceView = (MenuSurfaceView)findViewById(R.id.menu_surface_view);
        button = (ImageButton)findViewById(R.id.menu_button);
        button.setOnClickListener(menuSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!isFinishing()){
            menuSurfaceView.dealWithPauseEvent(true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        menuSurfaceView.dealWithPauseEvent(false);
    }
}
