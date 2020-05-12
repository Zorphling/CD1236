package com.business.cd1236.bean;

import java.util.List;

public class OrderBean {

    /**
     * address : {"address":""}
     * goods : [{"thumb":"http://my.1236sc.com/Public/Uploads/d58d8babb46844f69bf3ea456e0b544.jpg","total":"","marketprice":"12888.00","title":"中盐精致加碘食用盐350g","id":"1"}]
     * good_s : {"money":0,"moneys":0}
     * freight : 0
     * total_s : 0
     * since : 1
     */

    public AddressBean address;
    public GoodSBean good_s;
    public String freight;
    public String total_s;
    public String since;
    public List<GoodsBean> goods;

    public static class AddressBean {
        /**
         * address :
         */

        public String address;
    }

    public static class GoodSBean {
        /**
         * money : 0
         * moneys : 0
         */

        public int money;
        public int moneys;
    }

    public static class GoodsBean {
        /**
         * thumb : http://my.1236sc.com/Public/Uploads/d58d8babb46844f69bf3ea456e0b544.jpg
         * total :
         * marketprice : 12888.00
         * title : 中盐精致加碘食用盐350g
         * id : 1
         */

        public String thumb;
        public String total;
        public String marketprice;
        public String title;
        public String id;
    }
}
