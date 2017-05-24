package com.example.recyclerviewutil.docaration.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewutil.docaration.holder.BaseViewHolder;
import com.example.recyclerviewutil.docaration.listener.ItemClickListener;
import com.example.recyclerviewutil.docaration.listener.ItemLongClickListener;

import java.util.List;


/**
 * Created by Mr_g on 2017/3/30.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> dates;
    private int layout;
    private ItemClickListener mItemClickListener;
    private ItemLongClickListener itemLongCLickListener;

    public BaseAdapter(List<T> datas, @LayoutRes int layout) {
        this.dates = datas;
        this.layout = layout;
    }

    public void setDates(List<T> datas) {
        this.dates = datas;
      //  notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new BaseViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final int adapterPosition = holder.getAdapterPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.click(adapterPosition);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongCLickListener != null) {
                    itemLongCLickListener.longClick(adapterPosition);
                }
                return true;
            }
        });
        convert(holder, position,dates.get(position>dates.size()?position%dates.size():position));
    }

    public abstract void convert(BaseViewHolder holder, int position, T t);

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public void setOnItemCLickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setOnItemLongCLickListener(ItemLongClickListener itemLongCLickListener) {
        this.itemLongCLickListener = itemLongCLickListener;
    }
}
