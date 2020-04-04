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
    @GET(LoginApi.REGIST)
    Observable<ResponseBody> regist(@Query("req") String regist);

    @GET(LoginApi.LOGIN)
    Observable<ResponseBody> login(@Query("req") String login);

    @GET(LoginApi.USER_AGREEMENT)
    Observable<ResponseBody> getUserAgreement();

    @GET(LoginApi.PRIVACY_POLICY)
    Observable<ResponseBody> getPrivacyPolicy();

    @GET(LoginApi.UPDATE)
    Observable<ResponseBody> updatePassWord(@Query("req") String revisePsw);
}
