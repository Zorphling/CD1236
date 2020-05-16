package com.business.cd1236.net.api.goods;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ================================================
 * 存放关于设置的一些 API
 * ================================================
 */
public interface GoodsService {
    @GET(GoodsApi.GOODS_DETAIL)
    Observable<ResponseBody> getGoodsDetail(@Query("id") String goods_id);

    @GET(GoodsApi.BUSINESS_ENTER)
    Observable<ResponseBody> business_enter(@Query("pay_id") String pay_id, @Query("business_name") String business_name
            , @Query("real_name") String real_name, @Query("telephone") String telephone, @Query("type") String type, @Query("province") String province
            , @Query("city") String city, @Query("district") String district, @Query("address") String address, @Query("pid") String pid);

    @GET(GoodsApi.COLLECT_ADD)
    Observable<ResponseBody> addCollect(@Query("id") String goods_id, @Query("ment") String ment);

    @GET(GoodsApi.COLLECT_GOODS)
    Observable<ResponseBody> queryCollectGoods();

    @GET(GoodsApi.COLLECT_DELETE)
    Observable<ResponseBody> deleteCollectGoods(@Query("id") String ids);

    @GET(GoodsApi.BROWSE)
    Observable<ResponseBody> queryBrowse();

    @GET(GoodsApi.SEARCH)
    Observable<ResponseBody> search(@Query("name") String name, @Query("db") int page);

    @GET(GoodsApi.ADD_SHOPPING)
    Observable<ResponseBody> add_shopping(@Query("goodsid") String goodsid, @Query("total") String total
            , @Query("marketprice") String marketprice, @Query("shop_id") String shop_id);

    @GET(GoodsApi.SHOPPING_CAR)
    Observable<ResponseBody> getShopping();

    @GET(GoodsApi.SHOPPING_XG)
    Observable<ResponseBody> shopping_xg(@Query("id") String id, @Query("total") String total);

    @GET(GoodsApi.SHOPPING_DELETE)
    Observable<ResponseBody> shopping_delete(@Query("id") String carId);

    @GET(GoodsApi.ORDER_CONFIRM)//单个商品下单
    Observable<ResponseBody> orderConfirm(@Query("goodsid") String goodsid, @Query("goodsNum") String goodsNum, @Query("ment") String ment,@Query("judge")String jud);

    @GET(GoodsApi.ORDER_CONFIRM)//购物车下单
    Observable<ResponseBody> orderConfirm(@Query("goodsid") String goodsid, @Query("ment") String ment,@Query("judge")String jud);


    @POST(GoodsApi.ADD_ORDER)
    Observable<ResponseBody> addOrder(@Query("goodsid") String goodsid,@Query("addressid") String addressid, @Query("freight") String freight, @Query("since") String since, @Query("leave") String leave);
}
