package com.business.cd1236.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int normal;
    private int margin;
    private int leftMargin1;
    private int leftMargin2;
    private TYPE type;

    public enum TYPE {
        LEFT, ALL, TOP, BOTTOM, GRID, CUSTOM;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        switch (type) {
            case LEFT:
                outRect.top = normal;
                outRect.bottom = normal;
                outRect.left = normal;
                outRect.right = normal;
                if (parent.getChildLayoutPosition(view) >= 1)
                    outRect.left = margin;
                break;
            case TOP:
                outRect.top = normal;
                outRect.bottom = normal;
                outRect.left = normal;
                outRect.right = normal;
                if (parent.getChildLayoutPosition(view) >= 0)
                    outRect.top = margin;
                break;
            case BOTTOM:
                outRect.top = normal;
                outRect.bottom = normal;
                outRect.left = normal;
                outRect.right = normal;
                if (parent.getChildLayoutPosition(view) == parent.getAdapter().getItemCount() - 1)
                    outRect.bottom = margin;
                break;
            case ALL:
                outRect.top = margin;
                outRect.bottom = normal;
                outRect.right = normal;
                if (parent.getChildLayoutPosition(view) % 1 == 0)
                    outRect.left = margin;
                break;
            case GRID:
                //不是第一个的格子都设一个左边和底部的间距
                outRect.left = margin;
                outRect.bottom = margin;
                //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
//                if (parent.getChildLayoutPosition(view) % 3 == 0) {
//                    outRect.left = 0;
//                }
                break;
            case CUSTOM:
                outRect.top = normal;
                outRect.bottom = normal;
                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.left = leftMargin1;
                } else {
                    outRect.left = leftMargin2;
                }
                break;
        }


//        if (parent.getChildAdapterPosition(view) % 3 == 0) {
//            outRect.right = normal;
//            outRect.left = margin;
//        } else if (parent.getChildAdapterPosition(view) % 3 == 1) {
//            outRect.right = margin;
//            outRect.left = margin;
//        } else if (parent.getChildAdapterPosition(view) % 3 == 2) {
//            outRect.right = normal;
//            outRect.left = margin;
//        }

    }

    public SpaceItemDecoration(int normal, int margin, TYPE type) {
        this.normal = normal;
        this.margin = margin;
        this.type = type;
    }

    public SpaceItemDecoration(int normal, int leftMargin1, int leftMargin2, TYPE type) {
        this.normal = normal;
        this.leftMargin1 = leftMargin1;
        this.leftMargin2 = leftMargin2;
        this.type = type;
    }
}