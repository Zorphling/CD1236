package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.StoreDetailBean;
import com.business.cd1236.mvp.contract.StoreContract;
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
 * Created by MVPArmsTemplate on 04/15/2020 17:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class StorePresenter extends BasePresenter<StoreContract.Model, StoreContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public StorePresenter(StoreContract.Model model, StoreContract.View rootView) {
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

    public void getStoreDetail(String id, Context context) {
        RequestUtils.getStoreDetail(id, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                StoreDetailBean storeDetailBean = GsonUtils.parseJsonWithGson(jsonString, StoreDetailBean.class);
                mRootView.getStoreDetailSucc(storeDetailBean);
            }
        });
    }

    public void followStore(String id,String type, Context context) {
        RequestUtils.followStore(id,type, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
                mRootView.followStoreSucc(msg);
            }
        });
    }
}
