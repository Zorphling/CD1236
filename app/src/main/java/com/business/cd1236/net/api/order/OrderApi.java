package com.business.cd1236.net.api.order;

public interface OrderApi {
    String ORDER_CHANGE = "order_change";
    String PAY_SUCCESSFUL = "pay_successful";//支付成功给后台发个消息 他收不到支付宝异步回调
}
