package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.OrderDispatchingChooseModule;
import com.business.cd1236.mvp.contract.OrderDispatchingChooseContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.OrderDispatchingChooseActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/14/2020 14:41
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = OrderDispatchingChooseModule.class, dependencies = AppComponent.class)
public interface OrderDispatchingChooseComponent {
    void inject(OrderDispatchingChooseActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        OrderDispatchingChooseComponent.Builder view(OrderDispatchingChooseContract.View view);

        OrderDispatchingChooseComponent.Builder appComponent(AppComponent appComponent);

        OrderDispatchingChooseComponent build();
    }
}