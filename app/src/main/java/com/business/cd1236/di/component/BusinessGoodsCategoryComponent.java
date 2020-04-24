package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessGoodsCategoryModule;
import com.business.cd1236.mvp.contract.BusinessGoodsCategoryContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessGoodsCategoryActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/23/2020 10:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessGoodsCategoryModule.class, dependencies = AppComponent.class)
public interface BusinessGoodsCategoryComponent {
    void inject(BusinessGoodsCategoryActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessGoodsCategoryComponent.Builder view(BusinessGoodsCategoryContract.View view);

        BusinessGoodsCategoryComponent.Builder appComponent(AppComponent appComponent);

        BusinessGoodsCategoryComponent build();
    }
}