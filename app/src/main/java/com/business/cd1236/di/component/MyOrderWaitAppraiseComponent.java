package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.MyOrderWaitAppraiseModule;
import com.business.cd1236.mvp.contract.MyOrderWaitAppraiseContract;

import com.jess.arms.di.scope.FragmentScope;
import com.business.cd1236.mvp.ui.fragment.MyOrderWaitAppraiseFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/19/2020 18:07
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = MyOrderWaitAppraiseModule.class, dependencies = AppComponent.class)
public interface MyOrderWaitAppraiseComponent {
    void inject(MyOrderWaitAppraiseFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyOrderWaitAppraiseComponent.Builder view(MyOrderWaitAppraiseContract.View view);

        MyOrderWaitAppraiseComponent.Builder appComponent(AppComponent appComponent);

        MyOrderWaitAppraiseComponent build();
    }
}