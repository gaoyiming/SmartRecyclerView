package com.example.recyclerviewutil.docaration.banner;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

/**
 * Created by Mr_g on 2017/3/30.
 */

public class PagerSnapHelper extends LinearSnapHelper {
    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        int TargetPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
        View snapView = findSnapView(layoutManager);
        if (TargetPosition!= RecyclerView.NO_POSITION&&snapView!=null) {
            int currentposition = layoutManager.getPosition(snapView);
            int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            currentposition=TargetPosition<currentposition?lastVisibleItemPosition:(TargetPosition>currentposition? firstVisibleItemPosition:currentposition);
            TargetPosition=TargetPosition>currentposition?currentposition+1:currentposition-1;
        }

        return TargetPosition;

    }
}
