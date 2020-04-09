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
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.LogUtils;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.dialog.SetHeaderDialog;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
    CircleImageView ivSrc;
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

    @OnClick({R.id.rl_person_info, R.id.rl_nickname, R.id.rl_harvest_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_person_info:
                ShowDialog();
                break;
            case R.id.rl_nickname:
                launchActivity(new Intent(mActivity,ReviseNickNameActivity.class));
                break;
            case R.id.rl_harvest_address:
                launchActivity(new Intent(mActivity,AddressActivity.class));
                break;
        }
    }

    private void ShowDialog() {
        new SetHeaderDialog(this, this);
    }

    @Override
    public void onPhototaken() {//单独拍照
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofAll())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .enableCrop(true)// 是否裁剪
                .compress(true)//是否压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onSelectPic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .enableCrop(true)// 是否裁剪
                .compress(true)//是否压缩
                .maxSelectNum(1)//最大选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .isSingleDirectReturn(true)//单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .enableCrop(true)// 是否裁剪
                .compress(true)//是否压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回五种path
                    // 1.media.getPath(); 原图path
                    // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                    // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                    // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                    // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路径；注意：.isAndroidQTransform 为false 此字段将返回空
                    // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                    String path = "";
                    if (selectList.size() == 1) {
                        for (LocalMedia media : selectList) {
                            if (media.isCut() && !media.isCompressed()) {// 裁剪过
                                path = media.getCutPath();
                            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {// 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                                path = media.getCompressPath();
                            } else { // 原图
                                path = media.getPath();
                            }
                        }
                    }
                    LogUtils.e("path -------------------------- " + path);
                    if (StringUtils.checkString(path)){
                        GlideUtil.loadImg(path,ivSrc);
                    }
                    break;
            }
        }
    }
}
