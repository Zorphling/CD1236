package com.business.cd1236.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.HomeTwoStoreAdapter;
import com.business.cd1236.base.MyBaseFragment;
import com.business.cd1236.di.component.DaggerHomeTwoComponent;
import com.business.cd1236.mvp.contract.HomeTwoContract;
import com.business.cd1236.mvp.presenter.HomeTwoPresenter;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.material.appbar.AppBarLayout;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/02/2020 09:55
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class HomeTwoFragment extends MyBaseFragment<HomeTwoPresenter> implements HomeTwoContract.View, AppBarLayout.OnOffsetChangedListener, OnItemClickListener {
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.layout_open)
    FrameLayout layoutOpen;
    @BindView(R.id.layout_close)
    FrameLayout layoutClose;
    @BindView(R.id.view_search)
    View viewSearch;
    @BindView(R.id.view_open)
    View viewOpen;
    @BindView(R.id.view_close)
    View viewClose;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private HomeTwoStoreAdapter homeTwoStoreAdapter;


    public static HomeTwoFragment newInstance() {
        HomeTwoFragment fragment = new HomeTwoFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeTwoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_two, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        appBar.addOnOffsetChangedListener(this);

        ArmsUtils.configRecyclerView(rvContent, new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvContent.setNestedScrollingEnabled(false);
        int dp = SizeUtils.dp2px(mActivity, 10);
        rvContent.addItemDecoration(new SpaceItemDecoration(0, dp, SpaceItemDecoration.TYPE.LEFT));
        homeTwoStoreAdapter = new HomeTwoStoreAdapter(R.layout.item_home_two_store);
        rvContent.setAdapter(homeTwoStoreAdapter);
        homeTwoStoreAdapter.setOnItemClickListener(this);

//        mPresenter.get()
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

    /**
     * 通过计算滑动的距离，逐渐改变透明度。
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        //垂直方向偏移量
        int offset = Math.abs(i);
        //最大偏移距离
        int scrollRange = appBarLayout.getTotalScrollRange();
        //当滑动没超过一半，展开状态下 toolbar 显示内容，更具收缩位置，改变透明值
        if (offset <= scrollRange / 2) {
            layoutOpen.setVisibility(View.VISIBLE);
            layoutClose.setVisibility(View.GONE);
            //根据偏移百分比 计算透明值
            float scale = Float.valueOf(offset) / (scrollRange / 2);

            int alpha = Integer.valueOf((int) (255 * scale));
            viewOpen.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
        }//当滑动超过一半，收缩状态下 toolbar 显示内容，根据收缩位置，改变透明值
        else {
            layoutOpen.setVisibility(View.GONE);
            layoutClose.setVisibility(View.VISIBLE);
            float scale = Float.valueOf(scrollRange - offset) / (scrollRange / 2);
            int alpha = Integer.valueOf((int) (255 * scale));
            viewClose.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
        }
        //根据百分比计算扫一扫布局透明值
        float scale = Float.valueOf(offset) / scrollRange;
        int alpha = Integer.valueOf((int) (255 * scale));
        viewSearch.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appBar.removeOnOffsetChangedListener(this);
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

    }
}
