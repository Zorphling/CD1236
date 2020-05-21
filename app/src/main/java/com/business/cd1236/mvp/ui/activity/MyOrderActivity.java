package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.business.cd1236.R;
import com.business.cd1236.adapter.MyOrderPagerAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerMyOrderComponent;
import com.business.cd1236.mvp.contract.MyOrderContract;
import com.business.cd1236.mvp.presenter.MyOrderPresenter;
import com.business.cd1236.mvp.ui.fragment.MyOrderAllFragment;
import com.business.cd1236.mvp.ui.fragment.MyOrderWaitAppraiseFragment;
import com.business.cd1236.mvp.ui.fragment.MyOrderWaitPayFragment;
import com.business.cd1236.mvp.ui.fragment.MyOrderWaitReceiveFragment;
import com.business.cd1236.mvp.ui.fragment.MyOrderWaitSendFragment;
import com.google.android.material.tabs.TabLayout;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/17/2020 18:42
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MyOrderActivity extends MyBaseActivity<MyOrderPresenter> implements MyOrderContract.View {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager2 viewPager;

    List<Fragment> fragments = new ArrayList();
    String[] titles = {getString(R.string.myorder_all), getString(R.string.myorder_wait_pay),
            getString(R.string.myorder_wait_send), getString(R.string.myorder_wait_receive), getString(R.string.myorder_wait_appraise)};
    private MyOrderPagerAdapter myOrderPagerAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMyOrderComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_my_order; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(mActivity);
        StatusBarUtil.setTranslucent(mActivity, 0);
        setHeader("我的订单");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);

        fragments.add(MyOrderAllFragment.newInstance());
        fragments.add(MyOrderWaitPayFragment.newInstance());
        fragments.add(MyOrderWaitSendFragment.newInstance());
        fragments.add(MyOrderWaitReceiveFragment.newInstance());
        fragments.add(MyOrderWaitAppraiseFragment.newInstance());

        myOrderPagerAdapter = new MyOrderPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(myOrderPagerAdapter);
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
}
