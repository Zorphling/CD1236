package com.business.cd1236.adapter;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.business.cd1236.R;
import com.business.cd1236.bean.HomeGoodsBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeGoodsAdapter extends BaseQuickAdapter<HomeGoodsBean.DataBean, BaseViewHolder> {

    private String jud = "";

    public HomeGoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeGoodsBean.DataBean dataBean) {
        ImageView iv = baseViewHolder.getView(R.id.iv_goods);
        ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
        int width = (int) ((SizeUtils.getScreenHW((Activity) getContext())[0]) * 0.265);
//        layoutParams.width = width;
        layoutParams.height = (int) (width * 1.4545);
        GlideUtil.loadImg(dataBean.thumb, iv);

//TextUtils.equals("0", jud) ? "¥ " + dataBean.marketprice : "¥ " + dataBean.agent_marketprice
        baseViewHolder.setText(R.id.tv_goods_title, dataBean.title)
                .setText(R.id.tv_goods_price, "¥ " +dataBean.marketprice)
                .setText(R.id.tv_goods_sales, dataBean.sales + "人付款");
    }


    public void loadData(String jud, List<HomeGoodsBean.DataBean> data) {
        this.jud = jud;
        setNewInstance(data);
    }
}
