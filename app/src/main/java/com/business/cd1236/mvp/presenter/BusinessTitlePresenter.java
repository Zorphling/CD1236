package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.mvp.contract.BusinessTitleContract;
import com.business.cd1236.net.BaseCallBack;
import com.business.cd1236.net.RequestUtils;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/17/2020 18:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class BusinessTitlePresenter extends BasePresenter<BusinessTitleContract.Model, BusinessTitleContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public BusinessTitlePresenter(BusinessTitleContract.Model model, BusinessTitleContract.View rootView) {
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

    public void modifyBusinessTitle(String basic, String editText, Context context) {
        RequestUtils.modifyBusinessTitle(basic,editText, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                mRootView.modifyTitleSucc(msg);
            }

            @Override
            protected void onFailure(String errorMsg, int status) {
                super.onFailure(errorMsg, status);
                mRootView.modifyTitleFailure(errorMsg,status);
            }
        });
    }
}
