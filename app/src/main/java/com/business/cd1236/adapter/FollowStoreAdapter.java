package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.FollowStoreBean;
import com.business.cd1236.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class FollowStoreAdapter extends BaseQuickAdapter<FollowStoreBean, BaseViewHolder> {
    public FollowStoreAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, FollowStoreBean data) {
        GlideUtil.loadImg(data.shop.logo,baseViewHolder.getView(R.id.iv_icon));
        baseViewHolder.setText(R.id.tv_store_name,data.shop.business_name);

    }
}
