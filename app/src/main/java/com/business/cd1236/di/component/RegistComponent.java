package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.RegistModule;
import com.business.cd1236.mvp.contract.RegistContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.RegistActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/31/2020 17:35
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = RegistModule.class, dependencies = AppComponent.class)
public interface RegistComponent {
    void inject(RegistActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        RegistComponent.Builder view(RegistContract.View view);

        RegistComponent.Builder appComponent(AppComponent appComponent);

        RegistComponent build();
    }
}