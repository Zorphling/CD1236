package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.OrderAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.AddAddressBean;
import com.business.cd1236.bean.GoodsDetailBean;
import com.business.cd1236.bean.OrderBean;
import com.business.cd1236.bean.ShoppingCarBean;
import com.business.cd1236.di.component.DaggerOrderComponent;
import com.business.cd1236.mvp.contract.OrderContract;
import com.business.cd1236.mvp.presenter.OrderPresenter;
import com.business.cd1236.utils.LogUtils;
import com.business.cd1236.utils.SpannableStringUtils;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.ItemView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.text.DecimalFormat;
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
    @BindView(R.id.et_leave_message)
    EditText etLeaveMessage;
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
    @BindView(R.id.tv_store_title)
    TextView rvStoreTitle;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;

    public static String ORDER_INTENT = "order_intent";
    public static String ORDER_TYPE = "order_type";
    public static boolean isShoppingCar;

    ShoppingCarBean shoppingCarBean;
    private OrderAdapter orderAdapter;
    OrderBean orderBean;

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

        shoppingCarBean = getIntent().getParcelableExtra(ORDER_INTENT);
        isShoppingCar = getIntent().getBooleanExtra(ORDER_TYPE, false);
        ArmsUtils.configRecyclerView(rvOrder, new LinearLayoutManager(mActivity));
        orderAdapter = new OrderAdapter(R.layout.item_order_activity);
        rvOrder.setAdapter(orderAdapter);
        if (shoppingCarBean != null) {
            rvStoreTitle.setText(shoppingCarBean.business_name);
//            orderAdapter.setList(shoppingCarBean.goods);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isShoppingCar) {//购物车过来的
            StringBuilder builder = new StringBuilder();
            for (GoodsDetailBean.GoodsBean good : shoppingCarBean.goods) {
                builder.append(good.cart_id).append(",");
            }
            mPresenter.orderConfirm(builder.substring(0, builder.length() - 1), "cart",shoppingCarBean.jud, mActivity);
        } else {//单个商品过来的
            mPresenter.orderConfirm(shoppingCarBean.goods.get(0).id, shoppingCarBean.weight, "buy",shoppingCarBean.jud, mActivity);
        }
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
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.ll_address:
                intent.setClass(mActivity, AddressActivity.class);
                intent.putExtra(AddressActivity.SELECT_ADDRESS, "1");
                startActivityForResult(intent, 100);
                break;
            case R.id.ll_express:
//                intent.setClass(mActivity, OrderDispatchingChooseActivity.class);
//                launchActivity(intent);
                break;
            case R.id.ll_store_cheap:
                break;
            case R.id.tv_submit_order:
                if (StringUtils.equals("没有可用的收货地址", tvReceiver.getText())) {
                    ArmsUtils.snackbarText("请先选择收货地址");
                    return;
                }
                ArrayList<String> arrayList = new ArrayList<>();

                for (GoodsDetailBean.GoodsBean good : orderBean.goods) {
                    arrayList.add(good.id + "," + good.total + "," + shoppingCarBean.jud);//1 零售  2批发
                }
                LogUtils.e(arrayList.toArray() + " ===================== array");
                LogUtils.e(arrayList.toString() + " ====================== string");
                LogUtils.e(new Gson().toJson(arrayList) + " ====================== list");
                mPresenter.addOrder(new Gson().toJson(arrayList), orderBean.address.id, orderBean.freight, "0", StringUtils.getEditText(etLeaveMessage), mActivity);//1自提 0物流
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
        if (bean != null && StringUtils.checkString(bean.address)) {
            tvReceiver.setText("收货人：" + bean.realname + "       " + bean.mobile);
            tvAddress.setText(bean.address);//bean.province + bean.city + bean.area +
        } else {
            tvReceiver.setText("没有可用的收货地址");
            tvAddress.setText("去选择地址～");
        }
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

    @Override
    public void orderConfirmSucc(OrderBean orderBean) {
        this.orderBean = orderBean;
        setAddress(orderBean.address);
        if (orderBean.goods != null && orderBean.goods.size() > 0) {
            orderAdapter.setList(shoppingCarBean.jud,orderBean.goods);
        }
        tvGoodsSum.setText(getString(R.string.rmb) + " " + new DecimalFormat("#0.00").format(Double.parseDouble(orderBean.good_s.money)));
        tvCarriage.setText(getString(R.string.rmb) + " " + new DecimalFormat("#0.00").format(Double.parseDouble(orderBean.freight)));
        String paid = "实付金额：" + getString(R.string.rmb) + " " + new DecimalFormat("#0.00").format(Double.parseDouble(orderBean.good_s.moneys));
        tvAmountPaid.setText(SpannableStringUtils.textColor(paid, getRColor(R.color.text_select_red), paid.indexOf(" ") - 1, paid.length()));
        tvAmountPaidAndrExpress.setText(getString(R.string.rmb) + " " + new DecimalFormat("#0.00").format(Double.parseDouble(orderBean.good_s.moneys)));
    }
}
