package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerSettingComponent;
import com.business.cd1236.mvp.contract.SettingContract;
import com.business.cd1236.mvp.presenter.SettingPresenter;
import com.business.cd1236.utils.SPUtils;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/31/2020 18:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class SettingActivity extends MyBaseActivity<SettingPresenter> implements SettingContract.View {

    @BindView(R.id.rl_person_info)
    RelativeLayout rlPersonInfo;
    @BindView(R.id.rl_login_psw)
    RelativeLayout rlLoginPsw;
    @BindView(R.id.iv_cache)
    ImageView ivCache;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.rl_clean_cache)
    RelativeLayout rlCleanCache;
    @BindView(R.id.rl_secret)
    RelativeLayout rlSecret;
    @BindView(R.id.rl_user_agreement)
    RelativeLayout rlUserAgreement;
    @BindView(R.id.tv_login_out)
    TextView tvLoginOut;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSettingComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_setting; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("设置");
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

    @OnClick({R.id.rl_person_info, R.id.rl_login_psw, R.id.rl_clean_cache, R.id.rl_secret, R.id.rl_user_agreement, R.id.tv_login_out})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_person_info:
                launchActivity(new Intent(mActivity, PersonalInfoActivity.class));
                break;
            case R.id.rl_login_psw:
                launchActivity(new Intent(mActivity, RevisePswActivity.class));
                break;
            case R.id.rl_clean_cache:
                tvCache.setText("");
                ArmsUtils.snackbarText("清除成功");
                break;
            case R.id.rl_secret:
                intent.setClass(mActivity, HtmlActivity.class);
                intent.putExtra(HtmlActivity.AGREEMENT_TYPE, HtmlActivity.PRIVACY_POLICY);
                launchActivity(intent);
                break;
            case R.id.rl_user_agreement:
                intent.setClass(mActivity, HtmlActivity.class);
                intent.putExtra(HtmlActivity.AGREEMENT_TYPE, HtmlActivity.USER_AGREEMENT);
                launchActivity(intent);
                break;
            case R.id.tv_login_out:
                SPUtils.clear(mActivity);
                mPresenter.loginOut(mActivity);
                break;
        }
    }

    @Override
    public void loginOutSuccess() {
        Intent intent3 = new Intent(mActivity, MainActivity.class);
        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        launchActivity(intent3);
        killMyself();
    }

}
