package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessGoodsManageSortModule;
import com.business.cd1236.mvp.contract.BusinessGoodsManageSortContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessGoodsManageSortActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/26/2020 11:00
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessGoodsManageSortModule.class, dependencies = AppComponent.class)
public interface BusinessGoodsManageSortComponent {
    void inject(BusinessGoodsManageSortActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessGoodsManageSortComponent.Builder view(BusinessGoodsManageSortContract.View view);

        BusinessGoodsManageSortComponent.Builder appComponent(AppComponent appComponent);

        BusinessGoodsManageSortComponent build();
    }
}