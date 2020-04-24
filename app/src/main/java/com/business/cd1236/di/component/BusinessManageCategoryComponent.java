package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessManageCategoryModule;
import com.business.cd1236.mvp.contract.BusinessManageCategoryContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessManageCategoryActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/23/2020 16:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessManageCategoryModule.class, dependencies = AppComponent.class)
public interface BusinessManageCategoryComponent {
    void inject(BusinessManageCategoryActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessManageCategoryComponent.Builder view(BusinessManageCategoryContract.View view);

        BusinessManageCategoryComponent.Builder appComponent(AppComponent appComponent);

        BusinessManageCategoryComponent build();
    }
}