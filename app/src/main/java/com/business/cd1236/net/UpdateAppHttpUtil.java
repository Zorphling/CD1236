//package com.business.cd1236.net;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//
//import com.business.cd1236.globle.OnDownloadListener;
//import com.business.cd1236.utils.LogUtils;
//import com.vector.update_app.HttpManager;
//
//import java.io.File;
//import java.util.Map;
//
//import okhttp3.OkHttpClient;
//
//public class UpdateAppHttpUtil implements HttpManager {
//    private Context context;
//    private final OkHttpClient okHttpClient;
//
//    public UpdateAppHttpUtil(Context context) {
//        this.context = context;
//        okHttpClient = new OkHttpClient();
//    }
//
//    /**
//     * 异步get
//     *
//     * @param url      get请求地址
//     * @param params   get参数
//     * @param callBack 回调
//     */
//    @Override
//    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
//        RequestUtils.checkUpdate(new BaseCallBack(context) {
//            @Override
//            protected void onSuccess(String jsonString) {
//                callBack.onResponse(jsonString);
//            }
//
//            @Override
//            protected void onFailure(String errorMsg, int status) {
//                callBack.onError(errorMsg);
//            }
//        });
//    }
//
//    /**
//     * 异步post
//     *
//     * @param url      post请求地址
//     * @param params   post请求参数
//     * @param callBack 回调
//     */
//    @Override
//    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
//        RequestUtils.checkUpdate(new BaseCallBack(context) {
//            @Override
//            protected void onSuccess(String jsonString) {
//                callBack.onResponse(jsonString);
//            }
//
//            @Override
//            protected void onFailure(String errorMsg, int status) {
//                callBack.onError(errorMsg);
//            }
//        });
//    }
//
//    /**
//     * 下载
//     *
//     * @param url      下载地址
//     * @param path     文件保存路径
//     * @param fileName 文件名称
//     * @param callback 回调
//     */
//    @Override
//    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull final FileCallback callback) {
//        DownloadUtil.get().installApk(url, new OnDownloadListener() {
//            @Override
//            public void onDownloadSuccess(File file) {
//                callback.onResponse(file);
//            }
//
//            @Override
//            public void onDownloading(float progress, long total) {
//                LogUtils.e("下载进度 ===== " + progress + ":::" + total + ":::" + (progress / total) * 100 + "%");
//                callback.onProgress(progress, total);
//            }
//
//            @Override
//            public void onDownloadFailed() {
//
//            }
//        });
//    }
//}