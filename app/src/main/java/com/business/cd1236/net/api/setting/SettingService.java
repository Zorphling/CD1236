package com.business.cd1236.net.api.setting;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.POST;

/**
 * ================================================
 * 存放关于设置的一些 API
 * ================================================
 */
public interface SettingService {
    @POST(SettingApi.REVISE_NICK_NAME)
    Observable<ResponseBody> reviseNickName();
}
