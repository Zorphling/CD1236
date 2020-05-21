package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.MyOrderModule;
import com.business.cd1236.mvp.contract.MyOrderContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.MyOrderActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/17/2020 18:42
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = MyOrderModule.class, dependencies = AppComponent.class)
public interface MyOrderComponent {
    void inject(MyOrderActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyOrderComponent.Builder view(MyOrderContract.View view);

        MyOrderComponent.Builder appComponent(AppComponent appComponent);

        MyOrderComponent build();
    }
}