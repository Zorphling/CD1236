package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.MyOrderAllModule;
import com.business.cd1236.mvp.contract.MyOrderAllContract;

import com.jess.arms.di.scope.FragmentScope;
import com.business.cd1236.mvp.ui.fragment.MyOrderAllFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/19/2020 18:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = MyOrderAllModule.class, dependencies = AppComponent.class)
public interface MyOrderAllComponent {
    void inject(MyOrderAllFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyOrderAllComponent.Builder view(MyOrderAllContract.View view);

        MyOrderAllComponent.Builder appComponent(AppComponent appComponent);

        MyOrderAllComponent build();
    }
}