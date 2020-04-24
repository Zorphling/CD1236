package com.business.cd1236.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class BusinessGoodsManageBean {

    public List<?> goods;
    public List<CategoryBean> category;

    public static class CategoryBean implements Parcelable , IPickerViewData {
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
