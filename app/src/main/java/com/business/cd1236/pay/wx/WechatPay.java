package com.business.cd1236.pay.wx;

import android.app.Activity;
import android.text.TextUtils;

import com.business.cd1236.utils.LogUtils;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WechatPay {

    public static final int NO_OR_LOW_WX = 1;   //未安装微信或微信版本过低
    public static final int ERROR_PAY_PARAM = 2;  //支付参数错误
    public static final int ERROR_PAY = 3;  //支付失败
    private static WechatPay sMWechatPay;
    private IWXAPI mWXApi;
    private WxBean mPayParam;
    private WXPayResultCallBack mCallback;

    public WechatPay(Activity activity) {
        mWXApi = WXAPIFactory.createWXAPI(activity, null);
    }

    public static void init(Activity activity) {
        if (sMWechatPay == null) {
            sMWechatPay = new WechatPay(activity);
        }
    }

    public static WechatPay getInstance() {
        return sMWechatPay;
    }

    public IWXAPI getWXApi() {
        return mWXApi;
    }

    /**
     * 发起微信支付
     */
    public void doPay(WxBean pay_param, WXPayResultCallBack callback) {
        mPayParam = pay_param;
        mCallback = callback;

        if (!check()) {
            if (mCallback != null) {
                mCallback.onError(NO_OR_LOW_WX);
            }
            return;
        }

        if (TextUtils.isEmpty(pay_param.getAppid()) || TextUtils.isEmpty(pay_param.getPartnerid())
                || TextUtils.isEmpty(pay_param.getPrepayid()) || TextUtils.isEmpty(pay_param.getPackageX()) ||
                TextUtils.isEmpty(pay_param.getNoncestr()) || TextUtils.isEmpty(pay_param.getTimestamp()) ||
                TextUtils.isEmpty(pay_param.getSign())) {
            if (mCallback != null) {
                mCallback.onError(ERROR_PAY_PARAM);
            }
            return;
        }
       mWXApi.registerApp(pay_param.getAppid()); //TODO 支付之前调用
       // mWXApi.registerApp("wxd2f733fd3f81aa86"); //支付之前调用

        PayReq req = new PayReq();
        req.appId = pay_param.getAppid();
        req.partnerId = pay_param.getPartnerid();
        req.prepayId = pay_param.getPrepayid();
        req.packageValue = pay_param.getPackageX();
        req.nonceStr = pay_param.getNoncestr();
        req.timeStamp = pay_param.getTimestamp();
        req.sign = pay_param.getSign();
        boolean b = mWXApi.sendReq(req);
        LogUtils.i("mWXApi:::"+b);
    }

    //支付回调响应
    public void onResp(int error_code) {
        if (mCallback == null) {
            return;
        }
        if (error_code == 0) {   //成功
            mCallback.onSuccess();
        } else if (error_code == -1) {   //错误
            mCallback.onError(ERROR_PAY);
        } else if (error_code == -2) {   //取消
            mCallback.onCancel();
        }

        mCallback = null;
    }

    //检测是否支持微信支付
    private boolean check() {
     return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }


}
