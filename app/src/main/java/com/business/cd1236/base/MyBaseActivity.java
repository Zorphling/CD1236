package com.business.cd1236.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.business.cd1236.R;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public abstract class MyBaseActivity<P extends IPresenter> extends RxAppCompatActivity implements IActivity, ActivityLifecycleable, IView {
    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null
    private Unbinder mUnbinder;
    private Cache<String, Object> mCache;
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();
    protected Activity mActivity;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    private Toolbar toolBar;

    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            mCache = ArmsUtils.obtainAppComponentFromContext(this).cacheFactory().build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mActivity = this;

        initToolbar();
        try {
            int layoutResID = initView(savedInstanceState);
            //如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
            if (layoutResID != 0) {
                LayoutInflater.from(this).inflate(layoutResID, (ViewGroup) findViewById(R.id.fl_container), true);
                //绑定到butterknife
//                mUnbinder = ButterKnife.bind(this);
                mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            if (e instanceof InflateException)
                throw e;
            e.printStackTrace();
        }
        initData(savedInstanceState);
    }

    private void initToolbar() {
        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//显示返回图标
            actionBar.setDisplayShowTitleEnabled(false);//不显示应用图标
        }
    }

    protected void hideToolBar() {
        toolBar.setVisibility(View.GONE);
    }

    protected void setHeader(CharSequence text) {
        toolBar.setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_title)).setText(text);
    }

    protected void setHeaderColor(@ColorInt @ColorRes int bg, @ColorInt @ColorRes int textColor, @DrawableRes int drawable) {
        toolBar.setBackgroundColor(bg);
        ((TextView) toolBar.findViewById(R.id.tv_title)).setTextColor(textColor);
        toolBar.setNavigationIcon(drawable);
    }

    protected void setRightBtn(CharSequence text, @DrawableRes int idRes, View.OnClickListener onClick) {
        findViewById(R.id.ll_right).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_right).setOnClickListener(onClick);
        ((TextView) findViewById(R.id.tv_right)).setText(text);
        ((ImageView) findViewById(R.id.iv_right)).setImageResource(idRes);

        ((TextView) findViewById(R.id.tv_right)).setCompoundDrawables(null, null, null, null);
    }

    protected void setRightColor(@ColorInt @ColorRes int color) {
        ((TextView) findViewById(R.id.tv_right)).setTextColor(color);
    }

    protected void hideBackButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);//显示返回图标
        }
    }

    /**
     * 状态栏改变颜色
     *
     * @param activity
     * @param isTranslate
     * @param isDarkText
     * @param bgColor
     */
    public static void setStatusColor(Activity activity, boolean isTranslate, boolean isDarkText, @ColorRes int bgColor) {
        //如果系统为6.0及以上，就可以使用Android自带的方式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); //可有可无
            decorView.setSystemUiVisibility((isTranslate ? View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN : 0) | (isDarkText ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : 0));
            window.setStatusBarColor(isTranslate ? Color.TRANSPARENT : ContextCompat.getColor(activity, bgColor)); //Android5.0就可以用
        }
    }

    /**
     * 设置状态栏全透明
     *
     * @param activity 需要设置的activity
     */
    public static void setTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        transparentStatusBar(activity);
        setRootView(activity);
    }

    /**
     * 使状态栏透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个flag contentView才能延伸到状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //状态栏覆盖在contentView上面，设置透明使contentView的背景透出来
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            //让contentView延伸到状态栏并且设置状态栏颜色透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置根布局参数
     */
    private static void setRootView(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup) childView).setClipToPadding(true);
            }
        }
    }

    /**
     * Fragment替换(核心为隐藏当前的,显示现在的,用过的将不会destrory与create)
     */
    private Fragment currentFragment;

    protected void smartReplaceFragment(@IdRes int flId, Fragment toFragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        // 如有当前在使用的->隐藏当前的
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        // toFragment之前添加使用过->显示出来
        if (manager.findFragmentByTag(tag) != null) {
            transaction.show(toFragment);
        } else {// toFragment还没添加使用过->添加上去
            transaction.add(flId, toFragment, tag);
        }
        transaction.commitAllowingStateLoss();
        // toFragment 更新为当前的
        currentFragment = toFragment;
    }

    public void smartReplaceFragment(@IdRes int flId, Fragment toFragment) {
        smartReplaceFragment(flId, toFragment, toFragment.getClass().getSimpleName());
    }

    public void addFragment(Fragment... fragments) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment fragment : fragments) {
            if (!fragment.isAdded()) {
                transaction.add(fragment,fragment.getClass().getSimpleName());
            }
        }
    }

    @Override
    public abstract int initView(@Nullable Bundle savedInstanceState);

    @Override
    public abstract void initData(@Nullable Bundle savedInstanceState);

    /**
     * 是否使用 EventBus
     * Arms 核心库现在并不会依赖某个 EventBus, 要想使用 EventBus, 还请在项目中自行依赖对应的 EventBus
     * 现在支持两种 EventBus, greenrobot 的 EventBus 和畅销书 《Android源码设计模式解析与实战》的作者 何红辉 所作的 AndroidEventBus
     * 确保依赖后, 将此方法返回 true, Arms 会自动检测您依赖的 EventBus, 并自动注册
     * 这种做法可以让使用者有自行选择三方库的权利, 并且还可以减轻 Arms 的体积
     *
     * @return 返回 {@code true} (默认为使用 {@code true}), Arms 会自动注册 EventBus
     */
    @Override
    public boolean useEventBus() {
        return true;
    }

    /**
     * 这个Activity是否会使用Fragment,框架会根据这个属性判断是否注册{@link androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回false,那意味着这个Activity不需要绑定Fragment,那你再在这个Activity中绑定继承于 {@link com.jess.arms.base.BaseFragment} 的Fragment将不起任何作用
     *
     * @return
     */
    @Override
    public boolean useFragment() {
        return true;
    }

    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    //点击界面返回键
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();//按返回图标直接回退上个界面
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }
}
