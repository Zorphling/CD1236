package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessCenterModule;
import com.business.cd1236.mvp.contract.BusinessCenterContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessCenterActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/08/2020 19:14
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessCenterModule.class, dependencies = AppComponent.class)
public interface BusinessCenterComponent {
    void inject(BusinessCenterActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessCenterComponent.Builder view(BusinessCenterContract.View view);

        BusinessCenterComponent.Builder appComponent(AppComponent appComponent);

        BusinessCenterComponent build();
    }
}