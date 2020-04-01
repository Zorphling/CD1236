package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.ReviseNickNameModule;
import com.business.cd1236.mvp.contract.ReviseNickNameContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.ReviseNickNameActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/01/2020 14:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = ReviseNickNameModule.class, dependencies = AppComponent.class)
public interface ReviseNickNameComponent {
    void inject(ReviseNickNameActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ReviseNickNameComponent.Builder view(ReviseNickNameContract.View view);

        ReviseNickNameComponent.Builder appComponent(AppComponent appComponent);

        ReviseNickNameComponent build();
    }
}