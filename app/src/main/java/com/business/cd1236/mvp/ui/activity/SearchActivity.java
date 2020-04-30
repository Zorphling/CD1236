package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.SearchHistoryAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.SearchHistoryBean;
import com.business.cd1236.di.component.DaggerSearchComponent;
import com.business.cd1236.greendao.GreenDaoUtils;
import com.business.cd1236.greendao.db.DaoSession;
import com.business.cd1236.mvp.contract.SearchContract;
import com.business.cd1236.mvp.presenter.SearchPresenter;
import com.business.cd1236.utils.StringUtils;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/11/2020 10:38
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class SearchActivity extends MyBaseActivity<SearchPresenter> implements SearchContract.View {
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rv_hot_search)
    RecyclerView rvHotSearch;
    @BindView(R.id.rv_search_history)
    RecyclerView rvSearchHistory;
    private String[] hotSearch = new String[]{"盐", "中盐", "山东盐业", "天然", "深井盐", "海盐", "海天", "航天", "湘盐", "粤盐", "淮盐", "川盐", "奉盐"};
    private SearchHistoryAdapter searchHistoryAdapter;
    private DaoSession daoSession;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSearchComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_search; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        daoSession = GreenDaoUtils.getInstance().getDaoSession();

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(mActivity, FlexDirection.ROW, FlexWrap.WRAP);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        flexboxLayoutManager.setAlignItems(AlignItems.FLEX_START);

        ArmsUtils.configRecyclerView(rvHotSearch, flexboxLayoutManager);
        FlexboxItemDecoration flexboxItemDecoration = new FlexboxItemDecoration(mActivity);
        flexboxItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divide_flexbox));
        rvHotSearch.addItemDecoration(flexboxItemDecoration);

        SearchHistoryAdapter hotSearchAdapter = new SearchHistoryAdapter(R.layout.item_text_view_bg_gray);
        rvHotSearch.setAdapter(hotSearchAdapter);
        hotSearchAdapter.setList(Arrays.asList(hotSearch));
        hotSearchAdapter.addChildClickViewIds(R.id.tv_text);
        hotSearchAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Intent intent = new Intent(mActivity,CategoryActivity.class);
            intent.putExtra(CategoryActivity.SEARCH_STRING,((TextView) view).getText().toString());
            launchActivity(intent);
        });


        ArmsUtils.configRecyclerView(rvSearchHistory, new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        searchHistoryAdapter = new SearchHistoryAdapter(R.layout.item_search_history);
        searchHistoryAdapter.addChildClickViewIds(R.id.iv_delete);
        searchHistoryAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            daoSession.delete(adapter.getItem(position));
            adapter.remove(position);
        });
        searchHistoryAdapter.setOnItemClickListener((adapter, view, position) -> {
            startSearching(((SearchHistoryBean) adapter.getData().get(position)).getText());
        });
        rvSearchHistory.setAdapter(searchHistoryAdapter);


        editSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searching(StringUtils.getEditText(editSearch));
                return true;
            }
            return false;
        });
    }

    List<SearchHistoryBean> list;

    @Override
    protected void onResume() {
        super.onResume();
        editSearch.setText("");

        list = daoSession.getSearchHistoryBeanDao().loadAll();
        Collections.reverse(list);
        searchHistoryAdapter.setList(list);
    }

    private void searching(String searchString) {
        DaoSession daoSession = GreenDaoUtils.getInstance().getDaoSession();
        daoSession.getSearchHistoryBeanDao().insert(new SearchHistoryBean(searchString));
        startSearching(searchString);
    }

    private void startSearching(String searchString) {
        Intent intent = new Intent(mActivity, CategoryActivity.class);
        intent.putExtra(CategoryActivity.SEARCH_STRING, searchString);
        launchActivity(intent);
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

    @OnClick({R.id.tv_cancel, R.id.tv_clear_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                killMyself();
                break;
            case R.id.tv_clear_search:
                daoSession.deleteAll(SearchHistoryBean.class);
                searchHistoryAdapter.setList(null);
                break;
        }
    }
}
