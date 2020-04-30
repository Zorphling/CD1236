package com.business.cd1236.bean;

import java.util.List;

public class StoreDetailBean {

    /**
     * shop : {"business_name":"中盐","id":"1","introduction":"中国盐业集团有限公司（简称中盐集团），创立于1950年，原名中国盐业公司，现为国务院国资委监管的国有大型中央企业。 [1]  作为中央企业主要承担两大任务：一是做强做大，实现国有资产保值增值；二是承担全国食盐专营的生产经营任务，确保全国合格碘盐供应。2011年受日本地震影响，中国出现抢购食盐现象，中国盐业总公司积极回应，并确保食盐市场稳定供应。2016年8月，中国盐业总公司在\"2016中国企业500强\"中排名第236位。","culture":"公司致力于丰富和健康人们的日常生活，只有以\u201c盐\u201d融化自我的品质，通过不断的奉献和长期的艰苦奋斗，辛勤耕耘、超越自己来实现。让公司融入市场... ","sign_img":"http://my.1236sc.com/Public/Uploads/zhongyan1.png","uid":"1","jud":"0"}
     * hot : [{"title":"福晶盐（加碘）250g","marketprice":"3.00","agent_marketprice":"12000.00","sales":"949","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/fujing.jpg","id":"18"},{"title":"精选自然盐（加碘）350g","marketprice":"2.00","agent_marketprice":"5714.30","sales":"876","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/zhiran.png","id":"20"},{"title":"家庭腌制盐（加碘）400g","marketprice":"2.00","agent_marketprice":"5000.00","sales":"863","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/yanzhi.jpg","id":"22"}]
     * good_ss : [{"category":null,"title":"中盐精致加碘食用盐350g","marketprice":"4.95","agent_marketprice":"12888.00","sales":"159","weight":"4","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/d58d8babb46844f69bf3ea456e0b544.jpg","id":"1"},{"category":null,"title":"中盐未加碘纯精岩盐400g","marketprice":"2.80","agent_marketprice":"6666.00","sales":"325","weight":"6","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/zy1.png","id":"6"},{"category":null,"title":"中盐天然盐湖未加碘细盐200g","marketprice":"5.00","agent_marketprice":"23888.00","sales":"542","weight":"4","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/zy2.png","id":"7"},{"category":null,"title":"福晶盐（加碘）250g","marketprice":"3.00","agent_marketprice":"12000.00","sales":"949","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/fujing.jpg","id":"18"},{"category":null,"title":"贡晶盐（加碘）250g","marketprice":"3.50","agent_marketprice":"14000.00","sales":"546","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/gongjin.jpg","id":"19"},{"category":null,"title":"精选自然盐（加碘）350g","marketprice":"2.00","agent_marketprice":"5714.30","sales":"876","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/zhiran.png","id":"20"},{"category":null,"title":"日晒海盐（加碘）400g","marketprice":"2.00","agent_marketprice":"5000.00","sales":"378","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/rejing.png","id":"21"},{"category":null,"title":"家庭腌制盐（加碘）400g","marketprice":"2.00","agent_marketprice":"5000.00","sales":"863","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/yanzhi.jpg","id":"22"}]
     * category_s : [{"goods":[{"category":null,"title":"中盐精致加碘食用盐350g","marketprice":"4.95","agent_marketprice":"12888.00","sales":"159","weight":"4","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236s/my.1236sc.com/Public/Uploads/fujing.jpg","id":"18"},{"category":null,"title":"贡晶盐（加碘）250g","marketprice":"3.50","agent_marketprice":"14000.00","sales":"546","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/gongjin.jpg","id":"19"},{"category":null,"title":"精选自然盐（加碘）350g","marketprice":"2.00","agent_marketprice":"5714.30","sales":"876","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/zhiran.png","id":"20"},{"category":null,"title":"日晒海盐（加碘）400g","marketprice":"2.00","agent_marketprice":"5000.00","sales":"378","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/rejing.png","id":"21"},{"category":null,"title":"家庭腌制盐（加碘）400g","marketprice":"2.00","agent_marketprice":"5000.00","sales":"863","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","thumb":"http://my.1236sc.com/Public/Uploads/yanzhi.jpg","id":"22"}]}]
     * jud : 1
     */

    public ShopBean shop;
    public String jud;
    public List<HotBean> hot;
    public List<GoodSsBean> good_ss;
    public List<CategorySBean> category_s;

    public static class ShopBean {
        /**
         * business_name : 中盐
         * id : 1
         * introduction : 中国盐业集团有限公司（简称中盐集团），创立于1950年，原名中国盐业公司，现为国务院国资委监管的国有大型中央企业。 [1]  作为中央企业主要承担两大任务：一是做强做大，实现国有资产保值增值；二是承担全国食盐专营的生产经营任务，确保全国合格碘盐供应。2011年受日本地震影响，中国出现抢购食盐现象，中国盐业总公司积极回应，并确保食盐市场稳定供应。2016年8月，中国盐业总公司在"2016中国企业500强"中排名第236位。
         * culture : 公司致力于丰富和健康人们的日常生活，只有以“盐”融化自我的品质，通过不断的奉献和长期的艰苦奋斗，辛勤耕耘、超越自己来实现。让公司融入市场...
         * sign_img : http://my.1236sc.com/Public/Uploads/zhongyan1.png
         * uid : 1
         * jud : 0
         */

        public String business_name;
        public String id;
        public String introduction;
        public String culture;
        public String sign_img;
        public String logo;
        public String uid;
        public String jud;
    }

    public static class HotBean {
        /**
         * title : 福晶盐（加碘）250g
         * marketprice : 3.00
         * agent_marketprice : 12000.00
         * sales : 949
         * weight : 1
         * unit : 袋
         * agent_weight : 1
         * agent_unit : 吨
         * thumb : http://my.1236sc.com/Public/Uploads/fujing.jpg
         * id : 18
         */

        public String title;
        public String marketprice;
        public String agent_marketprice;
        public String sales;
        public String weight;
        public String unit;
        public String agent_weight;
        public String agent_unit;
        public String thumb;
        public String id;
    }

    public static class GoodSsBean {
        /**
         * category : null
         * title : 中盐精致加碘食用盐350g
         * marketprice : 4.95
         * agent_marketprice : 12888.00
         * sales : 159
         * weight : 4
         * unit : 袋
         * agent_weight : 1
         * agent_unit : 吨
         * thumb : http://my.1236sc.com/Public/Uploads/d58d8babb46844f69bf3ea456e0b544.jpg
         * id : 1
         */

        public Object category;
        public String title;
        public String marketprice;
        public String agent_marketprice;
        public String sales;
        public String weight;
        public String unit;
        public String agent_weight;
        public String agent_unit;
        public String thumb;
        public String id;
    }

    public static class CategorySBean {
        public List<GoodsBean> goods;

        public static class GoodsBean {
            /**
             * category : null
             * title : 中盐精致加碘食用盐350g
             * marketprice : 4.95
             * agent_marketprice : 12888.00
             * sales : 159
             * weight : 4
             * unit : 袋
             * agent_weight : 1
             * agent_unit : 吨
             * thumb : http://my.1236s/my.1236sc.com/Public/Uploads/fujing.jpg
             * id : 18
             */

            public Object category;
            public String title;
            public String marketprice;
            public String agent_marketprice;
            public String sales;
            public String weight;
            public String unit;
            public String agent_weight;
            public String agent_unit;
            public String thumb;
            public String id;
        }
    }
}
