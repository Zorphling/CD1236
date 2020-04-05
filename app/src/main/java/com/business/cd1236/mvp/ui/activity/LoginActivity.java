package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerLoginComponent;
import com.business.cd1236.globle.Constants;
import com.business.cd1236.mvp.contract.LoginContract;
import com.business.cd1236.mvp.presenter.LoginPresenter;
import com.business.cd1236.utils.ParamsToJson;
import com.business.cd1236.utils.SPUtils;
import com.business.cd1236.utils.StringUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/31/2020 17:37
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class LoginActivity extends MyBaseActivity<LoginPresenter> implements LoginContract.View, SwitchButton.OnCheckedChangeListener {

    @BindView(R.id.et_input_number)
    TextInputEditText etInputNumber;
    @BindView(R.id.et_input_psw)
    TextInputEditText etInputPsw;
    @BindView(R.id.btn_switch)
    SwitchButton btnSwitch;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_login_regist)
    TextView tvLoginRegist;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.tv_user_agreement)
    TextView tvUserAgreement;
    @BindView(R.id.tv_privacy_policy)
    TextView tvPrivacyPolicy;
    private String name;
    private String psw;
    private boolean isSaveInfo = true;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        name = getIntent().getStringExtra("name");
        psw = getIntent().getStringExtra("psw");
        setHeader(getResources().getString(R.string.login));
        btnSwitch.setOnCheckedChangeListener(this);
        etInputNumber.setText(name);
        etInputPsw.setText(psw);
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

    @OnClick({R.id.tv_forget, R.id.tv_login, R.id.tv_login_regist, R.id.tv_user_agreement, R.id.tv_privacy_policy})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_forget:
                launchActivity(new Intent(this, RevisePswActivity.class));
                break;
            case R.id.tv_login:
                String name = StringUtils.getEditText(etInputNumber);
                String psw = StringUtils.getEditText(etInputPsw);
                if (!StringUtils.checkString(name)) {
                    ArmsUtils.snackbarText("请输入手机号");
                    return;
                }
                if (name.length() != 11) {
                    ArmsUtils.snackbarText("请输入正确的手机号");
                    return;
                }
                if (!StringUtils.checkString(psw)) {
                    ArmsUtils.snackbarText("请输入密码");
                    return;
                }
                mPresenter.login(ParamsToJson.PTJ(ParamsToJson.PTO("username", "userpwd"), name, psw), mActivity);
                break;
            case R.id.tv_login_regist:
                launchActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.tv_user_agreement:
                intent.setClass(mActivity, HtmlActivity.class);
                intent.putExtra(HtmlActivity.AGREEMENT_TYPE, HtmlActivity.USER_AGREEMENT);
                launchActivity(intent);
                break;
            case R.id.tv_privacy_policy:
                intent.setClass(mActivity, HtmlActivity.class);
                intent.putExtra(HtmlActivity.AGREEMENT_TYPE, HtmlActivity.PRIVACY_POLICY);
                launchActivity(intent);
                break;
        }
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        isSaveInfo = isChecked;
    }

    @Override
    public void loginSuccsee(String jsonString) {
        SPUtils.put(mActivity, Constants.ID, jsonString);
        if (isSaveInfo) {
            SPUtils.put(mActivity, Constants.LOGIN, true);
//            SPUtils.put(mActivity, Constants.SAVE_LOGIN_INFO,isSaveInfo);
        }
        launchActivity(new Intent(mActivity, MainActivity.class));
        killMyself();
    }
}
