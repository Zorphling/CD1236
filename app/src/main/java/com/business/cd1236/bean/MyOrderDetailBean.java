package com.business.cd1236.bean;

import java.util.List;

public class MyOrderDetailBean {

    /**
     * id : 109
     * ordersn : SH202005182675476074
     * status : 0
     * realname : 具有我也
     * address : 北京市北京市朝阳区突击咯因为
     * mobile : 18888888888
     * createtime : 1589802359
     * paytime : null
     * hairtime : null
     * paytype : null
     * goods : [{"goodsid":"52","total":"1","title":"400克绿标","thumb":"http://my.1236sc.com/Public/Uploads/400lb.png","price":"900.00","optionid":null,"judge":"1"}]
     */

    public String id;
    public String ordersn;
    public String status;
    public String realname;
    public String address;
    public String mobile;
    public String createtime;
    public String paytime;
    public String hairtime;
    public String paytype;
    public List<GoodsBean> goods;

    public static class GoodsBean {
        /**
         * goodsid : 52
         * total : 1
         * title : 400克绿标
         * thumb : http://my.1236sc.com/Public/Uploads/400lb.png
         * price : 900.00
         * optionid : null
         * judge : 1
         */

        public String goodsid;
        public String total;
        public String title;
        public String thumb;
        public String price;
        public Object optionid;
        public String judge;
    }
}
