package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.BusinessQualificationsAdapter;
import com.business.cd1236.adapter.SearchHistoryAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerBusinessQualificationsUploadComponent;
import com.business.cd1236.mvp.contract.BusinessQualificationsUploadContract;
import com.business.cd1236.mvp.presenter.BusinessQualificationsUploadPresenter;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.Arrays;

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
public class BusinessQualificationsUploadActivity extends MyBaseActivity<BusinessQualificationsUploadPresenter> implements BusinessQualificationsUploadContract.View {

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
        rvHints.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dp2px(mActivity,30), SpaceItemDecoration.TYPE.BOTTOM));
        SearchHistoryAdapter stringAdapter = new SearchHistoryAdapter(R.layout.item_business_qualifications_hint);
        rvHints.setAdapter(stringAdapter);

        ArmsUtils.configRecyclerView(rvQualifications,new LinearLayoutManager(mActivity));
        rvQualifications.addItemDecoration(new SpaceItemDecoration(0,SizeUtils.dp2px(mActivity,60),SpaceItemDecoration.TYPE.BOTTOM));
        BusinessQualificationsAdapter businessQualificationsAdapter = new BusinessQualificationsAdapter(R.layout.item_business_qualifications_upload);
//        businessQualificationsAdapter.setData();
        rvQualifications.setAdapter(businessQualificationsAdapter);


        switch (type) {
            case 1:
                stringAdapter.setList(Arrays.asList(hints1));
                break;
            case 2:
                stringAdapter.setList(Arrays.asList(hints2));
                break;
            case 3:
                stringAdapter.setList(Arrays.asList(hints3));
                break;
        }
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
}
