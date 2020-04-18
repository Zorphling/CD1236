package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessInfoModule;
import com.business.cd1236.mvp.contract.BusinessInfoContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessInfoActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/16/2020 09:45
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessInfoModule.class, dependencies = AppComponent.class)
public interface BusinessInfoComponent {
    void inject(BusinessInfoActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessInfoComponent.Builder view(BusinessInfoContract.View view);

        BusinessInfoComponent.Builder appComponent(AppComponent appComponent);

        BusinessInfoComponent build();
    }
}