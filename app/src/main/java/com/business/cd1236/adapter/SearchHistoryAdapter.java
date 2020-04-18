package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.SearchHistoryBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class SearchHistoryAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public SearchHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {
        if (o instanceof String) {
            baseViewHolder.setText(R.id.tv_text, (String) o);
        } else {
            baseViewHolder.setText(R.id.tv_text, ((SearchHistoryBean) o).getText());
        }

    }
}
