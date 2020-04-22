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
    Observable<ResponseBody> businessGoodsManage();
}
