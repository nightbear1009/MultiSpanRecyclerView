package com.triplelayoutrecyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.GridLayout;

/**
 * Created by tedliang on 15/3/9.
 */
public class MultiSpanRecyclerView extends RecyclerView {
    private int mViewSpan;

    public MultiSpanRecyclerView(Context context) {
        super(context);
    }

    public MultiSpanRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiSpanRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        if (layout instanceof GridLayoutManager) {
            mViewSpan = ((GridLayoutManager) layout).getSpanCount();
            ((GridLayoutManager) layout).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    return ((GridLayoutManager) getLayoutManager()).getSpanCount() - mViewSpan +1;
                }
            });
        }
    }

    public void setViewSpan(int span) {
        mViewSpan = span;
    }
}
