package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.StoreModule;
import com.business.cd1236.mvp.contract.StoreContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.StoreActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/15/2020 17:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = StoreModule.class, dependencies = AppComponent.class)
public interface StoreComponent {
    void inject(StoreActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        StoreComponent.Builder view(StoreContract.View view);

        StoreComponent.Builder appComponent(AppComponent appComponent);

        StoreComponent build();
    }
}