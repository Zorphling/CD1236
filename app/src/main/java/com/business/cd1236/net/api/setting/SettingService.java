package com.business.cd1236.net.api.setting;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ================================================
 * 存放关于设置的一些 API
 * ================================================
 */
public interface SettingService {
    @POST(SettingApi.REVISE_NICK_NAME)
    Observable<ResponseBody> reviseNickName();

    @POST(SettingApi.GET_ADDRESS)
    Observable<ResponseBody> getAddress();

    @POST(SettingApi.ADD_ADDRESS)
    Observable<ResponseBody> addAddress(@Query("realname") String realname, @Query("mobile") String mobile
            , @Query("province") String province, @Query("city") String city, @Query("area") String area, @Query("address") String address
            , @Query("default") String def);

    @POST(SettingApi.ADD_ADDRESS)
    Observable<ResponseBody> editAddress(@Query("realname") String realname, @Query("mobile") String mobile
            , @Query("province") String province, @Query("city") String city, @Query("area") String area, @Query("address") String address
            , @Query("default") String def, @Query("id") String id);

    @POST(SettingApi.SET_ADDRESS_DEF)
    Observable<ResponseBody> setDefAddress(@Query("id") String id);

    @POST(SettingApi.DELETE_ADDRESS)
    Observable<ResponseBody> deleteAddress(@Query("id") String id);
}
