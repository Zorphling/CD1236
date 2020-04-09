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

    public static void regist(String name, String pwd, BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).regist(name, pwd).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void login(String name, String pwd, BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).login(name, pwd).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getUserAgreement(BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).getUserAgreement().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getPrivacyPolicy(BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).getPrivacyPolicy().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void revisePsw(String name, String pwd, BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).updatePassWord(name, pwd).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);

    }

    public static void getGoodsDetail(String id, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).getGoodsDetail(id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);

    }

    public static void getMore(BaseCallBack callBack) {
        RetrofitUtils.getService(MainService.class).getMore().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getPersonalInfo(BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).getPersonalInfo().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void reviseUserInfo(String realname,String img,BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).reviseUserInfo(realname,img).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getEnterType(BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).getEnterType().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void submitBusinessEnter(String ENTER_TIME, String editText, String text, String s, String ENTER_TYPE, String address, String address1, String s1, String editText1, String text1,BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).business_enter(ENTER_TIME,editText,text,s,ENTER_TYPE,address,address1,s1,editText1,text1).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void loginOut(BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).loginOut().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getAddress(BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).getAddress().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }
    public static void addAddress(String editText, String text, String address, String s, String address1, String editText1,String isDef ,BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).addAddress(editText,text,address,s,address1,editText1,isDef).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }
    public static void editAddress(String editText, String text, String address, String s, String address1, String editText1,String isDef ,String id,BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).editAddress(editText,text,address,s,address1,editText1,isDef,id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }
    public static void setDefAddress(String id, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).setDefAddress(id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);

    }
    public static void deleteAddress(String id, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).deleteAddress(id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);

    }
}