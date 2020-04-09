package com.business.cd1236.adapter;

import com.business.cd1236.R;
import com.business.cd1236.bean.AddAddressBean;
import com.business.cd1236.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class AddressManageAdapter extends BaseQuickAdapter<AddAddressBean, BaseViewHolder> {
    public AddressManageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AddAddressBean addAddressBean) {
        baseViewHolder.setText(R.id.tv_name, addAddressBean.realname)
                .setText(R.id.tv_number, addAddressBean.mobile)
                .setText(R.id.tv_address, addAddressBean.province + addAddressBean.city + addAddressBean.area + addAddressBean.address);

//        addChildClickViewIds(R.id.ll_set_def_address, R.id.tv_delete_address, R.id.tv_edit_address);
        baseViewHolder.setImageResource(R.id.iv_address_def, StringUtils.equals("0", addAddressBean.defaultX) ? R.mipmap.icon_address_def : R.mipmap.icon_address_check);
    }
}
