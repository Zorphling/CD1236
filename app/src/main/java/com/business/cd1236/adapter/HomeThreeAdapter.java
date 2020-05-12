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
import com.business.cd1236.bean.GoodsDetailBean;
import com.business.cd1236.bean.ShoppingCarBean;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.LogUtils;
import com.business.cd1236.utils.MathUtils;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jess.arms.utils.ArmsUtils;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HomeThreeAdapter extends BaseQuickAdapter<ShoppingCarBean, BaseViewHolder> implements OnItemChildClickListener {
    private OnChangeCarNumListener onChangeCarNumListener;

    public HomeThreeAdapter(int layoutResId, OnChangeCarNumListener onChangeCarNumListener) {
        super(layoutResId);
        this.onChangeCarNumListener = onChangeCarNumListener;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ShoppingCarBean bean) {
        baseViewHolder.setText(R.id.tv_store_title, bean.business_name);

        RecyclerView rvItem = baseViewHolder.getView(R.id.rv_item);

        //滑动会卡顿  待优化，保证只调用一次
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setInitialPrefetchItemCount(50);
        ArmsUtils.configRecyclerView(rvItem, linearLayoutManager);

//        rvItem.setHasFixedSize(true);//使用这个remove会有空白
        rvItem.setNestedScrollingEnabled(false);
        HomeThreeItemAdapter homeThreeItemAdapter = new HomeThreeItemAdapter(R.layout.item_home_three_item, new OnCheckChangeListener() {
            @Override
            public void onCheckChange() {
                HomeThreeAdapter.this.checkAll(baseViewHolder, bean);
            }

            @Override
            public void onGoodsCountChange() {
                changeTotalPrice(baseViewHolder, bean);
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
                bean.isCheck = isChecked;
                for (GoodsDetailBean.GoodsBean good : bean.goods) {
                    good.isCheck = isChecked;
                }
            } else {//
                int tempCount = 0;
                for (GoodsDetailBean.GoodsBean good : bean.goods) {
                    if (!good.isCheck) {
                        tempCount = 1;
                    }
                }
                if (tempCount != 0) {//这里好像哪里没对，但我也找不出来哪里没对，暂时这样吧
                    //但凡有一个小item没被选中
                } else {
                    bean.isCheck = isChecked;
                    for (GoodsDetailBean.GoodsBean good : bean.goods) {
                        good.isCheck = isChecked;
                    }
                }
            }
            if (rvItem.isComputingLayout()) {
                rvItem.post(() -> homeThreeItemAdapter.notifyDataSetChanged());
            } else {
                homeThreeItemAdapter.notifyDataSetChanged();
            }

            //改变最外面全选的监听
            if (onChangeCarNumListener != null) {
                int isCheckedTemp = 0;
                for (ShoppingCarBean datum : getData()) {
                    if (datum.isCheck) isCheckedTemp++;
                }
//                LogUtils.e(isCheckedTemp + " =============== " + getData().size());
                if (isCheckedTemp != getData().size()) {
                    onChangeCarNumListener.onAllChecked(false);//这里单独不选中最里面item 会回调最外面的isChecked 导致其他外部item全不选
                } else {
                    onChangeCarNumListener.onAllChecked(true);
                }
            }
        });
        ((AppCompatCheckBox) baseViewHolder.getView(R.id.check_box)).setChecked(bean.isCheck);
    }

    /**
     * 检查每个字条目都选择的话 单个全选
     *
     * @param baseViewHolder
     * @param bean
     */
    private void checkAll(@NotNull BaseViewHolder baseViewHolder, ShoppingCarBean bean) {
        changeTotalPrice(baseViewHolder, bean);

        /**
         * 更改选择状态
         */
        boolean isAllCheck = true;
        for (GoodsDetailBean.GoodsBean good : bean.goods) {
            if (!good.isCheck) {
                isAllCheck = false;
            }
        }
        bean.isCheck = isAllCheck;//记录主条目的选择状态
        ((AppCompatCheckBox) baseViewHolder.getView(R.id.check_box)).setChecked(isAllCheck);
    }

    private void changeTotalPrice(@NotNull BaseViewHolder baseViewHolder, ShoppingCarBean bean) {
        /**
         * 更改选择后价格
         */
        double totalPrice = 0;
        for (GoodsDetailBean.GoodsBean good : bean.goods) {
            if (good.isCheck) {
                totalPrice = MathUtils.add(totalPrice, MathUtils.mul(Double.parseDouble(good.marketprice), Double.parseDouble(good.total)));
            }
        }

        baseViewHolder.setText(R.id.tv_amount_to, String.valueOf(new DecimalFormat("#0.00").format(totalPrice)));
    }

    public void notifyRemoved(ArrayList<GoodsDetailBean.GoodsBean> goodsBeanTemp) {
        for (int i = 0; i < getData().size(); i++) {
            LogUtils.e("进来次数：：" + i);
            if (getData().get(i).isCheck) {
                LogUtils.e("notifyRemoved ========== remove:::" + i);
                remove(i);
                continue;
            }
            for (int j = 0; j < getData().get(i).goods.size(); j++) {
                for (GoodsDetailBean.GoodsBean goodsBean : goodsBeanTemp) {
                    if (StringUtils.equals(getData().get(i).goods.get(j).id, goodsBean.id)) {
                        RecyclerView rvItem = (RecyclerView) getViewByPosition(i, R.id.rv_item);
                        ((HomeThreeItemAdapter) rvItem.getAdapter()).remove(j);
                        LogUtils.e("notifyRemoved ========== removeremoveremove :::" + i);
                    }
                }
            }
        }
    }

    public interface OnChangeCarNumListener {
        void changeCarNum(String carId, String total);

        void onAllChecked(boolean isAllChecked);
    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        EditText etGoodsNum = (EditText) adapter.getViewByPosition(position, R.id.et_goods_num);
        GoodsDetailBean.GoodsBean goodsBean = (GoodsDetailBean.GoodsBean) adapter.getItem(position);
        switch (view.getId()) {
            case R.id.tv_goods_minus:
                if (StringUtils.equals("1", StringUtils.getEditText(etGoodsNum))) {

                } else {
                    String total = String.valueOf(Long.parseLong(StringUtils.getEditText(etGoodsNum)) - 1);
                    if (onChangeCarNumListener != null)
                        onChangeCarNumListener.changeCarNum(goodsBean.cart_id, total);
                    etGoodsNum.setText(total);
                }
                break;
            case R.id.tv_goods_add:
                String total = String.valueOf(Long.parseLong(StringUtils.getEditText(etGoodsNum)) + 1);
                if (onChangeCarNumListener != null)
                    onChangeCarNumListener.changeCarNum(goodsBean.cart_id, total);
                etGoodsNum.setText(total);
                break;
        }
    }

    public interface OnCheckChangeListener {
        void onCheckChange();

        void onGoodsCountChange();
    }

    class HomeThreeItemAdapter extends BaseQuickAdapter<GoodsDetailBean.GoodsBean, BaseViewHolder> {
        OnCheckChangeListener onCheckChangeListener;
        private TextWatcher watcher;


        public HomeThreeItemAdapter(int layoutResId, OnCheckChangeListener onCheckChangeListener) {
            super(layoutResId);
            this.onCheckChangeListener = onCheckChangeListener;
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, GoodsDetailBean.GoodsBean goodsBean) {
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

            watcher = new TextWatcher() {
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

                        //本来是这里在数量变化后再请求接口变化购物车数量，但是全选会刷新条目会造成多次请求
                        //onChangeCarNumListener.changeCarNum(goodsBean.cart_id, s.toString());

                    }
                    if (onCheckChangeListener != null) {
                        onCheckChangeListener.onGoodsCountChange();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            };
            etGoodsNum.addTextChangedListener(watcher);
        }
    }
}
