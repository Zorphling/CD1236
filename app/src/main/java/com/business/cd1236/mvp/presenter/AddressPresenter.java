package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.AddAddressBean;
import com.business.cd1236.mvp.contract.AddressContract;
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
 * Created by MVPArmsTemplate on 04/09/2020 11:23
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class AddressPresenter extends BasePresenter<AddressContract.Model, AddressContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public AddressPresenter(AddressContract.Model model, AddressContract.View rootView) {
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

    public void getAddress(Context context) {
        RequestUtils.getAddress(new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                ArrayList<AddAddressBean> addAddressBeans = GsonUtils.parseJsonArrayWithGson(jsonString, AddAddressBean.class);
                mRootView.setAddress(addAddressBeans);
            }
        });
    }

    public void setDefAddress(String id, Context context) {
        RequestUtils.setDefAddress(id,new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                mRootView.setDefAddress();
            }
        });
    }

    public void deleteAddress(String id, Context context) {
        RequestUtils.deleteAddress(id,new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                mRootView.deleteAddressSuccess();
            }
        });
    }
}
