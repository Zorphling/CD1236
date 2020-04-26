package com.business.cd1236.mvp.ui.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.BusinessGoodsManageCategoryAdapter;
import com.business.cd1236.adapter.BusinessGoodsManageGoodsSortAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BusinessGoodsManageBean;
import com.business.cd1236.di.component.DaggerBusinessGoodsManageSortComponent;
import com.business.cd1236.mvp.contract.BusinessGoodsManageSortContract;
import com.business.cd1236.mvp.presenter.BusinessGoodsManageSortPresenter;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/26/2020 11:00
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessGoodsManageSortActivity extends MyBaseActivity<BusinessGoodsManageSortPresenter> implements BusinessGoodsManageSortContract.View, OnItemClickListener {
    @BindView(R.id.rv_left_category)
    RecyclerView rvLeftCategory;
    @BindView(R.id.rv_right_goods)
    RecyclerView rvRightGoods;
    private BusinessGoodsManageCategoryAdapter categoryAdapter;
    private BusinessGoodsManageGoodsSortAdapter businessGoodsManageGoodsSortAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessGoodsManageSortComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_goods_manage_sort; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("商品排序");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        ArmsUtils.configRecyclerView(rvLeftCategory, new LinearLayoutManager(mActivity));
        categoryAdapter = new BusinessGoodsManageCategoryAdapter(R.layout.item_business_goods_manage_category);
        categoryAdapter.setOnItemClickListener(this);
        rvLeftCategory.setAdapter(categoryAdapter);

        ArmsUtils.configRecyclerView(rvRightGoods, new LinearLayoutManager(mActivity));
        businessGoodsManageGoodsSortAdapter = new BusinessGoodsManageGoodsSortAdapter(R.layout.item_business_manage_goods_sort);

        businessGoodsManageGoodsSortAdapter.getDraggableModule().setDragEnabled(true);
        businessGoodsManageGoodsSortAdapter.getDraggableModule().setOnItemDragListener(listener);

        View emptyView = View.inflate(mActivity, R.layout.layout_rv_empty, null);
        ((TextView) emptyView.findViewById(R.id.tv)).setText("快去添加商品吧~");
        businessGoodsManageGoodsSortAdapter.setEmptyView(emptyView);
        View footerView = View.inflate(mActivity, R.layout.view_goods_sort,null);
        footerView.setOnClickListener(v -> {
            sort();
        });
        businessGoodsManageGoodsSortAdapter.addFooterView(footerView);
        rvRightGoods.setAdapter(businessGoodsManageGoodsSortAdapter);

        mPresenter.getAllGoods("all",mActivity);
    }

    @SuppressLint("NewApi")
    private void sort() {
        List<BusinessGoodsManageBean.GoodsBean> data = businessGoodsManageGoodsSortAdapter.getData();
        data.sort((o1, o2) -> Integer.parseInt(o2.sales) - Integer.parseInt(o1.sales));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            builder.append(data.get(i).id).append(",").append(i).append(";");
        }
        mPresenter.goodsSort("goods", builder.substring(0, builder.length() - 1), mActivity);
    }

    // 拖拽监听
    OnItemDragListener listener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
            final BaseViewHolder holder = ((BaseViewHolder) viewHolder);

            // 开始时，item背景色变化，demo这里使用了一个动画渐变，使得自然
            int startColor = Color.WHITE;
            int endColor = Color.rgb(245, 245, 245);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ValueAnimator v = ValueAnimator.ofArgb(startColor, endColor);
                v.addUpdateListener(animation -> holder.itemView.setBackgroundColor((int) animation.getAnimatedValue()));
                v.setDuration(300);
                v.start();
            }
        }

        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
//            Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
        }

        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
            final BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            // 结束时，item背景色变化，demo这里使用了一个动画渐变，使得自然
            int startColor = Color.rgb(245, 245, 245);
            int endColor = Color.WHITE;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ValueAnimator v = ValueAnimator.ofArgb(startColor, endColor);
                v.addUpdateListener(animation -> holder.itemView.setBackgroundColor((int) animation.getAnimatedValue()));
                v.setDuration(300);
                v.start();
            }
            sort();
        }
    };
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    private ArrayList<BusinessGoodsManageBean.CategoryBean> categorys;
    private String CATEGORY_ID;
    private Map<String, List<BusinessGoodsManageBean.GoodsBean>> goodsBeanMap;

    @Override
    public void getAllGoodsSucc(BusinessGoodsManageBean businessGoodsManageBean) {
        categorys = new ArrayList<>();
        goodsBeanMap = new HashMap<>();
        if (businessGoodsManageBean.category.size() > 0) {
            businessGoodsManageBean.category.get(0).isChecked = true;
            CATEGORY_ID = businessGoodsManageBean.category.get(0).id;
            categorys.addAll(businessGoodsManageBean.category);
        }
        categoryAdapter.setList(businessGoodsManageBean.category);

        if (businessGoodsManageBean.goods != null && businessGoodsManageBean.goods.size() > 0) {
            for (BusinessGoodsManageBean.CategoryBean categoryBean : businessGoodsManageBean.category) {
                ArrayList<BusinessGoodsManageBean.GoodsBean> list = new ArrayList<>();
                for (BusinessGoodsManageBean.GoodsBean goods : businessGoodsManageBean.goods) {
                    if (StringUtils.equals(categoryBean.id, goods.category)) {
                        list.add(goods);
                    }
                }
                goodsBeanMap.put(categoryBean.id, list);
            }
        }
        clickCategoty();
    }

    @Override
    public void goodsSortSucc(String jsonString) {
//        for (BusinessGoodsManageBean.GoodsBean datum : businessGoodsManageGoodsSortAdapter.getData()) {
//
//        }
        mPresenter.getAllGoods("all",mActivity);
        ArmsUtils.snackbarText(jsonString);
    }

    private void clickCategoty() {
        for (Map.Entry<String, List<BusinessGoodsManageBean.GoodsBean>> stringListEntry : goodsBeanMap.entrySet()) {
            if (StringUtils.equals(CATEGORY_ID, stringListEntry.getKey())) {
                businessGoodsManageGoodsSortAdapter.setList(stringListEntry.getValue());
            }
        }
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        if (adapter instanceof BusinessGoodsManageCategoryAdapter) {
            for (BusinessGoodsManageBean.CategoryBean bean : (List<BusinessGoodsManageBean.CategoryBean>) adapter.getData()) {
                bean.isChecked = false;
            }
            ((BusinessGoodsManageBean.CategoryBean) adapter.getItem(position)).isChecked = true;
            CATEGORY_ID = ((BusinessGoodsManageBean.CategoryBean) adapter.getItem(position)).id;
            adapter.notifyDataSetChanged();
            clickCategoty();
        } else {

        }
    }
}
