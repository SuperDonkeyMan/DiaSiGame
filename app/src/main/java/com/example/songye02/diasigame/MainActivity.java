package com.example.songye02.diasigame;

import com.example.songye02.diasigame.view.SplashTextView;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SplashTextView tvPress;
    private ImageView ivIcon;
    private TextView tvAuthor;
    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        Animation animationIn = AnimationUtils.loadAnimation(this,R.anim.alpha_in);

        tvPress = (SplashTextView) findViewById(R.id.tv_press);
        tvPress.setVisibility(View.VISIBLE);
        tvPress.startAnimation(animationIn);

        ivIcon = (ImageView)findViewById(R.id.im_cion);
        ivIcon.setVisibility(View.VISIBLE);
        ivIcon.startAnimation(animationIn);

        tvAuthor = (TextView)findViewById(R.id.tv_author);
        tvAuthor.setVisibility(View.VISIBLE);
        tvAuthor.startAnimation(animationIn);

        findViewById(R.id.main_view).setOnClickListener((v)->{
            if(!isClicked){
                isClicked = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.activity_alpha_in, R.anim.activity_alpha_out);
                        finish();
                    }
                },500);

            }

        });
    }
}
