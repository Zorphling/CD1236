package com.business.cd1236.net;

import com.business.cd1236.base.MyApplication;
import com.business.cd1236.globle.Constants;
import com.business.cd1236.utils.SPUtils;
import com.business.cd1236.utils.StringUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import project.com.arms.mvp.model.api.Api;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public final static String BaseUrl = Api.APP_DOMAIN;
    private static final int DEFAULT_TIME = 20;
    private static RetrofitUtils mInstance;

    /**
     * 单例模式
     */
    public static RetrofitUtils getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化Retrofit
     */
    public Retrofit getRetrofit() {
        String DYNAMIC_URL = (String) SPUtils.get(MyApplication.mApp, Constants.INIT_GLOBAL_URL, "");
        if (StringUtils.checkString(DYNAMIC_URL)) {
            return getRetrofitBaseUrl(DYNAMIC_URL);
        } else {
            return getRetrofitBaseUrl(RetrofitUtils.BaseUrl);
        }
    }
    /**
     * =================================
     */
    /**
     * 单独为商户中心店铺管理创建的接口,由于接口地址更改
     */
    public Retrofit getStoreRetrofit() {
        return getRetrofitBaseUrl(Api.APP_SHOP_DOMAIN);
    }

    //返回一个泛型类
    public static <T> T getStoreService(Class<T> service) {
        return getInstance().getStoreRetrofit().create(service);
    }

    /**
     * =================================
     */
    //返回一个泛型类
    public static <T> T getService(Class<T> service) {
        return getInstance().getRetrofit().create(service);
    }

    /**
     * 初始化Retrofit,动态更改baseUrl
     *
     * @param baseUrl
     */
    ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.mApp));

    private Retrofit getRetrofitBaseUrl(String baseUrl) {
        // 初始化okhttp
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                //设置请求超时时间
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .cookieJar(cookieJar)
//                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//忽略证书
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//忽略证书
                .build();
        // 初始化Retrofit
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //返回一个泛型类
    public static <T> T getServiceBaseUrl(Class<T> service, String baseUrl) {
        return getInstance().getRetrofitBaseUrl(baseUrl).create(service);
    }
}