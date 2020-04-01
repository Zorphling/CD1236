package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerMainComponent;
import com.business.cd1236.mvp.contract.MainContract;
import com.business.cd1236.mvp.presenter.MainPresenter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/01/2020 16:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends MyBaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.iv_main01)
    ImageView ivMain01;
    @BindView(R.id.tv_main01)
    CheckedTextView tvMain01;
    @BindView(R.id.ll_main01)
    LinearLayout llMain01;
    @BindView(R.id.iv_main02)
    ImageView ivMain02;
    @BindView(R.id.tv_main02)
    TextView tvMain02;
    @BindView(R.id.ll_main02)
    LinearLayout llMain02;
    @BindView(R.id.iv_main03)
    ImageView ivMain03;
    @BindView(R.id.tv_main03)
    TextView tvMain03;
    @BindView(R.id.ll_main03)
    LinearLayout llMain03;
    @BindView(R.id.iv_main04)
    ImageView ivMain04;
    @BindView(R.id.tv_main04)
    TextView tvMain04;
    @BindView(R.id.ll_main04)
    LinearLayout llMain04;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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

    @OnClick({R.id.ll_main01, R.id.ll_main02, R.id.ll_main03, R.id.ll_main04})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main01:
                clickHomeOne();
                break;
            case R.id.ll_main02:
                break;
            case R.id.ll_main03:
                break;
            case R.id.ll_main04:
                break;
        }
    }

    /**
     * 点击首页
     */
    private void clickHomeOne() {
//        hideToolBar();
//        ClickedSelect(0);
//        smartReplaceFragment(R.id.fl_content, mHomeOneFragment);
    }
}
