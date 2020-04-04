package com.business.cd1236.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.business.cd1236.di.module.HtmlModule;
import com.business.cd1236.mvp.contract.HtmlContract;

import com.jess.arms.di.scope.ActivityScope;
import com.business.cd1236.mvp.ui.activity.HtmlActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/04/2020 11:05
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = HtmlModule.class, dependencies = AppComponent.class)
public interface HtmlComponent {
    void inject(HtmlActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HtmlComponent.Builder view(HtmlContract.View view);

        HtmlComponent.Builder appComponent(AppComponent appComponent);

        HtmlComponent build();
    }
}