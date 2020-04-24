package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.BusinessCategoryAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BusinessCategoryBean;
import com.business.cd1236.di.component.DaggerBusinessManageCategoryComponent;
import com.business.cd1236.mvp.contract.BusinessManageCategoryContract;
import com.business.cd1236.mvp.presenter.BusinessManageCategoryPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
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
 * Created by MVPArmsTemplate on 04/23/2020 16:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessManageCategoryActivity extends MyBaseActivity<BusinessManageCategoryPresenter> implements BusinessManageCategoryContract.View, OnItemChildClickListener, OnItemChildLongClickListener {
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.tv_add_category)
    TextView tvAddCategory;
    private BusinessCategoryAdapter businessCategoryAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessManageCategoryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_manage_category; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("管理分类");
        setRightColor(getResources().getColor(R.color.black));
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);
        ArmsUtils.configRecyclerView(rvCategory, new LinearLayoutManager(mActivity));
        businessCategoryAdapter = new BusinessCategoryAdapter(R.layout.item_business_manage_category);
        businessCategoryAdapter.addChildClickViewIds(R.id.tv_edit);
        businessCategoryAdapter.setOnItemChildClickListener(this);
        businessCategoryAdapter.setOnItemChildLongClickListener(this);
        rvCategory.setAdapter(businessCategoryAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.businessCategoty(mActivity);
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

    @OnClick(R.id.tv_add_category)
    public void onViewClicked() {
        launchActivity(new Intent(mActivity, BusinessGoodsCategoryActivity.class));
    }

    @Override
    public void businessCategotySucc(ArrayList<BusinessCategoryBean> businessCategoryBeans) {
        businessCategoryAdapter.setList(businessCategoryBeans);
    }

    @Override
    public void categotyDeleteSucc(String msg) {
        businessCategoryAdapter.remove(businessCategoryBean);
    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        Intent intent = new Intent(mActivity, BusinessGoodsCategoryActivity.class);
        intent.putExtra(BusinessGoodsCategoryActivity.isEdit, (BusinessCategoryBean) adapter.getItem(position));
        launchActivity(intent);
    }

    BusinessCategoryBean businessCategoryBean;

    @Override
    public boolean onItemChildLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        businessCategoryBean = (BusinessCategoryBean) adapter.getItem(position);
        mPresenter.businessCategotyDelete(businessCategoryBean.id, mActivity);
        return true;
    }
}
