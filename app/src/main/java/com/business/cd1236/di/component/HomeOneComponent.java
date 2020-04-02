package com.business.cd1236.di.component;

import com.business.cd1236.di.module.HomeOneModule;
import com.business.cd1236.mvp.contract.HomeOneContract;
import com.business.cd1236.mvp.ui.fragment.HomeOneFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.BindsInstance;
import dagger.Component;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/02/2020 09:53
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = HomeOneModule.class, dependencies = AppComponent.class)
public interface HomeOneComponent {
    void inject(HomeOneFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HomeOneComponent.Builder view(HomeOneContract.View view);

        HomeOneComponent.Builder appComponent(AppComponent appComponent);

        HomeOneComponent build();
    }
}