package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.FollowStoreAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerFollowStoreComponent;
import com.business.cd1236.mvp.contract.FollowStoreContract;
import com.business.cd1236.mvp.presenter.FollowStorePresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/10/2020 14:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class FollowStoreActivity extends MyBaseActivity<FollowStorePresenter> implements FollowStoreContract.View, OnItemClickListener {
    @BindView(R.id.rv_follow_store)
    RecyclerView rvFollowStore;
    FollowStoreAdapter followStoreAdapter;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerFollowStoreComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_follow_store; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("关注店铺");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        ArmsUtils.configRecyclerView(rvFollowStore, new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        followStoreAdapter = new FollowStoreAdapter(R.layout.item_follow_store);
        rvFollowStore.setAdapter(followStoreAdapter);
        followStoreAdapter.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

    }
}
