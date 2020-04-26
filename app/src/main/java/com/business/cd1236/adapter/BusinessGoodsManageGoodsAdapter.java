package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.BusinessGoodsManageBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class BusinessGoodsManageGoodsAdapter extends BaseQuickAdapter<BusinessGoodsManageBean.GoodsBean, BaseViewHolder> {
    public BusinessGoodsManageGoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, BusinessGoodsManageBean.GoodsBean goodsBean) {
        GlideUtil.loadImg(goodsBean.thumb, R.mipmap.logo, baseViewHolder.getView(R.id.iv_goods));
        baseViewHolder.setText(R.id.tv_goods_name, goodsBean.title)
                .setText(R.id.tv_sales_stock, "销量" + goodsBean.sales + "    " + "库存" + goodsBean.total)
                .setText(R.id.tv_goods_price, getContext().getResources().getString(R.string.rmb) + " " + goodsBean.marketprice + "/" + goodsBean.unit);
        if (StringUtils.equals("1", goodsBean.status)) {//上架状态
            baseViewHolder.setText(R.id.tv_goods_cancel, "下架").setTextColor(R.id.tv_goods_cancel, getContext().getResources().getColor(R.color.colorPrimary));
            baseViewHolder.setBackgroundResource(R.id.tv_goods_cancel, R.drawable.stroke_bg_business_goods_manage);
            baseViewHolder.setGone(R.id.tv_iscancel, true);
        } else {//下架状态
            baseViewHolder.setText(R.id.tv_goods_cancel, "上架").setTextColor(R.id.tv_goods_cancel, getContext().getResources().getColor(android.R.color.white));
            baseViewHolder.setBackgroundResource(R.id.tv_goods_cancel, R.drawable.solid_bg_revise_button);
            baseViewHolder.setVisible(R.id.tv_iscancel, true);
        }
    }
}
