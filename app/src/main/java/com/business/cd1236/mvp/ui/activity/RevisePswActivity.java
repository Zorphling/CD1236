package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerRevisePswComponent;
import com.business.cd1236.mvp.contract.RevisePswContract;
import com.business.cd1236.mvp.presenter.RevisePswPresenter;
import com.business.cd1236.utils.MyToastUtils;
import com.business.cd1236.utils.ParamsToJson;
import com.business.cd1236.utils.StringUtils;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/31/2020 17:38
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class RevisePswActivity extends MyBaseActivity<RevisePswPresenter> implements RevisePswContract.View {

    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.et_input_number)
    EditText etInputNumber;
    @BindView(R.id.et_input_code)
    EditText etInputCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_input_psw)
    EditText etInputPsw;
    @BindView(R.id.et_input_psw_again)
    EditText etInputPswAgain;
    @BindView(R.id.tv_confirm_revise)
    TextView tvConfirmRevise;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRevisePswComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_revise_psw; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader(getResources().getString(R.string.revise_psw));
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


    @OnClick({R.id.tv_get_code, R.id.tv_confirm_revise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                break;
            case R.id.tv_confirm_revise:
                String name = StringUtils.getEditText(etInputNumber);
                String code = StringUtils.getEditText(etInputCode);
                String psw = StringUtils.getEditText(etInputPsw);
                String pswAgain = StringUtils.getEditText(etInputPswAgain);
                if (!StringUtils.checkString(name)) {
                    ArmsUtils.snackbarText("请输入手机号");
                    return;
                }
                if (name.length() != 11) {
                    ArmsUtils.snackbarText("请输入正确的手机号");
                    return;
                }
                if (!StringUtils.checkString(psw)) {
                    ArmsUtils.snackbarText("请输入新密码");
                    return;
                }
                if (!StringUtils.checkString(pswAgain)) {
                    ArmsUtils.snackbarText("请再次输入新密码");
                    return;
                }
                if (!StringUtils.equals(psw, pswAgain)) {
                    ArmsUtils.snackbarText("密码不匹配");
                    return;
                }
                mPresenter.revisePsw(mActivity, ParamsToJson.PTJ(ParamsToJson.PTO("name", "pwd"), name, psw));
                break;
        }
    }

    @Override
    public void reviseSuccess() {
        MyToastUtils.showShort("修改成功");
        killMyself();
    }
}
