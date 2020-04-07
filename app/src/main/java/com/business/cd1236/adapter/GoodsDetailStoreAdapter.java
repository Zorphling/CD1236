package com.business.cd1236.adapter;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.business.cd1236.R;
import com.business.cd1236.bean.GoodsDetailBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class GoodsDetailStoreAdapter extends BaseQuickAdapter<GoodsDetailBean.GoodSsBean, BaseViewHolder> {


    public GoodsDetailStoreAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GoodsDetailBean.GoodSsBean dataBean) {
        LinearLayout ll = baseViewHolder.getView(R.id.ll_detail_goods);
        ImageView ivGoods = baseViewHolder.getView(R.id.iv_goods);
        ViewGroup.LayoutParams llParams = ll.getLayoutParams();
        int dp = SizeUtils.dp2px(getContext(), 40);
        int width = (int) ((SizeUtils.getScreenHW((Activity) getContext())[0]) - dp) / 3;
        llParams.width = width;

        ViewGroup.LayoutParams ivParams = ivGoods.getLayoutParams();
        ivParams.width = width;
        ivParams.height = width;
        GlideUtil.loadImg(dataBean.thumb, ivGoods);

        baseViewHolder.setText(R.id.tv_goods_title, dataBean.title)
                .setText(R.id.tv_goods_price, getContext().getResources().getString(R.string.rmb) + " " + dataBean.marketprice);
    }
}
