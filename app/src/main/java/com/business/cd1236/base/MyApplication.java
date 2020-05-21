package com.business.cd1236.base;

import android.content.Context;
import android.os.Handler;

import com.business.cd1236.greendao.GreenDaoUtils;
import com.jess.arms.base.BaseApplication;
import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends BaseApplication {
    public static Context mApp;
    private static Handler mHandler;
    private String BUGLY_ID = "9dfd5496b6";

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = getApplicationContext();
        GreenDaoUtils.getInstance().init();
        CrashReport.initCrashReport(getApplicationContext(), BUGLY_ID, true);
    }

    public static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        return mHandler;
    }
}
