package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.GoodsDetailBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderAdapter extends BaseQuickAdapter<GoodsDetailBean.GoodsBean, BaseViewHolder> {
    public OrderAdapter(int layoutResId) {
        super(layoutResId);
    }

    private String jud;

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GoodsDetailBean.GoodsBean goodsBean) {
        GlideUtil.loadImg(goodsBean.thumb, baseViewHolder.getView(R.id.riv_item_src));
        baseViewHolder.setText(R.id.tv_goods_title, goodsBean.title)
                .setText(R.id.tv_goods_price, getContext().getResources().getString(R.string.rmb) + " " + (StringUtils.equals("0", goodsBean.marketprice) ? goodsBean.marketprice : goodsBean.agent_marketprice))
                .setText(R.id.et_goods_num, goodsBean.total);
    }

    public void setList(String jud, List<GoodsDetailBean.GoodsBean> goods) {
        setList(goods);
        this.jud = jud;
    }
}
