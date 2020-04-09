package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.mvp.contract.AddAddressContract;
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
 * Created by MVPArmsTemplate on 04/09/2020 11:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class AddAddressPresenter extends BasePresenter<AddAddressContract.Model, AddAddressContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public AddAddressPresenter(AddAddressContract.Model model, AddAddressContract.View rootView) {
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

    public void addAddress(String editText, String text, String address, String s, String address1, String editText1, String isDef, Context context) {
        RequestUtils.addAddress(editText, text, address, s, address1, editText1, isDef, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                mRootView.saveAddressSuccess();
            }
        });
    }

    public void editAddress(String editText, String text, String address, String s, String address1, String editText1, String isDef, String id, Context context) {
        RequestUtils.editAddress(editText, text, address, s, address1, editText1, isDef, id, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                mRootView.saveAddressSuccess();
            }
        });
    }
}
