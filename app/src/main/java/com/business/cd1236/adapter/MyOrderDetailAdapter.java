package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.MyOrderDetailBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class MyOrderDetailAdapter extends BaseQuickAdapter<MyOrderDetailBean.GoodsBean, BaseViewHolder> {
    public MyOrderDetailAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MyOrderDetailBean.GoodsBean goodsBean) {
        baseViewHolder.setText(R.id.tv_goods_name, goodsBean.title).setText(R.id.tv_goods_num, "x" + goodsBean.total);
    }
}
