package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerPersonalInfoComponent;
import com.business.cd1236.mvp.contract.PersonalInfoContract;
import com.business.cd1236.mvp.presenter.PersonalInfoPresenter;
import com.business.cd1236.utils.GlideEngine;
import com.business.cd1236.view.dialog.SetHeaderDialog;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/01/2020 10:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class PersonalInfoActivity extends MyBaseActivity<PersonalInfoPresenter> implements PersonalInfoContract.View, SetHeaderDialog.SelectPicListener {

    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.iv_src)
    ImageView ivSrc;
    @BindView(R.id.rl_person_info)
    RelativeLayout rlPersonInfo;
    @BindView(R.id.iv_arrow1)
    ImageView ivArrow1;
    @BindView(R.id.rl_nickname)
    RelativeLayout rlNickname;
    @BindView(R.id.iv_arrow2)
    ImageView ivArrow2;
    @BindView(R.id.rl_harvest_address)
    RelativeLayout rlHarvestAddress;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPersonalInfoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_personal_info; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("个人信息");
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_person_info, R.id.rl_nickname, R.id.rl_harvest_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_person_info:
                ShowDialog();
                break;
            case R.id.rl_nickname:
                break;
            case R.id.rl_harvest_address:
                break;
        }
    }

    private void ShowDialog() {
        new SetHeaderDialog(this,this);
    }

    @Override
    public void onPhototaken() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onSelectPic() {

    }
}
