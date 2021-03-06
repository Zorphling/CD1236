package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.ShoppingCarBean;
import com.business.cd1236.mvp.contract.HomeThreeContract;
import com.business.cd1236.net.BaseCallBack;
import com.business.cd1236.net.RequestUtils;
import com.business.cd1236.utils.GsonUtils;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


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
@FragmentScope
public class HomeThreePresenter extends BasePresenter<HomeThreeContract.Model, HomeThreeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public HomeThreePresenter(HomeThreeContract.Model model, HomeThreeContract.View rootView) {
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

    public void getShoppingCar(Context context, boolean showDialog) {
        RequestUtils.getShopping(new BaseCallBack(context, showDialog) {
            @Override
            protected void onSuccess(String jsonString) {
                ArrayList<ShoppingCarBean> shoppingCarBeans = GsonUtils.parseJsonArrayWithGson(jsonString, ShoppingCarBean.class);
                mRootView.getShoppingSucc(shoppingCarBeans);
            }
        });
    }

    public void changeCarNum(String carId, String total, Context context) {
        RequestUtils.shopping_xg(carId, total, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }
        });
    }

    public void addCollect(String id, String ment, Context context) {
        RequestUtils.addCollect(id, ment, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
                mRootView.setCollectAllSuccess(msg);
            }
        });
    }

    public void shoppingDelete(String carId, Context context) {
        RequestUtils.shopping_delete(carId, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
                mRootView.shoppingDeleteSucc(msg);
            }
        });
    }
}
