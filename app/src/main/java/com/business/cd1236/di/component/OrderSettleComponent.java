package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.OrderSettleModule;
import com.business.cd1236.mvp.contract.OrderSettleContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.OrderSettleActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/17/2020 19:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = OrderSettleModule.class, dependencies = AppComponent.class)
public interface OrderSettleComponent {
    void inject(OrderSettleActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        OrderSettleComponent.Builder view(OrderSettleContract.View view);

        OrderSettleComponent.Builder appComponent(AppComponent appComponent);

        OrderSettleComponent build();
    }
}