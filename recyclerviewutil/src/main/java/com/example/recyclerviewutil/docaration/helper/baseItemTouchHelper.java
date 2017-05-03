package com.example.recyclerviewutil.docaration.helper;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by mr.gao on 2017/4/13.
 */

public class baseItemTouchHelper extends ItemTouchHelper {
    private  BaseTouchHelperCallback mCallback;

    /**
     * Creates an ItemTouchHelper that will work with the given Callback.
     * <p>
     * You can attach ItemTouchHelper to a RecyclerView via
     * {@link #attachToRecyclerView(RecyclerView)}. Upon attaching, it will add an item decoration,
     * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
     *
     * @param callback The Callback which controls the behavior of this touch helper.
     */
    public baseItemTouchHelper(Callback callback) {
        super(callback);
        this.mCallback=(BaseTouchHelperCallback)callback;

    }
    public Callback getCallback() {
        return mCallback;
    }
}
