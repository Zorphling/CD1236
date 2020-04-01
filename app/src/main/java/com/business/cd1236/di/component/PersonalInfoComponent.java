package com.business.cd1236.di.component;

import com.business.cd1236.di.module.PersonalInfoModule;
import com.business.cd1236.mvp.contract.PersonalInfoContract;
import com.business.cd1236.mvp.ui.activity.PersonalInfoActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/01/2020 10:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = PersonalInfoModule.class, dependencies = AppComponent.class)
public interface PersonalInfoComponent {
    void inject(PersonalInfoActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        PersonalInfoComponent.Builder view(PersonalInfoContract.View view);

        PersonalInfoComponent.Builder appComponent(AppComponent appComponent);

        PersonalInfoComponent build();
    }
}