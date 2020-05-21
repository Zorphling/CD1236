package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.MyOrderWaitReceiveModule;
import com.business.cd1236.mvp.contract.MyOrderWaitReceiveContract;

import com.jess.arms.di.scope.FragmentScope;
import com.business.cd1236.mvp.ui.fragment.MyOrderWaitReceiveFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/19/2020 18:06
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = MyOrderWaitReceiveModule.class, dependencies = AppComponent.class)
public interface MyOrderWaitReceiveComponent {
    void inject(MyOrderWaitReceiveFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyOrderWaitReceiveComponent.Builder view(MyOrderWaitReceiveContract.View view);

        MyOrderWaitReceiveComponent.Builder appComponent(AppComponent appComponent);

        MyOrderWaitReceiveComponent build();
    }
}