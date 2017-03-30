package com.example.recyclerviewutil.docaration.banner;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.*;
import android.support.v7.widget.PagerSnapHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.recyclerviewutil.R;

import java.util.List;

/**
 * Created by Mr_g on 2017/3/30.
 */

public  class RecyclerViewBanner extends FrameLayout {

    private   int INTERVAL = 3;
    private  Context context;
    private RecyclerView recyclerview;
    private LinearLayout indicator;

    public RecyclerViewBanner(@NonNull Context context) {
        super(context);
        this.context=context;
        init();
    }



    public RecyclerViewBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public RecyclerViewBanner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();


    }
    private void init() {
        View banner = LayoutInflater.from(context).inflate(R.layout.banner, this, false);
        recyclerview = (RecyclerView) banner.findViewById(R.id.recyclerview);
        indicator = (LinearLayout) banner.findViewById(R.id.indicator);
        addView(banner);
    }
    public void setDate(List<String> imgs){
        BannerAdapter bannerAdapter = new BannerAdapter<String>(imgs, R.layout.item_banner_img) {
            @Override
            void setImg(ImageView banner_img, String url) {
                loadUrl(banner_img,url);
            }
        };

        recyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        recyclerview.setAdapter(bannerAdapter);
       new PagerSnapHelper().attachToRecyclerView(recyclerview);

    }
    public void setTime(int second){
        INTERVAL=second;

    }

   public  void loadUrl(ImageView banner_img, String url) {
       banner_img.setBackgroundColor(Color.BLACK);
   }
   public void startChange( RecyclerView recyclerview){
      // recyclerview.la
      // recyclerview.smoothScrollToPosition();
   }

}
