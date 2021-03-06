package com.example.recyclerviewutil.docaration.banner;

import android.support.annotation.LayoutRes;
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

    public BannerAdapter(List<String> datas) {
        this(datas, R.layout.item_banner_img);

    }

    @Override
    public void convert(BaseViewHolder holder, int position, Object t) {
        position = position % datas.size();
        ImageView banner_img = holder.getView(R.id.banner_img);
        String url = datas.get(position);
        setImg(banner_img, url);
    }

    public abstract void setImg(ImageView banner_img, String url);

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
    public int getRealItemCount() {
        return datas.size();
    }
}
