package com.business.cd1236.adapter;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.bean.MyOrderBean;
import com.business.cd1236.mvp.ui.activity.MyOrderDetailActivity;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SpannableStringUtils;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jess.arms.utils.ArmsUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//上海果木烤鸭店
public class MyOrderAdapter extends BaseQuickAdapter<MyOrderBean, BaseViewHolder> {

    public MyOrderAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MyOrderBean myOrderBean) {
        String content = String.format(getContext().getString(R.string.myorder_total_prices), myOrderBean.total + "", myOrderBean.price);

//
        baseViewHolder.setText(R.id.tv_goods_num_and_totalprices,
                SpannableStringUtils.textColor(content, getContext().getResources().getColor(R.color.my_order_status),
                        content.indexOf(getContext().getString(R.string.rmb)), content.length()));
        baseViewHolder.setText(R.id.tv_store_title, myOrderBean.business_name)
                .setVisible(R.id.ll_buttons, true);
        if (StringUtils.equals("0", myOrderBean.jud)) {//0普通
            baseViewHolder.setGone(R.id.iv_iswholesale, true);
        } else {
            baseViewHolder.setVisible(R.id.iv_iswholesale, true);
        }
        switch (myOrderBean.status) {
            case "0"://待付款
                baseViewHolder.setText(R.id.tv_status, "待付款").setText(R.id.tv_order_status_pay, "付款")
                .setVisible(R.id.tv_order_status_pay,true).setGone(R.id.tv_order_status_cancel,true);
                break;
            case "1"://待发货
                baseViewHolder.setText(R.id.tv_status, "待发货").setText(R.id.tv_order_status_cancel, "提醒发货")
                .setGone(R.id.tv_order_status_pay,true).setVisible(R.id.tv_order_status_cancel,true);
                break;
            case "2"://待收货
                baseViewHolder.setText(R.id.tv_status, "待收货").setText(R.id.tv_order_status_pay, "确认收货")
                .setVisible(R.id.tv_order_status_pay,true).setGone(R.id.tv_order_status_cancel,true);
                break;
            case "3"://已完成
                baseViewHolder.setText(R.id.tv_status, "交易成功").setText(R.id.tv_order_status_pay, "交易完成")
                .setGone(R.id.ll_buttons, true);
                break;
            default:
                baseViewHolder.setText(R.id.tv_status, "未知的交易状态").setGone(R.id.ll_buttons, true);
                break;
        }
        RecyclerView rvGoods = baseViewHolder.getView(R.id.rv_goods);
        ArmsUtils.configRecyclerView(rvGoods, new LinearLayoutManager(getContext()));
        rvGoods.setAdapter(new OrderGoodsAdapter(myOrderBean.goods));

        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyOrderDetailActivity.class);
                intent.putExtra(MyOrderDetailActivity.ORDER_ID,myOrderBean.id);
                getContext().startActivity(intent);
            }
        });
        rvGoods.setOnTouchListener((v, event) -> baseViewHolder.itemView.onTouchEvent(event));

    }

    class OrderGoodsAdapter extends BaseQuickAdapter<MyOrderBean.GoodsBean, BaseViewHolder> {

        public OrderGoodsAdapter(@Nullable List<MyOrderBean.GoodsBean> data) {
            super(R.layout.item_my_order_goods, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, MyOrderBean.GoodsBean goodsBean) {
            GlideUtil.loadImg(goodsBean.thumb, baseViewHolder.getView(R.id.riv_goods));
            baseViewHolder.setText(R.id.tv_goods_title, goodsBean.title)
                    .setText(R.id.tv_goods_price, getContext().getString(R.string.rmb) + " " + goodsBean.price)
                    .setText(R.id.tv_goods_num, "x" + goodsBean.total);
        }
    }
}
