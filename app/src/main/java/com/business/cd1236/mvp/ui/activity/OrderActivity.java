package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.AddAddressBean;
import com.business.cd1236.di.component.DaggerOrderComponent;
import com.business.cd1236.mvp.contract.OrderContract;
import com.business.cd1236.mvp.presenter.OrderPresenter;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.ItemView;
import com.jaeger.library.StatusBarUtil;
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
 * Created by MVPArmsTemplate on 04/05/2020 14:45
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class OrderActivity extends MyBaseActivity<OrderPresenter> implements OrderContract.View {

    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.ll_express)
    LinearLayout llExpress;
    @BindView(R.id.ll_store_cheap)
    ItemView llStoreCheap;
    @BindView(R.id.et_goods_cost_price)
    EditText etGoodsCostPrice;
    @BindView(R.id.tv_goods_sum)
    TextView tvGoodsSum;
    @BindView(R.id.tv_carriage)
    TextView tvCarriage;
    @BindView(R.id.tv_cheap)
    TextView tvCheap;
    @BindView(R.id.tv_amount_paid)
    TextView tvAmountPaid;
    @BindView(R.id.tv_amount_paid_andr_express)
    TextView tvAmountPaidAndrExpress;
    @BindView(R.id.tv_submit_order)
    TextView tvSubmitOrder;
    @BindView(R.id.tv_receiver)
    TextView tvReceiver;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerOrderComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_order; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(mActivity);
        StatusBarUtil.setTranslucent(mActivity, 0);
        setHeader("填写订单");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getDefAddress(mActivity);
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

    @OnClick({R.id.ll_address, R.id.ll_express, R.id.ll_store_cheap, R.id.tv_submit_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                Intent intent = new Intent(mActivity, AddressActivity.class);
                intent.putExtra(AddressActivity.SELECT_ADDRESS, "1");
                startActivityForResult(intent, 100);
                break;
            case R.id.ll_express:
                break;
            case R.id.ll_store_cheap:
                break;
            case R.id.tv_submit_order:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    AddAddressBean bean = data.getParcelableExtra(AddressActivity.SELECT_ADDRESS);
                    if (bean != null) {
                        setAddress(bean);
                    }
                    break;
            }
        }
    }

    private void setAddress(AddAddressBean bean) {
        tvReceiver.setText("收货人：" + bean.realname + "       " + bean.mobile);
        tvAddress.setText(bean.province + bean.city + bean.area + bean.address);
    }

    @Override
    public void getDefAddressSucc(ArrayList<AddAddressBean> addAddressBeans) {
        if (addAddressBeans == null || addAddressBeans.size() == 0) {
            tvReceiver.setText("没有可用的收货地址");
            tvAddress.setText("去选择地址～");
            return;
        }
        for (AddAddressBean addAddressBean : addAddressBeans) {
            if (StringUtils.equals("1", addAddressBean.defaultX)) {
                setAddress(addAddressBean);
                break;
            }
        }
    }
}
