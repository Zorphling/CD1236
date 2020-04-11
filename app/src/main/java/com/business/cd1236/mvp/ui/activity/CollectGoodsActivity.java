package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.CollectGoodsAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.CollectGoodsBean;
import com.business.cd1236.di.component.DaggerCollectGoodsComponent;
import com.business.cd1236.mvp.contract.CollectGoodsContract;
import com.business.cd1236.mvp.presenter.CollectGoodsPresenter;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.LogUtils;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.utils.StringUtils;
import com.business.cd1236.view.SpaceItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/10/2020 14:51
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class CollectGoodsActivity extends MyBaseActivity<CollectGoodsPresenter> implements CollectGoodsContract.View, CollectGoodsAdapter.OnCheckedChangeListener, OnItemChildClickListener {

    @BindView(R.id.tv_all_type)
    TextView tvAllType;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_grid)
    ImageView ivGrid;
    @BindView(R.id.rv_collect)
    RecyclerView rvCollect;
    @BindView(R.id.check_box_bottom)
    CheckBox checkBoxBottom;
    @BindView(R.id.ll_all_select)
    LinearLayout llAllSelect;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private CollectGoodsAdapter adapter;

    private boolean isGrid;
    private boolean isManage;
    private boolean isAllSelect;
    private int dp;
    private SpaceItemDecoration spaceItemDecorationLeft;
    private SpaceItemDecoration spaceItemDecorationTop;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCollectGoodsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_collect_goods; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("我的收藏");
        setRightBtn("管理", 0, v -> {
            ((TextView) findViewById(R.id.tv_right)).setText((isManage = !isManage) ? "完成" : "管理");
            setIsManage();
        });

        setRightColor(getResources().getColor(R.color.text_default));
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        dp = SizeUtils.dp2px(mActivity, 10);
        spaceItemDecorationLeft = new SpaceItemDecoration(0, dp, SpaceItemDecoration.TYPE.LEFT);
        spaceItemDecorationTop = new SpaceItemDecoration(0, dp, SpaceItemDecoration.TYPE.TOP);

        initRecyclerView();

        mPresenter.queryCollectGoods(mActivity);
    }

    private void initRecyclerView() {
        ArmsUtils.configRecyclerView(rvCollect, new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        rvCollect.addItemDecoration(spaceItemDecorationTop);
        adapter = new CollectGoodsAdapter(R.layout.item_collect_goods_list, isGrid, this);
        rvCollect.setAdapter(adapter);
        adapter.addChildClickViewIds(R.id.ll_list);
        adapter.setOnItemChildClickListener(this);
    }

    private void setIsManage() {
        if (isManage) {
            cleanCheck();
            rlBottom.setVisibility(View.VISIBLE);
            for (CollectGoodsBean.NewBean datum : adapter.getData()) {
                datum.showCheckBox = true;
            }
            adapter.notifyDataSetChanged();
        } else {
            cleanCheck();
            rlBottom.setVisibility(View.GONE);
            for (CollectGoodsBean.NewBean datum : adapter.getData()) {
                datum.showCheckBox = false;
            }
            adapter.notifyDataSetChanged();
        }
    }

    private void cleanCheck() {
        checkBoxBottom.setChecked(false);
        for (CollectGoodsBean.NewBean newBean : adapter.getData()) {
            newBean.isCheck = false;
        }
        adapter.notifyDataSetChanged();
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

    @OnClick({R.id.tv_all_type, R.id.iv_search, R.id.iv_grid, R.id.ll_all_select, R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all_type:

                break;
            case R.id.iv_search:
                launchActivity(new Intent(mActivity, SearchActivity.class));
                break;
            case R.id.iv_grid:
                changeList(isGrid = !isGrid);
                break;
            case R.id.ll_all_select:
                isAllSelect = !isAllSelect;
                for (CollectGoodsBean.NewBean datum : adapter.getData()) {
                    datum.isCheck = isAllSelect;
                }
                checkBoxBottom.setChecked(isAllSelect);
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_delete:
                boolean haveCheck = false;
                StringBuilder builder = new StringBuilder();
                for (CollectGoodsBean.NewBean datum : adapter.getData()) {
                    if (datum.isCheck) {
                        builder.append(datum.id).append(",");
                        haveCheck = true;
                    }
                }
                if (haveCheck && StringUtils.checkString(builder.toString())) {
                    mPresenter.deleteCollect(builder.toString().substring(0, builder.toString().length() - 1), mActivity);
                } else {
                    ArmsUtils.snackbarText("请先选择商品");
                }
                break;
        }
    }

    private void changeList(boolean isGrid) {
        if (adapter != null && adapter.getData().size() > 0) {
            ArrayList<CollectGoodsBean.NewBean> list = (ArrayList<CollectGoodsBean.NewBean>) adapter.getData();
            if (isGrid) {
                ArmsUtils.configRecyclerView(rvCollect, new GridLayoutManager(mActivity, 3));
                rvCollect.removeItemDecoration(spaceItemDecorationLeft);
                rvCollect.removeItemDecoration(spaceItemDecorationTop);
                rvCollect.addItemDecoration(spaceItemDecorationLeft);
                adapter = new CollectGoodsAdapter(R.layout.item_collect_goods_grid, isGrid, this);
                adapter.addChildClickViewIds(R.id.rl_grid);
                adapter.setOnItemChildClickListener(this);
                GlideUtil.loadImg(R.mipmap.icon_collect_grid, ivGrid);
            } else {
                rvCollect.removeItemDecoration(spaceItemDecorationLeft);
                rvCollect.removeItemDecoration(spaceItemDecorationTop);
                initRecyclerView();
                GlideUtil.loadImg(R.mipmap.icon_collect_list, ivGrid);
            }
            rvCollect.setAdapter(adapter);
            adapter.setList(list);
        }
    }

    @Override
    public void getCollectSucc(CollectGoodsBean collectGoodsBean) {
        if (collectGoodsBean != null && collectGoodsBean.newX != null && collectGoodsBean.newX.size() > 0) {
            adapter.setNewInstance(collectGoodsBean.newX, collectGoodsBean.jud);
        } else {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_rv_empty, null);
            ((TextView) view.findViewById(R.id.tv)).setText("快去收藏喜欢的商品吧～");
            adapter.setEmptyView(view);
        }
    }

    @Override
    public void deleteSucc() {
        for (CollectGoodsBean.NewBean datum : adapter.getData()) {
            if (datum.isCheck)
                adapter.remove(datum);
        }
    }

    @Override
    public void onCheckedChange(int position) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        switch (view.getId()) {
            case R.id.ll_list:
            case R.id.rl_grid:
                onItemClick((CollectGoodsBean.NewBean) adapter.getItem(position), position);
                break;
        }
    }

    private void onItemClick(CollectGoodsBean.NewBean newBean, int position) {
        if (isManage) {
            newBean.isCheck = !newBean.isCheck;
            adapter.notifyItemChanged(position);
            isAllSelect();
        } else {
            Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
            intent.putExtra(GoodsDetailActivity.GOODS_ID, newBean.id);
            launchActivity(intent);
        }
    }

    private void isAllSelect() {
        for (CollectGoodsBean.NewBean datum : adapter.getData()) {
            if (!datum.isCheck){
                checkBoxBottom.setChecked(false);
                return;
            }
            else checkBoxBottom.setChecked(true);
        }
    }
}
