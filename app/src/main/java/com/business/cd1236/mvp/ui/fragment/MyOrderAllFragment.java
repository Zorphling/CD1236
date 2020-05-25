package com.business.cd1236.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.business.cd1236.R;
import com.business.cd1236.adapter.MyOrderAdapter;
import com.business.cd1236.base.AbstractLazyInitFrag;
import com.business.cd1236.bean.MyOrderBean;
import com.business.cd1236.di.component.DaggerMyOrderAllComponent;
import com.business.cd1236.mvp.contract.MyOrderAllContract;
import com.business.cd1236.mvp.presenter.MyOrderAllPresenter;
import com.business.cd1236.mvp.ui.activity.MyOrderDetailActivity;
import com.business.cd1236.mvp.ui.activity.OrderSettleActivity;
import com.business.cd1236.view.dialog.AlertDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/19/2020 18:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MyOrderAllFragment extends AbstractLazyInitFrag<MyOrderAllPresenter> implements MyOrderAllContract.View, OnItemClickListener, OnItemChildClickListener {
    //TODO 待优化显示批发角标时间距处理

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private MyOrderAdapter myOrderAdapter;

    private int STATUS;

    public static MyOrderAllFragment newInstance(int status) {
        MyOrderAllFragment fragment = new MyOrderAllFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMyOrderAllComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_recyclerview, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        ArmsUtils.configRecyclerView(recyclerView, new LinearLayoutManager(getActivity()));

        STATUS = getArguments().getInt("status");

        myOrderAdapter = new MyOrderAdapter(R.layout.item_my_order_all);
        View emptyView = View.inflate(getContext(), R.layout.layout_rv_empty, null);
        ((TextView) emptyView.findViewById(R.id.tv)).setText("暂无数据");
        myOrderAdapter.setEmptyView(emptyView);
        recyclerView.setAdapter(myOrderAdapter);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        myOrderAdapter.addChildClickViewIds(R.id.tv_order_status_cancel, R.id.tv_order_status_pay);
        myOrderAdapter.setOnItemChildClickListener(this);

        initData();
//        myOrderAdapter.setOnItemClickListener(this);
        //走下面懒加载initData()
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

    //懒加载
    @Override
    public void initData() {
        mPresenter.getMyOrder(String.valueOf(STATUS), getActivity(), false);
    }

    @Override
    public void getMyOrderSucc(ArrayList<MyOrderBean> myOrderBeans) {
        if (myOrderBeans == null || myOrderBeans.size() == 0) {

        } else {
            myOrderAdapter.setList(myOrderBeans);
        }
    }

    @Override
    public void orderCancelSucc(String msg) {
        ArmsUtils.snackbarText(msg);
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        MyOrderBean myOrderBean = (MyOrderBean) adapter.getItem(position);
        Intent intent = new Intent(getActivity(), MyOrderDetailActivity.class);
        intent.putExtra(MyOrderDetailActivity.ORDER_ID, myOrderBean.id);
        launchActivity(intent);
    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        MyOrderBean myOrderBean = (MyOrderBean) adapter.getItem(position);
        switch (view.getId()) {
            case R.id.tv_order_status_cancel:
                switch (myOrderBean.status) {
                    case "0"://待付款
                        Intent intent = new Intent(getActivity(), OrderSettleActivity.class);
                        intent.putExtra(OrderSettleActivity.ORDER_ID, myOrderBean.id);
                        launchActivity(intent);
                        new AlertDialog(getActivity()).builder().setMsg("确定取消该订单吗？").setNegativeButton("取消", null).setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPresenter.orderCancel(myOrderBean.id, getActivity());
                            }
                        });
                        break;
                    case "1"://待发货
                        ArmsUtils.snackbarText("提醒成功");
                        ((TextView) view).setText("已提醒发货");
                        break;
                    case "2"://待收货  确认收货 status 传 3
                        new AlertDialog(getActivity()).builder().setMsg("确认收货").setNegativeButton("取消", null).setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPresenter.orderConfirmReceive(myOrderBean.id, "3", getActivity());
                            }
                        });
                        break;
                    case "3"://已完成

                        break;
                }
                break;
            case R.id.tv_order_status_pay:
                switch (myOrderBean.status) {
                    case "0"://待付款
                        Intent intent = new Intent(getActivity(), OrderSettleActivity.class);
                        intent.putExtra(OrderSettleActivity.ORDER_ID, myOrderBean.id);
                        launchActivity(intent);
                        break;
                    case "1"://待发货
                        ArmsUtils.snackbarText("提醒成功");
                        ((TextView) view).setText("已提醒发货");
                        break;
                    case "2"://待收货
                        break;
                    case "3"://已完成
                        break;
                }

                break;
        }
    }
}
