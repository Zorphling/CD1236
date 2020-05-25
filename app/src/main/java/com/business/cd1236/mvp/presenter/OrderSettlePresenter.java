package com.business.cd1236.mvp.presenter;

import android.app.Application;
import android.content.Context;
import android.text.Html;

import com.business.cd1236.bean.OrderPayBean;
import com.business.cd1236.mvp.contract.OrderSettleContract;
import com.business.cd1236.net.BaseCallBack;
import com.business.cd1236.net.RequestUtils;
import com.business.cd1236.net.RetrofitUtils;
import com.business.cd1236.net.RxHelper;
import com.business.cd1236.net.api.goods.GoodsService;
import com.business.cd1236.utils.GsonUtils;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.ResponseBody;
import project.com.arms.mvp.model.api.Api;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/17/2020 19:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class OrderSettlePresenter extends BasePresenter<OrderSettleContract.Model, OrderSettleContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public OrderSettlePresenter(OrderSettleContract.Model model, OrderSettleContract.View rootView) {
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

    public void getOrderMoney(String orderId, Context context) {
        RequestUtils.getOrderMoney(orderId, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                OrderPayBean orderPayBean = GsonUtils.parseJsonWithGson(jsonString, OrderPayBean.class);
                mRootView.getOrderMoneySucc(orderPayBean);
            }
        });
    }

    public void pay(String order_id , Context context) {
        RetrofitUtils.getInstance().getRetrofitBaseUrl(Api.PAY_API).create(GoodsService.class).pay(order_id).compose(RxHelper.observableIO2Main(context)).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                String s = new String(responseBody.bytes());
//                String s = "alipay_sdk=alipay-sdk-php-20180705&app_id=2021001163668262&biz_content=%7B%22body%22%3A%22%5Cu4e70%5Cu76d0%5Cu7f51%22%2C%22subject%22%3A%22%5Cu4e70%5Cu76d0%5Cu7f51%22%2C%22out_trade_no%22%3A%22SH2020051850344189471219%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A0.01%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmy.1236sc.com%2Findex.php%2FAliPay%2Falinotify&sign_type=RSA2&timestamp=2020-05-23+10%3A20%3A36&version=1.0&sign=OgJ%2FJw3DIUCNV6QXU7JM4yPvLBG%2FCot%2FXCDo11D1or9BbX38kUjLSmSkn9geZFEh8sMO%2BCcpvSYIRiXm4fa7c8avvU%2F%2B7jM801IbD%2BvkQG1XDxGtBY5PfAXT2wC17NPAruZ3Meic9mlB%2BGAdFuAdh7JSa8sEDWTSFZ9f7uUC5ZfySTCCSvmxUG3gMEIO41myecn6d1DuW6zE12PjYWbbdwSXx4ZSYXcOq3hVB59eZ%2BEQEDcBgEP51cOOeChSvIkXm14swGE9kEuujmXUimcM3lL6cHfxJnfpbKcBNRHWsW2eJehCvWp8m3iRCGiAwxI10zoRhcQceRPG13zEi792Rg%3D%3D";
//                LogUtils.e();
                mRootView.payCallBack(Html.fromHtml(s).toString());
            }
        });
    }

    public void paySucc(String orderId, Context context) {
        RequestUtils.paySucc(orderId, new BaseCallBack(context) {
            @Override
            protected void onSuccess(String jsonString) {
                mRootView.paySuccBack();
            }
        });
    }
}
