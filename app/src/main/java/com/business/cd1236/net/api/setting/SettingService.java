package com.business.cd1236.net.api.setting;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * ================================================
 * 存放关于设置的一些 API
 * ================================================
 */
public interface SettingService {
    @GET(SettingApi.REVISE_NICK_NAME)
    Observable<ResponseBody> reviseNickName(@Query("realname") String realname);

    @Multipart
    @POST(SettingApi.UPLOAD_IMG)
    Observable<ResponseBody> uploadImg(@Part MultipartBody.Part part);

    @Multipart
    @POST(SettingApi.UPLOAD_IMG)
    Observable<ResponseBody> uploadImgs(@PartMap Map<String, RequestBody> maps);

    @Multipart
    @POST(SettingApi.UPLOAD_IMG)
    Observable<ResponseBody> uploadImgs(@Part List<MultipartBody.Part> partList);

    @GET(SettingApi.REVISE_NICK_NAME)
    Observable<ResponseBody> reviseUserImg(@Query("img") String img);

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
