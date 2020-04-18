package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BusinessInfoBean;
import com.business.cd1236.di.component.DaggerBusinessBusinessInfoComponent;
import com.business.cd1236.mvp.contract.BusinessBusinessInfoContract;
import com.business.cd1236.mvp.presenter.BusinessBusinessInfoPresenter;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.ItemView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/16/2020 11:11
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessBusinessInfoActivity extends MyBaseActivity<BusinessBusinessInfoPresenter> implements BusinessBusinessInfoContract.View {

    @BindView(R.id.item1)
    ItemView item1;
    @BindView(R.id.item2)
    ItemView item2;
    @BindView(R.id.item3)
    ItemView item3;
    private BusinessInfoBean businessInfoBean;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessBusinessInfoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_business_info; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("营业信息");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        businessInfoBean = getIntent().getParcelableExtra(BusinessInfoActivity.INFO);
        if (businessInfoBean != null) {
            if (StringUtils.checkString(businessInfoBean.open_time)) {
                item1.setSubTitleText(businessInfoBean.open_time + "-" + businessInfoBean.close_time + "," + businessInfoBean.open_time1 + "-" + businessInfoBean.close_time1);
            }
//            if (StringUtils.checkString(businessInfoBean.)) TODO 店铺公告接口似乎没返回
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getBusinessInfo(mActivity);
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

    @OnClick({R.id.item1, R.id.item2, R.id.item3})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.item1:
                intent.setClass(mActivity, BusinessTimeActivity.class);
                break;
            case R.id.item2:
                intent.setClass(mActivity, BusinessNoticeActivity.class);
                intent.putExtra(BusinessNoticeActivity.NOTICE, 1);
                break;
            case R.id.item3:
                intent.setClass(mActivity, BusinessNoticeActivity.class);
                intent.putExtra(BusinessNoticeActivity.NOTICE, 2);
                break;
        }
        launchActivity(intent);
    }

    @Override
    public void setBusinessInfo(BusinessInfoBean businessInfoBean) {
        if (StringUtils.checkString(businessInfoBean.open_time)) {
            item1.setSubTitleText(businessInfoBean.open_time + "-" + businessInfoBean.close_time + "," + businessInfoBean.open_time1 + "-" + businessInfoBean.close_time1);
        }
    }
}
