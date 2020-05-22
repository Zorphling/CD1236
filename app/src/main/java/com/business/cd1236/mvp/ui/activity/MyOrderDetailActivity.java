package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.MyOrderDetailAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.MyOrderDetailBean;
import com.business.cd1236.di.component.DaggerMyOrderDetailComponent;
import com.business.cd1236.mvp.contract.MyOrderDetailContract;
import com.business.cd1236.mvp.presenter.MyOrderDetailPresenter;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.TimeUtils;
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
 * Created by MVPArmsTemplate on 05/22/2020 10:09
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MyOrderDetailActivity extends MyBaseActivity<MyOrderDetailPresenter> implements MyOrderDetailContract.View {

    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.tv_receiver_name)
    TextView tvReceiverName;
    @BindView(R.id.tv_receiver_address)
    TextView tvReceiverAddress;
    @BindView(R.id.iv_call)
    ImageView ivCall;
    @BindView(R.id.ll_look_more)
    LinearLayout llLookMore;
    @BindView(R.id.iv_arrow_down)
    ImageView ivArrowDown;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;

    public static String ORDER_ID = "order_id";
    public String order_id;
    private MyOrderDetailAdapter myOrderDetailAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMyOrderDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_my_order_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setColor(mActivity,getRColor(R.color.colorPrimary),0);
//        StatusBarUtil.setLightMode(mActivity);
//        StatusBarUtil.setTranslucent(mActivity, 0);
        setHeader("订单详情");
        setHeaderColor(getRColor(R.color.colorPrimary), getRColor(android.R.color.black), R.mipmap.arrow_left_black);
        setRightBtn("", R.mipmap.icon_notice_black, v -> {

        });
        order_id = getIntent().getStringExtra(ORDER_ID);

        ArmsUtils.configRecyclerView(rvGoods, new LinearLayoutManager(mActivity));
        myOrderDetailAdapter = new MyOrderDetailAdapter(R.layout.item_myorderdetail_goods);
        rvGoods.setAdapter(myOrderDetailAdapter);


        mPresenter.getOrderDetail(order_id, mActivity);


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
    public void getOrderDetailSucc(MyOrderDetailBean myOrderDetailBean) {
        switch (myOrderDetailBean.status) {//0待付款  1待发货  2 待收货   3已完成
            case "0":
                tvStatus.setText("待付款");
                GlideUtil.loadImg(R.mipmap.icon_myorderdetail_waitpay, ivStatus);
                break;
            case "1":
                tvStatus.setText("待发货");
                GlideUtil.loadImg(R.mipmap.icon_myorderdetail_waitsend, ivStatus);
                break;
            case "2":
                tvStatus.setText("待收货");
                GlideUtil.loadImg(R.mipmap.icon_myorderdetail_waitreceive, ivStatus);
                break;
            case "3":
                GlideUtil.loadImg(R.mipmap.icon_myorderdetail_received, ivStatus);
                tvStatus.setText("已完成");
                break;
            default:
                break;
        }
        tvReceiverName.setText(myOrderDetailBean.realname);
        tvReceiverAddress.setText(myOrderDetailBean.address);
        myOrderDetailAdapter.setList(myOrderDetailBean.goods);
        tvOrderNo.setText("订单编号：    " + myOrderDetailBean.ordersn);
        tvOrderTime.setText("下单时间：  " + TimeUtils.millis2String(Long.parseLong(myOrderDetailBean.createtime)));
    }

    @OnClick({R.id.iv_call, R.id.ll_look_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_call:

                break;
            case R.id.ll_look_more:
                break;
        }
    }
}
