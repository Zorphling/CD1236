package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessTitleModule;
import com.business.cd1236.mvp.contract.BusinessTitleContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessTitleActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/17/2020 18:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessTitleModule.class, dependencies = AppComponent.class)
public interface BusinessTitleComponent {
    void inject(BusinessTitleActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessTitleComponent.Builder view(BusinessTitleContract.View view);

        BusinessTitleComponent.Builder appComponent(AppComponent appComponent);

        BusinessTitleComponent build();
    }
}