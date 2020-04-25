package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.BusinessGoodsManageBean;
import com.business.cd1236.mvp.contract.BusinessGoodsManageContract;
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
 * Created by MVPArmsTemplate on 04/21/2020 15:16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class BusinessGoodsManagePresenter extends BasePresenter<BusinessGoodsManageContract.Model, BusinessGoodsManageContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public BusinessGoodsManagePresenter(BusinessGoodsManageContract.Model model, BusinessGoodsManageContract.View rootView) {
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

    public void getAllGoods(Context context) {
        RequestUtils.businessGoodsManage(new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                BusinessGoodsManageBean businessGoodsManageBean = GsonUtils.parseJsonWithGson(jsonString, BusinessGoodsManageBean.class);
                mRootView.getAllGoodsSucc(businessGoodsManageBean);
            }
        });
    }

    public void businessGoodsQuick(String id, String type, String name, Context context) {
        RequestUtils.businessGoodsQuick(id, type, name, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
//                mRootView.businessGoodsQuickSucc(msg);
            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
//                mRootView.businessGoodsQuickSucc(msg);
            }
        });
    }
}
