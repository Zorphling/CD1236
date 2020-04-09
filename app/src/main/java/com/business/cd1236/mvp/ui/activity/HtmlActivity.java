package com.business.cd1236.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.business.cd1236.R;
import com.business.cd1236.base.MyBaseActivity;
import com.business.cd1236.di.component.DaggerHtmlComponent;
import com.business.cd1236.mvp.contract.HtmlContract;
import com.business.cd1236.mvp.presenter.HtmlPresenter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

import butterknife.BindView;
import project.com.arms.mvp.model.api.Api;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/04/2020 11:05
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class HtmlActivity extends MyBaseActivity<HtmlPresenter> implements HtmlContract.View {

    @BindView(R.id.ll_web_container)
    LinearLayout llWebContainer;
    public static final String AGREEMENT_TYPE = "AGREEMENT_TYPE";
    public static final String USER_AGREEMENT = "user_agreement";
    public static final String PRIVACY_POLICY = "privacy_policy";
    public static final String OPERATION_STEPS = "operation_steps";//入驻协议
    private AgentWeb mAgentWeb;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHtmlComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_html; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHeader("");
        String type = getIntent().getStringExtra(AGREEMENT_TYPE);
        getHtmlSuccess(type);
//        switch (type) {
//            case USER_AGREEMENT:
//                mPresenter.getUserAgreement(mActivity);
//                break;
//            case PRIVACY_POLICY:
//                mPresenter.getPrivacyPolicy(mActivity);
//                break;
//        }
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
    public void getHtmlSuccess(String jsonString) {
        mAgentWeb = AgentWeb.with(mActivity)
                .setAgentWebParent(llWebContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(chromeClient)
                .createAgentWeb()
                .ready()
                .go(Api.APP_DOMAIN + jsonString);
    }
    WebChromeClient chromeClient = new WebChromeClient(){
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setHeader(title);
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
