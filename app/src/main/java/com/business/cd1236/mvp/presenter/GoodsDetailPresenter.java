package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.business.cd1236.bean.GoodsDetailBean;
import com.business.cd1236.mvp.contract.GoodsDetailContract;
import com.business.cd1236.net.BaseCallBack;
import com.business.cd1236.net.RequestUtils;
import com.business.cd1236.utils.GsonUtils;
import com.business.cd1236.utils.StringUtils;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/04/2020 16:22
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class GoodsDetailPresenter extends BasePresenter<GoodsDetailContract.Model, GoodsDetailContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public GoodsDetailPresenter(GoodsDetailContract.Model model, GoodsDetailContract.View rootView) {
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

    public void getGoodsDetial(String id, Context context) {
        RequestUtils.getGoodsDetail(id, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                GoodsDetailBean goodsDetailBean = GsonUtils.parseJsonWithGson(jsonString, GoodsDetailBean.class);
                mRootView.setGoodsDetail(goodsDetailBean);
            }

            @Override
            protected void onFailure(String errorMsg, int status) {
                super.onFailure(errorMsg, status);
                if (StringUtils.checkString(errorMsg)) {
                    ArmsUtils.snackbarText(errorMsg);
                }
            }
        });
    }

    public void addCollect(String id, String ment, Context context) {
        RequestUtils.addCollect(id, ment, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
                mRootView.setCollectSuccess(msg);
            }
        });
    }

    public void addShopping(String goodsid , String total, String marketprice, String shop_id, Context context) {
        RequestUtils.add_shopping(goodsid, total, marketprice, shop_id, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {

            }

            @Override
            protected void onSuccess(String jsonString, String msg) {
                super.onSuccess(jsonString, msg);
                mRootView.addShoppingSucc(msg);
            }
        });
    }
}
