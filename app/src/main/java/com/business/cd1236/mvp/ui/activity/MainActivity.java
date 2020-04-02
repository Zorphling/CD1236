package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerMainComponent;
import com.business.cd1236.mvp.contract.MainContract;
import com.business.cd1236.mvp.presenter.MainPresenter;
import com.business.cd1236.mvp.ui.fragment.HomeFourFragment;
import com.business.cd1236.mvp.ui.fragment.HomeOneFragment;
import com.business.cd1236.mvp.ui.fragment.HomeThreeFragment;
import com.business.cd1236.mvp.ui.fragment.HomeTwoFragment;
import com.business.cd1236.view.homebtn.CircularRevealButton;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

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

    @BindView(R.id.ll_main01)
    CircularRevealButton llMain01;
    @BindView(R.id.ll_main02)
    CircularRevealButton llMain02;
    @BindView(R.id.ll_main03)
    CircularRevealButton llMain03;
    @BindView(R.id.ll_main04)
    CircularRevealButton llMain04;

    private HomeOneFragment homeOneFragment;
    private HomeTwoFragment homeTwoFragment;
    private HomeThreeFragment homeThreeFragment;
    private HomeFourFragment homeFourFragment;
    private List<CircularRevealButton> navs = new ArrayList<>();

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
        homeOneFragment = HomeOneFragment.newInstance();
        homeTwoFragment = HomeTwoFragment.newInstance();
        homeThreeFragment = HomeThreeFragment.newInstance();
        homeFourFragment = HomeFourFragment.newInstance();

        navs.add(llMain01);
        navs.add(llMain02);
        navs.add(llMain03);
        navs.add(llMain04);


        firstLoad();
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
                clickMaiNav(1);
                break;
            case R.id.ll_main02:
                clickMaiNav(2);
                break;
            case R.id.ll_main03:
                clickMaiNav(3);
                break;
            case R.id.ll_main04:
                clickMaiNav(4);
                break;
        }
    }

    /**
     * 点击首页
     */
    int page = 1;

    private void clickMaiNav(int page) {
        if (this.page == page) return;
        this.page = page;
        hideToolBar();
        switch (page) {
            case 1:
                changeNav(page);
                firstLoad();
                break;
            case 2:
                changeNav(page);
                smartReplaceFragment(R.id.fl_home_container, homeTwoFragment);
                setStatusColor(this, true, false, android.R.color.white);
                break;
            case 3:
                changeNav(page);
                smartReplaceFragment(R.id.fl_home_container, homeThreeFragment);
                setStatusColor(this, true, false, android.R.color.white);
                break;
            case 4:
                changeNav(page);
                smartReplaceFragment(R.id.fl_home_container, homeFourFragment);
                setStatusColor(this, true, false, android.R.color.white);
                break;
        }
    }

    private void changeNav(int page) {
        for (int i = 0; i < navs.size(); i++) {
            navs.get(i).setOnSelected(false);
        }
        navs.get(page - 1).setOnSelected(true);
    }

    private void firstLoad() {
        smartReplaceFragment(R.id.fl_home_container, homeOneFragment);
        setStatusColor(this, true, true, android.R.color.white);
    }
}
