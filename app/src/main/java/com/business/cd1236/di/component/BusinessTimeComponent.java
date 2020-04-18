package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessTimeModule;
import com.business.cd1236.mvp.contract.BusinessTimeContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessTimeActivity;


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
@ActivityScope
@Component(modules = BusinessTimeModule.class, dependencies = AppComponent.class)
public interface BusinessTimeComponent {
    void inject(BusinessTimeActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessTimeComponent.Builder view(BusinessTimeContract.View view);

        BusinessTimeComponent.Builder appComponent(AppComponent appComponent);

        BusinessTimeComponent build();
    }
}