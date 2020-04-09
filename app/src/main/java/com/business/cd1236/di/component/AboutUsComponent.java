package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.AboutUsModule;
import com.business.cd1236.mvp.contract.AboutUsContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.AboutUsActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/09/2020 10:23
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = AboutUsModule.class, dependencies = AppComponent.class)
public interface AboutUsComponent {
    void inject(AboutUsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AboutUsComponent.Builder view(AboutUsContract.View view);

        AboutUsComponent.Builder appComponent(AppComponent appComponent);

        AboutUsComponent build();
    }
}