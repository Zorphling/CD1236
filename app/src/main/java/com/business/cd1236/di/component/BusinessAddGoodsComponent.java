package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.BusinessAddGoodsModule;
import com.business.cd1236.mvp.contract.BusinessAddGoodsContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.BusinessAddGoodsActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/22/2020 16:58
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = BusinessAddGoodsModule.class, dependencies = AppComponent.class)
public interface BusinessAddGoodsComponent {
    void inject(BusinessAddGoodsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BusinessAddGoodsComponent.Builder view(BusinessAddGoodsContract.View view);

        BusinessAddGoodsComponent.Builder appComponent(AppComponent appComponent);

        BusinessAddGoodsComponent build();
    }
}