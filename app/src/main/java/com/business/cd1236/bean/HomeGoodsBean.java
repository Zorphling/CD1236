package com.business.cd1236.bean;

import java.util.List;

public class HomeGoodsBean {

    /**
     * data : [{"id":"52","title":"400克绿标","thumb":"http://my.1236sc.com/Public/Uploads/400lb.png","marketprice":"2.50","sales":"194","weight":"1","unit":"袋"},{"id":"51","title":"320克天然精纯","thumb":"http://my.1236sc.com/Public/Uploads/320jc.png","marketprice":"4.00","sales":"354","weight":"1","unit":"袋"},{"id":"50","title":"350克未加碘","thumb":"http://my.1236sc.com/Public/Uploads/350wjd.png","marketprice":"4.00","sales":"265","weight":"1","unit":"袋"},{"id":"49","title":"天然深井盐350克","thumb":"http://my.1236sc.com/Public/Uploads/350sj.png","marketprice":"2.50","sales":"215","weight":"1","unit":"袋"},{"id":"48","title":"天然深井盐320克","thumb":"http://my.1236sc.com/Public/Uploads/320sj.png","marketprice":"3.50","sales":"66","weight":"1","unit":"袋"},{"id":"47","title":"奉盐弱碱性食用盐400g","thumb":"http://my.1236sc.com/Public/Uploads/ftrjsy.png","marketprice":"3.00","sales":"148","weight":"1","unit":"袋"},{"id":"46","title":"奉盐精制盐加碘400g","thumb":"http://my.1236sc.com/Public/Uploads/fyjzjd.png","marketprice":"2.00","sales":"125","weight":"1","unit":"袋"},{"id":"45","title":"奉盐精制食用盐未加碘400g","thumb":"http://my.1236sc.com/Public/Uploads/fyjysy.png","marketprice":"2.50","sales":"165","weight":"1","unit":"袋"},{"id":"44","title":"奉盐精制海盐加碘400g","thumb":"http://my.1236sc.com/Public/Uploads/fyhzjd.png","marketprice":"2.00","sales":"1215","weight":"1","unit":"袋"},{"id":"43","title":"奉盐海藻碘盐加碘320g","thumb":"http://my.1236sc.com/Public/Uploads/fyhz.png","marketprice":"2.50","sales":"155","weight":"1","unit":"袋"}]
     * jud : 0
     */

    public String jud;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 52
         * title : 400克绿标
         * thumb : http://my.1236sc.com/Public/Uploads/400lb.png
         * marketprice : 2.50
         * sales : 194
         * weight : 1
         * unit : 袋
         */

        public String id;
        public String title;
        public String thumb;
        public String marketprice;
        public String agent_marketprice;
        public String sales;
        public String weight;
        public String unit;
    }
}
