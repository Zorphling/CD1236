package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessEnterModule;
import com.business.cd1236.mvp.contract.BusinessEnterContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessEnterActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/08/2020 09:31
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessEnterModule.class, dependencies = AppComponent.class)
public interface BusinessEnterComponent {
    void inject(BusinessEnterActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessEnterComponent.Builder view(BusinessEnterContract.View view);

        BusinessEnterComponent.Builder appComponent(AppComponent appComponent);

        BusinessEnterComponent build();
    }
}