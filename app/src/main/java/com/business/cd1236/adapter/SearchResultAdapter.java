package com.business.cd1236.adapter;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.business.cd1236.R;
import com.business.cd1236.bean.SearchResultBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class SearchResultAdapter extends BaseQuickAdapter<SearchResultBean.SearchBean, BaseViewHolder> {
    private String jud;

    public SearchResultAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SearchResultBean.SearchBean searchBean) {
        ImageView iv = baseViewHolder.getView(R.id.iv_goods);
        ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
        int width = (int) ((SizeUtils.getScreenHW((Activity) getContext())[0]) - SizeUtils.dp2px(getContext(), 30)) / 2;
        layoutParams.width = width;
        layoutParams.height = (int) (width);
        GlideUtil.loadImg(searchBean.thumb, R.mipmap.logo, iv);

        baseViewHolder.setText(R.id.tv_goods_title, searchBean.title)
                .setText(R.id.tv_sell, "零售价")
                .setText(R.id.tv_price, getContext().getResources().getString(R.string.rmb) + " " + (StringUtils.equals("0", jud) ? searchBean.marketprice : searchBean.agent_marketprice))
        ;
    }

    public void setList(@Nullable Collection<? extends SearchResultBean.SearchBean> list, String jud) {
        this.jud = jud;
        super.setList(list);
    }
}
