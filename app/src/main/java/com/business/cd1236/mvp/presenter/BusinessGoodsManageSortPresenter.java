package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.BusinessGoodsManageBean;
import com.business.cd1236.mvp.contract.BusinessGoodsManageSortContract;
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
 * Created by MVPArmsTemplate on 04/26/2020 11:00
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class BusinessGoodsManageSortPresenter extends BasePresenter<BusinessGoodsManageSortContract.Model, BusinessGoodsManageSortContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public BusinessGoodsManageSortPresenter(BusinessGoodsManageSortContract.Model model, BusinessGoodsManageSortContract.View rootView) {
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
    public void getAllGoods(String type ,Context context) {
        RequestUtils.businessGoodsManage(type ,new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                BusinessGoodsManageBean businessGoodsManageBean = GsonUtils.parseJsonWithGson(jsonString, BusinessGoodsManageBean.class);
                mRootView.getAllGoodsSucc(businessGoodsManageBean);
            }
        });
    }

    public void goodsSort(String type, String builder, Context context) {
        RequestUtils.businessDisplayorder(type, builder, new BaseCallBack(context,false) {
            @Override
            protected void onSuccess(String jsonString) {
                mRootView.goodsSortSucc(jsonString);
            }
        });
    }
}
