package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.BusinessGoodsShowBean;
import com.business.cd1236.mvp.contract.BusinessAddGoodsContract;
import com.business.cd1236.net.BaseCallBack;
import com.business.cd1236.net.RequestUtils;
import com.business.cd1236.utils.GsonUtils;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/22/2020 16:58
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class BusinessAddGoodsPresenter extends BasePresenter<BusinessAddGoodsContract.Model, BusinessAddGoodsContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public BusinessAddGoodsPresenter(BusinessAddGoodsContract.Model model, BusinessAddGoodsContract.View rootView) {
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

    public void getGoodsTypes(Context context) {
        RequestUtils.businessGoodsShow(new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                BusinessGoodsShowBean businessGoodsShowBean = GsonUtils.parseJsonWithGson(jsonString, BusinessGoodsShowBean.class);
                mRootView.getGoodsTypesSucc(businessGoodsShowBean);
            }
        });
    }

    public void uploadImg(MultipartBody.Part part, Context context) {
        RequestUtils.uploadImg(part, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                mRootView.uploadImgSucc(jsonString);
            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
            }
        });
    }
    public void uploadImgs(Map<String, RequestBody> map, Context context) {
        RequestUtils.uploadImgs(map, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                mRootView.uploadImgSucc(jsonString);
            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
            }
        });
    }
    public void uploadImgs(List<MultipartBody.Part> partList, Context context) {
        RequestUtils.uploadImgs(partList, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                mRootView.uploadImgSucc(jsonString);
            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
            }
        });
    }
}
