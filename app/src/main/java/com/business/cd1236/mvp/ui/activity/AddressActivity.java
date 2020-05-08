package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.AddressManageAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.AddAddressBean;
import com.business.cd1236.di.component.DaggerAddressComponent;
import com.business.cd1236.mvp.contract.AddressContract;
import com.business.cd1236.mvp.presenter.AddressPresenter;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
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
 * Created by MVPArmsTemplate on 04/09/2020 11:23
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class AddressActivity extends MyBaseActivity<AddressPresenter> implements AddressContract.View, OnItemClickListener {

    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    private AddressManageAdapter addressManageAdapter;
    public static String SELECT_ADDRESS = "select_address";
    private String isSelectAddress = "0";

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAddressComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_address; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("地址管理");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);
        isSelectAddress = getIntent().getStringExtra(SELECT_ADDRESS);

        ArmsUtils.configRecyclerView(rvAddress, new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        addressManageAdapter = new AddressManageAdapter(R.layout.item_address_manage);

        if (StringUtils.equals("1", isSelectAddress)) {
            addressManageAdapter.setOnItemClickListener(this);
        }

        addressManageAdapter.addChildClickViewIds(R.id.ll_set_def_address, R.id.tv_delete_address, R.id.tv_edit_address);
        addressManageAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            AddAddressBean bean = (AddAddressBean) adapter.getItem(position);
            switch (view.getId()) {
                case R.id.ll_set_def_address:
                    if (StringUtils.equals("0", bean.defaultX)) {
                        mPresenter.setDefAddress(bean.id, mActivity);
                    } else {
                        ArmsUtils.snackbarText("当前地址已是默认地址");
                    }
                    break;
                case R.id.tv_delete_address:
                    mPresenter.deleteAddress(bean.id, mActivity);
                    break;
                case R.id.tv_edit_address:
                    Intent intent = new Intent(mActivity, AddAddressActivity.class);
                    intent.putExtra(AddAddressActivity.EDIT, bean);
                    launchActivity(intent);
                    break;
            }
        });
        rvAddress.setAdapter(addressManageAdapter);

        mPresenter.getAddress(mActivity);
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
    public void setAddress(ArrayList<AddAddressBean> addAddressBeans) {
        addressManageAdapter.setList(addAddressBeans);
    }

    @Override
    public void setDefAddress() {
        mPresenter.getAddress(mActivity);
    }

    @Override
    public void deleteAddressSuccess() {
        mPresenter.getAddress(mActivity);
    }

    @OnClick(R.id.btn_add_address)
    public void onViewClicked() {
        startActivityForResult(new Intent(mActivity, AddAddressActivity.class), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                mPresenter.getAddress(mActivity);
            }
        }
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        AddAddressBean bean = (AddAddressBean) adapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra(SELECT_ADDRESS,bean);
        setResult(RESULT_OK,intent);
        killMyself();
    }
}
