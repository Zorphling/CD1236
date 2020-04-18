package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.SearchResultAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.SearchResultBean;
import com.business.cd1236.di.component.DaggerCategoryComponent;
import com.business.cd1236.mvp.contract.CategoryContract;
import com.business.cd1236.mvp.presenter.CategoryPresenter;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/15/2020 10:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class CategoryActivity extends MyBaseActivity<CategoryPresenter> implements CategoryContract.View, OnItemClickListener {

    public static final String SEARCH_STRING = "search_string";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ll_search)
    RelativeLayout llSearch;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private SearchResultAdapter searchResultAdapter;
    private int page = 1;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCategoryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_category; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        String stringExtra = getIntent().getStringExtra(SEARCH_STRING);

        ArmsUtils.configRecyclerView(rvContent, new GridLayoutManager(mActivity, 2));
        rvContent.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dp2px(mActivity, 10), SpaceItemDecoration.TYPE.ALL));
        searchResultAdapter = new SearchResultAdapter(R.layout.item_search_result);
        View emptyView = LayoutInflater.from(mActivity).inflate(R.layout.layout_rv_empty, null);
        ((TextView) emptyView.findViewById(R.id.tv)).setText("没有搜索结果～");
        searchResultAdapter.setEmptyView(emptyView);
        rvContent.setAdapter(searchResultAdapter);
        searchResultAdapter.setOnItemClickListener(this);

        etSearch.setText(stringExtra);
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searching(StringUtils.getEditText(etSearch));
                return true;
            }
            return false;
        });
        searching(stringExtra);
    }

    private void searching(String content) {
        mPresenter.search(content, page, mActivity);
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

    @OnClick({R.id.iv_back, R.id.iv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                killMyself();
                break;
            case R.id.iv_clear:
                etSearch.setText("");
                break;
        }
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        SearchResultBean.SearchBean searchBean = (SearchResultBean.SearchBean) adapter.getItem(position);
        Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
        intent.putExtra(GoodsDetailActivity.GOODS_ID, searchBean.id);
        launchActivity(intent);
    }

    @Override
    public void searchSucc(SearchResultBean searchResultBean) {
        searchResultAdapter.setList(searchResultBean.search, searchResultBean.jud);
    }
}
