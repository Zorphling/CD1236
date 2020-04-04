package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerGoodsDetailComponent;
import com.business.cd1236.mvp.contract.GoodsDetailContract;
import com.business.cd1236.mvp.presenter.GoodsDetailPresenter;
import com.business.cd1236.utils.SPUtils;
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
 * Created by MVPArmsTemplate on 04/04/2020 16:22
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class GoodsDetailActivity extends MyBaseActivity<GoodsDetailPresenter> implements GoodsDetailContract.View {

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
    public static String GOODS_ID = "goods_id";
    private String ID;

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
//        StatusBarUtil.setTransparent();
////        StatusBarUtil.setTransparent(mActivity);
        ID = getIntent().getStringExtra(GOODS_ID);
        mPresenter.getGoodsDetial(ID, mActivity);
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

    @OnClick({R.id.iv_back, R.id.tv_search, R.id.iv_collect, R.id.iv_cart, R.id.iv_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                killMyself();
                break;
            case R.id.tv_search:
                break;
            case R.id.iv_collect:
                break;
            case R.id.iv_cart:
                break;
            case R.id.iv_home:
                Intent intent = new Intent(mActivity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                launchActivity(intent);
                killMyself();
                break;
        }
    }

    @Override
    public void setGoodsDetail() {

    }
}
