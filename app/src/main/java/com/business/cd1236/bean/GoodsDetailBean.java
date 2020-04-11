package com.business.cd1236.bean;

import java.util.List;

public class GoodsDetailBean {

    /**
     * option : []
     * goods : {"title":"400克绿标","storeid":"10","marketprice":"2.50","agent_marketprice":"900.00","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","sales":"194","id":"52","thumb":"http://my.1236sc.com/Public/Uploads/400lb.png","thumb_url":null,"content":null,"province":"四川省","city":"达州市","shop_id":"23","thumb_s":["http://my.1236sc.com/Public/Uploads/400lb.png"]}
     * shop : {"logo":"http://my.1236sc.com/Public/Uploads/tq.png","business_name":"四川省天渠盐化有限公司","id":"23","new_number":0,"follow":0,"number":5}
     * good_ss : [{"title":"400克绿标","id":"52","thumb":"http://my.1236sc.com/Public/Uploads/400lb.png","marketprice":"2.50","agent_marketprice":"900.00","sales":"194","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","createtime":"1576477128"},{"title":"320克天然精纯","id":"51","thumb":"http://my.1236sc.com/Public/Uploads/320jc.png","marketprice":"4.00","agent_marketprice":"1600.00","sales":"354","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","createtime":"1576477128"},{"title":"350克未加碘","id":"50","thumb":"http://my.1236sc.com/Public/Uploads/350wjd.png","marketprice":"4.00","agent_marketprice":"1100.00","sales":"265","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","createtime":"1576477128"},{"title":"天然深井盐350克","id":"49","thumb":"http://my.1236sc.com/Public/Uploads/350sj.png","marketprice":"2.50","agent_marketprice":"1100.00","sales":"215","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","createtime":"1576477128"},{"title":"天然深井盐320克","id":"48","thumb":"http://my.1236sc.com/Public/Uploads/320sj.png","marketprice":"3.50","agent_marketprice":"1600.00","sales":"66","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","createtime":"1576477128"}]
     * comment : []
     * number : 0
     * jud : 0
     * jud_wholesale : 0
     */

    public GoodsBean goods;
    public ShopBean shop;
    public int number;
    public String jud;
    public String collect_jud;//0未收藏  1收藏
    public String jud_wholesale;//是否拥有批发权限  0没有  1 有
    public List<?> option;
    public List<GoodSsBean> good_ss;
    public List<?> comment;

    public static class GoodsBean {
        /**
         * title : 400克绿标
         * storeid : 10
         * marketprice : 2.50
         * agent_marketprice : 900.00
         * weight : 1
         * unit : 袋
         * agent_weight : 1
         * agent_unit : 吨
         * sales : 194
         * id : 52
         * thumb : http://my.1236sc.com/Public/Uploads/400lb.png
         * thumb_url : null
         * content : null
         * province : 四川省
         * city : 达州市
         * shop_id : 23
         * thumb_s : ["http://my.1236sc.com/Public/Uploads/400lb.png"]
         */

        public String title;
        public String storeid;
        public String marketprice;
        public String agent_marketprice;
        public String weight;
        public String unit;
        public String agent_weight;
        public String agent_unit;
        public String sales;
        public String id;
        public String thumb;
        public Object thumb_url;
        public Object content;
        public String province;
        public String city;
        public String shop_id;
        public List<String> thumb_s;
    }

    public static class ShopBean {
        /**
         * logo : http://my.1236sc.com/Public/Uploads/tq.png
         * business_name : 四川省天渠盐化有限公司
         * id : 23
         * new_number : 0
         * follow : 0
         * number : 5
         */

        public String logo;
        public String business_name;
        public String id;
        public int new_number;
        public int follow;
        public int number;
    }

    public static class GoodSsBean {
        /**
         * title : 400克绿标
         * id : 52
         * thumb : http://my.1236sc.com/Public/Uploads/400lb.png
         * marketprice : 2.50
         * agent_marketprice : 900.00
         * sales : 194
         * weight : 1
         * unit : 袋
         * agent_weight : 1
         * agent_unit : 吨
         * createtime : 1576477128
         */

        public String title;
        public String id;
        public String thumb;
        public String marketprice;
        public String agent_marketprice;
        public String sales;
        public String weight;
        public String unit;
        public String agent_weight;
        public String agent_unit;
        public String createtime;
    }
}
