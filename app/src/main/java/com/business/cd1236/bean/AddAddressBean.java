package com.business.cd1236.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AddAddressBean implements Parcelable {

    /**
     * realname : 看看
     * mobile : 13086664427
     * province : 山西省
     * city : 忻州市
     * area : 定襄县
     * address : 罢了
     * id : 6
     * default : 0
     */

    public String realname;
    public String mobile;
    public String province;
    public String city;
    public String area;
    public String address;
    public String id;
    @SerializedName("default")
    public String defaultX;

    protected AddAddressBean(Parcel in) {
        realname = in.readString();
        mobile = in.readString();
        province = in.readString();
        city = in.readString();
        area = in.readString();
        address = in.readString();
        id = in.readString();
        defaultX = in.readString();
    }

    public static final Creator<AddAddressBean> CREATOR = new Creator<AddAddressBean>() {
        @Override
        public AddAddressBean createFromParcel(Parcel in) {
            return new AddAddressBean(in);
        }

        @Override
        public AddAddressBean[] newArray(int size) {
            return new AddAddressBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(realname);
        dest.writeString(mobile);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(area);
        dest.writeString(address);
        dest.writeString(id);
        dest.writeString(defaultX);
    }
}
