package com.example.songye02.diasigame;

import com.example.songye02.diasigame.test.MySurfaceView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new MySurfaceView(this));
//        LinearLayout layout = (LinearLayout)findViewById(R.id.main_view);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(0,0);
//        params.gravity = Gravity.RIGHT;
//        layout.addView(new MySurfaceView(this),params);
    }


}
