package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerBusinessGoodsManageComponent;
import com.business.cd1236.mvp.contract.BusinessGoodsManageContract;
import com.business.cd1236.mvp.presenter.BusinessGoodsManagePresenter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

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
public class BusinessGoodsManageActivity extends MyBaseActivity<BusinessGoodsManagePresenter> implements BusinessGoodsManageContract.View {

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
        mPresenter.getAllGoods(mActivity);
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
        switch (view.getId()) {
            case R.id.tv_1:
                selectCheck(tv1);
                break;
            case R.id.tv_2:
                selectCheck(tv2);
                break;
            case R.id.tv_3:
                selectCheck(tv3);
                break;
            case R.id.ll_goods_category:
                break;
            case R.id.ll_goods_sort:
                break;
            case R.id.ll_goods_add:
                launchActivity(new Intent(mActivity,BusinessAddGoodsActivity.class));
                break;
        }
    }
}
