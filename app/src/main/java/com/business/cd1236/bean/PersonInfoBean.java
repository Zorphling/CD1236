package com.business.cd1236.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonInfoBean {

    /**
     * collect : 0
     * follow : 0
     * browse : 0
     * settled_in : 1
     * type : 1
     * personal
     */
    public String collect;
    public String follow;
    public String browse;
    public String settled_in;
    public String type;
    public personalBean personal;



    public static class personalBean implements Parcelable {
        public String img;
        public String realname;


        protected personalBean(Parcel in) {
            img = in.readString();
            realname = in.readString();
        }

        public static final Creator<personalBean> CREATOR = new Creator<personalBean>() {
            @Override
            public personalBean createFromParcel(Parcel in) {
                return new personalBean(in);
            }

            @Override
            public personalBean[] newArray(int size) {
                return new personalBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(img);
            dest.writeString(realname);
        }
    }
}
