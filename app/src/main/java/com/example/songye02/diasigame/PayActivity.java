package com.example.songye02.diasigame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PayActivity extends AppCompatActivity {

    private ImageView ivZhifbubao;
    private ImageView ivWeixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ivWeixin = (ImageView)findViewById(R.id.iv_weixin);
        ivZhifbubao = (ImageView)findViewById(R.id.iv_zhifubao);
    }
}
