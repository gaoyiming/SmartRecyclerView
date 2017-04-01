package com.example.recyclerviewutil.docaration.docaration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.example.recyclerviewutil.R;

/**
 * Created by Mr_g on 2017/2/9.
 */

public class LinnerItemDocaration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;
    private static final int DEFAULSIZE = 1;
    private Context mContext;
    private int mItemDividerSize = 1;
    private int mOrientation = LinearLayout.VERTICAL;
    private Paint mPaint;

    //绘制分隔线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null) {
            return;
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
//            final View child = parent.getChildAt(i);
//            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
//            final int right = mBounds.right + Math.round(ViewCompat.getTranslationX(child));
//            final int left = right - mDivider.getIntrinsicWidth();
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(canvas);


            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - layoutParams.leftMargin;
            final int right = left + mItemDividerSize;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
        canvas.restore();
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }
        //   mItemDividerSize = (int) TypedValue.applyDimension(mItemDividerSize, TypedValue.COMPLEX_UNIT_DIP, mContext.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            //这是系统的源码DividerItemDecoration
//            final View child = parent.getChildAt(i);
//            parent.getDecoratedBoundsWithMargins(child, mBounds);
//            final int bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
//            final int top = bottom - mDivider.getIntrinsicHeight();
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(canvas);


            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mItemDividerSize;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
        canvas.restore();


    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


    //设置分隔线的宽高
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL) {
            outRect.set(0, 0, 0, mItemDividerSize);
        } else {
            outRect.set(0, 0, mItemDividerSize, 0);
        }
    }

    public LinnerItemDocaration(Context context, int orientation, int color, int size) {
        this.mOrientation = orientation;
        this.mItemDividerSize = size;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
        if (orientation != LinearLayout.VERTICAL && orientation != LinearLayout.HORIZONTAL) {
            throw new IllegalArgumentException("LinearLayoutManager error");
        }


    }

    public LinnerItemDocaration(Context context, int orientation, int color) {
        this(context, orientation, color, DEFAULSIZE);

    }

    public LinnerItemDocaration(Context context, int orientation) {
        this(context, orientation, context.getResources().getColor(
                R.color.defaultdivider));


    }

    public LinnerItemDocaration(Context context) {
        this(context, LinearLayout.VERTICAL);
        this.mContext = context;
    }
}
