package com.example.recyclerviewutil.docaration.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.recyclerviewutil.docaration.holder.BaseViewHolder;
import com.example.recyclerviewutil.docaration.listener.ItemClickListener;

import java.util.ArrayList;

/**
 * Created by mr.gao on 2017/3/22.
 */

public abstract class HeaderFooterAdapter extends RecyclerView.Adapter {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;
    private ArrayList dates;
    private View mHeaderView;
    private View mFooterView;
    private BaseViewHolder holder;
    private View mContentView;
    private int layout;
    private ItemClickListener mItemClickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {
            ViewParent parent_ = mHeaderView.getParent();
            return new BaseViewHolder(mHeaderView);
        } else if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            return new BaseViewHolder(view);

        } else {

            return new BaseViewHolder(mFooterView);
        }


    }

    public HeaderFooterAdapter(ArrayList datas, @LayoutRes int layout) {
        this.dates = datas;
        this.layout = layout;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.click(position);
            }
        });

        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof BaseViewHolder) {
                if (mHeaderView == null) {
                    // convert((BaseViewHolder) holder, dates.get(position));


                }

                return;
            } else if (getItemViewType(position) == TYPE_HEADER) {
                return;
            } else {

                return;
            }
        }
    }

    public abstract void convert(BaseViewHolder holder, Object o);

    @Override
    public int getItemCount() {
        if (mHeaderView != null && mFooterView != null) {
            return dates.size() + 2;
        } else if (mHeaderView == null && mFooterView == null) {
            return dates.size();
        } else {
            return dates.size() + 1;
        }

    }

    public void addHeader(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void addFooter(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    public void addContentView(View contentView) {
        mContentView = contentView;
    }


    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) return TYPE_HEADER;
        if (mFooterView != null && position == getItemCount() - 1) return TYPE_FOOTER;
        return TYPE_NORMAL;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);


        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (mHeaderView != null || mFooterView != null) {
                        return getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER
                                ? gridManager.getSpanCount() : 1;
                    } else {
                        return 1;
                    }

                }
            });
        }
    }

    public void setOnItemCLickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

}
