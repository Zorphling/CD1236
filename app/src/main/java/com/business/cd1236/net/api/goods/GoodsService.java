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
    Observable<ResponseBody> getGoodsDetail(@Query("id") String goods_id);

    @GET(GoodsApi.BUSINESS_ENTER)
    Observable<ResponseBody> business_enter(@Query("pay_id") String pay_id, @Query("business_name") String business_name
            , @Query("real_name") String real_name, @Query("telephone") String telephone, @Query("type") String type, @Query("province") String province
            , @Query("city") String city, @Query("district") String district, @Query("address") String address, @Query("pid") String pid);

    @GET(GoodsApi.COLLECT_ADD)
    Observable<ResponseBody> addCollect(@Query("id") String goods_id, @Query("ment") String ment);

    @GET(GoodsApi.COLLECT_GOODS)
    Observable<ResponseBody> queryCollectGoods();

    @GET(GoodsApi.COLLECT_DELETE)
    Observable<ResponseBody> deleteCollectGoods(@Query("id") String ids);
    @GET(GoodsApi.BROWSE)
    Observable<ResponseBody> queryBrowse();
}
