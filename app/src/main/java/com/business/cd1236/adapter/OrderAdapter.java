package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.GoodsDetailBean;
import com.business.cd1236.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class OrderAdapter extends BaseQuickAdapter<GoodsDetailBean.GoodsBean, BaseViewHolder> {
    public OrderAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GoodsDetailBean.GoodsBean goodsBean) {
        GlideUtil.loadImg(goodsBean.thumb,baseViewHolder.getView(R.id.riv_item_src));
        baseViewHolder.setText(R.id.tv_goods_title,goodsBean.title)
                .setText(R.id.tv_goods_price,getContext().getResources().getString(R.string.rmb) + " " + goodsBean.marketprice)
                .setText(R.id.et_goods_num, goodsBean.total);
    }
}
