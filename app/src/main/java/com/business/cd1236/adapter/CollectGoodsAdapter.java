package com.business.cd1236.adapter;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.business.cd1236.R;
import com.business.cd1236.bean.CollectGoodsBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CollectGoodsAdapter extends BaseQuickAdapter<CollectGoodsBean.NewBean, BaseViewHolder> {
    private boolean isGrid;
    private int width;
    private String jud;
    private OnCheckedChangeListener onCheckedChangeListener;

    public CollectGoodsAdapter(int layoutResId, boolean isGrid, OnCheckedChangeListener onCheckedChangeListener) {
        super(layoutResId);
        this.isGrid = isGrid;
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CollectGoodsBean.NewBean newBean) {
        width = (SizeUtils.getScreenHW((Activity) getContext())[0] - SizeUtils.dp2px(getContext(), 40)) / 3;
        ImageView ivGoodsIcon = baseViewHolder.getView(R.id.iv_goods_icon);
        if (isGrid) {
            ViewGroup.LayoutParams layoutParams = ivGoodsIcon.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = width;
            GlideUtil.loadImg(newBean.thumb, ivGoodsIcon);
        } else {
            GlideUtil.loadImg(newBean.thumb, ivGoodsIcon);
            baseViewHolder.setText(R.id.tv_title, newBean.title)
//                    .setText(R.id.collect_num,newBean.)
                    .setText(R.id.tv_price, getContext().getString(R.string.rmb) + (StringUtils.equals("0", jud) ? newBean.marketprice : newBean.agent_marketprice));
        }
        if (newBean.showCheckBox) {
            baseViewHolder.setVisible(R.id.check_box, true);
        } else {
            baseViewHolder.setGone(R.id.check_box, true);
        }
        ((CheckBox) baseViewHolder.getView(R.id.check_box)).setChecked(newBean.isCheck);

    }

    public void setNewInstance(@Nullable List<CollectGoodsBean.NewBean> list, String jud) {
        setList(list);
        this.jud = jud;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChange(int position);
    }


    public void showCheckBox(boolean showCheckBox) {

    }
}
