package com.business.cd1236.bean;

import java.util.List;

public class ShoppingCarBean {

    /**
     * business_name : 四川省天渠盐化有限公司
     * uid : 10
     * id : 23
     * jud_wholesale : 0
     * goods : [{"total":"1","marketprice":"2.50","agent_marketprice":"900.00","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","storeid":"10","title":"400克绿标","thumb":"http://my.1236sc.com/Public/Uploads/400lb.png","cart_id":"24","id":"52","option":null,"judge":"0","selected":"true"},{"total":"1","marketprice":"4.00","agent_marketprice":"1100.00","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","storeid":"10","title":"350克未加碘","thumb":"http://my.1236sc.com/Public/Uploads/350wjd.png","cart_id":"25","id":"50","option":null,"judge":"0","selected":"true"}]
     */

    public String business_name;
    public String uid;
    public String id;
    public String jud_wholesale;
    public List<GoodsBean> goods;
    /**
     *
     */
    public boolean isCheck;

    public static class GoodsBean {
        /**
         * total : 1
         * marketprice : 2.50
         * agent_marketprice : 900.00
         * weight : 1
         * unit : 袋
         * agent_weight : 1
         * agent_unit : 吨
         * storeid : 10
         * title : 400克绿标
         * thumb : http://my.1236sc.com/Public/Uploads/400lb.png
         * cart_id : 24
         * id : 52
         * option : null
         * judge : 0
         * selected : true
         */

        public String total;
        public String marketprice;
        public String agent_marketprice;
        public String weight;
        public String unit;
        public String agent_weight;
        public String agent_unit;
        public String storeid;
        public String title;
        public String thumb;
        public String cart_id;
        public String id;
        public Object option;
        public String judge;
        public String selected;

        /**
         *
         */
        public boolean isCheck;
    }
}
