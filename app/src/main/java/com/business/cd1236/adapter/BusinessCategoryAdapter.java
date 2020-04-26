package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.BusinessCategoryBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class BusinessCategoryAdapter extends BaseQuickAdapter<BusinessCategoryBean, BaseViewHolder> implements DraggableModule{
    public BusinessCategoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, BusinessCategoryBean businessCategoryBean) {
        baseViewHolder.setText(R.id.tv_name, businessCategoryBean.name);
        if (businessCategoryBean.isDrag) {
            baseViewHolder.setVisible(R.id.iv_drag, true).setGone(R.id.tv_edit, true);
        } else {
            baseViewHolder.setVisible(R.id.tv_edit, true).setGone(R.id.iv_drag, true);
        }
    }
}
