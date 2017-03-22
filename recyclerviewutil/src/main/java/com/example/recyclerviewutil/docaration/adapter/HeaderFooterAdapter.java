package com.example.recyclerviewutil.docaration.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewutil.R;
import com.example.recyclerviewutil.docaration.holder.ViewHolder;

import java.util.ArrayList;

/**
 * Created by mr.gao on 2017/3/22.
 */

public class HeaderFooterAdapter extends RecyclerView.Adapter {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;
    private ArrayList dates;
    private View mHeaderView;
    private View mFooterView;
    private ViewHolder holder;
    private View mContentView;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        if (viewType == TYPE_HEADER) {
             view = mHeaderView;

        } else if (viewType == TYPE_FOOTER) {
             view = mContentView;

        } else if (viewType == TYPE_NORMAL) {
             view = mFooterView;

        }
        holder = new ViewHolder(parent.getContext(),view, parent);
        return holder;
    }

    public void HeaderFooterAdapter(ArrayList datas) {
        this.dates = datas;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;

//        final int pos = getRealPosition(viewHolder);
//        final String data = mDatas.get(pos);
//        if (viewHolder instanceof Holder) {
//            ((Holder) viewHolder).text.setText(data);
//            if (mListener == null) return;
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mListener.onItemClick(pos, data);
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        //if()
        return 0;
    }

    public void addHeader(View headerView) {
        mHeaderView = headerView;
    }

    public void addFooter(View footerView) {
        mFooterView = footerView;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) return TYPE_HEADER;
        if (position == getItemCount()) return TYPE_FOOTER;

        return TYPE_NORMAL;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        //        public TextView mTextView;
//        public Button mButton;
//        public int viewType;
        public RecyclerViewHolder(View itemView, int viewType) {
            super(itemView);
//            this.viewType = viewType;
//            if(viewType==IS_HEADER){
//                mButton = (Button) itemView.findViewById(R.id.button);
//            }
//            if(viewType==IS_FOOTER){
//                //do some sthing
//            }
//            if(viewType==IS_NORMAL){
//                mTextView = (TextView) itemView.findViewById(R.id.tv_content);
//            }
        }
    }
}
