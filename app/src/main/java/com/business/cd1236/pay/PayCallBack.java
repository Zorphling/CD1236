package com.business.cd1236.pay;

/**
 * 支付宝支付成功后的处理回调接口
 */
public interface PayCallBack {
    void call();

    void fail();
}