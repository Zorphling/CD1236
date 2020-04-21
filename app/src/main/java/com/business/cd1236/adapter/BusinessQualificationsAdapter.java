package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.BusinessQualificationsBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class BusinessQualificationsAdapter extends BaseQuickAdapter<BusinessQualificationsBean, BaseViewHolder> {
    public BusinessQualificationsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, BusinessQualificationsBean businessQualificationsBean) {
        baseViewHolder.setText(R.id.tv_subtitle,businessQualificationsBean.subTitle);
    }
}
