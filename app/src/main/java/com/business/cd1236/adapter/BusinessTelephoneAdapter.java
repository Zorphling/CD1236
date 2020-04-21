package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class BusinessTelephoneAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public BusinessTelephoneAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_text, s).setEnabled(R.id.tv_text, !StringUtils.checkString(s));
    }
}
