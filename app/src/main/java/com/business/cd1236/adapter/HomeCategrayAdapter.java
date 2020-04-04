package com.business.cd1236.adapter;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.business.cd1236.R;
import com.business.cd1236.bean.HomeBannerBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class HomeCategrayAdapter extends BaseQuickAdapter<HomeBannerBean.CategoryBean, BaseViewHolder> {
    public HomeCategrayAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeBannerBean.CategoryBean categoryBean) {
        ImageView iv = baseViewHolder.getView(R.id.iv);
        int dp = SizeUtils.dp2px(getContext(), 30);
        ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
        int width = ((SizeUtils.getScreenHW((Activity) getContext())[0]) - dp)/2 ;
        layoutParams.width = width;
        layoutParams.height = width / 2;
        GlideUtil.loadImg(categoryBean.thumb, iv);
    }
}
