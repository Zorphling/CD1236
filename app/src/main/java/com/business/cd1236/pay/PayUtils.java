package com.business.cd1236.pay;

import android.app.Activity;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.business.cd1236.pay.alipay.PayResult;
import com.business.cd1236.pay.wx.WXPayResultCallBack;
import com.business.cd1236.pay.wx.WechatPay;
import com.business.cd1236.pay.wx.WxBean;
import com.business.cd1236.utils.LogUtils;
import com.business.cd1236.utils.MyToastUtils;

import org.reactivestreams.Publisher;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PayUtils {
    public static final int ALIPAY = 1; //支付宝
    public static final int WECHAT = 2; //微信

    /**
     * 支付
     *
     * @param activity
     * @param type        支付方式
     * @param data
     * @param payCallBack
     */
    public static void pay(Activity activity, int type, String data, PayCallBack payCallBack) {
        if (type == ALIPAY) {
            doAliPay(activity, data, payCallBack);
        } else if (type == WECHAT) {
            //doWXPay(activity, data, payCallBack);
        }
    }


    /**
     * 支付宝支付
     *
     * @param data     支付信息
     * @param callBack 支付成功回调
     */
    public static void doAliPay(final Activity context, String data, final PayCallBack callBack) {
        // 必须异步调用
        Flowable.just(data).map(new Function<String, Map<String, String>>() {
            @Override
            public Map<String, String> apply(String str) throws Exception {
                PayTask alipay = new PayTask(context);
                return alipay.payV2(str, true);
            }
        }).compose(applySchedulers()).subscribe(map -> {
            PayResult payResult = new PayResult(map);
            LogUtils.e("PayResult -- "+payResult.toString());
            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                callBack.call();
            } else if (TextUtils.equals(resultStatus, "6001")) {
                MyToastUtils.showShort("支付取消");
                callBack.cancel();
            } else {
                MyToastUtils.showShort("支付失败");
                callBack.fail();
            }
        });
    }

    /**
     * 修改rx线程
     */
    public static <T> FlowableTransformer<T, T> applySchedulers() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> flowable) {
                return flowable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 微信支付
     *
     * @param activity
     * @param data
     * @param callBack
     */
    public static void doWXPay(Activity activity, WxBean data, PayCallBack callBack) {
        WechatPay.init(activity);
        WechatPay.getInstance().doPay(data, new WXPayResultCallBack() {
            @Override
            public void onSuccess() {
                callBack.call();
            }

            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case WechatPay.NO_OR_LOW_WX:
                        MyToastUtils.showShort("未安装微信或微信版本过低");
                        callBack.fail();
                        break;

                    case WechatPay.ERROR_PAY_PARAM:
                        MyToastUtils.showShort("参数错误");
                        callBack.fail();
                        break;

                    case WechatPay.ERROR_PAY:
                        MyToastUtils.showShort("支付失败");
                        callBack.fail();
                        break;
                }
            }

            @Override
            public void onCancel() {
                MyToastUtils.showShort("支付取消");
                // callBack.call();
            }
        });
    }

}
