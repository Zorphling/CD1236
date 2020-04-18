package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerBusinessTimeComponent;
import com.business.cd1236.mvp.contract.BusinessTimeContract;
import com.business.cd1236.mvp.presenter.BusinessTimePresenter;
import com.business.cd1236.utils.MyToastUtils;
import com.business.cd1236.utils.TimeUtils;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.suke.widget.SwitchButton;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/16/2020 11:15
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class BusinessTimeActivity extends MyBaseActivity<BusinessTimePresenter> implements BusinessTimeContract.View, SwitchButton.OnCheckedChangeListener {

    @BindView(R.id.btn_switch)
    SwitchButton btnSwitch;
    @BindView(R.id.tv_open_time)
    TextView tvOpenTime;
    @BindView(R.id.tv_close_time)
    TextView tvCloseTime;
    @BindView(R.id.tv_open_time1)
    TextView tvOpenTime1;
    @BindView(R.id.tv_close_time1)
    TextView tvCloseTime1;
    @BindView(R.id.tv_save_time)
    TextView tvSaveTime;
    private TimePickerView timePickerView1, timePickerView2, timePickerView3, timePickerView4;
    private Calendar selectedDate;
    private Calendar startDate;
    private Calendar endDate;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBusinessTimeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business_time; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("营业时间");
        setHeaderColor(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.black), R.mipmap.arrow_left_black);
        setStatusColor(mActivity, false, true, android.R.color.white);

        btnSwitch.setOnCheckedChangeListener(this);
        initTimePicker();
    }

    private int startHourOfDay, endHourOfDay;

    private void initTimePicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(0, 0, 0, 0, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(0, 0, 0, 0, 0);
        //时间选择器 ，自定义布局
        timePickerView1 = new TimePickerBuilder(this, (date, v) -> {//选中事件回调
            String time = TimeUtils.date2String(date, "HH:mm");
            ((TextView) mActivity.findViewById(timeId)).setText(time);
        })
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_business_time, (CustomListener) v -> {
                    final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                    ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                    tvSubmit.setOnClickListener(v1 -> {
                        timePickerView1.returnData();
                        timePickerView1.dismiss();
                    });
                    ivCancel.setOnClickListener(v12 -> timePickerView1.dismiss());
                })
                .setDividerColor(getResources().getColor(R.color.colorPrimary))
                .setContentTextSize(18)
                .setType(new boolean[]{false, false, false, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.9f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
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

    int timeId = R.id.tv_open_time;

    @OnClick({R.id.tv_open_time, R.id.tv_close_time, R.id.tv_open_time1, R.id.tv_close_time1, R.id.tv_save_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_open_time:
                timeId = R.id.tv_open_time;
                if (timePickerView1 != null) timePickerView1.show();
                break;
            case R.id.tv_close_time:
                timeId = R.id.tv_close_time;
                if (timePickerView1 != null) timePickerView1.show();
                break;
            case R.id.tv_open_time1:
                timeId = R.id.tv_open_time1;
                if (timePickerView1 != null) timePickerView1.show();
                break;
            case R.id.tv_close_time1:
                timeId = R.id.tv_close_time1;
                if (timePickerView1 != null) timePickerView1.show();
                break;
            case R.id.tv_save_time:
                mPresenter.businessTime("time", getString(tvOpenTime), getString(tvCloseTime), getString(tvOpenTime1), getString(tvCloseTime1), mActivity);
                break;
        }
    }

    private String getString(TextView tv) {
        return tv.getText().toString();
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        if (isChecked) {
            tvOpenTime.setText("00:00");
            tvCloseTime.setText("12:00");
            tvOpenTime1.setText("12:00");
            tvCloseTime1.setText("24:00");
        } else {

        }
    }

    @Override
    public void businessTimeSucc(String msg) {
        MyToastUtils.showShort(msg);
        killMyself();
    }
}
