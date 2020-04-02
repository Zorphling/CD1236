package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.HomeFourModule;
import com.business.cd1236.mvp.contract.HomeFourContract;

import com.jess.arms.di.scope.FragmentScope;
import com.business.cd1236.mvp.ui.fragment.HomeFourFragment;


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
@Component(modules = HomeFourModule.class, dependencies = AppComponent.class)
public interface HomeFourComponent {
    void inject(HomeFourFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HomeFourComponent.Builder view(HomeFourContract.View view);

        HomeFourComponent.Builder appComponent(AppComponent appComponent);

        HomeFourComponent build();
    }
}