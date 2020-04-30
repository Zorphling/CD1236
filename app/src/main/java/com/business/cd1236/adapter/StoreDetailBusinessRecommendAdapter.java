package com.business.cd1236.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import com.business.cd1236.R;
import com.business.cd1236.bean.StoreDetailBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;

import org.jetbrains.annotations.NotNull;

public class StoreDetailBusinessRecommendAdapter extends BaseQuickAdapter<StoreDetailBean.HotBean, BaseViewHolder> {
    public StoreDetailBusinessRecommendAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, StoreDetailBean.HotBean hotBean) {

        int width = (SizeUtils.getScreenHW((Activity) getContext())[0] - SizeUtils.dp2px(getContext(), 60)) / 5;
        RoundedImageView rivImg = baseViewHolder.getView(R.id.riv_img);
        ViewGroup.LayoutParams layoutParams = baseViewHolder.itemView.getLayoutParams();
        ViewGroup.LayoutParams rivParams = rivImg.getLayoutParams();
        layoutParams.width = width;
        rivParams.height = width;
        baseViewHolder.itemView.setLayoutParams(layoutParams);
        GlideUtil.loadImg(hotBean.thumb, R.mipmap.logo, rivImg);
        baseViewHolder.setText(R.id.tv_title, hotBean.title);
    }
}
