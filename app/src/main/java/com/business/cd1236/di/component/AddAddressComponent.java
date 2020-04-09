package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.AddAddressModule;
import com.business.cd1236.mvp.contract.AddAddressContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.AddAddressActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/09/2020 11:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = AddAddressModule.class, dependencies = AppComponent.class)
public interface AddAddressComponent {
    void inject(AddAddressActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AddAddressComponent.Builder view(AddAddressContract.View view);

        AddAddressComponent.Builder appComponent(AppComponent appComponent);

        AddAddressComponent build();
    }
}