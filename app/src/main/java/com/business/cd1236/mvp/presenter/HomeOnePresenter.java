package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.HomeBannerBean;
import com.business.cd1236.bean.HomeGoodsBean;
import com.business.cd1236.mvp.contract.HomeOneContract;
import com.business.cd1236.net.BaseCallBack;
import com.business.cd1236.net.RequestUtils;
import com.business.cd1236.utils.GsonUtils;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


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
@FragmentScope
public class HomeOnePresenter extends BasePresenter<HomeOneContract.Model, HomeOneContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public HomeOnePresenter(HomeOneContract.Model model, HomeOneContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
    public void getBanner(Context context){
        RequestUtils.getBanner(new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                HomeBannerBean homeBannerBean = GsonUtils.parseJsonWithGson(jsonString, HomeBannerBean.class);
                mRootView.getBannerSuccess(homeBannerBean);
            }

            @Override
            protected void onFailure(String errorMsg, int status) {
                super.onFailure(errorMsg, status);

            }
        });
    }

    public void getGoods(Context context) {
        RequestUtils.getGoods(new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                HomeGoodsBean homeGoodsBean = GsonUtils.parseJsonWithGson(jsonString, HomeGoodsBean.class);
                mRootView.getGoodsSuccess(homeGoodsBean);
            }

            @Override
            protected void onFailure(String errorMsg, int status) {
                super.onFailure(errorMsg, status);

            }
        });
    }
}
