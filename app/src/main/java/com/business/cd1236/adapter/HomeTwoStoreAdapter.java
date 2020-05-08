package com.business.cd1236.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.bean.MoreBean;
import com.business.cd1236.mvp.ui.activity.GoodsDetailActivity;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jess.arms.utils.ArmsUtils;

import org.jetbrains.annotations.NotNull;

public class HomeTwoStoreAdapter extends BaseQuickAdapter<MoreBean.LocalizeSBean, BaseViewHolder> {
    public HomeTwoStoreAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MoreBean.LocalizeSBean data) {
        ImageView iv = baseViewHolder.getView(R.id.riv_store);
        GlideUtil.loadImg(data.logo, R.mipmap.logo, iv);
        baseViewHolder.setText(R.id.tv_store_name, data.business_name)
                .setText(R.id.tv_time_and_sales, data.open_time + "-" + data.close_time + " | 销量" + data.sales)
                .setText(R.id.tv_store_range, data.mi);


        RecyclerView rvStoreGoods = baseViewHolder.getView(R.id.rv_store_goods);
        ArmsUtils.configRecyclerView(rvStoreGoods, new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        int dpRange = SizeUtils.dp2px(getContext(), 10);
        rvStoreGoods.addItemDecoration(new SpaceItemDecoration(0, dpRange, SpaceItemDecoration.TYPE.LEFT));
        HomeTwoStoreGoodsAdapter homeTwoStoreGoodsAdapter = new HomeTwoStoreGoodsAdapter(R.layout.item_home_two_goods);
        rvStoreGoods.setAdapter(homeTwoStoreGoodsAdapter);
        if (data.goods.size() > 0) {
            homeTwoStoreGoodsAdapter.setList(data.goods);
        } else {
            rvStoreGoods.setVisibility(View.GONE);
        }
    }

    class HomeTwoStoreGoodsAdapter extends BaseQuickAdapter<MoreBean.LocalizeSBean.GoodsBean, BaseViewHolder> {

        public HomeTwoStoreGoodsAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, MoreBean.LocalizeSBean.GoodsBean data) {
            CardView iv = baseViewHolder.getView(R.id.cv_store_goods);
            int dp = SizeUtils.dp2px(getContext(), 60);
            ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
            int width = ((SizeUtils.getScreenHW((Activity) getContext())[0]) - dp) / 3;
            layoutParams.width = width;

            ImageView ivGoods = baseViewHolder.getView(R.id.iv_goods);
            ViewGroup.LayoutParams ivParams = ivGoods.getLayoutParams();
            ivParams.height = width;
            GlideUtil.loadImg(data.thumb, R.mipmap.logo, ivGoods);
            baseViewHolder.setText(R.id.tv_goods_title, data.title);

            baseViewHolder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                intent.putExtra(GoodsDetailActivity.GOODS_ID,data.id);
                getContext().startActivity(intent);
            });
        }
    }
}
