package com.business.cd1236.adapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jess.arms.utils.ArmsUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeThreeAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    private ArrayList<Object> objects ;

    public HomeThreeAdapter(int layoutResId) {
        super(layoutResId);
        objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.add(4);
        objects.add(5);
        objects.add(6);
        objects.add(7);
        objects.add(8);
        objects.add(9);
        objects.add(10);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {

        RecyclerView rvItem = baseViewHolder.getView(R.id.rv_item);

        //滑动会卡顿  待优化，保证只调用一次
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setInitialPrefetchItemCount(50);
        ArmsUtils.configRecyclerView(rvItem, linearLayoutManager);

        rvItem.setHasFixedSize(true);
        rvItem.setNestedScrollingEnabled(false);
        HomeThreeItemAdapter homeThreeItemAdapter = new HomeThreeItemAdapter(R.layout.item_home_three_item);
        rvItem.setAdapter(homeThreeItemAdapter);
        homeThreeItemAdapter.setNewInstance(objects);
    }

    class HomeThreeItemAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

        public HomeThreeItemAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {

        }
    }
}
