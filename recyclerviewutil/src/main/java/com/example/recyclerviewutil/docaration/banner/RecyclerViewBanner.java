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

    private Timer timer;
    private int size;
    private int DisplayItemCount=1;

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

    public void setDate(BannerAdapter bannerAdapter) {

        recyclerview.setLayoutManager(new ScrollSpeedLinearLayoutManger(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerview.setAdapter(bannerAdapter);

        new PagerSnapHelper().attachToRecyclerView(recyclerview);

        if (myhandler == null) {
            myhandler = new Myhandler();
        }

        size = bannerAdapter.getRealItemCount();
        recyclerview.scrollToPosition(1000 * size-DisplayItemCount);
        recyclerview.smoothScrollToPosition(1000 * size);

        indicator.setDate(size);
        startPlay(true);

    }


    public void setTime(int second) {
        INTERVAL = second;

    }


    public void startPlay(boolean isPlay) {

        if (isPlay) {
            if(timer!=null)
            timer.cancel();
            indicator.setCheck(0);
            timer = new Timer();
            timer.schedule(new MyTimerTask(), INTERVAL * 1000, INTERVAL * 1000);
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


    private class Myhandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerview.getLayoutManager();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            int currentPosition = firstVisibleItemPosition + 1;
            recyclerview.smoothScrollToPosition(currentPosition);
            indicator.setCheck(currentPosition % size);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startPlay(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        startPlay(false);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        if (visibility == View.GONE) {
            // 停止轮播
            startPlay(false);
        } else if (visibility == View.VISIBLE) {
            // 开始轮播
            startPlay(true);
        }
        super.onWindowVisibilityChanged(visibility);
    }
}
