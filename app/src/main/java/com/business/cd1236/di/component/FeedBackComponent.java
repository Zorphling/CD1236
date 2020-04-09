package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.FeedBackModule;
import com.business.cd1236.mvp.contract.FeedBackContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.FeedBackActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/09/2020 10:39
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = FeedBackModule.class, dependencies = AppComponent.class)
public interface FeedBackComponent {
    void inject(FeedBackActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        FeedBackComponent.Builder view(FeedBackContract.View view);

        FeedBackComponent.Builder appComponent(AppComponent appComponent);

        FeedBackComponent build();
    }
}