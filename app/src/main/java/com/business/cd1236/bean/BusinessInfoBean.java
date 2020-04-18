package com.business.cd1236.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BusinessInfoBean implements Parcelable {

    /**
     * id : 24
     * logo : null
     * business_name : 不了
     * province : 四川省
     * city : 成都市
     * district : 新都区
     * address : 四川省成都市新都区2222
     * telephone : ["13086664427"]
     * shop_thumb : [""]
     * open_time : 00:00
     * close_time : 12:00
     * open_time1 : 12:00
     * close_time1 : 24:00
     */

    public String id;
    public String logo;
    public String business_name;
    public String province;
    public String city;
    public String district;
    public String address;
    public String open_time;
    public String close_time;
    public String open_time1;
    public String close_time1;
    public List<String> telephone;
    public List<String> shop_thumb;

    protected BusinessInfoBean(Parcel in) {
        id = in.readString();
        logo = in.readString();
        business_name = in.readString();
        province = in.readString();
        city = in.readString();
        district = in.readString();
        address = in.readString();
        open_time = in.readString();
        close_time = in.readString();
        open_time1 = in.readString();
        close_time1 = in.readString();
        telephone = in.createStringArrayList();
        shop_thumb = in.createStringArrayList();
    }

    public static final Creator<BusinessInfoBean> CREATOR = new Creator<BusinessInfoBean>() {
        @Override
        public BusinessInfoBean createFromParcel(Parcel in) {
            return new BusinessInfoBean(in);
        }

        @Override
        public BusinessInfoBean[] newArray(int size) {
            return new BusinessInfoBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(logo);
        dest.writeString(business_name);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(district);
        dest.writeString(address);
        dest.writeString(open_time);
        dest.writeString(close_time);
        dest.writeString(open_time1);
        dest.writeString(close_time1);
        dest.writeStringList(telephone);
        dest.writeStringList(shop_thumb);
    }
}
