package com.business.cd1236.net.api.goods;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ================================================
 * 存放关于设置的一些 API
 * ================================================
 */
public interface GoodsService {
    @GET(GoodsApi.GOODS_DETAIL)
    Observable<ResponseBody> getGoodsDetail(@Query("id")String goods_id);
}
