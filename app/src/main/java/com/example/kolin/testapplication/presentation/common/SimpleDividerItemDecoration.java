package com.example.kolin.testapplication.presentation.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kolin.testapplication.R;

/**
 * Created by kolin on 14.09.2016.
 */

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable drawable;

    public SimpleDividerItemDecoration(Context context) {
        drawable = ContextCompat.getDrawable(context, R.drawable.line_divider);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);

            RecyclerView.LayoutParams layoutParams =(RecyclerView.LayoutParams)
                    childView.getLayoutParams();

            int top = childView.getBottom() + layoutParams.bottomMargin;
            int bottom = top + drawable.getIntrinsicHeight();

            drawable.setBounds(left,top, right, bottom);
            drawable.draw(c);
        }
    }
}
