package com.business.cd1236.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.AddAddressBean;
import com.business.cd1236.bean.AddressBean;
import com.business.cd1236.di.component.DaggerAddAddressComponent;
import com.business.cd1236.mvp.contract.AddAddressContract;
import com.business.cd1236.mvp.presenter.AddAddressPresenter;
import com.business.cd1236.utils.GsonUtils;
import com.business.cd1236.utils.MyToastUtils;
import com.business.cd1236.utils.SoftKeyboardUtils;
import com.business.cd1236.utils.StringUtils;
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
 * Created by MVPArmsTemplate on 04/09/2020 11:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class AddAddressActivity extends MyBaseActivity<AddAddressPresenter> implements AddAddressContract.View {

    public static final String EDIT = "edit";
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_detail_address)
    EditText etDetailAddress;
    @BindView(R.id.ll_def_address)
    RelativeLayout llDefAddress;
    @BindView(R.id.tv_save_address)
    TextView tvSaveAddress;
    @BindView(R.id.check_box)
    CheckBox checkBox;
    private OptionsPickerView opvAddress;
    private boolean isEdit;
    private AddAddressBean bean;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAddAddressComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_add_address; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("添加地址");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        bean = (AddAddressBean) getIntent().getSerializableExtra(EDIT);
        if (bean != null) {
            isEdit = true;
            etName.setText(bean.realname);
            etNumber.setText(bean.mobile);
            etAddress.setText(bean.province + "-" + bean.city + "-" + bean.area);
            etDetailAddress.setText(bean.address);
        }

        initAddress();
    }

    private List<AddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private void initAddress() {
        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        ArrayList<AddressBean> addressBeans = GsonUtils.parseJsonArrayWithGson(GsonUtils.getJson(mActivity, "province.json"), AddressBean.class);
        for (int i = 0; i < addressBeans.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < addressBeans.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = addressBeans.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(addressBeans.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加省份数据
             */
            options1Items = addressBeans;
            /**
             * 添加城市数据
             */
            options2Items.add(cityList);
            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }
        opvAddress = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String opt1tx = options1Items.size() > 0 ?
                    options1Items.get(options1).getPickerViewText() : "";

            String opt2tx = options2Items.size() > 0
                    && options2Items.get(options1).size() > 0 ?
                    options2Items.get(options1).get(options2) : "";

            String opt3tx = options2Items.size() > 0
                    && options3Items.get(options1).size() > 0
                    && options3Items.get(options1).get(options2).size() > 0 ?
                    options3Items.get(options1).get(options2).get(options3) : "";

            String tx = opt1tx + "-" + opt2tx + "-" + opt3tx;
            etAddress.setText(tx);
        })
                .setTitleText("地区选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        opvAddress.setPicker(options1Items, options2Items, options3Items);//三级选择器
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

    @OnClick({R.id.et_address, R.id.ll_def_address, R.id.tv_save_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_address:
                if (opvAddress != null) {
                    opvAddress.show();
                }
                break;
            case R.id.ll_def_address:
                checkBox.setChecked(!checkBox.isChecked());
                break;
            case R.id.tv_save_address:
                checkSubmit();
                break;
        }
    }

    private void checkSubmit() {
        if (!StringUtils.checkString(StringUtils.getEditText(etName))) {
            ArmsUtils.snackbarText("请输入收货人姓名");
            return;
        }
        if (!StringUtils.checkString(StringUtils.getEditText(etNumber))) {
            ArmsUtils.snackbarText("请输入手机号");
            return;
        }
        if (!StringUtils.checkString(StringUtils.getEditText(etAddress))) {
            ArmsUtils.snackbarText("请选择地区");
            return;
        }
        if (!StringUtils.checkString(StringUtils.getEditText(etDetailAddress))) {
            ArmsUtils.snackbarText("请输入详细地址");
            return;
        }
        String[] address = StringUtils.getEditText(etAddress).split("-");
        if (isEdit) {
            mPresenter.editAddress(StringUtils.getEditText(etName), StringUtils.getEditText(etNumber),
                    address[0], address[1], address[2], StringUtils.getEditText(etDetailAddress), checkBox.isChecked() ? "1" : "", bean.id, mActivity);

        } else {
            mPresenter.addAddress(StringUtils.getEditText(etName), StringUtils.getEditText(etNumber),
                    address[0], address[1], address[2], StringUtils.getEditText(etDetailAddress), checkBox.isChecked() ? "1" : "", mActivity);
        }
    }

    @Override
    public void saveAddressSuccess() {
        MyToastUtils.showShort("添加成功");
        setResult(RESULT_OK);
        killMyself();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (SoftKeyboardUtils.isShouldHideInput(view, ev)) {
                InputMethodManager Object = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (Object != null) {
                    Object.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
