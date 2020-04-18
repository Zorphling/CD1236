package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerBusinessNoticeComponent;
import com.business.cd1236.mvp.contract.BusinessNoticeContract;
import com.business.cd1236.mvp.presenter.BusinessNoticePresenter;
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
 * Created by MVPArmsTemplate on 04/16/2020 14:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessNoticeActivity extends MyBaseActivity<BusinessNoticePresenter> implements BusinessNoticeContract.View {
    @BindView(R.id.tv_save_notice)
    TextView tvSaveNotice;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.et_notice)
    TextInputEditText etNotice;
    public static final String NOTICE = "notice_summary";
    private int TYPE;
    private String CULTURE = "culture";//公告
    private String INTRODUCTION = "introduction";//简介

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessNoticeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_notice; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        TYPE = getIntent().getIntExtra(NOTICE, 1);
        setHeader(TYPE == 1 ? "门店公告" : "门店简介");//"门店简介"
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);
        tvHint.setText(TYPE == 1 ? getResources().getString(R.string.business_notice) : getResources().getString(R.string.business_summary));
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

    @OnClick(R.id.tv_save_notice)
    public void onViewClicked() {
        if (StringUtils.checkString(StringUtils.getEditText(etNotice))) {
            ArmsUtils.snackbarText("请输入" + (TYPE == 1 ? "门店公告" : "门店简介"));
            return;
        }
        mPresenter.updateNotice(TYPE == 1 ? CULTURE : INTRODUCTION, StringUtils.getEditText(etNotice), mActivity);
    }

    @Override
    public void updateNoticeSucc(String msg) {
        MyToastUtils.showShort(msg);
        killMyself();
    }
}
