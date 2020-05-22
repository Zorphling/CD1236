package com.business.cd1236.pay.wx;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WxBean implements Parcelable {

    /**
     * package : Sign=WXPay
     * paySign : C375A0E580D29D7E5EA5815A741E8209
     * appId : wxa09c6971359ad94d
     * partnerid : 1536907051
     * prepayid : wx1516493707083224cc423c911172613900
     * nonceStr : 01762020046735133444165363880471
     * key : QianBaJiaXinXiKeJi13880050319LDQ
     * timestamp : 1565858979475
     */
    private  String appid;
    private  String noncestr;
    @SerializedName("package")
    private String packageX;
    private  String partnerid;
    private  String prepayid;
    private  String sign;
    private  String timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    protected WxBean(Parcel in) {
        appid = in.readString();
        noncestr = in.readString();
        packageX = in.readString();
        partnerid = in.readString();
        prepayid = in.readString();
        sign = in.readString();
        timestamp = in.readString();
    }

    public static final Creator<WxBean> CREATOR = new Creator<WxBean>() {
        @Override
        public WxBean createFromParcel(Parcel in) {
            return new WxBean(in);
        }

        @Override
        public WxBean[] newArray(int size) {
            return new WxBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(appid);
        parcel.writeString(noncestr);
        parcel.writeString(packageX);
        parcel.writeString(partnerid);
        parcel.writeString(prepayid);
        parcel.writeString(sign);
        parcel.writeString(timestamp);
    }



  /*  @SerializedName("package")
    private String packageX;
    private String paySign;
    private String appId;
    private String partnerid;
    private String prepayid;
    private String nonceStr;
    private String key;
    private String timeStamp;

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimestamp(String timestamp) {
        this.timeStamp = timeStamp;
    }

    public WxBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.packageX);
        dest.writeString(this.paySign);
        dest.writeString(this.appId);
        dest.writeString(this.partnerid);
        dest.writeString(this.prepayid);
        dest.writeString(this.nonceStr);
        dest.writeString(this.key);
        dest.writeString(this.timeStamp);
    }

    protected WxBean(Parcel in) {
        this.packageX = in.readString();
        this.paySign = in.readString();
        this.appId = in.readString();
        this.partnerid = in.readString();
        this.prepayid = in.readString();
        this.nonceStr = in.readString();
        this.key = in.readString();
        this.timeStamp = in.readString();
    }

    public static final Creator<WxBean> CREATOR = new Creator<WxBean>() {
        @Override
        public WxBean createFromParcel(Parcel source) {
            return new WxBean(source);
        }

        @Override
        public WxBean[] newArray(int size) {
            return new WxBean[size];
        }
    };*/
}

