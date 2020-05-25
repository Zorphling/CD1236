package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.LogisticsApplyModule;
import com.business.cd1236.mvp.contract.LogisticsApplyContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.LogisticsApplyActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/25/2020 14:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = LogisticsApplyModule.class, dependencies = AppComponent.class)
public interface LogisticsApplyComponent {
    void inject(LogisticsApplyActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LogisticsApplyComponent.Builder view(LogisticsApplyContract.View view);

        LogisticsApplyComponent.Builder appComponent(AppComponent appComponent);

        LogisticsApplyComponent build();
    }
}