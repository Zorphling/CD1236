package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.BusinessCategoryBean;
import com.business.cd1236.mvp.contract.BusinessManageCategoryContract;
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
 * Created by MVPArmsTemplate on 04/23/2020 16:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class BusinessManageCategoryPresenter extends BasePresenter<BusinessManageCategoryContract.Model, BusinessManageCategoryContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public BusinessManageCategoryPresenter(BusinessManageCategoryContract.Model model, BusinessManageCategoryContract.View rootView) {
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

    public void businessCategoty(Context context) {
        RequestUtils.businessCategoty(new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                ArrayList<BusinessCategoryBean> businessCategoryBeans = GsonUtils.parseJsonArrayWithGson(jsonString, BusinessCategoryBean.class);
                mRootView.businessCategotySucc(businessCategoryBeans);
            }
        });
    }

    public void businessCategotyDelete(String id, Context context) {
        RequestUtils.businessCategotyDelete(id, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
                mRootView.categotyDeleteSucc(msg);
            }
        });
    }
}
