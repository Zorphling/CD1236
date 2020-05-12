package com.business.cd1236.net.api.goods;

public interface GoodsApi {
    String GOODS_DETAIL = "goods";
    String BUSINESS_ENTER = "get_into";
    String COLLECT_ADD = "collect_add";//1收藏 0取消
    String COLLECT_GOODS = "collect";
    String COLLECT_DELETE = "collect_delete";
    String BROWSE = "browse";
    String SEARCH = "search";
    String ADD_SHOPPING = "add_shopping";
    String SHOPPING_CAR = "shopping";
    String SHOPPING_XG = "shopping_xg";//购物车数量修改
    String SHOPPING_DELETE = "shopping_delete";//删除购物车
    String ORDER_CONFIRM = "confirm";//下单
    String ADD_ORDER = "add_order";//生成订单
}
