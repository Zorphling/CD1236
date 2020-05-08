package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.StoreDetailBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.utils.SpannableStringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class StoreDetailAllGoodsAdapter extends BaseQuickAdapter<StoreDetailBean.GoodSsBean, BaseViewHolder> {
    public StoreDetailAllGoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, StoreDetailBean.GoodSsBean goodSsBean) {
        SizeUtils.dp2px(getContext(), 10);
        GlideUtil.loadImg(goodSsBean.thumb, baseViewHolder.getView(R.id.iv_src));

        baseViewHolder.setText(R.id.tv_title, goodSsBean.title).setText(R.id.tv_subtitle, getContext().getResources().getString(R.string.rmb) + goodSsBean.marketprice);
        if (goodSsBean.sales!=null){
            baseViewHolder.setText(R.id.tv_sales, SpannableStringUtils.textColor((goodSsBean.sales == null ? "0" : goodSsBean.sales) + "人付款",
                    getContext().getResources().getColor(R.color.store_detail_goods_sales), 0, goodSsBean.sales.length() + 1));
        }
    }
}
