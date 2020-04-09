package com.business.cd1236.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class EnterTypeBean {


    public List<NameBean> name;
    public List<ChooseBean> choose;
    public MemberBean member;

    public static class ChooseBean implements IPickerViewData {
        /**
         * payfee : 普通入驻1年(1236元/365天)
         * id : 1
         * type : 2
         */

        public String payfee;
        public String id;
        public String type;

        @Override
        public String getPickerViewText() {
            return payfee;
        }
    }

    public static class NameBean implements IPickerViewData {
        /**
         * name : 企业入驻
         * id : 1
         */

        public String name;
        public String id;

        @Override
        public String getPickerViewText() {
            return name;
        }
    }

    public static class MemberBean {
        /**
         * id : 27
         * business_name : 阿里巴巴
         * real_name : 王建立
         * telephone : 13055556666
         * type : 1
         * province : 北京市
         * city : 北京市
         * district : 东城区
         * address : 罢了
         * pid : 0
         * logo : null
         * license : null
         * sign_img : null
         * introduction : null
         * culture : null
         * status : 0
         */

        public String id;
        public String business_name;
        public String real_name;
        public String telephone;
        public String type;
        public String province;
        public String city;
        public String district;
        public String address;
        public String pid;
        public Object logo;
        public Object license;
        public Object sign_img;
        public Object introduction;
        public Object culture;
        public String status;
    }
}
