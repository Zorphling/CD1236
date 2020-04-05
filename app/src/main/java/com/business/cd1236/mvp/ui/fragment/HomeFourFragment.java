package com.business.cd1236.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseFragment;
import com.business.cd1236.di.component.DaggerHomeFourComponent;
import com.business.cd1236.mvp.contract.HomeFourContract;
import com.business.cd1236.mvp.presenter.HomeFourPresenter;
import com.business.cd1236.mvp.ui.activity.PersonalInfoActivity;
import com.business.cd1236.mvp.ui.activity.SettingActivity;
import com.business.cd1236.utils.SizeUtils;
import com.business.cd1236.view.PitemView;
import com.business.cd1236.view.homebtn.CircularRevealButton;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/02/2020 09:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class HomeFourFragment extends MyBaseFragment<HomeFourPresenter> implements HomeFourContract.View {

    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.ll_person_info)
    LinearLayout llPersonInfo;
    @BindView(R.id.tv_follow_store)
    TextView tvFollowStore;
    @BindView(R.id.ll_follow_store)
    LinearLayout llFollowStore;
    @BindView(R.id.tv_my_collect)
    TextView tvMyCollect;
    @BindView(R.id.ll_my_collect)
    LinearLayout llMyCollect;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.iv_header)
    CircleImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_notice)
    ImageView ivNotice;
    @BindView(R.id.crb_1)
    CircularRevealButton crb1;
    @BindView(R.id.crb_2)
    CircularRevealButton crb2;
    @BindView(R.id.crb_3)
    CircularRevealButton crb3;
    @BindView(R.id.crb_4)
    CircularRevealButton crb4;
    @BindView(R.id.crb_5)
    CircularRevealButton crb5;
    @BindView(R.id.rl_my_order)
    RelativeLayout rlMyOrder;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.piv_address)
    PitemView pivAddress;
    @BindView(R.id.piv_cooperation)
    PitemView pivCooperation;
    @BindView(R.id.piv_seller)
    PitemView pivSeller;
    @BindView(R.id.piv_fankui)
    PitemView pivFankui;
    @BindView(R.id.piv_custom_service)
    PitemView pivCustomService;
    @BindView(R.id.piv_about_us)
    PitemView pivAboutUs;

    public static HomeFourFragment newInstance() {
        HomeFourFragment fragment = new HomeFourFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeFourComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_four, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initHeader();
    }

    private void initHeader() {
        ViewGroup.LayoutParams layoutParams = rlHeader.getLayoutParams();
        layoutParams.height = (SizeUtils.getScreenHW(mActivity)[1] / 3) - SizeUtils.dp2px(mActivity, 30);
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

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

    }

    @OnClick({R.id.ll_follow_store, R.id.ll_my_collect, R.id.ll_history, R.id.ll_person_info, R.id.iv_setting, R.id.iv_notice, R.id.crb_1, R.id.crb_2, R.id.crb_3, R.id.crb_4, R.id.crb_5, R.id.rl_my_order
            , R.id.piv_address, R.id.piv_cooperation, R.id.piv_seller, R.id.piv_fankui, R.id.piv_custom_service, R.id.piv_about_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_follow_store:
                break;
            case R.id.ll_my_collect:
                break;
            case R.id.ll_history:
                break;
            case R.id.ll_person_info:
                launchActivity(new Intent(mActivity, PersonalInfoActivity.class));
                break;
            case R.id.iv_setting:
                launchActivity(new Intent(mActivity, SettingActivity.class));
                break;
            case R.id.iv_notice:
                break;
            case R.id.rl_my_order:
                break;
            case R.id.crb_1:
                break;
            case R.id.crb_2:
                break;
            case R.id.crb_3:
                break;
            case R.id.crb_4:
                break;
            case R.id.crb_5:
                break;
            case R.id.piv_address:
                break;
            case R.id.piv_cooperation:
                break;
            case R.id.piv_seller:
                break;
            case R.id.piv_fankui:
                break;
            case R.id.piv_custom_service:
                break;
            case R.id.piv_about_us:
                break;

        }
    }
}
