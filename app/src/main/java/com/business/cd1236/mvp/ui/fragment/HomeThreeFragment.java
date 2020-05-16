package com.business.cd1236.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.HomeThreeAdapter;
import com.business.cd1236.base.MyBaseFragment;
import com.business.cd1236.bean.GoodsDetailBean;
import com.business.cd1236.bean.ShoppingCarBean;
import com.business.cd1236.di.component.DaggerHomeThreeComponent;
import com.business.cd1236.mvp.contract.HomeThreeContract;
import com.business.cd1236.mvp.presenter.HomeThreePresenter;
import com.business.cd1236.mvp.ui.activity.OrderActivity;
import com.business.cd1236.mvp.ui.activity.StoreActivity;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.business.cd1236.view.dialog.AlertDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
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
 * Created by MVPArmsTemplate on 04/02/2020 09:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class HomeThreeFragment extends MyBaseFragment<HomeThreePresenter> implements HomeThreeContract.View, OnItemChildClickListener, HomeThreeAdapter.OnChangeCarNumListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.rv_home_three)
    RecyclerView rvHomeThree;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.all_check_box)
    AppCompatCheckBox allCheckBox;
    @BindView(R.id.tv_all_delete)
    TextView tvAllDelete;
    @BindView(R.id.tv_all_collect)
    TextView tvAllCollect;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private HomeThreeAdapter homeThreeAdapter;

    public static HomeThreeFragment newInstance() {
        HomeThreeFragment fragment = new HomeThreeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeThreeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_three, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ArmsUtils.configRecyclerView(rvHomeThree, new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mPresenter != null) {
            mPresenter.getShoppingCar(mActivity, false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

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

    }

    private ArrayList<GoodsDetailBean.GoodsBean> goodsBeanTemp;//记录要删除的商品集合

    @OnClick({R.id.tv_right, R.id.tv_all_delete, R.id.tv_all_collect})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
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

    private void isShowBottom() {
        if (rlBottom.getVisibility() == View.VISIBLE) {
            tvRight.setText("管理");
            rlBottom.setVisibility(View.GONE);
        } else {
            tvRight.setText("完成");
            rlBottom.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getShoppingSucc(ArrayList<ShoppingCarBean> shoppingCarBeans) {
        if (shoppingCarBeans != null && shoppingCarBeans.size() > 0) {
            homeThreeAdapter.setList(shoppingCarBeans);
        }
        if (homeThreeAdapter.getData().size() == 0) {
            allCheckBox.setChecked(false);
            tvRight.setText("管理");
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
}
