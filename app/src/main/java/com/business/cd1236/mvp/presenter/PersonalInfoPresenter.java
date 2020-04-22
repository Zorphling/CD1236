package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.mvp.contract.PersonalInfoContract;
import com.business.cd1236.net.BaseCallBack;
import com.business.cd1236.net.RequestUtils;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.MultipartBody;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/01/2020 10:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class PersonalInfoPresenter extends BasePresenter<PersonalInfoContract.Model, PersonalInfoContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public PersonalInfoPresenter(PersonalInfoContract.Model model, PersonalInfoContract.View rootView) {
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

    public void updateImg(String img, Context context) {
        RequestUtils.reviseUserImg(img, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
                mRootView.updateImgSucc(msg);
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
}
