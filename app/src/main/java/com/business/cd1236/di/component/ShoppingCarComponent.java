package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.ShoppingCarModule;
import com.business.cd1236.mvp.contract.ShoppingCarContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.ShoppingCarActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/07/2020 16:00
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = ShoppingCarModule.class, dependencies = AppComponent.class)
public interface ShoppingCarComponent {
    void inject(ShoppingCarActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ShoppingCarComponent.Builder view(ShoppingCarContract.View view);

        ShoppingCarComponent.Builder appComponent(AppComponent appComponent);

        ShoppingCarComponent build();
    }
}