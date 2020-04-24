package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessGoodsSortModule;
import com.business.cd1236.mvp.contract.BusinessGoodsSortContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessGoodsSortActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/23/2020 16:08
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessGoodsSortModule.class, dependencies = AppComponent.class)
public interface BusinessGoodsSortComponent {
    void inject(BusinessGoodsSortActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessGoodsSortComponent.Builder view(BusinessGoodsSortContract.View view);

        BusinessGoodsSortComponent.Builder appComponent(AppComponent appComponent);

        BusinessGoodsSortComponent build();
    }
}