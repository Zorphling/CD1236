package com.business.cd1236.net;


import com.business.cd1236.net.api.main.MainService;
import com.business.cd1236.net.api.setting.SettingService;


public class RequestUtils {

    public static void reviseNickName(BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).reviseNickName().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }
    public static void getBanner(BaseCallBack callBack){
        RetrofitUtils.getService(MainService.class).homeBanner().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }
    public static void getGoods(BaseCallBack callBack){
        RetrofitUtils.getService(MainService.class).homeGoods().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }
}