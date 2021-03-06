package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.EventBusBean;
import com.business.cd1236.di.component.DaggerReviseNickNameComponent;
import com.business.cd1236.mvp.contract.ReviseNickNameContract;
import com.business.cd1236.mvp.presenter.ReviseNickNamePresenter;
import com.business.cd1236.utils.MyToastUtils;
import com.business.cd1236.utils.StringUtils;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import project.com.arms.app.EventBusTags;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/01/2020 14:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class ReviseNickNameActivity extends MyBaseActivity<ReviseNickNamePresenter> implements ReviseNickNameContract.View {
    @BindView(R.id.et_nickname)
    EditText etNickName;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerReviseNickNameComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_revise_nick_name; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("修改昵称");
        setRightBtn("保存", 0, v -> {
            String name = StringUtils.getEditText(etNickName);
            if (StringUtils.checkString(name)) {
                mPresenter.reviseNickName(name, this);
            } else {
                ArmsUtils.snackbarText("请输入昵称");
            }
        });
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
    public void reviseNickNameSucc(String msg) {
        MyToastUtils.showShort(msg);
        EventBus.getDefault().post(new EventBusBean(EventBusTags.NICK_NAME, StringUtils.getEditText(etNickName)));
        killMyself();
    }
}
