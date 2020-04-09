package com.business.cd1236.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.bean.AddressBean;
import com.business.cd1236.bean.EnterTypeBean;
import com.business.cd1236.di.component.DaggerBusinessEnterComponent;
import com.business.cd1236.mvp.contract.BusinessEnterContract;
import com.business.cd1236.mvp.presenter.BusinessEnterPresenter;
import com.business.cd1236.utils.GsonUtils;
import com.business.cd1236.utils.SoftKeyboardUtils;
import com.business.cd1236.utils.SpannableStringUtils;
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
 * Created by MVPArmsTemplate on 04/08/2020 09:31
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessEnterActivity extends MyBaseActivity<BusinessEnterPresenter> implements BusinessEnterContract.View, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.et_business_name)
    EditText etBusinessName;
    @BindView(R.id.et_real_name)
    EditText etRealName;
    @BindView(R.id.et_contacts_num)
    EditText etContactsNum;
    @BindView(R.id.et_enter_type)
    EditText etEnterType;
    @BindView(R.id.et_enter_time)
    EditText etEnterTime;
    @BindView(R.id.et_manage_address)
    EditText etManageAddress;
    @BindView(R.id.et_detail_address)
    EditText etDetailAddress;
    @BindView(R.id.et_clerk)
    EditText etClerk;
    @BindView(R.id.check_box)
    CheckBox checkBox;
    @BindView(R.id.tv_enter_agreement)
    TextView tvEnterAgreement;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private OptionsPickerView opvType, opvTime, opvAddress;
    private List<EnterTypeBean.ChooseBean> chooseBeans = new ArrayList<>();
    private List<EnterTypeBean.NameBean> nameBeans = new ArrayList<>();


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessEnterComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_enter; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("入驻信息");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        SpannableStringUtils.textColorAndClickable(tvEnterAgreement, getString(R.string.enter_agreement), 7, getString(R.string.enter_agreement).length()
                , "#" + Integer.toHexString(R.color.colorPrimary), new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        Intent intent = new Intent(mActivity, HtmlActivity.class);
                        intent.putExtra(HtmlActivity.AGREEMENT_TYPE, HtmlActivity.OPERATION_STEPS);
                        launchActivity(intent);
                    }
                });
        checkBox.setOnCheckedChangeListener(this);

        mPresenter.getEnterType(mActivity);
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
    public void setEnterType(EnterTypeBean enterTypeBean) {
        nameBeans = enterTypeBean.name;
        chooseBeans = enterTypeBean.choose;
        initDialog();

        if (enterTypeBean.member != null && StringUtils.checkString(enterTypeBean.member.business_name)) {
            etBusinessName.setText(enterTypeBean.member.business_name);
            etRealName.setText(enterTypeBean.member.real_name);
            etContactsNum.setText(enterTypeBean.member.telephone);
            for (EnterTypeBean.NameBean nameBean : nameBeans) {
                if (StringUtils.equals(nameBean.id, enterTypeBean.member.type))
                    etEnterType.setText(nameBean.name);
            }
            for (EnterTypeBean.ChooseBean chooseBean : chooseBeans) {
                if (StringUtils.equals(chooseBean.id, enterTypeBean.member.type))
                    etEnterTime.setText(chooseBean.type);
            }
            etManageAddress.setText(enterTypeBean.member.province + "-" + enterTypeBean.member.city + "-" + enterTypeBean.member.district);
            etDetailAddress.setText(enterTypeBean.member.address);
            if (!StringUtils.equals("0", enterTypeBean.member.pid))
                etClerk.setText(enterTypeBean.member.pid);
            checkBox.setChecked(true);
            btnSubmit.setText("资料审核中");
            btnSubmit.setEnabled(false);
        }
    }

    @Override
    public void setEnterSuccess(String jsonString) {
        ArmsUtils.snackbarText("入驻成功");
        setResult(RESULT_OK);
        killMyself();
    }

    private List<AddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private String ENTER_TIME;//入驻时长
    private String ENTER_TYPE;//入驻类型

    private void initDialog() {
        opvType = new OptionsPickerBuilder(this, (options1, option2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String tx = nameBeans.get(options1).getPickerViewText();
            ENTER_TIME = nameBeans.get(options1).id;
            etEnterType.setText(tx);
        }).setLayoutRes(R.layout.dialog_business_enter, v -> {
            final TextView tvConfirm = v.findViewById(R.id.tv_confirm);
            TextView tvTitle = v.findViewById(R.id.tv_dialog_title);
            tvTitle.setText("请选择入驻类型");
            TextView tvCancel = v.findViewById(R.id.tv_cancel);
            tvConfirm.setOnClickListener(v1 -> {
                opvType.returnData();
                opvType.dismiss();
            });
            tvCancel.setOnClickListener(v12 -> opvType.dismiss());
        }).build();
        opvType.setPicker(nameBeans);//添加数据

        opvTime = new OptionsPickerBuilder(this, (options1, option2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String tx = chooseBeans.get(options1).getPickerViewText();
            ENTER_TYPE = chooseBeans.get(options1).id;
            etEnterTime.setText(tx);
        }).setLayoutRes(R.layout.dialog_business_enter, v -> {
            final TextView tvConfirm = v.findViewById(R.id.tv_confirm);
            TextView tvTitle = v.findViewById(R.id.tv_dialog_title);
            tvTitle.setText("请选择入驻时长");
            TextView tvCancel = v.findViewById(R.id.tv_cancel);
            tvConfirm.setOnClickListener(v1 -> {
                opvTime.returnData();
                opvTime.dismiss();
            });
            tvCancel.setOnClickListener(v12 -> opvTime.dismiss());
        })
                .setContentTextSize(18)
                .build();
        opvTime.setPicker(chooseBeans);//添加数据

        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        ArrayList<AddressBean> addressBeans = GsonUtils.parseJsonArrayWithGson(GsonUtils.getJson(mActivity,"province.json"), AddressBean.class);
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
            etManageAddress.setText(tx);
        })
                .setTitleText("地区选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        opvAddress.setPicker(options1Items, options2Items, options3Items);//三级选择器
    }

    @OnClick({R.id.et_enter_type, R.id.et_enter_time, R.id.et_manage_address, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_enter_type:
                if (opvType != null) {
                    opvType.show();
                }
                break;
            case R.id.et_enter_time:
                if (opvTime != null) {
                    opvTime.show();
                }
                break;
            case R.id.et_manage_address:
                if (opvAddress != null) {
                    opvAddress.show();
                }
                break;
            case R.id.btn_submit:
                checkSubmit();
                break;
        }
    }

    private void checkSubmit() {
        if (!StringUtils.checkString(StringUtils.getEditText(etBusinessName))) {
            ArmsUtils.snackbarText("请输入店铺/公司名称");
            return;
        }
        if (!StringUtils.checkString(StringUtils.getEditText(etRealName))) {
            ArmsUtils.snackbarText("请输入名称");
            return;
        }
        if (!StringUtils.checkString(StringUtils.getEditText(etContactsNum))) {
            ArmsUtils.snackbarText("请输入手机号");
            return;
        }
        if (!StringUtils.checkString(StringUtils.getEditText(etEnterType))) {
            ArmsUtils.snackbarText("请选择入驻类型");
            return;
        }
        if (!StringUtils.checkString(StringUtils.getEditText(etEnterTime))) {
            ArmsUtils.snackbarText("请选择入驻时长");
            return;
        }
        if (!StringUtils.checkString(StringUtils.getEditText(etManageAddress))) {
            ArmsUtils.snackbarText("请选择经营地址");
            return;
        }
        if (!StringUtils.checkString(StringUtils.getEditText(etDetailAddress))) {
            ArmsUtils.snackbarText("请输入详细地址");
            return;
        }
        if (!checkBox.isChecked()) {
            ArmsUtils.snackbarText("请同意<<入驻协议>>");
            return;
        }
        String[] address = StringUtils.getEditText(etManageAddress).split("-");
        mPresenter.submit(ENTER_TIME, StringUtils.getEditText(etBusinessName), StringUtils.getEditText(etRealName), StringUtils.getEditText(etContactsNum)
                , ENTER_TYPE, address[0], address[1], address[2], StringUtils.getEditText(etDetailAddress), StringUtils.getEditText(etClerk), mActivity);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

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
