package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.BusinessGoodsManageCategoryAdapter;
import com.business.cd1236.adapter.BusinessGoodsManageGoodsAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BusinessGoodsManageBean;
import com.business.cd1236.di.component.DaggerBusinessGoodsManageComponent;
import com.business.cd1236.mvp.contract.BusinessGoodsManageContract;
import com.business.cd1236.mvp.presenter.BusinessGoodsManagePresenter;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.dialog.AlertDialog;
import com.business.cd1236.view.dialog.AlertEditDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/21/2020 15:16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessGoodsManageActivity extends MyBaseActivity<BusinessGoodsManagePresenter> implements BusinessGoodsManageContract.View, OnItemClickListener, OnItemChildClickListener, AlertEditDialog.OnPosClickListener {

    @BindView(R.id.tv_1)
    CheckedTextView tv1;
    @BindView(R.id.tv_2)
    CheckedTextView tv2;
    @BindView(R.id.tv_3)
    CheckedTextView tv3;
    @BindView(R.id.rv_left_category)
    RecyclerView rvLeftCategory;
    @BindView(R.id.rv_right_goods)
    RecyclerView rvRightGoods;
    @BindView(R.id.ll_goods_category)
    LinearLayout llGoodsCategory;
    @BindView(R.id.ll_goods_sort)
    LinearLayout llGoodsSort;
    @BindView(R.id.ll_goods_add)
    LinearLayout llGoodsAdd;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    private ArrayList<CheckedTextView> tvs = new ArrayList<>();
    private BusinessGoodsManageCategoryAdapter categoryAdapter;
    private BusinessGoodsManageGoodsAdapter businessGoodsManageGoodsAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessGoodsManageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_goods_manage; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("商品管理");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        tvs.add(tv1);
        tvs.add(tv2);
        tvs.add(tv3);

        ArmsUtils.configRecyclerView(rvLeftCategory, new LinearLayoutManager(mActivity));
        categoryAdapter = new BusinessGoodsManageCategoryAdapter(R.layout.item_business_goods_manage_category);
        categoryAdapter.setOnItemClickListener(this);
        rvLeftCategory.setAdapter(categoryAdapter);

        ArmsUtils.configRecyclerView(rvRightGoods, new LinearLayoutManager(mActivity));
        businessGoodsManageGoodsAdapter = new BusinessGoodsManageGoodsAdapter(R.layout.item_business_goods_manage_goods);
        View emptyView = View.inflate(mActivity, R.layout.layout_rv_empty, null);
        ((TextView) emptyView.findViewById(R.id.tv)).setText("快去添加商品吧~");
        businessGoodsManageGoodsAdapter.setEmptyView(emptyView);
        businessGoodsManageGoodsAdapter.addChildClickViewIds(R.id.tv_goods_cancel, R.id.tv_revise_price, R.id.tv_revise_stock, R.id.tv_edit_goods);
        businessGoodsManageGoodsAdapter.setOnItemChildClickListener(this);
        rvRightGoods.setAdapter(businessGoodsManageGoodsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllGoods("all");

    }

    private void getAllGoods(String type) {
        mPresenter.getAllGoods(type, mActivity);
    }

    private void selectCheck(CheckedTextView ctv) {
        for (CheckedTextView tv : tvs) {
            tv.setChecked(false);
        }
        ctv.setChecked(true);
    }

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


    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.ll_goods_category, R.id.ll_goods_sort, R.id.ll_goods_add})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_1:
                selectCheck(tv1);
                getAllGoods("all");
                break;
            case R.id.tv_2:
                selectCheck(tv2);
                getAllGoods("upper");
                break;
            case R.id.tv_3:
                selectCheck(tv3);
                getAllGoods("under");
                break;
            case R.id.ll_goods_category:
                launchActivity(new Intent(mActivity, BusinessManageCategoryActivity.class));
                break;
            case R.id.ll_goods_sort:
                launchActivity(new Intent(mActivity, BusinessGoodsManageSortActivity.class));
                break;
            case R.id.ll_goods_add:
                intent.setClass(mActivity, BusinessAddGoodsActivity.class);
                intent.putExtra(BusinessAddGoodsActivity.CATEGORY_INTENT, categorys);
                launchActivity(intent);
                break;
        }
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

    private void clickCategoty() {
        businessGoodsManageGoodsAdapter.setList(null);
        for (Map.Entry<String, List<BusinessGoodsManageBean.GoodsBean>> stringListEntry : goodsBeanMap.entrySet()) {
            if (StringUtils.equals(CATEGORY_ID, stringListEntry.getKey())) {
                businessGoodsManageGoodsAdapter.setList(stringListEntry.getValue());
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

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        BusinessGoodsManageBean.GoodsBean goodsBean = (BusinessGoodsManageBean.GoodsBean) adapter.getItem(position);
        if (adapter instanceof BusinessGoodsManageGoodsAdapter) {
            switch (view.getId()) {
                case R.id.tv_goods_cancel: //下架0，上架1    name
                    new AlertDialog(mActivity).builder().setMsg("确定" + (StringUtils.equals("0", goodsBean.status) ? "上架" : "下架") + "该商品吗").setNegativeButton("取消", null).setPositiveButton("确定", v -> {
                        mPresenter.businessGoodsQuick(goodsBean.id, "status", StringUtils.equals("0", goodsBean.status) ? "1" : "0", position, mActivity);
                    }).show();
                    break;
                case R.id.tv_revise_price:
                    new AlertEditDialog(mActivity, goodsBean, "marketprice", position, this);
                    break;
                case R.id.tv_revise_stock:
                    new AlertEditDialog(mActivity, goodsBean, "total", position, this);
                    break;
                case R.id.tv_edit_goods:
                    Intent intent = new Intent(mActivity, BusinessAddGoodsActivity.class);
                    intent.putExtra(BusinessAddGoodsActivity.GOODS_INTENT, goodsBean);
                    intent.putExtra(BusinessAddGoodsActivity.CATEGORY_INTENT, categorys);
                    launchActivity(intent);
                    break;
            }
        } else {

        }
    }

    @Override
    public void businessGoodsQuickSucc(String type, String name, String msg, int position) {
        ArmsUtils.snackbarText(msg);
        BusinessGoodsManageBean.GoodsBean bean = businessGoodsManageGoodsAdapter.getItem(position);
        switch (type) {
            case "status":
                bean.status = name;
                break;
            case "marketprice":
                bean.marketprice = name;
                break;
            case "total":
                bean.total = name;
                break;
        }
        businessGoodsManageGoodsAdapter.notifyItemChanged(position);

    }

    @Override
    public void onPosClick(Object obj, String type, int position, String editText) {
        mPresenter.businessGoodsQuick(((BusinessGoodsManageBean.GoodsBean) obj).id, type, editText, position, mActivity);
    }
}
