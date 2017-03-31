package com.example.recyclerviewutil.docaration.banner;

import android.content.Context;
import android.graphics.Color;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.*;
import android.support.v7.widget.PagerSnapHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.recyclerviewutil.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Mr_g on 2017/3/30.
 */

public class RecyclerViewBanner extends FrameLayout {

    private int INTERVAL = 5;
    private Context context;
    private RecyclerView recyclerview;
    private IndicatorView indicator;
    private Myhandler myhandler;
    private boolean isPlay;
    private Thread thread;
    private Timer timer;

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            myhandler.sendEmptyMessage(0);
        }
    }

    public RecyclerViewBanner(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }


    public RecyclerViewBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public RecyclerViewBanner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();


    }

    private void init() {
        View banner = LayoutInflater.from(context).inflate(R.layout.banner, this, false);
        recyclerview = (RecyclerView) banner.findViewById(R.id.recyclerview);
        indicator = (IndicatorView) banner.findViewById(R.id.indicator);
        addView(banner);

    }

    public void setDate(List<String> imgs) {
        BannerAdapter bannerAdapter = new BannerAdapter<String>(imgs, R.layout.item_banner_img) {
            @Override
            void setImg(ImageView banner_img, String url) {
                loadUrl(banner_img, url);
            }
        };

        recyclerview.setLayoutManager(new ScrollSpeedLinearLayoutManger(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerview.setAdapter(bannerAdapter);
        new PagerSnapHelper().attachToRecyclerView(recyclerview);

        if (myhandler == null) {
            myhandler = new Myhandler();
        }
        int size = imgs.size();
        indicator.setDate(size);
        indicator.setCheck(2);
    }

    public void setTime(int second) {
        INTERVAL = second;

    }

    public void loadUrl(ImageView banner_img, String url) {
        banner_img.setBackgroundColor(Color.BLACK);
    }

    public void startPlay(boolean isPlay) {

        if (isPlay) {
            timer = new Timer();
            timer.schedule(new MyTimerTask(),INTERVAL*1000,INTERVAL*1000);
        } else {
            timer.cancel();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                startPlay(true);
                break;
            case MotionEvent.ACTION_DOWN:
                startPlay(false);
                break;
            case MotionEvent.ACTION_MOVE:
                startPlay(false);
                break;


        }
        return super.dispatchTouchEvent(ev);
    }



    class Myhandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerview.getLayoutManager();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            recyclerview.smoothScrollToPosition(firstVisibleItemPosition + 1);

        }
    }

}
