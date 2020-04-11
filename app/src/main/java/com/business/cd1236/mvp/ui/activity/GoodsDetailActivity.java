package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.GoodsDetailStoreAdapter;
import com.business.cd1236.adapter.HomeBannerAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.GoodsDetailBean;
import com.business.cd1236.bean.HomeBannerBean;
import com.business.cd1236.di.component.DaggerGoodsDetailComponent;
import com.business.cd1236.mvp.contract.GoodsDetailContract;
import com.business.cd1236.mvp.presenter.GoodsDetailPresenter;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.utils.SpannableStringUtils;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/04/2020 16:22
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class GoodsDetailActivity extends MyBaseActivity<GoodsDetailPresenter> implements GoodsDetailContract.View {
    @BindView(R.id.goods_detail_banner)
    Banner banner;
    @BindView(R.id.tv_goods_title)
    TextView goodsTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.iv_cart)
    ImageView ivCart;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_marketprice)
    TextView tvMarketprice;
    @BindView(R.id.tv_sales)
    TextView tvSales;
    @BindView(R.id.tv_send_address)
    TextView tvSendAddress;
    @BindView(R.id.tv_brand)
    TextView tvBrand;
    @BindView(R.id.tv_sug_price)
    TextView tvSugPrice;
    @BindView(R.id.tv_start_num)
    TextView tvStartNum;
    @BindView(R.id.tv_bar_code)
    TextView tvBarCode;
    @BindView(R.id.rl_appraise)
    RelativeLayout rlAppraise;
    @BindView(R.id.riv_store)
    RoundedImageView rivStore;
    @BindView(R.id.tv_store_title)
    TextView tvStoreTitle;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_go_store)
    TextView tvGoStore;
    @BindView(R.id.rv_goods_detail)
    RecyclerView rvGoodsDetail;
    public static String GOODS_ID = "goods_id";
    private String ID;
    private GoodsDetailStoreAdapter goodsDetailStoreAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerGoodsDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_goods_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setStatusColor(mActivity, false, true, R.color.colorGray1);
        initBanner();
        ArmsUtils.configRecyclerView(rvGoodsDetail, new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        int dp = SizeUtils.dp2px(mActivity, 10);
        rvGoodsDetail.addItemDecoration(new SpaceItemDecoration(0, dp, SpaceItemDecoration.TYPE.LEFT));
        goodsDetailStoreAdapter = new GoodsDetailStoreAdapter(R.layout.item_goods_detail_store);
        rvGoodsDetail.setAdapter(goodsDetailStoreAdapter);

//        StatusBarUtil.setTransparent();
////        StatusBarUtil.setTransparent(mActivity);
        ID = getIntent().getStringExtra(GOODS_ID);
        mPresenter.getGoodsDetial(ID, mActivity);
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

    @OnClick({R.id.iv_back, R.id.tv_search, R.id.iv_collect, R.id.iv_cart, R.id.iv_home, R.id.rl_appraise, R.id.tv_go_store})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                killMyself();
                break;
            case R.id.tv_search:
                break;
            case R.id.iv_collect:
                if (goodsDetailBean != null && StringUtils.checkString(goodsDetailBean.goods.id))
                    mPresenter.addCollect(goodsDetailBean.goods.id, TextUtils.equals("0", goodsDetailBean.collect_jud) ? "1" : "0", mActivity);
                break;
            case R.id.iv_cart:
                break;
            case R.id.iv_home:
                Intent intent = new Intent(mActivity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                launchActivity(intent);
                killMyself();
                break;
            case R.id.rl_appraise:

                break;
            case R.id.tv_go_store:

                break;
        }
    }

    private GoodsDetailBean goodsDetailBean;

    @Override
    public void setGoodsDetail(GoodsDetailBean goodsDetailBean) {
        this.goodsDetailBean = goodsDetailBean;
        /**
         * 设置是否收藏
         */
        if (StringUtils.equals("0", goodsDetailBean.collect_jud)) {
            GlideUtil.loadImg(R.mipmap.goods_uncollect, ivCollect);
        } else {
            GlideUtil.loadImg(R.mipmap.goods_collect, ivCollect);
        }
        /**
         * 设置banner
         */
        if (goodsDetailBean.goods.thumb_s != null && goodsDetailBean.goods.thumb_s.size() > 0) {
            ArrayList<HomeBannerBean.BannerBean> bannerBeans = new ArrayList<>();
            for (String thumb : goodsDetailBean.goods.thumb_s) {
                HomeBannerBean.BannerBean bannerBean = new HomeBannerBean.BannerBean();
                bannerBean.img = thumb;
                bannerBeans.add(bannerBean);
            }
            banner.setAdapter(new HomeBannerAdapter(bannerBeans)).start();
        }
        goodsTitle.setText(goodsDetailBean.goods.title);
        SpannableString spannableString = SpannableStringUtils.textColor(goodsDetailBean.goods.marketprice + "/袋",
                mActivity.getResources().getColor(R.color.text_select_red), 0, goodsDetailBean.goods.marketprice.length());
        tvMarketprice.setText(spannableString);
        tvSales.setText("已售" + goodsDetailBean.goods.sales);
        tvSendAddress.setText("发货 " + goodsDetailBean.goods.province + " " + goodsDetailBean.goods.city);

        tvStartNum.setText("起批量：" + goodsDetailBean.goods.agent_weight + goodsDetailBean.goods.agent_unit);

        tvStoreTitle.setText(goodsDetailBean.shop.business_name);
        GlideUtil.loadImg(goodsDetailBean.shop.logo, rivStore);
        tvGoodsNum.setText("商品数量：" + goodsDetailBean.shop.number + " 关注" + goodsDetailBean.shop.follow);

        goodsDetailStoreAdapter.setNewInstance(goodsDetailBean.good_ss);

    }

    @Override
    public void setCollectSuccess() {
        if (StringUtils.equals("0", goodsDetailBean.collect_jud)) {
            this.goodsDetailBean.collect_jud = "1";
            GlideUtil.loadImg(R.mipmap.goods_collect, ivCollect);
        } else {
            this.goodsDetailBean.collect_jud = "0";
            GlideUtil.loadImg(R.mipmap.goods_uncollect, ivCollect);
        }
    }
}
