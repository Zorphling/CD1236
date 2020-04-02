package com.business.cd1236.net.api.main;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.POST;

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
}
