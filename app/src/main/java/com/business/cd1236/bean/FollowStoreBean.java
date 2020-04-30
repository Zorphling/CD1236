package com.business.cd1236.bean;

public class FollowStoreBean {

    /**
     * shop : {"id":"3","business_name":"粤盐","logo":"http://my.1236sc.com/Public/Uploads/yueyanlogo.png","province":"广东省","city":"广州市","district":"天河区","address":"广东省广州市天河区天河区员村北社石东街12号"}
     */

    public ShopBean shop;

    public static class ShopBean {
        /**
         * id : 3
         * business_name : 粤盐
         * logo : http://my.1236sc.com/Public/Uploads/yueyanlogo.png
         * province : 广东省
         * city : 广州市
         * district : 天河区
         * address : 广东省广州市天河区天河区员村北社石东街12号
         */

        public String id;
        public String business_name;
        public String logo;
        public String province;
        public String city;
        public String district;
        public String address;
    }
}
