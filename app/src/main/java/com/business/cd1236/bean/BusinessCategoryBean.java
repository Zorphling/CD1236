package com.business.cd1236.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class BusinessCategoryBean implements Parcelable {
    public String id;
    public String name;
    public String displayorder;

    protected BusinessCategoryBean(Parcel in) {
        id = in.readString();
        name = in.readString();
        displayorder = in.readString();
    }

    public static final Creator<BusinessCategoryBean> CREATOR = new Creator<BusinessCategoryBean>() {
        @Override
        public BusinessCategoryBean createFromParcel(Parcel in) {
            return new BusinessCategoryBean(in);
        }

        @Override
        public BusinessCategoryBean[] newArray(int size) {
            return new BusinessCategoryBean[size];
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
        dest.writeString(displayorder);
    }
}
