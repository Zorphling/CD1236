package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessNoticeModule;
import com.business.cd1236.mvp.contract.BusinessNoticeContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessNoticeActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/16/2020 14:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessNoticeModule.class, dependencies = AppComponent.class)
public interface BusinessNoticeComponent {
    void inject(BusinessNoticeActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessNoticeComponent.Builder view(BusinessNoticeContract.View view);

        BusinessNoticeComponent.Builder appComponent(AppComponent appComponent);

        BusinessNoticeComponent build();
    }
}