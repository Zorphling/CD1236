package com.business.cd1236.net;


import com.business.cd1236.net.api.setting.SettingService;


public class RequestUtils {

    public static void reviseNickName(BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).reviseNickName().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }
}