package com.business.cd1236.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class BusinessGoodsShowBean {

    /**
     * unit : ["袋","顿","箱","瓶"]
     * category : [{"name":"工业盐","id":"1","son":[{"name":"天鹅牌","id":"3","parentid":"1"},{"name":"瑞江牌","id":"4","parentid":"1"},{"name":"百仙牌","id":"5","parentid":"1"},{"name":"长舟牌","id":"6","parentid":"1"},{"name":"丰源牌","id":"7","parentid":"1"},{"name":"九二牌","id":"8","parentid":"1"},{"name":"桂花牌","id":"9","parentid":"1"}]},{"name":"食用盐","id":"2","son":[{"name":"雪天牌","id":"10","parentid":"2"},{"name":"天鹅牌","id":"11","parentid":"2"},{"name":"雪盐","id":"12","parentid":"2"},{"name":"井岗牌","id":"13","parentid":"2"},{"name":"淮盐","id":"14","parentid":"2"},{"name":"中盐","id":"15","parentid":"2"},{"name":"粤盐","id":"16","parentid":"2"},{"name":"川晶","id":"17","parentid":"2"},{"name":"唐丰盐","id":"18","parentid":"2"},{"name":"长芦盐","id":"19","parentid":"2"},{"name":"鲁晶牌","id":"20","parentid":"2"},{"name":"奉盐","id":"21","parentid":"2"},{"name":"天渠","id":"22","parentid":"2"}]}]
     * category_ss : []
     * format : [{"name":"食用盐","id":"2","format":[{"id":"1","parentid":"2","name":"350g"},{"id":"2","parentid":"2","name":"320g"},{"id":"3","parentid":"2","name":"260g"},{"id":"4","parentid":"2","name":"400g"},{"id":"5","parentid":"2","name":"200g"},{"id":"6","parentid":"2","name":"500g"},{"id":"7","parentid":"2","name":"250g"}]},{"name":"工业盐","id":"1","format":[{"id":"8","parentid":"1","name":"25kg"},{"id":"9","parentid":"1","name":"50kg"},{"id":"10","parentid":"1","name":"1000kg"}]}]
     * good : 0
     */

    public String good;
    public List<String> unit;
    public List<CategoryBean> category;
    public List<CategorySsBean> category_ss;
    public List<FormatBeanX> format;

    public static class CategorySsBean{
        public String name;
        public String id;
    }

    public static class CategoryBean implements IPickerViewData {
        /**
         * name : 工业盐
         * id : 1
         * son : [{"name":"天鹅牌","id":"3","parentid":"1"},{"name":"瑞江牌","id":"4","parentid":"1"},{"name":"百仙牌","id":"5","parentid":"1"},{"name":"长舟牌","id":"6","parentid":"1"},{"name":"丰源牌","id":"7","parentid":"1"},{"name":"九二牌","id":"8","parentid":"1"},{"name":"桂花牌","id":"9","parentid":"1"}]
         */

        public String name;
        public String id;
        public List<SonBean> son;

        public static class SonBean implements IPickerViewData {
            /**
             * name : 天鹅牌
             * id : 3
             * parentid : 1
             */

            public String name;
            public String id;
            public String parentid;

            @Override
            public String getPickerViewText() {
                return name;
            }
        }

        @Override
        public String getPickerViewText() {
            return name;
        }
    }

    public static class FormatBeanX {
        /**
         * name : 食用盐
         * id : 2
         * format : [{"id":"1","parentid":"2","name":"350g"},{"id":"2","parentid":"2","name":"320g"},{"id":"3","parentid":"2","name":"260g"},{"id":"4","parentid":"2","name":"400g"},{"id":"5","parentid":"2","name":"200g"},{"id":"6","parentid":"2","name":"500g"},{"id":"7","parentid":"2","name":"250g"}]
         */

        public String name;
        public String id;
        public List<FormatBean> format;

        public static class FormatBean implements IPickerViewData {
            /**
             * id : 1
             * parentid : 2
             * name : 350g
             */

            public String id;
            public String parentid;
            public String name;

            @Override
            public String getPickerViewText() {
                return name;
            }
        }
    }
}
