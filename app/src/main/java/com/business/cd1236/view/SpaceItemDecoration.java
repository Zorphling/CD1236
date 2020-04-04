package com.business.cd1236.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int normal;
    private final int margin;
    private final TYPE type;

    public enum TYPE {
        LEFT, ALL
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
            case ALL:
                outRect.top = margin;
                outRect.bottom = normal;
                outRect.right = normal;
                if (parent.getChildLayoutPosition(view) % 1 == 0)
                    outRect.left = margin;
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
}