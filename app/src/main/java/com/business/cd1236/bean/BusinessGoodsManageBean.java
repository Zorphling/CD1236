package com.business.cd1236.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class BusinessGoodsManageBean {

    public List<GoodsBean> goods;
    public List<CategoryBean> category;

    public static class GoodsBean implements Parcelable{

        /**
         * id : 58
         * ccate : 1
         * pcate : 3
         * category : 37
         * displayorder : 3
         * region_id : null
         * status : 1
         * title : 具体
         * weight : 1
         * unit : 袋
         * agent_weight : 99
         * agent_unit : 袋
         * format : 8
         * standard :
         * bar_code :
         * thumb : http://my.1236sc.com/Public/Uploads/2020-04/5ea3acf7089542.43099638.jpg
         * content : http://my.1236sc.com/Public/Uploads/2020-04/5ea3acf7098967.09726335.jpg
         * marketprice : 8.00
         * agent_marketprice : 7.00
         * productprice : 0.00
         * createtime : 1587784951
         * total : 9999
         * sales : 0
         * thumb_url : http://my.1236sc.com/Public/Uploads/2020-04/5ea3acf7098967.09726335.jpg
         * storeid : 39
         * brand : 天鹅牌
         * cate_id : 3
         * formatname : 25kg
         * formatid : 8
         */

        public String id;
        public String ccate;
        public String pcate;
        public String category;
        public String displayorder;
        public Object region_id;
        public String status;
        public String title;
        public String weight;
        public String unit;
        public String agent_weight;
        public String agent_unit;
        public String format;
        public String standard;
        public String bar_code;
        public String thumb;
        public String content;
        public String marketprice;
        public String agent_marketprice;
        public String productprice;
        public String createtime;
        public String total;
        public String sales;
        public String thumb_url;
        public String storeid;
        public String brand;
        public String cate_id;
        public String formatname;
        public String formatid;

        protected GoodsBean(Parcel in) {
            id = in.readString();
            ccate = in.readString();
            pcate = in.readString();
            category = in.readString();
            displayorder = in.readString();
            status = in.readString();
            title = in.readString();
            weight = in.readString();
            unit = in.readString();
            agent_weight = in.readString();
            agent_unit = in.readString();
            format = in.readString();
            standard = in.readString();
            bar_code = in.readString();
            thumb = in.readString();
            content = in.readString();
            marketprice = in.readString();
            agent_marketprice = in.readString();
            productprice = in.readString();
            createtime = in.readString();
            total = in.readString();
            sales = in.readString();
            thumb_url = in.readString();
            storeid = in.readString();
            brand = in.readString();
            cate_id = in.readString();
            formatname = in.readString();
            formatid = in.readString();
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
            dest.writeString(id);
            dest.writeString(ccate);
            dest.writeString(pcate);
            dest.writeString(category);
            dest.writeString(displayorder);
            dest.writeString(status);
            dest.writeString(title);
            dest.writeString(weight);
            dest.writeString(unit);
            dest.writeString(agent_weight);
            dest.writeString(agent_unit);
            dest.writeString(format);
            dest.writeString(standard);
            dest.writeString(bar_code);
            dest.writeString(thumb);
            dest.writeString(content);
            dest.writeString(marketprice);
            dest.writeString(agent_marketprice);
            dest.writeString(productprice);
            dest.writeString(createtime);
            dest.writeString(total);
            dest.writeString(sales);
            dest.writeString(thumb_url);
            dest.writeString(storeid);
            dest.writeString(brand);
            dest.writeString(cate_id);
            dest.writeString(formatname);
            dest.writeString(formatid);
        }
    }

    public static class CategoryBean implements Parcelable, IPickerViewData {
        /**
         * id : 34
         * name : 可能性
         */

        public String id;
        public String name;

        public boolean isChecked;

        protected CategoryBean(Parcel in) {
            id = in.readString();
            name = in.readString();
            isChecked = in.readByte() != 0;
        }

        public static final Creator<CategoryBean> CREATOR = new Creator<CategoryBean>() {
            @Override
            public CategoryBean createFromParcel(Parcel in) {
                return new CategoryBean(in);
            }

            @Override
            public CategoryBean[] newArray(int size) {
                return new CategoryBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeByte((byte) (isChecked ? 1 : 0));
        }

        @Override
        public String getPickerViewText() {
            return name;
        }
    }
}
