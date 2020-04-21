package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessGoodsManageModule;
import com.business.cd1236.mvp.contract.BusinessGoodsManageContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessGoodsManageActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/21/2020 15:16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessGoodsManageModule.class, dependencies = AppComponent.class)
public interface BusinessGoodsManageComponent {
    void inject(BusinessGoodsManageActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessGoodsManageComponent.Builder view(BusinessGoodsManageContract.View view);

        BusinessGoodsManageComponent.Builder appComponent(AppComponent appComponent);

        BusinessGoodsManageComponent build();
    }
}