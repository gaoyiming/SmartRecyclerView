package com.example.mr_g.smartrecyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import com.example.recyclerviewutil.docaration.adapter.BaseAdapter;
import com.example.recyclerviewutil.docaration.holder.BaseViewHolder;

import java.util.List;

/**
 * Created by mr.gao on 2017/4/13.
 */

public class DIffAdapter extends BaseAdapter {
    public DIffAdapter(List datas, @LayoutRes int layout) {
        super(datas, layout);
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            BaseViewHolder myViewHolder = (BaseViewHolder) holder;
            Bundle bundle = (Bundle) payloads.get(0);
            Object key = bundle.get("key");
//            if (bundle.getString(NAME_KEY) != null) {
//                myViewHolder.name.setText(bundle.getString(NAME_KEY));
//                myViewHolder.name.setTextColor(Color.BLUE);
//            }
        }
    }
}
