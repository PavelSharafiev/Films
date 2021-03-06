package com.example.sharafievp.filmss.Other;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Декорация для отступа между элементами RecyclerView
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration
{
    private int space;
    public SpacesItemDecoration(int space)
    {
        this.space = space;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        outRect.bottom = space;
    }
}