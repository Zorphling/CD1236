package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessQualificationsUploadModule;
import com.business.cd1236.mvp.contract.BusinessQualificationsUploadContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessQualificationsUploadActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/18/2020 14:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessQualificationsUploadModule.class, dependencies = AppComponent.class)
public interface BusinessQualificationsUploadComponent {
    void inject(BusinessQualificationsUploadActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessQualificationsUploadComponent.Builder view(BusinessQualificationsUploadContract.View view);

        BusinessQualificationsUploadComponent.Builder appComponent(AppComponent appComponent);

        BusinessQualificationsUploadComponent build();
    }
}