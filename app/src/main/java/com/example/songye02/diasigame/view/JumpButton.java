package com.example.songye02.diasigame.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by songye02 on 2017/6/20.
 */

public class JumpButton extends AppCompatButton {

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
        }
        return true;
    }
}
