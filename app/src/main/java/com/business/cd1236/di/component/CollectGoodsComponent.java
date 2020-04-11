package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.CollectGoodsModule;
import com.business.cd1236.mvp.contract.CollectGoodsContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.CollectGoodsActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/10/2020 14:51
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = CollectGoodsModule.class, dependencies = AppComponent.class)
public interface CollectGoodsComponent {
    void inject(CollectGoodsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CollectGoodsComponent.Builder view(CollectGoodsContract.View view);

        CollectGoodsComponent.Builder appComponent(AppComponent appComponent);

        CollectGoodsComponent build();
    }
}