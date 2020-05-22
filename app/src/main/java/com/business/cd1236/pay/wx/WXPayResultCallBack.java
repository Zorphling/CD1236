package com.business.cd1236.pay.wx;

public interface WXPayResultCallBack {
    void onSuccess(); //支付成功

    void onError(int error_code);   //支付失败

    void onCancel();    //支付取消
}