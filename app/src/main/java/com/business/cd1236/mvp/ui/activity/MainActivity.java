package com.business.cd1236.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerMainComponent;
import com.business.cd1236.globle.Constants;
import com.business.cd1236.mvp.contract.MainContract;
import com.business.cd1236.mvp.presenter.MainPresenter;
import com.business.cd1236.mvp.ui.fragment.HomeFourFragment;
import com.business.cd1236.mvp.ui.fragment.HomeOneFragment;
import com.business.cd1236.mvp.ui.fragment.HomeThreeFragment;
import com.business.cd1236.mvp.ui.fragment.HomeTwoFragment;
import com.business.cd1236.utils.MyToastUtils;
import com.business.cd1236.utils.SPUtils;
import com.business.cd1236.view.homebtn.CircularRevealButton;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.permissions.PermissionChecker;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
        if (savedInstanceState != null){
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
                setStatusColor(this, false, true, android.R.color.white);
                smartReplaceFragment(R.id.fl_home_container, homeTwoFragment);
                break;
            case 3:
                changeNav(page);
                setStatusColor(this, false, false, R.color.colorPrimary);
                smartReplaceFragment(R.id.fl_home_container, homeThreeFragment);
                break;
            case 4:
                changeNav(page);
                setStatusColor(this, true, false, R.color.colorPrimary);
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
        setStatusColor(this, true, true, android.R.color.white);
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

}
