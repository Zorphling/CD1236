package com.business.cd1236.adapter;

import android.content.Intent;

import com.business.cd1236.R;
import com.business.cd1236.bean.CollectGoodsBean;
import com.business.cd1236.bean.PinnedHeaderEntity;
import com.business.cd1236.mvp.ui.activity.GoodsDetailActivity;
import com.business.cd1236.utils.GlideUtil;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BrowseRecordAdapter extends BaseHeaderAdapter<PinnedHeaderEntity<CollectGoodsBean.NewBean>, BaseViewHolder> {

    private String jud;

    public BrowseRecordAdapter(List<PinnedHeaderEntity<CollectGoodsBean.NewBean>> data, String jud) {
        super(data);
        this.jud = jud;
    }

    @Override
    protected void addItemTypes() {
        addItemType(BaseHeaderAdapter.TYPE_HEADER, R.layout.item_browse_record_header);
        addItemType(BaseHeaderAdapter.TYPE_DATA, R.layout.item_browse_record_goods);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, PinnedHeaderEntity<CollectGoodsBean.NewBean> item) {
        switch (baseViewHolder.getItemViewType()) {
            case BaseHeaderAdapter.TYPE_HEADER:
                baseViewHolder.setText(R.id.tv_header, item.getPinnedHeaderName());
                break;
            case BaseHeaderAdapter.TYPE_DATA:
                baseViewHolder.setText(R.id.tv_goods_price,
                        getContext().getResources().getString(R.string.rmb) + " " +
                                (StringUtils.equals("0", jud) ? item.getData().marketprice : item.getData().agent_marketprice));
                GlideUtil.loadImg(item.getData().thumb, R.mipmap.logo, baseViewHolder.getView(R.id.iv_goods));
                baseViewHolder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                    intent.putExtra(GoodsDetailActivity.GOODS_ID, item.getData().id);
                    getContext().startActivity(intent);
                });
                break;
        }
    }
}