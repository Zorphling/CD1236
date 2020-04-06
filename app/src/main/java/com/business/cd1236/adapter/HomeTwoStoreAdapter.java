package com.business.cd1236.adapter;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jess.arms.utils.ArmsUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeTwoStoreAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public HomeTwoStoreAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {
        ImageView iv = baseViewHolder.getView(R.id.riv_store);
        GlideUtil.loadImg(R.mipmap.p_header, iv);

        RecyclerView rvStoreGoods = baseViewHolder.getView(R.id.rv_store_goods);
        ArmsUtils.configRecyclerView(rvStoreGoods, new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        int dpRange = SizeUtils.dp2px(getContext(), 10);
        rvStoreGoods.addItemDecoration(new SpaceItemDecoration(0, dpRange, SpaceItemDecoration.TYPE.LEFT));
        HomeTwoStoreGoodsAdapter homeTwoStoreGoodsAdapter = new HomeTwoStoreGoodsAdapter(R.layout.item_home_two_goods);
        rvStoreGoods.setAdapter(homeTwoStoreGoodsAdapter);
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        homeTwoStoreGoodsAdapter.setNewInstance(objects);
    }

    class HomeTwoStoreGoodsAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

        public HomeTwoStoreGoodsAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {
            CardView iv = baseViewHolder.getView(R.id.cv_store_goods);
            int dp = SizeUtils.dp2px(getContext(), 60);
            ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
            int width = ((SizeUtils.getScreenHW((Activity) getContext())[0]) - dp) / 3;
            layoutParams.width = width;

            ImageView ivGoods = baseViewHolder.getView(R.id.iv_goods);
            ViewGroup.LayoutParams ivParams = ivGoods.getLayoutParams();
            ivParams.height = width;
            GlideUtil.loadImg(R.mipmap.p_header, ivGoods);
        }
    }
}
