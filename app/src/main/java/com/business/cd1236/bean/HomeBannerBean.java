package com.business.cd1236.bean;

import java.util.List;

public class HomeBannerBean {

    /**
     * banner : [{"name":"中盐","img":"http://my.1236sc.com/Public/Uploads/zhongyan.png","link":null},{"name":"粤盐","img":"http://my.1236sc.com/Public/Uploads/yueyan.png","link":null},{"name":"雪天","img":"http://my.1236sc.com/Public/Uploads/xuetian.png","link":null},{"name":"川盐","img":"http://my.1236sc.com/Public/Uploads/chuanyan.png","link":null},{"name":"淮盐","img":"http://my.1236sc.com/Public/Uploads/huaiyan.png","link":null}]
     * category : [{"name":"工业盐","thumb":"http://my.1236sc.com/Public/Uploads/gyyan.png","id":"1","content":"各类工业盐、元明粉、饲料盐"},{"name":"食用盐","thumb":"http://my.1236sc.com/Public/Uploads/syyan.png","id":"2","content":"各类加碘、低钠、进口食盐"}]
     * eating : http://my.1236sc.com/Public/Uploads/eating.png
     * Industry : http://my.1236sc.com/Public/Uploads/Industry.png
     * transport : http://my.1236sc.com/Public/Uploads/transport.png
     * transport_content : 招募物流合作商，欢迎各大物流公司合作
     */

    public String eating;
    public String Industry;
    public String transport;
    public String transport_content;
    public List<BannerBean> banner;
    public List<CategoryBean> category;

    public static class BannerBean {
        /**
         * name : 中盐
         * img : http://my.1236sc.com/Public/Uploads/zhongyan.png
         * link : null
         */

        public String name;
        public String img;
        public Object link;
    }

    public static class CategoryBean {
        /**
         * name : 工业盐
         * thumb : http://my.1236sc.com/Public/Uploads/gyyan.png
         * id : 1
         * content : 各类工业盐、元明粉、饲料盐
         */

        public String name;
        public String thumb;
        public String id;
        public String content;
    }
}
