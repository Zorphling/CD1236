package com.business.cd1236.bean;

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

    public class personalBean {
        public String img;
        public String realname;
    }
}
