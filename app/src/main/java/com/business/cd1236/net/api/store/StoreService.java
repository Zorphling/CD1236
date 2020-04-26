package com.business.cd1236.net.api.store;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ================================================
 * 存放关于店铺管理设置的一些 API
 * ================================================
 */
public interface StoreService {
    @GET(StoreApi.BUSINESS_DETAIL)
    Observable<ResponseBody> getBusinessDetail();

    @GET(StoreApi.BUSINESS_BASIS)
    Observable<ResponseBody> getBusinessInfo();

    @GET(StoreApi.BUSINESS_INFO_CHANGE)
    Observable<ResponseBody> businessInfoChange(@Query("type") String type, @Query("open_time") String open_time, @Query("close_time") String close_time,
                                                @Query("open_time1") String open_time1, @Query("close_time1") String close_time1);

    @GET(StoreApi.BUSINESS_INFO_CHANGE)
    Observable<ResponseBody> businessInfoChange(@Query("type") String type,
                                                @Query("province") String province, @Query("city") String city, @Query("district") String district,
                                                @Query("address") String address, @Query("map_lng") String map_lng, @Query("map_lat") String map_lat);

    @GET(StoreApi.BUSINESS_INFO_CHANGE)
    Observable<ResponseBody> businessInfoChange_business_name(@Query("type") String type, @Query("business_name") String business_name);

    @GET(StoreApi.BUSINESS_INFO_CHANGE)
    Observable<ResponseBody> businessInfoChange(@Query("type") String type, @Query("production") String production, @Query("production_s") String production_s,
                                                @Query("wholesale") String wholesale, @Query("wholesale_s") String wholesale_s, @Query("remains") String remains);


    @GET(StoreApi.BUSINESS_INFO_CHANGE)
    Observable<ResponseBody> businessInfoChange_introduction(@Query("type") String type, @Query("introduction") String introduction);

    @GET(StoreApi.BUSINESS_INFO_CHANGE)
    Observable<ResponseBody> businessInfoChange_culture(@Query("type") String type, @Query("culture") String culture);

    @GET(StoreApi.BUSINESS_INFO_MODIFY)
    Observable<ResponseBody> businessInfoChange_telephone(@Query("type") String type, @Query("telephone") String telephone);

    @GET(StoreApi.BUSINESS_INFO_MODIFY)
    Observable<ResponseBody> businessInfoChange_shop_thumb(@Query("type") String type, @Query("shop_thumb") String shop_thumb);

    @GET(StoreApi.BUSINESS_INFO_MODIFY)
    Observable<ResponseBody> businessInfoChange_shop_logo(@Query("type") String type, @Query("logo") String logo);

    @GET(StoreApi.BUSINESS_GOODS)
    Observable<ResponseBody> businessGoodsManage(@Query("type")String type);

    @GET(StoreApi.BUSINESS_GOODS_SHOW)
    Observable<ResponseBody> businessGoodsShow();

    @GET(StoreApi.BUSINESS_ADD_GOODS)
    Observable<ResponseBody> businessAddGoods(@Query("id") String id, @Query("ccate") String ccate, @Query("pcate") String pcate, @Query("category") String category, @Query("title") String title,
                                              @Query("weight") String weight, @Query("unit") String unit, @Query("agent_weight") String agent_weight,
                                              @Query("agent_unit") String agent_unit, @Query("format") String format, @Query("thumb") String thumb
            , @Query("content") String content, @Query("marketprice") String marketprice, @Query("agent_marketprice") String agent_marketprice
            , @Query("productprice") String productprice, @Query("total") String total, @Query("thumb_url") String thumb_url);

    @GET(StoreApi.BUSINESS_GOODS_DELETE)
    Observable<ResponseBody> businessGoodsDelete(@Query("id") String id);

    @GET(StoreApi.BUSINESS_GOODS_QUICK)
    Observable<ResponseBody> businessGoodsQuick(@Query("id") String id, @Query("type") String type, @Query("name") String name);

    @GET(StoreApi.BUSINESS_CATEGORY)
    Observable<ResponseBody> businessCategoty();

    @GET(StoreApi.BUSINESS_CATEGORY_CHANGE)
    Observable<ResponseBody> businessCategotyChange(@Query("name") String name, @Query("content") String content, @Query("id") String id);

    @GET(StoreApi.BUSINESS_CATEGORY_DELETE)
    Observable<ResponseBody> businessCategotyDelete(@Query("id") String id);

    @GET(StoreApi.BUSINESS_DISPLAYORDER)
    Observable<ResponseBody> businessDisplayorder(@Query("type") String type, @Query("row") String row);
}
