package com.example.songye02.diasigame;

import com.bumptech.glide.Glide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class CheckInfoActivity extends AppCompatActivity {

    private ImageView ivMyIcon;
    private ImageView ivOuhuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_layout);
        ivMyIcon = (ImageView)findViewById(R.id.my_icon);
        ivOuhuIcon = (ImageView)findViewById(R.id.ouhu_icon);

        Glide.with(this).load(R.drawable.my_icon).into(ivMyIcon);
        Glide.with(this).load(R.drawable.ouhu_icon).into(ivOuhuIcon);
    }
}
