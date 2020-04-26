package com.business.cd1236.mvp.ui.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.business.cd1236.R;
import com.business.cd1236.adapter.BusinessCategoryAdapter;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.BusinessCategoryBean;
import com.business.cd1236.di.component.DaggerBusinessManageCategoryComponent;
import com.business.cd1236.mvp.contract.BusinessManageCategoryContract;
import com.business.cd1236.mvp.presenter.BusinessManageCategoryPresenter;
import com.business.cd1236.view.dialog.AlertDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/23/2020 16:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessManageCategoryActivity extends MyBaseActivity<BusinessManageCategoryPresenter> implements BusinessManageCategoryContract.View, OnItemChildClickListener, OnItemLongClickListener {
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.tv_add_category)
    TextView tvAddCategory;
    private BusinessCategoryAdapter businessCategoryAdapter;
    private boolean isSort;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessManageCategoryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_manage_category; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("管理分类");
        setRightColor(getResources().getColor(R.color.black));
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setRightBtn("排序", 0, v -> {
            isSort = !isSort;
            ((TextView) findViewById(R.id.tv_right)).setText(isSort ? "编辑" : "排序");
            businessCategoryAdapter.getDraggableModule().setDragEnabled(isSort);
            businessCategoryAdapter.setOnItemLongClickListener(isSort ? null : this);
            for (BusinessCategoryBean datum : businessCategoryAdapter.getData()) {
                datum.isDrag = isSort;
            }
            businessCategoryAdapter.notifyDataSetChanged();
            if (isSort) {
                businessCategoryAdapter.getDraggableModule().setOnItemDragListener(listener);
                businessCategoryAdapter.getDraggableModule().getItemTouchHelperCallback().setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);

                //mAdapter.getDraggableModule().getItemTouchHelperCallback().setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
            } else {

            }
        });
        setStatusColor(mActivity, false, true, android.R.color.white);
        ArmsUtils.configRecyclerView(rvCategory, new LinearLayoutManager(mActivity));
        businessCategoryAdapter = new BusinessCategoryAdapter(R.layout.item_business_manage_category);
        businessCategoryAdapter.addChildClickViewIds(R.id.tv_edit);
        businessCategoryAdapter.setOnItemChildClickListener(this);
        businessCategoryAdapter.setOnItemLongClickListener(this);
        rvCategory.setAdapter(businessCategoryAdapter);
    }

    // 拖拽监听
    OnItemDragListener listener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
            final BaseViewHolder holder = ((BaseViewHolder) viewHolder);

            // 开始时，item背景色变化，demo这里使用了一个动画渐变，使得自然
            int startColor = Color.WHITE;
            int endColor = Color.rgb(245, 245, 245);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ValueAnimator v = ValueAnimator.ofArgb(startColor, endColor);
                v.addUpdateListener(animation -> holder.itemView.setBackgroundColor((int) animation.getAnimatedValue()));
                v.setDuration(300);
                v.start();
            }
        }

        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
//            Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
        }

        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
            final BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            // 结束时，item背景色变化，demo这里使用了一个动画渐变，使得自然
            int startColor = Color.rgb(245, 245, 245);
            int endColor = Color.WHITE;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ValueAnimator v = ValueAnimator.ofArgb(startColor, endColor);
                v.addUpdateListener(animation -> holder.itemView.setBackgroundColor((int) animation.getAnimatedValue()));
                v.setDuration(300);
                v.start();
            }
            sort();
        }
    };

    private void sort() {
        StringBuilder builder = new StringBuilder();
        List<BusinessCategoryBean> data = businessCategoryAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            builder.append(data.get(i).id).append(",").append(i).append(";");
        }
        mPresenter.categorySort("category",builder.substring(0,builder.length() - 1),mActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.businessCategoty(mActivity);
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

    @OnClick(R.id.tv_add_category)
    public void onViewClicked() {
        launchActivity(new Intent(mActivity, BusinessGoodsCategoryActivity.class));
    }

    @Override
    public void businessCategotySucc(ArrayList<BusinessCategoryBean> businessCategoryBeans) {
        isSort = false;
        ((TextView) findViewById(R.id.tv_right)).setText("排序");
        businessCategoryAdapter.setList(businessCategoryBeans);
    }

    @Override
    public void categotyDeleteSucc(String msg) {
        businessCategoryAdapter.remove(businessCategoryBean);
    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        Intent intent = new Intent(mActivity, BusinessGoodsCategoryActivity.class);
        intent.putExtra(BusinessGoodsCategoryActivity.isEdit, (BusinessCategoryBean) adapter.getItem(position));
        launchActivity(intent);
    }

    BusinessCategoryBean businessCategoryBean;

    @Override
    public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        new AlertDialog(mActivity).builder().setMsg("是否删除该分类").setNegativeButton("取消", null).setPositiveButton("确定", v -> {
            businessCategoryBean = (BusinessCategoryBean) adapter.getItem(position);
            mPresenter.businessCategotyDelete(businessCategoryBean.id, mActivity);
        }).show();
        return true;
    }
}
