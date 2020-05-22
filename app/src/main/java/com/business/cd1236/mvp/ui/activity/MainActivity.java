package com.business.cd1236.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.CheckUpdateBean;
import com.business.cd1236.bean.CheckUpdateBean2;
import com.business.cd1236.di.component.DaggerMainComponent;
import com.business.cd1236.globle.Constants;
import com.business.cd1236.globle.OnDownloadListener;
import com.business.cd1236.mvp.contract.MainContract;
import com.business.cd1236.mvp.presenter.MainPresenter;
import com.business.cd1236.mvp.ui.fragment.HomeFourFragment;
import com.business.cd1236.mvp.ui.fragment.HomeOneFragment;
import com.business.cd1236.mvp.ui.fragment.HomeThreeFragment;
import com.business.cd1236.mvp.ui.fragment.HomeTwoFragment;
import com.business.cd1236.net.DownloadUtil;
import com.business.cd1236.net.api.main.MainApi;
import com.business.cd1236.utils.FileUtils;
import com.business.cd1236.utils.GsonUtils;
import com.business.cd1236.utils.MyToastUtils;
import com.business.cd1236.utils.SPUtils;
import com.business.cd1236.view.homebtn.CircularRevealButton;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import project.com.arms.mvp.model.api.Api;
import razerdp.basepopup.BasePopupWindow;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/01/2020 16:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends MyBaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.ll_main01)
    CircularRevealButton llMain01;
    @BindView(R.id.ll_main02)
    CircularRevealButton llMain02;
    @BindView(R.id.ll_main03)
    CircularRevealButton llMain03;
    @BindView(R.id.ll_main04)
    CircularRevealButton llMain04;

    private HomeOneFragment homeOneFragment;
    private HomeTwoFragment homeTwoFragment;
    private HomeThreeFragment homeThreeFragment;
    private HomeFourFragment homeFourFragment;
    private List<CircularRevealButton> navs = new ArrayList<>();
    private BasePopupWindow mPopupWindow;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
//            this.recreate();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        homeOneFragment = HomeOneFragment.newInstance();
        homeTwoFragment = HomeTwoFragment.newInstance();
        homeThreeFragment = HomeThreeFragment.newInstance();
        homeFourFragment = HomeFourFragment.newInstance();
//        addFragment(homeOneFragment,homeTwoFragment,homeThreeFragment,homeFourFragment);

        navs.add(llMain01);
        navs.add(llMain02);
        navs.add(llMain03);
        navs.add(llMain04);


        firstLoad();

//        checkUpdate();
        mPresenter.checkUpdate(mActivity);
    }

    private void checkUpdate() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {//同意权限
                            AllenVersionChecker
                                    .getInstance()
                                    .requestVersion()
                                    .setRequestUrl(Api.APP_DOMAIN + MainApi.APP_UPDATE)
                                    .request(new RequestVersionListener() {

                                        @Nullable
                                        @Override
                                        public UIData onRequestVersionSuccess(DownloadBuilder downloadBuilder, String result) {
                                            //拿到服务器返回的数据，解析，拿到downloadUrl和一些其他的UI数据
                                            //如果是最新版本直接return null
                                            CheckUpdateBean checkUpdateBean = GsonUtils.parseJsonWithGson(result, CheckUpdateBean.class);
                                            if (FileUtils.getVerName(mActivity).equalsIgnoreCase(checkUpdateBean.result.versionCode)) {//版本一致不更新
                                                return null;
                                            }
                                            return UIData.create().setTitle("有新的版本").setContent(checkUpdateBean.result.versionName).setDownloadUrl(checkUpdateBean.result.downLoadUrl);
                                        }

                                        @Override
                                        public void onRequestVersionFailure(String message) {

                                        }
                                    })
                                    .executeMission(mActivity);
                        } else {//拒绝权限
                            checkUpdate();
                        }
                    }
                });
    }

    public void onPopUpdate(CheckUpdateBean2 beans) {
        mPopupWindow = new BasePopupWindow(this) {
            @Override
            public View onCreateContentView() {
                return createPopupById(R.layout.layout_update);
            }
        };
        if (mPopupWindow != null) {
            TextView tv_content = mPopupWindow.getContentView().findViewById(R.id.tv_content);
            SeekBar seekBar_mid = mPopupWindow.getContentView().findViewById(R.id.seekBar_mid);
            TextView tv_pos = mPopupWindow.getContentView().findViewById(R.id.tv_pos);
            Button btn_neg = mPopupWindow.getContentView().findViewById(R.id.btn_neg);
            Button btn_pos = mPopupWindow.getContentView().findViewById(R.id.btn_pos);
            LinearLayout ll_commit = mPopupWindow.getContentView().findViewById(R.id.ll_commit);
            LinearLayout ll_show_progress = mPopupWindow.getContentView().findViewById(R.id.ll_show_progress);

            tv_content.setText(beans.versionName);

//            if (beans.isUpdate.equalsIgnoreCase("0")) { // 1 :强更  0:正常更新
//                ll_commit.setVisibility(View.VISIBLE);
//                //TODO 点击确认后更新APP
//                //onDownloadApp(beans, seekBar_mid, tv_pos);
//            } else {
//                ll_show_progress.setVisibility(View.VISIBLE);
//                ll_commit.setVisibility(View.GONE);
//                //TODO  直接更新 APP
//                onDownloadApp(beans, seekBar_mid, tv_pos);
//            }

            btn_neg.setOnClickListener(v -> {
                if (beans.isUpdate.equalsIgnoreCase("0"))
                    mPopupWindow.dismiss();
                else {

                }
            });
            btn_pos.setOnClickListener(v -> {
                ll_commit.setVisibility(View.GONE);
                ll_show_progress.setVisibility(View.VISIBLE);
                onDownloadApp(beans, seekBar_mid, tv_pos);
            });
        }
        mPopupWindow.setAllowDismissWhenTouchOutside(false);
        //mPopupWindow.setAllowInterceptTouchEvent(false);
        mPopupWindow.setBackPressEnable(true);
        mPopupWindow.setClipChildren(false);
        mPopupWindow.setPopupGravity(Gravity.CENTER);
        mPopupWindow.showPopupWindow();
    }

    private void onDownloadApp(CheckUpdateBean2 beans, SeekBar seekBar_mid, TextView tv_pos) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
//                            ToastUtils.showShort("同意权限");
                            DownloadUtil.get().installApk(beans.downLoadUrl, new OnDownloadListener() {

                                @Override
                                public void onDownloadSuccess(File file) {
                                    mPopupWindow.dismiss();
                                    DownloadUtil.get().downSuccess(mActivity);
                                }

                                @Override
                                public void onDownloading(int progress) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            seekBar_mid.setProgress(progress);
                                            tv_pos.setText("正在更新,请稍后..." + progress + "%");
                                        }
                                    });
                                }

                                @Override
                                public void onDownloadFailed() {
                                    mPopupWindow.dismiss();
                                    finish();
                                }
                            });
                        } else {
                            onDownloadApp(beans, seekBar_mid, tv_pos);
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PictureConfig.APPLY_STORAGE_PERMISSIONS_CODE:
                // 存储权限
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        PictureFileUtils.deleteCacheDirFile(mActivity, PictureMimeType.ofImage());
                    } else {
                        ArmsUtils.snackbarText("请打开文件读取权限");
                    }
                }
                break;
        }
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

    @OnClick({R.id.ll_main01, R.id.ll_main02, R.id.ll_main03, R.id.ll_main04})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main01:
                clickMaiNav(1);
                break;
            case R.id.ll_main02:
                clickMaiNav(2);
                break;
            case R.id.ll_main03:
                clickMaiNav(3);
                break;
            case R.id.ll_main04:
                if ((Boolean) SPUtils.get(mActivity, Constants.LOGIN, false)) {
                    clickMaiNav(4);
                } else {
                    launchActivity(new Intent(mActivity, LoginActivity.class));
                }
                break;
        }
    }

    /**
     * 点击首页
     */
    int page = 1;

    public void clickMaiNav(int page) {
        if (this.page == page) return;
        this.page = page;
        hideToolBar();
        switch (page) {
            case 1:
                changeNav(page);
                firstLoad();
                break;
            case 2:
                changeNav(page);
                StatusBarUtil.setLightMode(mActivity);
                StatusBarUtil.setTranslucent(mActivity,0);
//                setStatusColor(this, false, true, android.R.color.white);
                smartReplaceFragment(R.id.fl_home_container, homeTwoFragment);
                break;
            case 3:
                changeNav(page);
                StatusBarUtil.setLightMode(mActivity);
//                StatusBarUtil.setTranslucent(mActivity,0);
//                setStatusColor(this, false, false, R.color.colorPrimary);
                smartReplaceFragment(R.id.fl_home_container, homeThreeFragment);
                break;
            case 4:
                changeNav(page);
                StatusBarUtil.setLightMode(mActivity);
//                StatusBarUtil.setTranslucentForImageViewInFragment(mActivity,0,null);
//                setStatusColor(this, true, false, R.color.colorPrimary);
                smartReplaceFragment(R.id.fl_home_container, homeFourFragment);
                break;
        }
    }

    private void changeNav(int page) {
        for (int i = 0; i < navs.size(); i++) {
            navs.get(i).setOnSelected(false);
        }
        navs.get(page - 1).setOnSelected(true);
    }

    private void firstLoad() {
        smartReplaceFragment(R.id.fl_home_container, homeOneFragment);
        StatusBarUtil.setLightMode(mActivity);
        StatusBarUtil.setTranslucent(mActivity,0);
//        setStatusColor(this, false, true, android.R.color.white);
    }

    /**
     * 监听返回--是否退出程序
     */
    private long exitTime = 0;

    //改写物理按键——返回的逻辑，希望浏览的网页后退而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //  System.exit(0);//退出程序
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    MyToastUtils.showLong("再按一次退出程序！");
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                    Process.killProcess(android.os.Process.myPid());
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void checkUpdateSucc(CheckUpdateBean2 checkUpdateBean) {
        if (!checkUpdateBean.versionCode.equalsIgnoreCase(FileUtils.getVerName(mActivity)))
            onPopUpdate(checkUpdateBean);
    }
}
