package com.business.cd1236.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.bean.ShoppingCarBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.MathUtils;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jess.arms.utils.ArmsUtils;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class HomeThreeAdapter extends BaseQuickAdapter<ShoppingCarBean, BaseViewHolder> implements OnItemChildClickListener {
    private OnChangeCarNumListener onChangeCarNumListener;

    public HomeThreeAdapter(int layoutResId, OnChangeCarNumListener onChangeCarNumListener) {
        super(layoutResId);
        this.onChangeCarNumListener = onChangeCarNumListener;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ShoppingCarBean bean) {
        baseViewHolder.setText(R.id.tv_store_title, bean.business_name);
        ((AppCompatCheckBox) baseViewHolder.getView(R.id.check_box)).setChecked(bean.isCheck);
        RecyclerView rvItem = baseViewHolder.getView(R.id.rv_item);

        //滑动会卡顿  待优化，保证只调用一次
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setInitialPrefetchItemCount(50);
        ArmsUtils.configRecyclerView(rvItem, linearLayoutManager);

        rvItem.setHasFixedSize(true);
        rvItem.setNestedScrollingEnabled(false);
        HomeThreeItemAdapter homeThreeItemAdapter = new HomeThreeItemAdapter(R.layout.item_home_three_item, new OnCheckChangeListener() {
            @Override
            public void onCheckChange() {
                HomeThreeAdapter.this.checkAll(baseViewHolder, bean);
            }

            @Override
            public void onGoodsCountChange() {

            }
        });
        rvItem.setAdapter(homeThreeItemAdapter);
        homeThreeItemAdapter.setList(bean.goods);
        homeThreeItemAdapter.addChildClickViewIds(R.id.tv_goods_minus, R.id.tv_goods_add);
        homeThreeItemAdapter.setOnItemChildClickListener(this);

        /**
         * 单个全选
         */
        ((AppCompatCheckBox) baseViewHolder.getView(R.id.check_box)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {//全选
                for (ShoppingCarBean.GoodsBean good : bean.goods) {
                    good.isCheck = isChecked;
                }
            } else {//
                int tempCount = 0;
                for (ShoppingCarBean.GoodsBean good : bean.goods) {
                    if (!good.isCheck) {
                        tempCount = 1;
                    }
                }
                if (tempCount != 0) {//这里好像哪里没对，但我也找不出来哪里没对，暂时这样吧
                } else {
                    for (ShoppingCarBean.GoodsBean good : bean.goods) {
                        good.isCheck = isChecked;
                    }
                }
            }
            homeThreeItemAdapter.notifyDataSetChanged(true);
        });
    }

    /**
     * 检查每个字条目都选择的话 单个全选
     *
     * @param baseViewHolder
     * @param bean
     */
    private void checkAll(@NotNull BaseViewHolder baseViewHolder, ShoppingCarBean bean) {
        /**
         * 更改选择后价格
         */
        double totalPrice = 0;
        for (ShoppingCarBean.GoodsBean good : bean.goods) {
            if (good.isCheck) {
                totalPrice = MathUtils.add(totalPrice, MathUtils.mul(Double.parseDouble(good.marketprice), Double.parseDouble(good.total)));
            }
        }

        baseViewHolder.setText(R.id.tv_amount_to, String.valueOf(new DecimalFormat("#0.00").format(totalPrice)));

        /**
         * 更改选择状态
         */
        boolean isAllCheck = true;
        for (ShoppingCarBean.GoodsBean good : bean.goods) {
            if (!good.isCheck) {
                isAllCheck = false;
            }
        }
        ((AppCompatCheckBox) baseViewHolder.getView(R.id.check_box)).setChecked(isAllCheck);
    }

    public interface OnChangeCarNumListener {
        void changeCarNum(String carId, String total);
    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        EditText etGoodsNum = (EditText) adapter.getViewByPosition(position, R.id.et_goods_num);
        ShoppingCarBean.GoodsBean goodsBean = (ShoppingCarBean.GoodsBean) adapter.getItem(position);
        switch (view.getId()) {
            case R.id.tv_goods_minus:
                if (StringUtils.equals("1", StringUtils.getEditText(etGoodsNum))) {

                } else {
                    String total = String.valueOf(Long.parseLong(StringUtils.getEditText(etGoodsNum)) - 1);
                    etGoodsNum.setText(total);
                }
                break;
            case R.id.tv_goods_add:
                etGoodsNum.setText(String.valueOf(Long.parseLong(StringUtils.getEditText(etGoodsNum)) + 1));
                break;
        }
    }

    public interface OnCheckChangeListener {
        void onCheckChange();
        void onGoodsCountChange();
    }

    class HomeThreeItemAdapter extends BaseQuickAdapter<ShoppingCarBean.GoodsBean, BaseViewHolder> {
        OnCheckChangeListener onCheckChangeListener;
        boolean isNotify;


        public HomeThreeItemAdapter(int layoutResId, OnCheckChangeListener onCheckChangeListener) {
            super(layoutResId);
            this.onCheckChangeListener = onCheckChangeListener;
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, ShoppingCarBean.GoodsBean goodsBean) {
            GlideUtil.loadImg(goodsBean.thumb, baseViewHolder.getView(R.id.riv_item_src));
            EditText etGoodsNum = (EditText) baseViewHolder.getView(R.id.et_goods_num);

            ((AppCompatCheckBox) baseViewHolder.getView(R.id.item_check_box)).setOnCheckedChangeListener((buttonView, isChecked) -> {
                goodsBean.isCheck = isChecked;
                if (onCheckChangeListener != null)
                    onCheckChangeListener.onCheckChange();
            });
            ((AppCompatCheckBox) baseViewHolder.getView(R.id.item_check_box)).setChecked(goodsBean.isCheck);

            baseViewHolder.setText(R.id.tv_goods_title, goodsBean.title).setText(R.id.tv_goods_price,
                    getContext().getResources().getString(R.string.rmb) + " " + goodsBean.marketprice)
                    .setText(R.id.et_goods_num, goodsBean.total);

            if (StringUtils.equals("1", goodsBean.total)) {
                ((TextView) baseViewHolder.getView(R.id.tv_goods_minus)).setTextColor(getContext().getResources().getColor(R.color.colorGray_hintcolor));
            } else {
                ((TextView) baseViewHolder.getView(R.id.tv_goods_minus)).setTextColor(getContext().getResources().getColor(R.color.goods_detail_title));
            }
            etGoodsNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (Long.parseLong(s.toString()) > 1) {
                        ((TextView) baseViewHolder.getView(R.id.tv_goods_minus)).setTextColor(getContext().getResources().getColor(R.color.goods_detail_title));
                    } else {
                        ((TextView) baseViewHolder.getView(R.id.tv_goods_minus)).setTextColor(getContext().getResources().getColor(R.color.colorGray_hintcolor));
                    }
                    if (onChangeCarNumListener != null) {
                        goodsBean.total = s.toString();//更改数量
                        if (!isNotify) {//点击选择会刷新item ，为了不调用下面代码 暂时用这个笨方法屏蔽掉
                            onChangeCarNumListener.changeCarNum(goodsBean.cart_id, s.toString());
                            isNotify = false;
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        public void notifyDataSetChanged(boolean isNotify) {
            this.isNotify = isNotify;
            notifyDataSetChanged();
        }
    }
}
