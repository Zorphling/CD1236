package com.business.cd1236.net.api.order;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ================================================
 * 存放关于订单的一些 API
 * ================================================
 */
public interface OrderService {
    @GET(OrderApi.ORDER_CHANGE)
    Observable<ResponseBody> orderStatusChange(@Query("id") String order_id, @Query("status") String status);//3 确认收货

    @GET(OrderApi.PAY_SUCCESSFUL)
    Observable<ResponseBody> paySucc(@Query("id") String goods_id);//3 确认收货

}
