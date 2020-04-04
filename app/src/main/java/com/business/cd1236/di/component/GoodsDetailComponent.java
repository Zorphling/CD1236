package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.GoodsDetailModule;
import com.business.cd1236.mvp.contract.GoodsDetailContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.GoodsDetailActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/04/2020 16:22
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = GoodsDetailModule.class, dependencies = AppComponent.class)
public interface GoodsDetailComponent {
    void inject(GoodsDetailActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        GoodsDetailComponent.Builder view(GoodsDetailContract.View view);

        GoodsDetailComponent.Builder appComponent(AppComponent appComponent);

        GoodsDetailComponent build();
    }
}