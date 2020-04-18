package com.business.cd1236.greendao;

import android.database.sqlite.SQLiteDatabase;

import com.business.cd1236.base.MyApplication;
import com.business.cd1236.greendao.db.DaoMaster;
import com.business.cd1236.greendao.db.DaoSession;


public class GreenDaoUtils {
    private volatile static GreenDaoUtils greenDaoUtils;
    private DaoSession daoSession;
    private SQLiteDatabase database;

    private GreenDaoUtils() {

    }

    public static GreenDaoUtils getInstance() {
        if (greenDaoUtils == null) {
            synchronized (GreenDaoUtils.class) {
                if (greenDaoUtils == null) {
                    greenDaoUtils = new GreenDaoUtils();
                }
            }
        }

        return greenDaoUtils;
    }

    //初始化数据   这个方法在Application里面调用
    public void init() {
        setDataBase();
    }

    private void setDataBase() {
        //调用Application里面的上下文   参数二为数据库名字
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.mApp, "search.db", null);

        database = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(database);

        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {

        return daoSession;
    }

    public SQLiteDatabase getSQLiteDatabase() {
        return database;
    }
}