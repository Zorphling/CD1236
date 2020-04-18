package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BusinessCenterBean;
import com.business.cd1236.di.component.DaggerBusinessCenterComponent;
import com.business.cd1236.mvp.contract.BusinessCenterContract;
import com.business.cd1236.mvp.presenter.BusinessCenterPresenter;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.StoreItemView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/08/2020 19:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessCenterActivity extends MyBaseActivity<BusinessCenterPresenter> implements BusinessCenterContract.View {

    @BindView(R.id.tv_in_come)
    TextView tvInCome;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_wait_send_goods)
    TextView tvWaitSendGoods;
    @BindView(R.id.tv_today_order)
    TextView tvTodayOrder;
    @BindView(R.id.item_store_data)
    StoreItemView itemStoreData;
    @BindView(R.id.item_store_goods)
    StoreItemView itemStoreGoods;
    @BindView(R.id.item_store_manage)
    StoreItemView itemStoreManage;
    @BindView(R.id.item_store_trans)
    StoreItemView itemStoreTrans;
    @BindView(R.id.item_store_feedback)
    StoreItemView itemStoreFeedback;
    @BindView(R.id.item_store_service)
    StoreItemView itemStoreService;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessCenterComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_center; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("商户中心");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        mPresenter.getBusinessDetail(mActivity);
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

    @OnClick({R.id.item_store_data, R.id.item_store_goods, R.id.item_store_manage, R.id.item_store_trans, R.id.item_store_feedback, R.id.item_store_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_store_data:
                launchActivity(new Intent(mActivity, BusinessInfoActivity.class));
                break;
            case R.id.item_store_goods:
                break;
            case R.id.item_store_manage:
                break;
            case R.id.item_store_trans:
                break;
            case R.id.item_store_feedback:
                break;
            case R.id.item_store_service:
                break;
        }
    }

    @Override
    public void getStoreDetailSucc(BusinessCenterBean businessCenterBean) {

        tvInCome.setText(StringUtils.checkString(businessCenterBean.cumulative) ? businessCenterBean.cumulative : "0.00");
        tvBalance.setText(StringUtils.checkString(businessCenterBean.balance) ? businessCenterBean.balance : "0.00");
        tvWaitSendGoods.setText(String.valueOf(businessCenterBean.paid));
        tvTodayOrder.setText(String.valueOf(businessCenterBean.yesterday));
    }
}
