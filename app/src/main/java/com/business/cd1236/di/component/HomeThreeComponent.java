package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.HomeThreeModule;
import com.business.cd1236.mvp.contract.HomeThreeContract;

import com.jess.arms.di.scope.FragmentScope;
import com.business.cd1236.mvp.ui.fragment.HomeThreeFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/02/2020 09:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = HomeThreeModule.class, dependencies = AppComponent.class)
public interface HomeThreeComponent {
    void inject(HomeThreeFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HomeThreeComponent.Builder view(HomeThreeContract.View view);

        HomeThreeComponent.Builder appComponent(AppComponent appComponent);

        HomeThreeComponent build();
    }
}