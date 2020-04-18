package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.BaseHeaderAdapter;
import com.business.cd1236.adapter.BrowseRecordAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BrowseRecordBean;
import com.business.cd1236.bean.CollectGoodsBean;
import com.business.cd1236.bean.PinnedHeaderEntity;
import com.business.cd1236.di.component.DaggerBrowseRecordComponent;
import com.business.cd1236.mvp.contract.BrowseRecordContract;
import com.business.cd1236.mvp.presenter.BrowseRecordPresenter;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.utils.TimeUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/11/2020 17:58
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BrowseRecordActivity extends MyBaseActivity<BrowseRecordPresenter> implements BrowseRecordContract.View {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    BaseHeaderAdapter adapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBrowseRecordComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_browse_record; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("浏览中心");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        rvContent.setLayoutManager(new GridLayoutManager(mActivity, 3));
        rvContent.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dp2px(mActivity, 10), SpaceItemDecoration.TYPE.GRID));

        mPresenter.queryBrowse(mActivity);
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
    public void queryBrowseSucc(BrowseRecordBean browseRecordBean) {
        Map<String, List<CollectGoodsBean.NewBean>> mapData = browseRecordBean.data;
        List<PinnedHeaderEntity<CollectGoodsBean.NewBean>> data = new ArrayList<>();
        for (String key : mapData.keySet()) {
            String time = TimeUtils.millis2String(Long.parseLong(key) * 1000, "yyyy-MM-dd");
            data.add(new PinnedHeaderEntity(null, BaseHeaderAdapter.TYPE_HEADER, time));
            List<CollectGoodsBean.NewBean> newBeans = mapData.get(key);
            for (CollectGoodsBean.NewBean newBean : newBeans) {
                data.add(new PinnedHeaderEntity<>(newBean, BaseHeaderAdapter.TYPE_DATA, time));
            }
        }
        adapter = new BrowseRecordAdapter(data, browseRecordBean.jud);
        adapter.onAttachedToRecyclerView(rvContent);
        rvContent.setAdapter(adapter);
        //TODO 这个界面还未完善
    }
}
