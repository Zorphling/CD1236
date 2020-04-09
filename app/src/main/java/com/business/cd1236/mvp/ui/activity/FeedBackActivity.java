package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyApplication;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerFeedBackComponent;
import com.business.cd1236.mvp.contract.FeedBackContract;
import com.business.cd1236.mvp.presenter.FeedBackPresenter;
import com.business.cd1236.utils.StringUtils;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/09/2020 10:39
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class FeedBackActivity extends MyBaseActivity<FeedBackPresenter> implements FeedBackContract.View {

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.iv_upload)
    ImageView ivUpload;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerFeedBackComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_feed_back; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("意见反馈");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

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

    @OnClick({R.id.iv_upload, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_upload:
                ArmsUtils.snackbarText("抱歉，暂时无法上传图片");
                break;
            case R.id.btn_submit:
                if (StringUtils.checkString(StringUtils.getEditText(etContent))) {
                    ArmsUtils.snackbarText("请输入反馈问题");
                    return;
                }
                ArmsUtils.snackbarText("反馈成功");
                btnSubmit.setText("反馈成功");
                btnSubmit.setEnabled(false);
                MyApplication.getHandler().postDelayed(() -> killMyself(), 2000);
                break;
        }
    }
}
