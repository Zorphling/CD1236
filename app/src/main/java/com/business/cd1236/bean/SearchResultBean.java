package com.business.cd1236.bean;

import java.util.List;

public class SearchResultBean {

    /**
     * search : [{"id":"29","title":"渤海湾 纯净海盐（加碘）350g","thumb":"http://my.1236sc.com/Public/Uploads/bhw.jpg","format":"1","marketprice":"0.00","agent_marketprice":"0.00","sales":"251","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨"},{"id":"34","title":"燕京 自然晶盐350g(加碘）","thumb":"http://my.1236sc.com/Public/Uploads/yanjzhiranjing.jpg","format":"6","marketprice":"0.00","agent_marketprice":"0.00","sales":"545","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨"},{"id":"35","title":"低钠盐320g","thumb":"http://my.1236sc.com/Public/Uploads/dly.jpg","format":"2","marketprice":"0.00","agent_marketprice":"0.00","sales":"265","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨"},{"id":"36","title":"海水日晒盐300g","thumb":"http://my.1236sc.com/Public/Uploads/hsrsxy.jpg","format":"2","marketprice":"0.00","agent_marketprice":"0.00","sales":"515","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨"},{"id":"42","title":"奉盐低钠盐320g","thumb":"http://my.1236sc.com/Public/Uploads/fydl.png","format":"6","marketprice":"3.00","agent_marketprice":"2880.00","sales":"158","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨"},{"id":"43","title":"奉盐海藻碘盐加碘320g","thumb":"http://my.1236sc.com/Public/Uploads/fyhz.png","format":"6","marketprice":"2.50","agent_marketprice":"1960.00","sales":"155","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨"},{"id":"48","title":"天然深井盐320克","thumb":"http://my.1236sc.com/Public/Uploads/320sj.png","format":"6","marketprice":"3.50","agent_marketprice":"1600.00","sales":"66","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨"},{"id":"49","title":"天然深井盐350克","thumb":"http://my.1236sc.com/Public/Uploads/350sj.png","format":"1","marketprice":"2.50","agent_marketprice":"1100.00","sales":"215","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨"},{"id":"50","title":"350克未加碘","thumb":"http://my.1236sc.com/Public/Uploads/350wjd.png","format":"1","marketprice":"4.00","agent_marketprice":"1100.00","sales":"265","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨"},{"id":"51","title":"320克天然精纯","thumb":"http://my.1236sc.com/Public/Uploads/320jc.png","format":"6","marketprice":"4.00","agent_marketprice":"1600.00","sales":"354","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨"}]
     * jud : 0
     */

    public String jud;
    public List<SearchBean> search;

    public static class SearchBean {
        /**
         * id : 29
         * title : 渤海湾 纯净海盐（加碘）350g
         * thumb : http://my.1236sc.com/Public/Uploads/bhw.jpg
         * format : 1
         * marketprice : 0.00
         * agent_marketprice : 0.00
         * sales : 251
         * weight : 1
         * unit : 袋
         * agent_weight : 1
         * agent_unit : 吨
         */

        public String id;
        public String title;
        public String thumb;
        public String format;
        public String marketprice;
        public String agent_marketprice;
        public String sales;
        public String weight;
        public String unit;
        public String agent_weight;
        public String agent_unit;
    }
}
