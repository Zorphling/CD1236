package com.business.cd1236.net.api.main;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * ================================================
 * 存放首页的一些 API
 * ================================================
 */
public interface MainService {
    @POST(MainApi.HOME_BANNER)
    Observable<ResponseBody> homeBanner();

    @POST(MainApi.HOME_GOODS)
    Observable<ResponseBody> homeGoods();

    @GET(MainApi.MORE)
    Observable<ResponseBody> getMore();

    @GET(MainApi.APP_UPDATE)
    Observable<ResponseBody> appUpdate();

    /**
     * 下载文件用
     * @param fileUrl
     * @return
     */
    @Streaming //添加这个注解用来下载大文件
    @GET()
    Call<ResponseBody> downloadFileUrl(@Url String fileUrl);
}
