package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.HomeThreeAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.GoodsDetailBean;
import com.business.cd1236.bean.ShoppingCarBean;
import com.business.cd1236.di.component.DaggerShoppingCarComponent;
import com.business.cd1236.mvp.contract.ShoppingCarContract;
import com.business.cd1236.mvp.presenter.ShoppingCarPresenter;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.business.cd1236.view.dialog.AlertDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jaeger.library.StatusBarUtil;
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
 * Created by MVPArmsTemplate on 05/07/2020 16:00
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class ShoppingCarActivity extends MyBaseActivity<ShoppingCarPresenter> implements ShoppingCarContract.View, OnItemChildClickListener, HomeThreeAdapter.OnChangeCarNumListener, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_manage)
    TextView tvRightManage;
    @BindView(R.id.rv_home_three)
    RecyclerView rvHomeThree;
    @BindView(R.id.all_check_box)
    AppCompatCheckBox allCheckBox;
    @BindView(R.id.tv_all_delete)
    TextView tvAllDelete;
    @BindView(R.id.tv_all_collect)
    TextView tvAllCollect;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private HomeThreeAdapter homeThreeAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerShoppingCarComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_shopping_car; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(mActivity);
        StatusBarUtil.setTranslucent(mActivity, 0);
//        setHeader("进货单");
//        setHeaderColor(android.R.color.white,android.R.color.black,0);
//        setRightBtn();
//        setRightColor(android.R.color.black);

        ArmsUtils.configRecyclerView(rvHomeThree, new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        int dp = SizeUtils.dp2px(mActivity, 10);
        rvHomeThree.addItemDecoration(new SpaceItemDecoration(0, dp, SpaceItemDecoration.TYPE.TOP));
        homeThreeAdapter = new HomeThreeAdapter(R.layout.item_home_three, this);
        View emptyView = View.inflate(mActivity, R.layout.layout_rv_empty, null);
        ((TextView) emptyView.findViewById(R.id.tv)).setText("快去添加商品吧～");
        homeThreeAdapter.setEmptyView(emptyView);
        homeThreeAdapter.addChildClickViewIds(R.id.tv_store_title, R.id.tv_pay);
        homeThreeAdapter.setOnItemChildClickListener(this);
        rvHomeThree.setAdapter(homeThreeAdapter);

        allCheckBox.setOnCheckedChangeListener(this);

        mPresenter.getShoppingCar(mActivity, true);
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

    private ArrayList<GoodsDetailBean.GoodsBean> goodsBeanTemp;//记录要删除的商品集合


    private void isShowBottom() {
        if (rlBottom.getVisibility() == View.VISIBLE) {
            tvRightManage.setText("管理");
            rlBottom.setVisibility(View.GONE);
        } else {
            tvRightManage.setText("完成");
            rlBottom.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getShoppingSucc(ArrayList<ShoppingCarBean> shoppingCarBeans) {
        if (shoppingCarBeans != null) {
            homeThreeAdapter.setList(shoppingCarBeans);
        }
        if (homeThreeAdapter.getData().size() == 0) {
            allCheckBox.setChecked(false);
            tvRightManage.setText("管理");
            rlBottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void setCollectAllSuccess(String msg) {
        ArmsUtils.snackbarText(msg);
    }

    /**
     * 删除选中
     *
     * @param msg
     */
    @Override
    public void shoppingDeleteSucc(String msg) {
        ArmsUtils.snackbarText(msg);
        mPresenter.getShoppingCar(mActivity, false);
//        Iterator<ShoppingCarBean> iterator = homeThreeAdapter.getData().iterator();
//        while (iterator.hasNext()) {
//            ShoppingCarBean next = iterator.next();
//            for (ShoppingCarBean.GoodsBean goodsBean : goodsBeanTemp) {
//                if (StringUtils.equals(next.id, goodsBean.id)) {
//                    iterator.remove();
//                }
//            }
//        }
//        homeThreeAdapter.notifyDataSetChanged();
//        homeThreeAdapter.notifyRemoved(goodsBeanTemp);

    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        ShoppingCarBean shoppingCarBean = (ShoppingCarBean) adapter.getItem(position);
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_store_title:
                intent.setClass(mActivity, StoreActivity.class);
                intent.putExtra(StoreActivity.STORE_ID, shoppingCarBean.id);
                launchActivity(intent);
                break;
            case R.id.tv_pay:
                intent.setClass(mActivity, OrderActivity.class);
                ArrayList<GoodsDetailBean.GoodsBean> arrayList = new ArrayList<>();
                for (GoodsDetailBean.GoodsBean good : shoppingCarBean.goods) {
                    if (good.isCheck) {
                        arrayList.add(good);
                    }
                }
                if (arrayList.size() == 0) {
                    ArmsUtils.snackbarText("请选择商品");
                    return;
                }
                ShoppingCarBean bean = shoppingCarBean;
                bean.goods = arrayList;
                intent.putExtra(OrderActivity.ORDER_INTENT, bean);
                intent.putExtra(OrderActivity.ORDER_TYPE, true);
                launchActivity(intent);
                break;
        }
    }

    @Override
    public void changeCarNum(String carId, String total) {
        mPresenter.changeCarNum(carId, total, mActivity);
    }

    @Override
    public void onAllChecked(boolean isAllChecked) {
        allCheckBox.setChecked(isAllChecked);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        List<ShoppingCarBean> data = homeThreeAdapter.getData();
        for (ShoppingCarBean datum : data) {
            datum.isCheck = isChecked;
        }//TODO 待处理  最外部全选状态取消会让所有都取消选择
        if (rvHomeThree.isComputingLayout()) { //这里有问题
            rvHomeThree.post(() -> {
                homeThreeAdapter.notifyDataSetChanged();
            });
        } else {
            homeThreeAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_right_manage, R.id.tv_all_delete, R.id.tv_all_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                killMyself();
                break;
            case R.id.tv_right_manage:
                isShowBottom();
                break;
            case R.id.tv_all_delete:
                StringBuilder builderDelete = new StringBuilder();
                goodsBeanTemp = new ArrayList<>();
                for (ShoppingCarBean datum : homeThreeAdapter.getData()) {
                    for (GoodsDetailBean.GoodsBean good : datum.goods) {
                        if (good.isCheck) {
                            goodsBeanTemp.add(good);
                            builderDelete.append(good.cart_id).append(",");
                        }
                    }
                }
                if (builderDelete.toString().length() == 0) {
                    ArmsUtils.snackbarText("请选中商品");
                    return;
                }
                new AlertDialog(mActivity).builder().setMsg("是否删除选中商品").setNegativeButton("取消", null).setPositiveButton("确定", v1 -> {
                    mPresenter.shoppingDelete(builderDelete.substring(0, builderDelete.length() - 1), mActivity);
                }).show();
                break;
            case R.id.tv_all_collect:
                StringBuilder builder = new StringBuilder();
                for (ShoppingCarBean datum : homeThreeAdapter.getData()) {
                    for (GoodsDetailBean.GoodsBean good : datum.goods) {
                        if (good.isCheck)
                            builder.append(good.id).append(",");
                    }
                }
                if (builder.toString().length() == 0) {
                    ArmsUtils.snackbarText("请选中商品");
                    return;
                }
                mPresenter.addCollect(builder.substring(0, builder.length() - 1), "1", mActivity);
                break;
        }
    }
}
