package com.example.songye02.diasigame.timecontroller;

import java.util.List;

import com.example.songye02.diasigame.model.Showable;

/**
 * Created by songye02 on 2017/6/19.
 */

public class BaseViewHolder<T extends Showable> {
    public List<T> mMoveables;

    public BaseViewHolder(List<T> mMoveables) {
        this.mMoveables = mMoveables;
    }
}
