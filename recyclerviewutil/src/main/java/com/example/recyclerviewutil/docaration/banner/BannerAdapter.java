package com.example.recyclerviewutil.docaration.banner;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;

import com.example.recyclerviewutil.R;
import com.example.recyclerviewutil.docaration.adapter.BaseAdapter;
import com.example.recyclerviewutil.docaration.holder.BaseViewHolder;

import java.util.List;

/**
 * Created by Mr_g on 2017/3/30.
 */

public abstract class BannerAdapter<String> extends BaseAdapter {


    private final List<String> datas;

    public BannerAdapter(List<String> datas, @LayoutRes int layout) {
        super(datas, layout);
        this.datas = datas;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        position = position % datas.size();
        ImageView banner_img = holder.getView(R.id.banner_img);
        String url = datas.get(position);
        setImg(banner_img, url);
    }

    abstract void setImg(ImageView banner_img, String url);

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}
