package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.StoreDetailAllGoodsAdapter;
import com.business.cd1236.adapter.StoreDetailBusinessRecommendAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.StoreDetailBean;
import com.business.cd1236.di.component.DaggerStoreComponent;
import com.business.cd1236.mvp.contract.StoreContract;
import com.business.cd1236.mvp.presenter.StorePresenter;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.MyToastUtils;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/15/2020 17:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class StoreActivity extends MyBaseActivity<StorePresenter> implements StoreContract.View {
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.scroll_need_offset)
    NestedScrollView scrollView;
    @BindView(R.id.rl_need_offset)
    RelativeLayout rlNeedOffset;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.rv_business_recommend)
    RecyclerView rvBusinessRecommmend;
    @BindView(R.id.iv_xx)
    ImageView ivXx;
    @BindView(R.id.tv_store_title)
    TextView tvStoreTitle;
    @BindView(R.id.tv_store_sales_follow)
    TextView tvStoreSalesFollow;
    @BindView(R.id.tv_store_notice)
    TextView tvStoreNotice;
    @BindView(R.id.riv_store_logo)
    RoundedImageView rivStoreLogo;
    @BindView(R.id.iv_quality)
    ImageView ivQuality;
    @BindView(R.id.ll_notice)
    LinearLayout llNotice;
    @BindView(R.id.iv_store_follow)
    ImageView ivStoreFollow;
    @BindView(R.id.rv_center_category)
    RecyclerView rvCenterCategory;
    public static String STORE_ID = "store_id";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    private String ID;
    private StoreDetailBusinessRecommendAdapter storeDetailBusinessRecommendAdapter;
    private StoreDetailAllGoodsAdapter storeDetailAllGoodsAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerStoreComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_store; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageView(this,0, null);
//        StatusBarUtil.setTranslucentForImageView(this,0, scrollView);
        ID = getIntent().getStringExtra(STORE_ID);

        //
        ArmsUtils.configRecyclerView(rvBusinessRecommmend, new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));

        rvBusinessRecommmend.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dp2px(mActivity, 20), SizeUtils.dp2px(mActivity, 10), SpaceItemDecoration.TYPE.CUSTOM));
        storeDetailBusinessRecommendAdapter = new StoreDetailBusinessRecommendAdapter(R.layout.item_store_detail_business_recommend);
        rvBusinessRecommmend.setAdapter(storeDetailBusinessRecommendAdapter);
        storeDetailBusinessRecommendAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
            intent.putExtra(GoodsDetailActivity.GOODS_ID, ((StoreDetailBean.HotBean) adapter.getItem(position)).id);
            launchActivity(intent);
        });

        ArmsUtils.configRecyclerView(rvCenterCategory,new GridLayoutManager(mActivity,3));
        rvCenterCategory.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dp2px(mActivity, 10), SpaceItemDecoration.TYPE.ALL));
        rvCenterCategory.setNestedScrollingEnabled(false);
        rvCenterCategory.setHasFixedSize(true);
        storeDetailAllGoodsAdapter = new StoreDetailAllGoodsAdapter(R.layout.item_store_detail_goods);
        rvCenterCategory.setAdapter(storeDetailAllGoodsAdapter);


        mPresenter.getStoreDetail(ID, mActivity);
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

    StoreDetailBean storeDetailBean;

    @Override
    public void getStoreDetailSucc(StoreDetailBean storeDetailBean) {
        this.storeDetailBean = storeDetailBean;
        if (storeDetailBean != null) {
            if (storeDetailBean.shop != null) {
                GlideUtil.loadImg(storeDetailBean.shop.sign_img, ivBg);
                GlideUtil.loadImg(storeDetailBean.shop.logo, rivStoreLogo);
                GlideUtil.loadImg(StringUtils.equals("0", storeDetailBean.shop.jud) ? R.mipmap.icon_store_follow : R.mipmap.icon_store_disfollow, ivStoreFollow);
                tvStoreTitle.setText(storeDetailBean.shop.business_name);
                tvStoreNotice.setText(storeDetailBean.shop.culture);
            }
            if (storeDetailBean.good_ss!=null){
                storeDetailAllGoodsAdapter.setList(storeDetailBean.good_ss);
            }

            if (storeDetailBean.hot != null)
                storeDetailBusinessRecommendAdapter.setList(storeDetailBean.hot);
        }
    }

    @Override
    public void followStoreSucc(String msg) {//1为已关注  0为关注
        storeDetailBean.shop.jud = StringUtils.equals("0", storeDetailBean.shop.jud) ? "1" : "0";
        GlideUtil.loadImg(StringUtils.equals("0", storeDetailBean.shop.jud) ? R.mipmap.icon_store_follow : R.mipmap.icon_store_disfollow, ivStoreFollow);
        MyToastUtils.showShort(msg);
    }

    @OnClick({R.id.iv_back, R.id.iv_xx, R.id.iv_search, R.id.iv_quality, R.id.ll_notice, R.id.iv_store_follow,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.iv_xx:
                killMyself();
                break;
            case R.id.iv_search:
                break;
            case R.id.iv_store_follow:
                mPresenter.followStore(storeDetailBean.shop.id, storeDetailBean.shop.jud, mActivity);
                break;
            case R.id.iv_quality:
                break;
            case R.id.ll_notice:
                break;
        }
    }
}
