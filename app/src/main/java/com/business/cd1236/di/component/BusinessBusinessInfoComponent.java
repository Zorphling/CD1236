package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessBusinessInfoModule;
import com.business.cd1236.mvp.contract.BusinessBusinessInfoContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessBusinessInfoActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/16/2020 11:11
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessBusinessInfoModule.class, dependencies = AppComponent.class)
public interface BusinessBusinessInfoComponent {
    void inject(BusinessBusinessInfoActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessBusinessInfoComponent.Builder view(BusinessBusinessInfoContract.View view);

        BusinessBusinessInfoComponent.Builder appComponent(AppComponent appComponent);

        BusinessBusinessInfoComponent build();
    }
}