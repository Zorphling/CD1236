package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.MyOrderBean;
import com.business.cd1236.mvp.contract.MyOrderAllContract;
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
 * Created by MVPArmsTemplate on 05/19/2020 18:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
public class MyOrderAllPresenter extends BasePresenter<MyOrderAllContract.Model, MyOrderAllContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MyOrderAllPresenter(MyOrderAllContract.Model model, MyOrderAllContract.View rootView) {
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

    public void getMyOrder(String status, Context context,boolean isShowDialog) {
        RequestUtils.getMyOrder(status, new BaseCallBack(context,isShowDialog) {
            @Override
            protected void onSuccess(String jsonString) {
                ArrayList<MyOrderBean> myOrderBeans = GsonUtils.parseJsonArrayWithGson(jsonString, MyOrderBean.class);
                mRootView.getMyOrderSucc(myOrderBeans);
            }
        });
    }

    public void orderCancel(String order_id, Context context) {
        RequestUtils.deleteOrder(order_id, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
                mRootView.orderCancelSucc(msg);
            }
        });
    }

    public void orderConfirmReceive(String order_id, String status, Context context) {
        RequestUtils.orderStatusChange(order_id,status, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }
        });
    }
}
