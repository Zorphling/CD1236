package com.business.cd1236.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GoodsDetailBean implements Parcelable {

    /**
     * option : []
     * goods : {"title":"400克绿标","storeid":"10","marketprice":"2.50","agent_marketprice":"900.00","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","sales":"194","id":"52","thumb":"http://my.1236sc.com/Public/Uploads/400lb.png","thumb_url":null,"content":null,"province":"四川省","city":"达州市","shop_id":"23","thumb_s":["http://my.1236sc.com/Public/Uploads/400lb.png"]}
     * shop : {"logo":"http://my.1236sc.com/Public/Uploads/tq.png","business_name":"四川省天渠盐化有限公司","id":"23","new_number":0,"follow":0,"number":5}
     * good_ss : [{"title":"400克绿标","id":"52","thumb":"http://my.1236sc.com/Public/Uploads/400lb.png","marketprice":"2.50","agent_marketprice":"900.00","sales":"194","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","createtime":"1576477128"},{"title":"320克天然精纯","id":"51","thumb":"http://my.1236sc.com/Public/Uploads/320jc.png","marketprice":"4.00","agent_marketprice":"1600.00","sales":"354","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","createtime":"1576477128"},{"title":"350克未加碘","id":"50","thumb":"http://my.1236sc.com/Public/Uploads/350wjd.png","marketprice":"4.00","agent_marketprice":"1100.00","sales":"265","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","createtime":"1576477128"},{"title":"天然深井盐350克","id":"49","thumb":"http://my.1236sc.com/Public/Uploads/350sj.png","marketprice":"2.50","agent_marketprice":"1100.00","sales":"215","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","createtime":"1576477128"},{"title":"天然深井盐320克","id":"48","thumb":"http://my.1236sc.com/Public/Uploads/320sj.png","marketprice":"3.50","agent_marketprice":"1600.00","sales":"66","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","createtime":"1576477128"}]
     * comment : []
     * number : 0
     * jud : 0
     * jud_wholesale : 0
     */

    public GoodsBean goods;
    public ShopBean shop;
    public int number;
    public String jud;//0个体 1商家
    public String collect_jud;//0未收藏  1收藏
    public String jud_wholesale;//是否拥有批发权限  0没有  1 有
    public List<?> option;
    public List<GoodSsBean> good_ss;
    public List<?> comment;

    protected GoodsDetailBean(Parcel in) {
        goods = in.readParcelable(GoodsBean.class.getClassLoader());
        shop = in.readParcelable(ShopBean.class.getClassLoader());
        number = in.readInt();
        jud = in.readString();
        collect_jud = in.readString();
        jud_wholesale = in.readString();
    }

    public static final Creator<GoodsDetailBean> CREATOR = new Creator<GoodsDetailBean>() {
        @Override
        public GoodsDetailBean createFromParcel(Parcel in) {
            return new GoodsDetailBean(in);
        }

        @Override
        public GoodsDetailBean[] newArray(int size) {
            return new GoodsDetailBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(goods, flags);
        dest.writeParcelable(shop, flags);
        dest.writeInt(number);
        dest.writeString(jud);
        dest.writeString(collect_jud);
        dest.writeString(jud_wholesale);
    }

    public static class GoodsBean implements Parcelable{
        /**
         * title : 400克绿标
         * storeid : 10
         * marketprice : 2.50
         * agent_marketprice : 900.00
         * weight : 1
         * unit : 袋
         * agent_weight : 1
         * agent_unit : 吨
         * sales : 194
         * id : 52
         * thumb : http://my.1236sc.com/Public/Uploads/400lb.png
         * thumb_url : null
         * content : null
         * province : 四川省
         * city : 达州市
         * shop_id : 23
         * thumb_s : ["http://my.1236sc.com/Public/Uploads/400lb.png"]
         */

        public String title;
        public String storeid;
        public String marketprice;
        public String agent_marketprice;
        public String weight;
        public String unit;
        public String agent_weight;
        public String agent_unit;
        public String sales;
        public String agent_total;//库存
        public String id;
        public String thumb;
        public Object thumb_url;
        public Object content;
        public String province;
        public String city;
        public String shop_id;
        public List<String> thumb_s;


        /**
         * 购物车也使用这个bean类 下面是购物车多出来的
         */
        public String total;
        public String cart_id;
        public Object option;
        public String judge;
        public String selected;

        /**
         *
         * @param
         */
        public boolean isCheck;

        protected GoodsBean(Parcel in) {
            title = in.readString();
            storeid = in.readString();
            marketprice = in.readString();
            agent_marketprice = in.readString();
            weight = in.readString();
            unit = in.readString();
            agent_weight = in.readString();
            agent_unit = in.readString();
            sales = in.readString();
            agent_total = in.readString();
            id = in.readString();
            thumb = in.readString();
            province = in.readString();
            city = in.readString();
            shop_id = in.readString();
            thumb_s = in.createStringArrayList();
            total = in.readString();
            cart_id = in.readString();
            judge = in.readString();
            selected = in.readString();
        }

        public static final Creator<GoodsBean> CREATOR = new Creator<GoodsBean>() {
            @Override
            public GoodsBean createFromParcel(Parcel in) {
                return new GoodsBean(in);
            }

            @Override
            public GoodsBean[] newArray(int size) {
                return new GoodsBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(storeid);
            dest.writeString(marketprice);
            dest.writeString(agent_marketprice);
            dest.writeString(weight);
            dest.writeString(unit);
            dest.writeString(agent_weight);
            dest.writeString(agent_unit);
            dest.writeString(sales);
            dest.writeString(agent_total);
            dest.writeString(id);
            dest.writeString(thumb);
            dest.writeString(province);
            dest.writeString(city);
            dest.writeString(shop_id);
            dest.writeStringList(thumb_s);
            dest.writeString(total);
            dest.writeString(cart_id);
            dest.writeString(judge);
            dest.writeString(selected);
        }
    }

    public static class ShopBean implements Parcelable{
        /**
         * logo : http://my.1236sc.com/Public/Uploads/tq.png
         * business_name : 四川省天渠盐化有限公司
         * id : 23
         * new_number : 0
         * follow : 0
         * number : 5
         */

        public String logo;
        public String business_name;
        public String id;
        public int new_number;
        public int follow;
        public int number;

        protected ShopBean(Parcel in) {
            logo = in.readString();
            business_name = in.readString();
            id = in.readString();
            new_number = in.readInt();
            follow = in.readInt();
            number = in.readInt();
        }

        public static final Creator<ShopBean> CREATOR = new Creator<ShopBean>() {
            @Override
            public ShopBean createFromParcel(Parcel in) {
                return new ShopBean(in);
            }

            @Override
            public ShopBean[] newArray(int size) {
                return new ShopBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(logo);
            dest.writeString(business_name);
            dest.writeString(id);
            dest.writeInt(new_number);
            dest.writeInt(follow);
            dest.writeInt(number);
        }
    }

    public static class GoodSsBean {
        /**
         * title : 400克绿标
         * id : 52
         * thumb : http://my.1236sc.com/Public/Uploads/400lb.png
         * marketprice : 2.50
         * agent_marketprice : 900.00
         * sales : 194
         * weight : 1
         * unit : 袋
         * agent_weight : 1
         * agent_unit : 吨
         * createtime : 1576477128
         */

        public String title;
        public String id;
        public String thumb;
        public String marketprice;
        public String agent_marketprice;
        public String sales;
        public String weight;
        public String unit;
        public String agent_weight;
        public String agent_unit;
        public String createtime;
    }
}
