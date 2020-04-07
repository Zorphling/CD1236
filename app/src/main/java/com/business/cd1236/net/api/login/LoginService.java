package com.business.cd1236.net.api.login;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ================================================
 * ================================================
 */
public interface LoginService {
    //    @FormUrlEncoded
//    @Multipart
    @GET(LoginApi.REGIST)
    Observable<ResponseBody> regist(@Query("name") String name, @Query("pwd") String pwd);

    @GET(LoginApi.LOGIN)
    Observable<ResponseBody> login(@Query("username") String username, @Query("userpwd") String userpwd);

    @GET(LoginApi.USER_AGREEMENT)
    Observable<ResponseBody> getUserAgreement();

    @GET(LoginApi.PRIVACY_POLICY)
    Observable<ResponseBody> getPrivacyPolicy();

    @GET(LoginApi.UPDATE)
    Observable<ResponseBody> updatePassWord(@Query("name") String name, @Query("pwd") String pwd);

    @GET(LoginApi.PERSONAL)
    Observable<ResponseBody> getPersonalInfo();

    @GET(LoginApi.UPLOAD)
    Observable<ResponseBody> uploadAvatar();

    @GET(LoginApi.MEMBER)
    Observable<ResponseBody> reviseUserInfo(@Query("realname") String realname, @Query("img") String img);

}
