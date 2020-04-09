package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.EnterTypeBean;
import com.business.cd1236.mvp.contract.BusinessEnterContract;
import com.business.cd1236.net.BaseCallBack;
import com.business.cd1236.net.RequestUtils;
import com.business.cd1236.utils.GsonUtils;
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
 * Created by MVPArmsTemplate on 04/08/2020 09:31
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class BusinessEnterPresenter extends BasePresenter<BusinessEnterContract.Model, BusinessEnterContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public BusinessEnterPresenter(BusinessEnterContract.Model model, BusinessEnterContract.View rootView) {
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

    public void getEnterType(Context context) {
        RequestUtils.getEnterType(new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                EnterTypeBean enterTypeBean = GsonUtils.parseJsonWithGson(jsonString, EnterTypeBean.class);
                mRootView.setEnterType(enterTypeBean);
            }
        });
    }

    public void submit(String ENTER_TIME, String editText, String text, String s, String ENTER_TYPE, String address, String address1, String s1, String editText1, String text1, Context context) {
        RequestUtils.submitBusinessEnter(ENTER_TIME,editText,text,s,ENTER_TYPE,address,address1,s1,editText1,text1,new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                mRootView.setEnterSuccess(jsonString);
            }
        });
    }
}
