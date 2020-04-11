package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BrowseRecordModule;
import com.business.cd1236.mvp.contract.BrowseRecordContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BrowseRecordActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/11/2020 17:58
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BrowseRecordModule.class, dependencies = AppComponent.class)
public interface BrowseRecordComponent {
    void inject(BrowseRecordActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BrowseRecordComponent.Builder view(BrowseRecordContract.View view);

        BrowseRecordComponent.Builder appComponent(AppComponent appComponent);

        BrowseRecordComponent build();
    }
}