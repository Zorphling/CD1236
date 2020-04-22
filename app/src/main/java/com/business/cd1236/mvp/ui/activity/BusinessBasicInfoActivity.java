package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BusinessInfoBean;
import com.business.cd1236.di.component.DaggerBusinessBasicInfoComponent;
import com.business.cd1236.mvp.contract.BusinessBasicInfoContract;
import com.business.cd1236.mvp.presenter.BusinessBasicInfoPresenter;
import com.business.cd1236.utils.GlideEngine;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.ItemView;
import com.business.cd1236.view.dialog.SetHeaderDialog;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/16/2020 10:37
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessBasicInfoActivity extends MyBaseActivity<BusinessBasicInfoPresenter> implements BusinessBasicInfoContract.View, SetHeaderDialog.SelectPicListener {

    @BindView(R.id.item1)
    ItemView item1;
    @BindView(R.id.item2)
    ItemView item2;
    @BindView(R.id.item3)
    ItemView item3;
    @BindView(R.id.item4)
    ItemView item4;
    @BindView(R.id.item5)
    ItemView item5;
    @BindView(R.id.item6)
    ItemView item6;
    @BindView(R.id.item7)
    ItemView item7;
    private BusinessInfoBean businessInfoBean;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessBasicInfoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_basic_info; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("基本信息");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        businessInfoBean = getIntent().getParcelableExtra(BusinessInfoActivity.INFO);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getBusinessInfo(mActivity);
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

    @OnClick({R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.item1:
                ShowDialog();
                break;
            case R.id.item2:
                intent.setClass(mActivity, BusinessTitleActivity.class);
                launchActivity(intent);
                break;
            case R.id.item3:
                break;
            case R.id.item4:
                break;
            case R.id.item5:
                intent.setClass(mActivity, BusinessTelephoneActivity.class);
                launchActivity(intent);
                break;
            case R.id.item6:
                break;
            case R.id.item7:
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
                            uploadImg(media, path);
                        }
                    }
                    break;
            }
        }
    }

    private void uploadImg(LocalMedia media, String path) {
        try {
            if (StringUtils.checkString(path)) {
                item1.setImageView(path);
                File file = new File(path);
                RequestBody requestBody = RequestBody.create(MediaType.parse(media.getMimeType()), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", URLEncoder.encode(file.getName(), "UTF-8"), requestBody);
                mPresenter.uploadImg(part, mActivity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String path;
    @Override
    public void uploadImgSucc(String path) {
        this.path = path;
        mPresenter.updateImg("logo", path, mActivity);
    }

    @Override
    public void updateLogoSucc(String logo, String jsonString) {
        item1.setImageView(logo);
    }

    @Override
    public void setBusinessInfo(BusinessInfoBean businessInfoBean) {
        if (businessInfoBean != null) {
            if (StringUtils.checkString(businessInfoBean.business_name))
                item2.setSubTitleText(businessInfoBean.business_name);
            if (StringUtils.checkString(businessInfoBean.id))
                item3.setSubTitleText(businessInfoBean.id);
            if (StringUtils.checkString(businessInfoBean.address))
                item4.setSubTitleText(businessInfoBean.address);
            if (businessInfoBean.telephone != null) {
                StringBuilder builder = new StringBuilder();
                for (String s : businessInfoBean.telephone) {
                    builder.append(s).append(",");
                }
                item5.setSubTitleText(builder.substring(0, builder.length() - 1));
            }
        }
        item1.setImageView(businessInfoBean.logo);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
