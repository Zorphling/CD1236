package com.business.cd1236.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderBean {

    /**
     * address : {"realname":"距离","mobile":"18888888888","province":"北京市","city":"北京市","area":"崇文区","address":"北京市北京市崇文区推荐莫具体","id":"15","default":"1"}
     * goods : [{"thumb":"http://my.1236sc.com/Public/Uploads/64ca8ba1fc7137b32514ff221bdc163.jpg","total":"","marketprice":"35888.00","title":"粤盐无碘盐食用盐家用无碘盐260g","id":"3"}]
     * good_s : {"money":0,"moneys":0}
     * freight : 0
     * total_s : 0
     * since : 0
     */

    public AddAddressBean address;
    public OrderMoneyBean good_s;
    public String freight;
    public String total_s;
    public String since;
    public List<GoodsDetailBean.GoodsBean> goods;

    public static class AddressBean {
        /**
         * realname : 距离
         * mobile : 18888888888
         * province : 北京市
         * city : 北京市
         * area : 崇文区
         * address : 北京市北京市崇文区推荐莫具体
         * id : 15
         * default : 1
         */

        public String realname;
        public String mobile;
        public String province;
        public String city;
        public String area;
        public String address;
        public String id;
        @SerializedName("default")
        public String defaultX;
    }

    public static class OrderMoneyBean {
        /**
         * money : 0
         * moneys : 0
         */

        public String money;
        public String moneys;
    }

    public static class GoodsBean {
        /**
         * thumb : http://my.1236sc.com/Public/Uploads/64ca8ba1fc7137b32514ff221bdc163.jpg
         * total :
         * marketprice : 35888.00
         * title : 粤盐无碘盐食用盐家用无碘盐260g
         * id : 3
         */

        public String thumb;
        public String total;
        public String marketprice;
        public String title;
        public String id;
    }
}
