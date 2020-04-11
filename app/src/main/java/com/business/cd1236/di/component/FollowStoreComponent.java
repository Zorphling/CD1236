package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.FollowStoreModule;
import com.business.cd1236.mvp.contract.FollowStoreContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.FollowStoreActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/10/2020 14:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = FollowStoreModule.class, dependencies = AppComponent.class)
public interface FollowStoreComponent {
    void inject(FollowStoreActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        FollowStoreComponent.Builder view(FollowStoreContract.View view);

        FollowStoreComponent.Builder appComponent(AppComponent appComponent);

        FollowStoreComponent build();
    }
}