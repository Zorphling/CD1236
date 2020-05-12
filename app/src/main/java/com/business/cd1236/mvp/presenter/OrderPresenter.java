package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.AddAddressBean;
import com.business.cd1236.mvp.contract.OrderContract;
import com.business.cd1236.net.BaseCallBack;
import com.business.cd1236.net.RequestUtils;
import com.business.cd1236.utils.GsonUtils;
import com.jess.arms.di.scope.ActivityScope;
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
 * Created by MVPArmsTemplate on 04/05/2020 14:45
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class OrderPresenter extends BasePresenter<OrderContract.Model, OrderContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public OrderPresenter(OrderContract.Model model, OrderContract.View rootView) {
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

    public void getDefAddress(Context context) {
        RequestUtils.getAddress(new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                ArrayList<AddAddressBean> addAddressBeans = GsonUtils.parseJsonArrayWithGson(jsonString, AddAddressBean.class);
                mRootView.getDefAddressSucc(addAddressBeans);
            }
        });
    }
    public void orderConfirm(String goodsids, String ment,Context context) {
        RequestUtils.orderConfirm(goodsids, ment, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }
        });
    }
    public void orderConfirm(String goodsid, String goodsNum, String ment,Context context) {
        RequestUtils.orderConfirm(goodsid,goodsNum,ment, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }
        });
    }

}
