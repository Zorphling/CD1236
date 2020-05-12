package com.business.cd1236.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ShoppingCarBean implements Parcelable {

    /**
     * business_name : 四川省天渠盐化有限公司
     * uid : 10
     * id : 23
     * jud_wholesale : 0
     * goods : [{"total":"1","marketprice":"2.50","agent_marketprice":"900.00","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","storeid":"10","title":"400克绿标","thumb":"http://my.1236sc.com/Public/Uploads/400lb.png","cart_id":"24","id":"52","option":null,"judge":"0","selected":"true"},{"total":"1","marketprice":"4.00","agent_marketprice":"1100.00","weight":"1","unit":"袋","agent_weight":"1","agent_unit":"吨","storeid":"10","title":"350克未加碘","thumb":"http://my.1236sc.com/Public/Uploads/350wjd.png","cart_id":"25","id":"50","option":null,"judge":"0","selected":"true"}]
     */

    public String business_name;
    public String uid;
    public String id;
    public String jud_wholesale;
    public List<GoodsDetailBean.GoodsBean> goods;

    /**
     * 单个商品是用的这个bean类，这个参数是记录单个商品有几个数量的
     */
    public String weight;
    /**
     *
     */
    public boolean isCheck;

    public ShoppingCarBean() {

    }

    protected ShoppingCarBean(Parcel in) {
        business_name = in.readString();
        uid = in.readString();
        id = in.readString();
        jud_wholesale = in.readString();
        goods = in.createTypedArrayList(GoodsDetailBean.GoodsBean.CREATOR);
        isCheck = in.readByte() != 0;
    }

    public static final Creator<ShoppingCarBean> CREATOR = new Creator<ShoppingCarBean>() {
        @Override
        public ShoppingCarBean createFromParcel(Parcel in) {
            return new ShoppingCarBean(in);
        }

        @Override
        public ShoppingCarBean[] newArray(int size) {
            return new ShoppingCarBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(business_name);
        dest.writeString(uid);
        dest.writeString(id);
        dest.writeString(jud_wholesale);
        dest.writeTypedList(goods);
        dest.writeByte((byte) (isCheck ? 1 : 0));
    }

    public static class GoodsBean implements Parcelable {
        /**
         * total : 1
         * marketprice : 2.50
         * agent_marketprice : 900.00
         * weight : 1
         * unit : 袋
         * agent_weight : 1
         * agent_unit : 吨
         * storeid : 10
         * title : 400克绿标
         * thumb : http://my.1236sc.com/Public/Uploads/400lb.png
         * cart_id : 24
         * id : 52
         * option : null
         * judge : 0
         * selected : true
         */

        public String total;
        public String marketprice;
        public String agent_marketprice;
        public String weight;
        public String unit;
        public String agent_weight;
        public String agent_unit;
        public String storeid;
        public String title;
        public String thumb;
        public String cart_id;
        public String id;
        public Object option;
        public String judge;
        public String selected;

        /**
         *
         */
        public boolean isCheck;

        public GoodsBean(){

        }
        protected GoodsBean(Parcel in) {
            total = in.readString();
            marketprice = in.readString();
            agent_marketprice = in.readString();
            weight = in.readString();
            unit = in.readString();
            agent_weight = in.readString();
            agent_unit = in.readString();
            storeid = in.readString();
            title = in.readString();
            thumb = in.readString();
            cart_id = in.readString();
            id = in.readString();
            judge = in.readString();
            selected = in.readString();
            isCheck = in.readByte() != 0;
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
            dest.writeString(total);
            dest.writeString(marketprice);
            dest.writeString(agent_marketprice);
            dest.writeString(weight);
            dest.writeString(unit);
            dest.writeString(agent_weight);
            dest.writeString(agent_unit);
            dest.writeString(storeid);
            dest.writeString(title);
            dest.writeString(thumb);
            dest.writeString(cart_id);
            dest.writeString(id);
            dest.writeString(judge);
            dest.writeString(selected);
            dest.writeByte((byte) (isCheck ? 1 : 0));
        }
    }
}
