package com.business.cd1236.bean;

import java.util.List;

public class MyOrderBean {

    /**
     * id : 109
     * express : 0
     * express_name : null
     * express_number : 0
     * jud : 1
     * price : 900.00
     * address : 渠江镇渠光路997号
     * realname : 具有我也
     * mobile : 18888888888
     * since : 0
     * status : 0
     * business_name : 四川省天渠盐化有限公司
     * shop_id : 23
     * province : 四川省
     * city : 达州市
     * district : 渠县
     * statusstr : 待付款
     * total : 1
     * goods : [{"goodsid":"52","total":"1","title":"400克绿标","thumb":"http://my.1236sc.com/Public/Uploads/400lb.png","price":"900.00","optionid":null,"judge":"1"}]
     */

    public String id;
    public String express;
    public Object express_name;
    public String express_number;
    public String jud;//0 普通  1批发
    public String price;
    public String address;
    public String realname;
    public String mobile;
    public String since;
    public String status;
    public String business_name;
    public String shop_id;
    public String province;
    public String city;
    public String district;
    public String statusstr;
    public int total;
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
