package com.business.cd1236.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectGoodsBean {

    /**
     * new : [{"id":"52","title":"400克绿标","thumb":"http://my.1236sc.com/Public/Uploads/400lb.png","marketprice":"2.50","weight":"1","unit":"袋","agent_marketprice":"900.00","agent_weight":"1","agent_unit":"吨","productprice":"1.90"},{"id":"51","title":"320克天然精纯","thumb":"http://my.1236sc.com/Public/Uploads/320jc.png","marketprice":"4.00","weight":"1","unit":"袋","agent_marketprice":"1600.00","agent_weight":"1","agent_unit":"吨","productprice":"3.20"}]
     * jud : 1
     */

    public String jud;
    @SerializedName("new")
    public List<NewBean> newX;

    public static class NewBean {
        /**
         * id : 52
         * title : 400克绿标
         * thumb : http://my.1236sc.com/Public/Uploads/400lb.png
         * marketprice : 2.50
         * weight : 1
         * unit : 袋
         * agent_marketprice : 900.00
         * agent_weight : 1
         * agent_unit : 吨
         * productprice : 1.90
         */

        public String id;
        public String title;
        public String thumb;
        public String marketprice;
        public String weight;
        public String unit;
        public String agent_marketprice;
        public String agent_weight;
        public String agent_unit;
        public String productprice;

        public boolean isCheck;
        public boolean showCheckBox;
    }
}
