package com.business.cd1236.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.CheckUpdateBean;
import com.business.cd1236.di.component.DaggerSplashComponent;
import com.business.cd1236.mvp.contract.SplashContract;
import com.business.cd1236.mvp.presenter.SplashPresenter;
import com.business.cd1236.net.UpdateAppHttpUtil;
import com.business.cd1236.utils.FileUtils;
import com.business.cd1236.utils.GsonUtils;
import com.business.cd1236.utils.StringUtils;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.permissions.PermissionChecker;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import io.reactivex.functions.Consumer;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/01/2020 16:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class SplashActivity extends MyBaseActivity<SplashPresenter> implements SplashContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        /**
         * 权限，版本更新
         */
//        clearCache();
        RxPermissions rxPermissions = new RxPermissions(this);
        if (rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            mPresenter.checkUpdate(mActivity);
        } else {
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                //同意权限
                                mPresenter.checkUpdate(mActivity);
                            } else {
                                //拒绝权限
                            }
                        }
                    });
        }
    }

    /**
     * 清空缓存包括裁剪、压缩、AndroidQToPath所生成的文件，注意调用时机必须是处理完本身的业务逻辑后调用；非强制性
     */
    private void clearCache() {
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        if (PermissionChecker.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //PictureFileUtils.deleteCacheDirFile(this, PictureMimeType.ofImage());
            PictureFileUtils.deleteAllCacheDirFile(mActivity);
        } else {
            PermissionChecker.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PictureConfig.APPLY_STORAGE_PERMISSIONS_CODE);
        }
    }

    private void downLoadApk(CheckUpdateBean bean) {
        /**
         * 基本使用
         */
//        new UpdateAppManager
//                .Builder()
//                //当前Activity
//                .setActivity(this)
//                //更新地址
//                .setUpdateUrl(bean.downLoadUrl)
//                //实现httpManager接口的对象
//                .setHttpManager(new UpdateAppHttpUtil(mActivity))
//                .build()
//                .update();

        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(this)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil(mActivity))
                //必须设置，更新地址
                .setUpdateUrl(bean.downLoadUrl)

        //以下设置，都是可选
        //设置请求方式，默认get
//                .setPost(false)
        //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
//                .setParams(params)
        //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度
//                .hideDialogOnDownloading(false)
        //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
//                .setTopPic(R.mipmap.top_8)
        //为按钮，进度条设置颜色，默认从顶部图片自动识别。
        //.setThemeColor(ColorUtil.getRandomColor())
        //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
//                .setTargetPath(path)
        //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
        //.setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
        //不显示通知栏进度条
//                .dismissNotificationProgress()
        //是否忽略版本
        //.showIgnoreVersion()
                .build()
        //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        CheckUpdateBean checkUpdateBean = GsonUtils.parseJsonWithGson(json, CheckUpdateBean.class);
                        updateAppBean
                                //（必须）是否更新Yes,No
                                .setUpdate(StringUtils.equals("1", checkUpdateBean.isUpdate) ? "Yes" : "No")
                                //（必须）新版本号，
                                .setNewVersion(checkUpdateBean.versionCode)
                                //（必须）下载地址
                                .setApkFileUrl(checkUpdateBean.downLoadUrl)
                                //（必须）更新内容
                                .setUpdateLog(checkUpdateBean.versionName);
                        //大小，不设置不显示大小，可以不设置
//                                    .setTargetSize(jsonObject.optString("target_size"))
                        //是否强制更新，可以不设置
//                                    .setConstraint(false)
                        //设置md5，可以不设置
//                                    .setNewMd5(jsonObject.optString("new_md51"));
                        return updateAppBean;
                    }

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
//                        CProgressDialogUtils.showProgressDialog(JavaActivity.this);
                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
//                        CProgressDialogUtils.cancelProgressDialog(JavaActivity.this);
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp(String error) {
                        launchActivity(new Intent(mActivity, MainActivity.class));
                        killMyself();
                    }
                });


//        RxPermissions rxPermissions = new RxPermissions(this);
//        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//        )
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if (aBoolean) {
//                            ToastUtils.showShort("同意权限");
//                        } else {
//                            ToastUtils.showShort("拒绝权限");
//                        }
//
//                        DownloadUtil.get().installApk(beans.getDownload_url(), new OnDownloadListener() {
//                            @Override
//                            public void onDownloadSuccess() {
//                                DownloadUtil.get().downSuccess(SplashActivity.this);
//                                LogUtils.i("onDownloadApp===onDownloadSuccess==更新下载成功");
//                            }
//
//                            @Override
//                            public void onDownloading(int progress) {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        seekBar_mid.setProgress(progress);
//                                        tv_pos.setText("正在更新,请稍后..." + progress + "%");
//                                    }
//                                });
//
//                                LogUtils.i("onDownloadApp===onDownloading 002==更新下载::" + progress);
//                            }
//
//                            @Override
//                            public void onDownloadFailed() {
//                                ToastUtils.showShort("更新下载失败");
//                                mPopupWindow.dismiss();
//                                finish();
//                                LogUtils.i("onDownloadApp===onDownloadFailed==更新下载失败");
//                            }
//                        });
//
//                    }
//                });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void checkUpdateSucc(CheckUpdateBean checkUpdateBean) {
        if (FileUtils.getVerName(mActivity).equalsIgnoreCase(checkUpdateBean.versionCode)) {//版本一致不更新
            launchActivity(new Intent(mActivity, MainActivity.class));
            killMyself();
        } else {
            downLoadApk(checkUpdateBean);
        }
    }
}
