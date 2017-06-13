package com.example.songye02.diasigame.callback;

import com.example.songye02.diasigame.model.textview.PauseViewText;

/**
 * Created by songye02 on 2017/6/13.
 */

public interface PauseTextViewDeadCallback {
    /**
     * 是给PauseViewTextGroup设置的单个字dead后调用的回调
     * index 当前死亡的是第几个view
     * totalNum是总共有几个View
     * */
    void onDead(PauseViewText view, int index, int totalNum);
}
