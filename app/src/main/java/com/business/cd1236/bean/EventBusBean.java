package com.business.cd1236.bean;

public class EventBusBean {

    public int what;
    public String message;

    public EventBusBean(int what) {
        this.what = what;
    }
    public EventBusBean(int what, String message) {
        this.what = what;
        this.message = message;
    }
}
