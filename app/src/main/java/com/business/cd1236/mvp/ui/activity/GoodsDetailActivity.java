package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.GoodsDetailBannerAdapter;
import com.business.cd1236.adapter.GoodsDetailStoreAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.GoodsDetailBean;
import com.business.cd1236.bean.HomeBannerBean;
import com.business.cd1236.di.component.DaggerGoodsDetailComponent;
import com.business.cd1236.mvp.contract.GoodsDetailContract;
import com.business.cd1236.mvp.presenter.GoodsDetailPresenter;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.MyToastUtils;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.utils.SpannableStringUtils;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.listener.OnBannerListener;
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
    @BindView(R.id.riv_temp)
    RoundedImageView rivTemp;
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
    @BindView(R.id.tv_custom_service)
    TextView tvCustomService;
    @BindView(R.id.tv_store)
    TextView tvStore;
    @BindView(R.id.tv_add_goods_list)
    TextView tvAddGoodsList;
    @BindView(R.id.tv_buy1)
    TextView tvBuy1;
    @BindView(R.id.ll_buy1)
    LinearLayout llBuy1;
    @BindView(R.id.tv_buy2)
    TextView tvBuy2;
    @BindView(R.id.ll_buy2)
    LinearLayout llBuy2;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private String ID;
    private GoodsDetailStoreAdapter goodsDetailStoreAdapter;
    private ArrayList<HomeBannerBean.BannerBean> bannerBeans;

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
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {

                    }
                })
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

    @OnClick({R.id.iv_back, R.id.tv_search, R.id.iv_collect, R.id.iv_cart, R.id.iv_home, R.id.rl_appraise, R.id.tv_go_store, R.id.tv_custom_service, R.id.tv_store, R.id.tv_add_goods_list, R.id.ll_buy1, R.id.ll_buy2})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
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
                intent.setClass(mActivity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                launchActivity(intent);
                killMyself();
                break;
            case R.id.rl_appraise:

                break;
            case R.id.tv_store:
            case R.id.tv_go_store:
                intent.setClass(mActivity, StoreActivity.class);
                intent.putExtra(StoreActivity.STORE_ID, goodsDetailBean.shop.id);
                launchActivity(intent);
                break;
            case R.id.tv_custom_service:
                break;
            case R.id.tv_add_goods_list:
                initAnim();
                break;
            case R.id.ll_buy1:
                intent.setClass(mActivity, OrderActivity.class);
//                intent.putExtra(OrderActivity.);
                launchActivity(intent);
                break;
            case R.id.ll_buy2:
                break;
        }
    }

    private void initAnim() {
        //计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLoc = new int[2];
        getWindow().getDecorView().getRootView().getLocationInWindow(parentLoc);

        //得到商品图片的坐标（用于计算动画开始的坐标）（此图片控件添加到根布局，居中）
        int startLoc[] = new int[2];
        rivTemp.getLocationInWindow(startLoc);

        //购物车控件的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        ivCart.getLocationInWindow(endLoc);

        float startX = startLoc[0] - parentLoc[0] + rivTemp.getWidth() / 2;
        float startY = startLoc[1] - parentLoc[1] + rivTemp.getHeight() / 2;

        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        float toX = endLoc[0] - parentLoc[0] + ivCart.getWidth() / 2;
        float toY = endLoc[1] - parentLoc[1] + ivCart.getHeight() * 2 / 5;

        //透明度和缩放动画，动画持续时间和动画透明度可以自己根据想要的效果调整
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(280);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.1f, 1f, 0.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);

        //平移动画X轴 计算X轴要平移的距离，设置动画的偏移时间由透明度和缩放动画持续时间决定
        TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                toX - startX, 0, 0);
        translateAnimationX.setStartOffset(300);
        translateAnimationX.setDuration(700);
        //设置线性插值器
        translateAnimationX.setInterpolator(new LinearInterpolator());

        //平移动画Y轴 同X轴
        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                0, toY - startY);
        translateAnimationY.setStartOffset(300);
        translateAnimationY.setDuration(700);
        //设置加速插值器
        translateAnimationY.setInterpolator(new AccelerateInterpolator());


        //动画集合
        final AnimationSet set = new AnimationSet(false);
        set.addAnimation(alphaAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(translateAnimationX);
        set.addAnimation(translateAnimationY);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                rivTemp.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画执行完成
                rivTemp.setVisibility(View.GONE);
                rivTemp.clearAnimation();
                set.cancel();
                animation.cancel();
                //购物车商品数量更新
                mPresenter.addShopping(goodsDetailBean.goods.id,  "1", goodsDetailBean.goods.marketprice, goodsDetailBean.shop.id, mActivity);
                //购物车控件 开始一个放大动画
//                Animation scaleAnim = AnimationUtils.loadAnimation(mActivity, R.anim.shop_car_scale);
//                ivCart.startAnimation(scaleAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //设置动画播放完以后消失，终止填充
        set.setFillAfter(false);
        rivTemp.startAnimation(set);
    }

    /**
     * 添加商品成功后回调
     *
     * @param msg
     */
    @Override
    public void addShoppingSucc(String msg) {
        MyToastUtils.showShort(msg);
        //购物车控件 开始一个放大动画
        Animation scaleAnim = AnimationUtils.loadAnimation(mActivity, R.anim.shop_car_scale);
        ivCart.startAnimation(scaleAnim);
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
            bannerBeans = new ArrayList<>();
            for (String thumb : goodsDetailBean.goods.thumb_s) {
                HomeBannerBean.BannerBean bannerBean = new HomeBannerBean.BannerBean();
                bannerBean.img = thumb;
                bannerBeans.add(bannerBean);
            }
            banner.setAdapter(new GoodsDetailBannerAdapter(bannerBeans)).start();
            GlideUtil.loadImg(goodsDetailBean.goods.thumb_s.get(0), rivTemp);
        }
        goodsTitle.setText(goodsDetailBean.goods.title);
        SpannableString spannableString = SpannableStringUtils.textColor(getResources().getString(R.string.rmb) + goodsDetailBean.goods.marketprice + "/袋",
                mActivity.getResources().getColor(R.color.text_select_red), 0, goodsDetailBean.goods.marketprice.length() + 1);
        tvMarketprice.setText(spannableString);
        tvSales.setText("已售" + goodsDetailBean.goods.sales);
        tvSendAddress.setText("发货 " + goodsDetailBean.goods.province + " " + goodsDetailBean.goods.city);

        tvStartNum.setText("起批量：" + goodsDetailBean.goods.agent_weight + goodsDetailBean.goods.agent_unit);

        tvStoreTitle.setText(goodsDetailBean.shop.business_name);
        GlideUtil.loadImg(goodsDetailBean.shop.logo, rivStore);
        tvGoodsNum.setText("商品数量：" + goodsDetailBean.shop.number + " 关注" + goodsDetailBean.shop.follow);

        goodsDetailStoreAdapter.setList(goodsDetailBean.good_ss);
        goodsDetailStoreAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
            intent.putExtra(GoodsDetailActivity.GOODS_ID, ((GoodsDetailBean.GoodSsBean) adapter.getItem(position)).id);
            launchActivity(intent);
        });
    }

    @Override
    public void setCollectSuccess(String msg) {
        if (StringUtils.equals("0", goodsDetailBean.collect_jud)) {
            this.goodsDetailBean.collect_jud = "1";
            GlideUtil.loadImg(R.mipmap.goods_collect, ivCollect);
        } else {
            this.goodsDetailBean.collect_jud = "0";
            GlideUtil.loadImg(R.mipmap.goods_uncollect, ivCollect);
        }
        MyToastUtils.showShort(msg);
    }
}
