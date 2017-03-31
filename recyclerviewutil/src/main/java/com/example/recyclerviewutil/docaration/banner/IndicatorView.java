package com.example.recyclerviewutil.docaration.banner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.recyclerviewutil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.gao on 2017/3/31.
 */

public class IndicatorView extends RadioGroup {
    private  Context context;
    private ArrayList<RadioButton> views;

    public IndicatorView(Context context) {
        super(context);
        this.context=context;
        init();
    }



    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }


    private void init() {

    }
    public void setDate(int size) {
        views = new ArrayList<>();

        for (int i = 0; i <size ; i++) {
            RadioButton indicator = (RadioButton) LayoutInflater.from(context).inflate(R.layout.indicator, this, false);
            addView(indicator);
            views.add(indicator);
        }
    }
    public void setCheck (int index) {
        if (views!=null) {
            for (int i = 0; i <views.size() ; i++) {
                    views.get(i).setChecked(index==i);

            }
        }
    }
}
