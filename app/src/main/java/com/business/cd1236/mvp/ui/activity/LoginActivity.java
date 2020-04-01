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
import com.business.cd1236.mvp.contract.LoginContract;
import com.business.cd1236.mvp.presenter.LoginPresenter;
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
        setHeader(getResources().getString(R.string.login));
        btnSwitch.setOnCheckedChangeListener(this);
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

    @OnClick({R.id.btn_switch, R.id.tv_forget, R.id.tv_login, R.id.tv_login_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_switch:
                break;
            case R.id.tv_forget:
                launchActivity(new Intent(this,RevisePswActivity.class));
                break;
            case R.id.tv_login:
                launchActivity(new Intent(this,SettingActivity.class));
                break;
            case R.id.tv_login_regist:
                launchActivity(new Intent(this, RegistActivity.class));
                break;
        }
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        ArmsUtils.snackbarText(isChecked ? "选中" : "未选中");
    }
}
