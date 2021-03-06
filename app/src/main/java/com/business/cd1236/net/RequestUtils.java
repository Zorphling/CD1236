package com.business.cd1236.net;


import android.text.TextUtils;

import com.business.cd1236.net.api.goods.GoodsService;
import com.business.cd1236.net.api.login.LoginService;
import com.business.cd1236.net.api.main.MainService;
import com.business.cd1236.net.api.order.OrderService;
import com.business.cd1236.net.api.setting.SettingService;
import com.business.cd1236.net.api.store.StoreService;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class RequestUtils {

    public static void reviseNickName(String nickName, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).reviseNickName(nickName).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void reviseUserImg(String img, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).reviseUserImg(img).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void uploadImg(MultipartBody.Part part, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).uploadImg(part).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void uploadImgs(Map<String, RequestBody> maps, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).uploadImgs(maps).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void uploadImgs(List<MultipartBody.Part> partList, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).uploadImgs(partList).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getBanner(BaseCallBack callBack) {
        RetrofitUtils.getService(MainService.class).homeBanner().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getGoods(int db, BaseCallBack callBack) {
        RetrofitUtils.getService(MainService.class).homeGoods(db).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
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

    public static void checkUpdate(BaseCallBack callBack) {
        RetrofitUtils.getService(MainService.class).appUpdate().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getPersonalInfo(BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).getPersonalInfo().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void reviseUserInfo(String realname, String img, BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).reviseUserInfo(realname, img).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getEnterType(BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).getEnterType().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void submitBusinessEnter(String ENTER_TIME, String editText, String text, String s, String ENTER_TYPE, String address, String address1, String s1, String editText1, String text1, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).business_enter(ENTER_TIME, editText, text, s, ENTER_TYPE, address, address1, s1, editText1, text1).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void loginOut(BaseCallBack callBack) {
        RetrofitUtils.getService(LoginService.class).loginOut().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getAddress(BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).getAddress().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void addAddress(String editText, String text, String address, String s, String address1, String editText1, String isDef, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).addAddress(editText, text, address, s, address1, editText1, isDef).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void editAddress(String editText, String text, String address, String s, String address1, String editText1, String isDef, String id, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).editAddress(editText, text, address, s, address1, editText1, isDef, id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void setDefAddress(String id, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).setDefAddress(id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);

    }

    public static void deleteAddress(String id, BaseCallBack callBack) {
        RetrofitUtils.getService(SettingService.class).deleteAddress(id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void addCollect(String id, String ment, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).addCollect(id, ment).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void queryCollectGoods(BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).queryCollectGoods().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void deleteCollectGoods(String ids, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).deleteCollectGoods(ids).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void queryBrowse(BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).queryBrowse().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void search(String stringExtra, int page, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).search(stringExtra, page).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void orderConfirm(String goodsid, String ment, String jud, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).orderConfirm(goodsid, ment, jud).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void orderConfirm(String goodsid, String goodsNum, String ment, String jud, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).orderConfirm(goodsid, goodsNum, ment, jud).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void addOrder(String arrayList, String addressId, String freight, String since, String editText, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).addOrder(arrayList, addressId, freight, since, editText).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    //数组
    public static void addOrder(String[] arrayList, String addressId, String freight, String since, String editText, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).addOrder(arrayList, addressId, freight, since, editText).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    //Map
    public static void addOrder(Map<String, String> arrayList, String addressId, String freight, String since, String editText, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).addOrder(arrayList, addressId, freight, since, editText).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    //下单后
    public static void getOrderMoney(String orderId, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).orderPay(orderId).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    //查询我的订单
    public static void getMyOrder(String status, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).getMyOrder(status).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    //删除订单
    public static void deleteOrder(String id, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).deleteOrder(id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    //订单状态改变
    public static void orderStatusChange(String id, String status, BaseCallBack callBack) {
        RetrofitUtils.getService(OrderService.class).orderStatusChange(id, status).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    //支付成功给后台发个消息 他收不到支付宝异步回调
    public static void paySucc(String order_id, BaseCallBack callBack) {
        RetrofitUtils.getService(OrderService.class).paySucc(order_id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    //订单详情
    public static void orderDetails(String order_id, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).orderDetails(order_id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void add_shopping(String goodsid, String total, String marketprice, String shop_id, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).add_shopping(goodsid, total, marketprice, shop_id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getShopping(BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).getShopping().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void shopping_xg(String id, String total, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).shopping_xg(id, total).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void shopping_delete(String carId, BaseCallBack callBack) {
        RetrofitUtils.getService(GoodsService.class).shopping_delete(carId).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getStoreDetail(String id, BaseCallBack callBack) {
        RetrofitUtils.getService(StoreService.class).getStoreDetail(id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);

    }

    public static void followStore(String id, String type, BaseCallBack callBack) {
        RetrofitUtils.getService(StoreService.class).followStore(id, type).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getFollowStore(BaseCallBack callBack) {
        RetrofitUtils.getService(StoreService.class).getFollowStore().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    /**
     * stroe 店铺管理  换了请求接口地址
     *
     * @param callBack
     */
    public static void getBusinessDetail(BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).getBusinessDetail().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void getBusinessInfo(BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).getBusinessInfo().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void businessTime(String type, String string, String string1, String string2, String string3, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessInfoChange(type, string, string1, string2, string3).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void updateNotice(String type, String notice, BaseCallBack callBack) {
        if (TextUtils.equals("culture", type))
            RetrofitUtils.getStoreService(StoreService.class).businessInfoChange_culture(type, notice).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
        else
            RetrofitUtils.getStoreService(StoreService.class).businessInfoChange_introduction(type, notice).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void modifyBusinessTitle(String type, String editText, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessInfoChange_business_name(type, editText).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void addBusinessTelephone(String type, String telephone, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessInfoChange_telephone(type, telephone).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void updateBusinessLogo(String type, String logo, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessInfoChange_shop_logo(type, logo).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void businessGoodsManage(String type, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessGoodsManage(type).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void businessGoodsShow(BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessGoodsShow().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void businessAddGoods(String id, String typeId, String brandId, String categoryId, String editText, String editText1, String editText2, String editText3, String editText4, String formatId, String s, String s1, String editText5, String editText6, String editText7, String total, String agent_total, String s2, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessAddGoods(id, typeId, brandId, categoryId, editText, editText1, editText2, editText3, editText4, formatId, s, s1, editText5, editText6, editText7, total, agent_total, s2).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void businessGoodsDelete(String id, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessGoodsDelete(id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void businessGoodsQuick(String id, String type, String name, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessGoodsQuick(id, type, name).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void businessCategoty(BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessCategoty().compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void businessCategotyChange(String name, String content, String id, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessCategotyChange(name, content, id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void businessCategotyDelete(String id, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessCategotyDelete(id).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }

    public static void businessDisplayorder(String type, String builder, BaseCallBack callBack) {
        RetrofitUtils.getStoreService(StoreService.class).businessDisplayorder(type, builder).compose(RxHelper.observableIO2Main(callBack.mContext)).subscribe(callBack);
    }
}