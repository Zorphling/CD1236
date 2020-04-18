package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerBusinessTitleComponent;
import com.business.cd1236.mvp.contract.BusinessTitleContract;
import com.business.cd1236.mvp.presenter.BusinessTitlePresenter;
import com.business.cd1236.utils.MyToastUtils;
import com.business.cd1236.utils.StringUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/17/2020 18:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessTitleActivity extends MyBaseActivity<BusinessTitlePresenter> implements BusinessTitleContract.View {

    @BindView(R.id.et_title)
    TextInputEditText etTitle;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessTitleComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_title; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("门店名称");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);
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


    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        if (!StringUtils.checkString(StringUtils.getEditText(etTitle))) {
            ArmsUtils.snackbarText("请输入门店名称");
            return;
        }
        mPresenter.modifyBusinessTitle("basic",StringUtils.getEditText(etTitle),mActivity);
    }

    @Override
    public void modifyTitleSucc(String msg) {
        MyToastUtils.showShort(msg);
        killMyself();
    }

    @Override
    public void modifyTitleFailure(String errorMsg, int status) {
        if (StringUtils.checkString(errorMsg)){
            ArmsUtils.snackbarText(errorMsg);
        }
    }
}
