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
import com.business.cd1236.bean.BusinessCategoryBean;
import com.business.cd1236.di.component.DaggerBusinessGoodsCategoryComponent;
import com.business.cd1236.mvp.contract.BusinessGoodsCategoryContract;
import com.business.cd1236.mvp.presenter.BusinessGoodsCategoryPresenter;
import com.business.cd1236.utils.MyToastUtils;
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
 * Created by MVPArmsTemplate on 04/23/2020 10:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessGoodsCategoryActivity extends MyBaseActivity<BusinessGoodsCategoryPresenter> implements BusinessGoodsCategoryContract.View {
    @BindView(R.id.et_category_name)
    EditText etCategoryName;
    @BindView(R.id.et_category_des)
    EditText etCategoryDes;
    @BindView(R.id.tv_category_save)
    TextView tvCategorySave;
    public static String isEdit = "isEdit";
    private BusinessCategoryBean businessCategoryBean;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessGoodsCategoryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_goods_category; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        businessCategoryBean = getIntent().getParcelableExtra(isEdit);
        setHeader(businessCategoryBean == null ? "新建分类" : "编辑分类");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);
        setRightBtn("保存", 0, v -> {
            checkSave();
        });
        setRightColor(getResources().getColor(android.R.color.black));
        if (businessCategoryBean != null) {
            etCategoryName.setText(businessCategoryBean.name);
        }
    }

    private void checkSave() {
        if (!StringUtils.checkString(StringUtils.getEditText(etCategoryName))) {
            ArmsUtils.snackbarText("请输入分类名称");
            return;
        }
        if (!StringUtils.checkString(StringUtils.getEditText(etCategoryDes))) {
            ArmsUtils.snackbarText("请输入分类描述");
            return;
        }
        if (businessCategoryBean != null && StringUtils.checkString(businessCategoryBean.id)) {
            mPresenter.categorySave(StringUtils.getEditText(etCategoryName), StringUtils.getEditText(etCategoryDes), businessCategoryBean.id, mActivity);
        } else {
            mPresenter.categorySave(StringUtils.getEditText(etCategoryName), StringUtils.getEditText(etCategoryDes), null, mActivity);
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

    @OnClick(R.id.tv_category_save)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_category_save:
                checkSave();
                break;
        }
    }

    @Override
    public void categorySaveSucc(String msg) {
        MyToastUtils.showShort(msg);
        setResult(RESULT_OK);
        killMyself();
    }
}
