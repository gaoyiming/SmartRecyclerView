package com.example.recyclerviewutil.docaration.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by mr.gao on 2017/4/13.
 */

public class BaseDiffCallBack extends DiffUtil.Callback {
    private List<String> olddatas;
    private List<String> newDatas;

    public BaseDiffCallBack(List<String> olddatas, List<String> newDatas) {
        this.olddatas = olddatas;
        this.newDatas = newDatas;
    }

    @Override
    public int getOldListSize() {
        return olddatas.size();
    }

    @Override
    public int getNewListSize() {
        return newDatas.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return olddatas.get(oldItemPosition).equals(newDatas.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return olddatas.get(oldItemPosition).equals(newDatas.get(newItemPosition));
    }

    @Nullable
    @Override//这个方法用来局部刷新
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {



        String s1 = newDatas.get(newItemPosition);
        Bundle bundle = new Bundle();
        bundle.putString("key",s1);
        return bundle;
    }
}
