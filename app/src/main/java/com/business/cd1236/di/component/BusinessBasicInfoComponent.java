package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessBasicInfoModule;
import com.business.cd1236.mvp.contract.BusinessBasicInfoContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessBasicInfoActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/16/2020 10:37
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessBasicInfoModule.class, dependencies = AppComponent.class)
public interface BusinessBasicInfoComponent {
    void inject(BusinessBasicInfoActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessBasicInfoComponent.Builder view(BusinessBasicInfoContract.View view);

        BusinessBasicInfoComponent.Builder appComponent(AppComponent appComponent);

        BusinessBasicInfoComponent build();
    }
}