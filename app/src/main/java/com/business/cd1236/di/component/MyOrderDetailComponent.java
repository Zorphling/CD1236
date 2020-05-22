package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.MyOrderDetailModule;
import com.business.cd1236.mvp.contract.MyOrderDetailContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.MyOrderDetailActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/22/2020 10:09
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = MyOrderDetailModule.class, dependencies = AppComponent.class)
public interface MyOrderDetailComponent {
    void inject(MyOrderDetailActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyOrderDetailComponent.Builder view(MyOrderDetailContract.View view);

        MyOrderDetailComponent.Builder appComponent(AppComponent appComponent);

        MyOrderDetailComponent build();
    }
}