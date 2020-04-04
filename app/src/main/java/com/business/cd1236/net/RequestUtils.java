package com.business.cd1236.net;


import com.business.cd1236.net.api.goods.GoodsService;
import com.business.cd1236.net.api.login.LoginService;
import com.business.cd1236.net.api.main.MainService;
import com.business.cd1236.net.api.setting.SettingService;


public class RequestUtils {

    public static void reviseNickName(BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).reviseNickName().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getBanner(BaseCallBack callBack) {
        RetrofitUtils.getService(MainService.class).homeBanner().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getGoods(BaseCallBack callBack) {
        RetrofitUtils.getService(MainService.class).homeGoods().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void regist(String regist, BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).regist(regist).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void login(String login, BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).login(login).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getUserAgreement(BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).getUserAgreement().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getPrivacyPolicy(BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).getPrivacyPolicy().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void revisePsw(String ptj, BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).updatePassWord(ptj).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);

    }
   public static void getGoodsDetail(String id, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).getGoodsDetail(id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);

    }

}