package com.example.songye02.diasigame.view;

import com.example.songye02.diasigame.R;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by songye02 on 2017/6/20.
 */

public class JumpButton extends AppCompatImageButton {

    public JumpButton(Context context) {
        super(context);
    }

    public JumpButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JumpButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                callOnClick();
                setBackgroundResource(R.drawable.circle_selected);
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.drawable.circle_unselected);
                break;
        }
        return true;
    }
}
