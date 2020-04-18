package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessQualificationsModule;
import com.business.cd1236.mvp.contract.BusinessQualificationsContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessQualificationsActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/18/2020 14:17
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessQualificationsModule.class, dependencies = AppComponent.class)
public interface BusinessQualificationsComponent {
    void inject(BusinessQualificationsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessQualificationsComponent.Builder view(BusinessQualificationsContract.View view);

        BusinessQualificationsComponent.Builder appComponent(AppComponent appComponent);

        BusinessQualificationsComponent build();
    }
}