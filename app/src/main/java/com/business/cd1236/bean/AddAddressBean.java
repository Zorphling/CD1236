package com.business.cd1236.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddAddressBean implements Serializable {

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
}
