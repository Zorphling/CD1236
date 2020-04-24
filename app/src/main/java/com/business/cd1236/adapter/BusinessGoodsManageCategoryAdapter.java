package com.business.cd1236.adapter;

import androidx.appcompat.widget.AppCompatCheckedTextView;

import com.business.cd1236.R;
import com.business.cd1236.bean.BusinessGoodsManageBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class BusinessGoodsManageCategoryAdapter extends BaseQuickAdapter<BusinessGoodsManageBean.CategoryBean, BaseViewHolder> {
    public BusinessGoodsManageCategoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, BusinessGoodsManageBean.CategoryBean categoryBean) {
        baseViewHolder.setText(R.id.tv_category,categoryBean.name);
        AppCompatCheckedTextView checkedTextView = baseViewHolder.getView(R.id.tv_category);
        checkedTextView.setChecked(categoryBean.isChecked);
    }
}
