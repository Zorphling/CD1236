package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.BusinessQualificationsAdapter;
import com.business.cd1236.adapter.SearchHistoryAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BusinessQualificationsBean;
import com.business.cd1236.di.component.DaggerBusinessQualificationsUploadComponent;
import com.business.cd1236.mvp.contract.BusinessQualificationsUploadContract;
import com.business.cd1236.mvp.presenter.BusinessQualificationsUploadPresenter;
import com.business.cd1236.utils.GlideEngine;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.business.cd1236.view.dialog.SetHeaderDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

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
 * Created by MVPArmsTemplate on 04/18/2020 14:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessQualificationsUploadActivity extends MyBaseActivity<BusinessQualificationsUploadPresenter> implements BusinessQualificationsUploadContract.View, OnItemChildClickListener, SetHeaderDialog.SelectPicListener {

    @BindView(R.id.rv_hints)
    RecyclerView rvHints;
    @BindView(R.id.rv_qualifications)
    RecyclerView rvQualifications;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    public static String TYPE = "type";
    public static int TYPE_1 = 1;//资质
    public static int TYPE_2 = 2;//人像
    public static int TYPE_3 = 3;//其他证
    private int type;
//    private String[] hints1 = new String[]{getResources().getString(R.string.business_qualifications_hint_1), getResources().getString(R.string.business_qualifications_hint_2)};
//    private String[] hints2 = new String[]{getResources().getString(R.string.business_qualifications_hint_3), getResources().getString(R.string.business_qualifications_hint_4)};
//    private String[] hints3 = new String[]{getResources().getString(R.string.business_qualifications_hint_5), getResources().getString(R.string.business_qualifications_hint_6)
//            , getResources().getString(R.string.business_qualifications_hint_7), getResources().getString(R.string.business_qualifications_hint_8), getResources().getString(R.string.business_qualifications_hint_9)};

    private int[] subTitles1 = new int[]{R.string.business_qualifications_1, R.string.business_qualifications_2};
    private int[] subTitles2 = new int[]{R.string.business_qualifications_3, R.string.business_qualifications_4};
    private int[] subTitles3 = new int[]{R.string.business_qualifications_5, R.string.business_qualifications_6,
            R.string.business_qualifications_7, R.string.business_qualifications_8, R.string.business_qualifications_9};

    private ArrayList<BusinessQualificationsBean> beans = new ArrayList<>();
    private BusinessQualificationsAdapter businessQualificationsAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessQualificationsUploadComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_qualifications_upload; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("营业资质");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);
        String[] hints1 = new String[]{getResources().getString(R.string.business_qualifications_hint_1), getResources().getString(R.string.business_qualifications_hint_2)};
        String[] hints2 = new String[]{getResources().getString(R.string.business_qualifications_hint_3), getResources().getString(R.string.business_qualifications_hint_4)};
        String[] hints3 = new String[]{getResources().getString(R.string.business_qualifications_hint_5), getResources().getString(R.string.business_qualifications_hint_6)
                , getResources().getString(R.string.business_qualifications_hint_7), getResources().getString(R.string.business_qualifications_hint_8), getResources().getString(R.string.business_qualifications_hint_9)};


        type = getIntent().getIntExtra(TYPE, 0);

        ArmsUtils.configRecyclerView(rvHints, new LinearLayoutManager(mActivity));
        rvHints.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dp2px(mActivity, 30), SpaceItemDecoration.TYPE.BOTTOM));
        SearchHistoryAdapter stringAdapter = new SearchHistoryAdapter(R.layout.item_business_qualifications_hint);//要改重新写Adapter，这个别动
        rvHints.setAdapter(stringAdapter);

        ArmsUtils.configRecyclerView(rvQualifications, new LinearLayoutManager(mActivity));
        rvQualifications.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dp2px(mActivity, 60), SpaceItemDecoration.TYPE.BOTTOM));
        businessQualificationsAdapter = new BusinessQualificationsAdapter(R.layout.item_business_qualifications_upload);
        businessQualificationsAdapter.addChildClickViewIds(R.id.iv_qualifications);
        businessQualificationsAdapter.setOnItemChildClickListener(this);
        rvQualifications.setAdapter(businessQualificationsAdapter);

        switch (type) {
            case 1:
                stringAdapter.setList(Arrays.asList(hints1));
                addData(subTitles1);
                break;
            case 2:
                stringAdapter.setList(Arrays.asList(hints2));
                addData(subTitles2);
                break;
            case 3:
                stringAdapter.setList(Arrays.asList(hints3));
                addData(subTitles3);
                break;
        }
    }

    private void addData(int[] subTitles) {
        for (int i : subTitles) {
            BusinessQualificationsBean businessQualificationsBean = new BusinessQualificationsBean();
            businessQualificationsBean.subTitle = getResources().getString(i);
            beans.add(businessQualificationsBean);
        }
        businessQualificationsAdapter.setList(beans);
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

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {

    }

    private ImageView ivQualifications;

    ImageView imageView;

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        imageView = (ImageView) view;
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
                    if (StringUtils.checkString(path)) {
                        GlideUtil.loadImg(path, imageView);
                    }
                    break;
            }
        }
    }
}
