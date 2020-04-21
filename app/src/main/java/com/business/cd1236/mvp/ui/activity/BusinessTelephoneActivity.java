package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.BusinessTelephoneAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BusinessInfoBean;
import com.business.cd1236.di.component.DaggerBusinessTelephoneComponent;
import com.business.cd1236.mvp.contract.BusinessTelephoneContract;
import com.business.cd1236.mvp.presenter.BusinessTelephonePresenter;
import com.business.cd1236.utils.LogUtils;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
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
 * Created by MVPArmsTemplate on 04/18/2020 13:53
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessTelephoneActivity extends MyBaseActivity<BusinessTelephonePresenter> implements BusinessTelephoneContract.View, OnItemChildClickListener {

    @BindView(R.id.rv_telephone)
    RecyclerView rvTelephone;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    private BusinessTelephoneAdapter stringAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessTelephoneComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_telephone; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("订单电话");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        ArmsUtils.configRecyclerView(rvTelephone, new LinearLayoutManager(mActivity));
        rvTelephone.setHasFixedSize(false);
        stringAdapter = new BusinessTelephoneAdapter(R.layout.item_business_telephone);
        stringAdapter.addChildClickViewIds(R.id.tv_delete);
        stringAdapter.setOnItemChildClickListener(this);
        rvTelephone.setAdapter(stringAdapter);

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

    @OnClick({R.id.tv_add, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                stringAdapter.addData("");
                checkItemCount();
                break;
            case R.id.btn_submit:
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < stringAdapter.getItemCount(); i++) {
                    EditText editText = (EditText) stringAdapter.getViewByPosition(i, R.id.tv_text);
                    if (StringUtils.checkString(StringUtils.getEditText(editText)))
                        builder.append(StringUtils.getEditText(editText)).append(",");
                }
                mPresenter.addBusinessTelephone("phone", builder.substring(0, builder.length() - 1), mActivity);
                break;
        }
    }

    private void checkItemCount() {
        if (stringAdapter.getItemCount() == 3) {
            tvAdd.setVisibility(View.GONE);
        } else {
            tvAdd.setVisibility(View.VISIBLE);
        }
    }

    private ArrayList<String> telephones = new ArrayList<>();

    @Override
    public void setBusinessInfo(BusinessInfoBean businessInfoBean) {
        telephones.addAll(businessInfoBean.telephone);
        stringAdapter.setList(telephones);
    }

    @Override
    public void businessTeleSucc(String msg) {
        ArmsUtils.snackbarText(msg);
    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        adapter.remove(position);
        checkItemCount();
    }
}
