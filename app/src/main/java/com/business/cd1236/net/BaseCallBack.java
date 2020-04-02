package com.business.cd1236.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.business.cd1236.utils.LogUtils;
import com.business.cd1236.utils.RxExceptionUtil;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public abstract class BaseCallBack implements Observer<ResponseBody> {
    private String tag = "okhttp";
    private boolean mShowDialog;
    private ProgressDialog dialog;
    Context mContext;
    private Disposable d;
    private boolean mShowToast = true;

    protected BaseCallBack(Context context, Boolean showDialog) {
        mContext = context;
        mShowDialog = showDialog;
    }

    protected BaseCallBack(Context context, Boolean showDialog, Boolean mShowToast) {
        mContext = context;
        mShowDialog = showDialog;
        this.mShowToast = mShowToast;
    }

    protected BaseCallBack(Context context) {
        this(context, true);
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        if (!isConnected(mContext)) {
            if (d.isDisposed()) {
                d.dispose();
            }
        } else {
            showDialog();
        }
    }

    @Override
    public void onNext(ResponseBody tResponse) {
        try {
            String jsonString = new String(tResponse.bytes());
            JSONObject jsonObject = new JSONObject(jsonString);
            /*JsonObject.opt 无key值时会得到默认值,JsonObject.get无key值会出错*/
            String data = jsonObject.optString("result");
            int status = Integer.parseInt(jsonObject.optString("status"));
            String msg = jsonObject.optString("msg");
            LogUtils.i(tag, "=====okgo002===status====" + status);
            LogUtils.i(tag, "=====okgo002====msg===" + msg);
            LogUtils.i(tag, "=====okgo002====data===" + data);

            if (status == 200) {
                onSuccess(data);
            } else if (status == -4001 || status == -4008 || status == -4010) {
//                SPUtils.put(mContext, Constants.user_isLogin, false);
//                SPUtils.put(mContext, Constants.Token, "");
//                if (!TextUtils.isEmpty(msg) && mShowToast)
//                    ToastUtils.showShort(msg);
//                needLogin();
            } else {
                onFailure(msg);
                onFailure(status);
                onFailure(msg, status);
//                if (!TextUtils.isEmpty(msg) && mShowToast)
//                    ToastUtils.showShort(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            onFailure("");
        }
    }


    protected abstract void onSuccess(String jsonString);

    protected void onFailure(String errorMsg) {
        //别在这儿加土司
    }

    protected void onFailure(int status) {
    }

    protected void onFailure(String errorMsg, int status) {

    }

    @Override
    public void onError(Throwable e) {
        if (d.isDisposed()) {
            d.dispose();
        }
        hideDialog();
        endRefresh();
        onFailure(RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {
        if (d.isDisposed()) {
            d.dispose();
        }
        hideDialog();
        endRefresh();
    }


    private void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (dialog != null) {
            dialog.cancel();
            dialog = null;
        }
    }

    private void showDialog() {
        if (dialog == null && mShowDialog) {
            dialog = new ProgressDialog(mContext);
            dialog.show();
        }
    }

    protected void endRefresh() {

    }
//
//    protected void needLogin() {
//        Activity activity = AppManager.getAppManager().getTopActivity();
//        if (activity == null) {
//            return;
//        }
//        SPUtils.put(App.getInstance(), Constants.user_isLogin, false);
//        SPUtils.put(App.getInstance(), Constants.Token, "");
//        if (activity instanceof LoginActivity) {
//            return;
//        }
//        AppManager.getAppManager().startActivity(new Intent(AppManager.getAppManager().getTopActivity(), LoginActivity.class));
//    }

    private boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        return info.isAvailable();
    }

}