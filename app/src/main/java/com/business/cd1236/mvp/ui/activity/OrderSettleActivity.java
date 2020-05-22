package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.OrderPayBean;
import com.business.cd1236.di.component.DaggerOrderSettleComponent;
import com.business.cd1236.mvp.contract.OrderSettleContract;
import com.business.cd1236.mvp.presenter.OrderSettlePresenter;
import com.business.cd1236.pay.PayCallBack;
import com.business.cd1236.pay.PayUtils;
import com.business.cd1236.utils.MyToastUtils;
import com.business.cd1236.utils.StringUtils;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/17/2020 19:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class OrderSettleActivity extends MyBaseActivity<OrderSettlePresenter> implements OrderSettleContract.View {

    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_order_amount)
    TextView tvOrderAmount;
    @BindView(R.id.btn_pay)
    Button btnPay;
    public static String ORDER_ID = "order_id";
    private String orderId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerOrderSettleComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_order_settle; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(mActivity);
        StatusBarUtil.setTranslucent(mActivity, 0);
        setHeader("我的订单");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);

        orderId = getIntent().getStringExtra(ORDER_ID);
        if (StringUtils.checkString(orderId))
            mPresenter.getOrderMoney(orderId, mActivity);
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

    @OnClick(R.id.btn_pay)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pay:
                mPresenter.pay(mActivity);
                break;
        }
    }

    @Override
    public void getOrderMoneySucc(OrderPayBean orderPayBean) {
        tvOrderId.setText(orderPayBean.ordersn);
        tvOrderAmount.setText(getString(R.string.rmb) + " " + orderPayBean.price);
    }

    @Override
    public void payCallBack(String payCallBack) {
        PayUtils.doAliPay(mActivity, payCallBack, new PayCallBack() {
            @Override
            public void call() {
                MyToastUtils.showShort("支付成功");
            }

            @Override
            public void fail() {

            }
        });
    }
}
