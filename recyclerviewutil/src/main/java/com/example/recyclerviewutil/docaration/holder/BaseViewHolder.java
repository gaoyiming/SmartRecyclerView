package com.example.recyclerviewutil.docaration.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mr.gao on 2017/3/22.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder
{
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public BaseViewHolder(Context context, View itemView, ViewGroup parent)
    {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }


    public static BaseViewHolder get(Context context, ViewGroup parent, int layoutId)
    {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        BaseViewHolder holder = new BaseViewHolder(context, itemView, parent);
        return holder;
    }

    public BaseViewHolder(View itemView)
    {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }


    public static BaseViewHolder get(View itemView)
    {



        return   new BaseViewHolder( itemView);
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
