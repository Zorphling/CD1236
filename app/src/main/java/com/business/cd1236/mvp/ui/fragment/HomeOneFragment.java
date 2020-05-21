package com.business.cd1236.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.HomeBannerAdapter;
import com.business.cd1236.adapter.HomeCategrayAdapter;
import com.business.cd1236.adapter.HomeGoodsAdapter;
import com.business.cd1236.base.MyBaseFragment;
import com.business.cd1236.bean.HomeBannerBean;
import com.business.cd1236.bean.HomeGoodsBean;
import com.business.cd1236.di.component.DaggerHomeOneComponent;
import com.business.cd1236.mvp.contract.HomeOneContract;
import com.business.cd1236.mvp.presenter.HomeOnePresenter;
import com.business.cd1236.mvp.ui.activity.CategoryActivity;
import com.business.cd1236.mvp.ui.activity.GoodsDetailActivity;
import com.business.cd1236.mvp.ui.activity.SearchActivity;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/02/2020 09:53
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class HomeOneFragment extends MyBaseFragment<HomeOnePresenter> implements HomeOneContract.View, OnItemClickListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ll_category)
    LinearLayout llCategory;
    //    @BindView(R.id.ll_recommend)
//    LinearLayout llRecommend;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    @BindView(R.id.iv_transport)
    ImageView ivTransport;
    @BindView(R.id.tv_transport_content)
    TextView ivTransportContent;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    private HomeCategrayAdapter homeCategrayAdapter;
    private HomeGoodsAdapter homeGoodsAdapter;

    public static HomeOneFragment newInstance() {
        HomeOneFragment fragment = new HomeOneFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeOneComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_one, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initBanner();
        ArmsUtils.configRecyclerView(rvCategory, new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        int dp = SizeUtils.dp2px(mActivity, 10);
        rvCategory.addItemDecoration(new SpaceItemDecoration(0, dp, SpaceItemDecoration.TYPE.LEFT));
        homeCategrayAdapter = new HomeCategrayAdapter(R.layout.item_home_category);
        rvCategory.setAdapter(homeCategrayAdapter);
        homeCategrayAdapter.setOnItemClickListener(this);

        rvRecommend.setNestedScrollingEnabled(false);
        ArmsUtils.configRecyclerView(rvRecommend, new GridLayoutManager(mContext, 2));
        rvRecommend.addItemDecoration(new SpaceItemDecoration(0, dp, SpaceItemDecoration.TYPE.ALL));
        homeGoodsAdapter = new HomeGoodsAdapter(R.layout.item_home_goods);
        rvRecommend.setAdapter(homeGoodsAdapter);
        homeGoodsAdapter.setOnItemClickListener(this);

        mPresenter.getBanner(mActivity);
        mPresenter.getGoods(1, mActivity);
    }

    private void initBanner() {
        banner.isAutoLoop(true)
                .setDelayTime(2500)
                .setIndicator(new RectangleIndicator(mActivity))
                .setIndicatorNormalColorRes(R.color.colorGray4)
                .setIndicatorSelectedColorRes(R.color.white)
                .addPageTransformer(new ZoomOutPageTransformer())
                .addPageTransformer(new DepthPageTransformer());
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

    @Override
    public void getBannerSuccess(HomeBannerBean homeBannerBean) {
        banner.setAdapter(new HomeBannerAdapter(homeBannerBean.banner)).start();
        llCategory.setVisibility(View.VISIBLE);
        homeCategrayAdapter.setList(homeBannerBean.category);
        GlideUtil.loadImg(homeBannerBean.transport, ivTransport);
        ivTransportContent.setText(homeBannerBean.transport_content);
    }

    @Override
    public void getGoodsSuccess(HomeGoodsBean homeGoodsBean) {
//        llRecommend.setVisibility(View.VISIBLE);
        homeGoodsAdapter.loadData(homeGoodsBean.jud, homeGoodsBean.data);
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        Intent intent = new Intent();
        if (adapter instanceof HomeCategrayAdapter) {
            intent.setClass(mActivity, CategoryActivity.class);//TODO 这里分类不是搜索接口，需要单独传递分类id 进行分类查询，赶时间暂时写这样
            intent.putExtra(CategoryActivity.SEARCH_STRING, ((HomeBannerBean.CategoryBean) adapter.getData().get(position)).name);
        } else {
            intent.setClass(mActivity, GoodsDetailActivity.class);
            intent.putExtra(GoodsDetailActivity.GOODS_ID, ((HomeGoodsBean.DataBean) adapter.getData().get(position)).id);
        }
        launchActivity(intent);
    }

    @OnClick({R.id.ll_search, R.id.iv_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                launchActivity(new Intent(mActivity, SearchActivity.class));
                break;
            case R.id.iv_message:
                break;
        }
    }
}
