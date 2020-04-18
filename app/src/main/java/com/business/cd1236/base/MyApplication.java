package com.business.cd1236.base;

import android.content.Context;
import android.os.Handler;

import com.business.cd1236.greendao.GreenDaoUtils;
import com.jess.arms.base.BaseApplication;

public class MyApplication extends BaseApplication {
    public static Context mApp;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = getApplicationContext();
        GreenDaoUtils.getInstance().init();
    }

    public static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        return mHandler;
    }
}
