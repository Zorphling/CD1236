package com.business.cd1236.net;


import androidx.annotation.NonNull;

import com.business.cd1236.utils.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class LogInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String TAG = "okhttp_request";

    @Override
    public okhttp3.Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
//        Request request = chain.request();
//
//        Timber.tag(TAG).i("request:%s", request.toString());
//        RequestBody requestBody = request.body();
//        if (requestBody != null) {
//            Buffer buffer = new Buffer();
//            requestBody.writeTo(buffer);
//
//            Charset charset = UTF8;
//            MediaType contentType = requestBody.contentType();
//            if (contentType != null) {
//                charset = contentType.charset(UTF8);
//            }
//
//            Timber.tag(TAG).i("");
//            if (charset != null) {
//                Timber.tag(TAG).i("request:%s", buffer.readString(charset));
//            }
//            Timber.tag(TAG).i("--> END_REQUEST " + request.method()
//                    + " (" + requestBody.contentLength() + "  byte body)");
//        }
//
//        long t1 = System.nanoTime();
//        okhttp3.Response response = chain.proceed(chain.request());
//        long t2 = System.nanoTime();
//        Timber.tag(TAG).i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
//                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//        assert response.body() != null;
//        okhttp3.MediaType mediaType = response.body().contentType();
//        String content = response.body().string();
//        Timber.tag(TAG).i("response body:%s", content);
//        return response.newBuilder()
//                .body(okhttp3.ResponseBody.create(mediaType, content))
//                .build();


        Request request = chain.request();
        String method = request.method();
        HttpUrl url = request.url();
        LogUtils.e("本次请求", "url:" + url.toString() + " === method:" + method);
        Headers headers = request.headers();
        Set<String> names = headers.names();
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            String value = headers.get(next);
            LogUtils.e(next + ":" + value);
        }
        return chain.proceed(request);
    }

}