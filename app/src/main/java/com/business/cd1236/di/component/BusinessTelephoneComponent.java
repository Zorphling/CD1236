package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessTelephoneModule;
import com.business.cd1236.mvp.contract.BusinessTelephoneContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessTelephoneActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/18/2020 13:53
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessTelephoneModule.class, dependencies = AppComponent.class)
public interface BusinessTelephoneComponent {
    void inject(BusinessTelephoneActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessTelephoneComponent.Builder view(BusinessTelephoneContract.View view);

        BusinessTelephoneComponent.Builder appComponent(AppComponent appComponent);

        BusinessTelephoneComponent build();
    }
}