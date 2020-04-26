package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.BusinessGoodsManageBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class BusinessGoodsManageGoodsSortAdapter extends BaseQuickAdapter<BusinessGoodsManageBean.GoodsBean, BaseViewHolder> implements DraggableModule {
    public BusinessGoodsManageGoodsSortAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, BusinessGoodsManageBean.GoodsBean goodsBean) {
        baseViewHolder.setText(R.id.tv_goods_name, goodsBean.title)
                .setText(R.id.tv_sales_stock, "销量" + goodsBean.sales + "    " + "库存" + goodsBean.total)
                .setText(R.id.tv_goods_price, getContext().getResources().getString(R.string.rmb) + " " + goodsBean.marketprice + "/" + goodsBean.unit);
    }
}
