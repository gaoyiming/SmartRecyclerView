package com.example.recyclerviewutil.docaration.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewutil.docaration.holder.BaseViewHolder;

import java.util.List;


/**
 * Created by Mr_g on 2017/3/30.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> dates;
    private int layout;

    public BaseAdapter(List<T> datas, @LayoutRes int layout) {
        this.dates = datas;
        this.layout = layout;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new BaseViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, position);
    }

    public abstract void convert(BaseViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return dates.size();
    }
}
