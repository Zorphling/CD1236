package com.business.cd1236.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.business.cd1236.mvp.contract.BusinessBasicInfoContract;
import com.business.cd1236.mvp.model.BusinessBasicInfoModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/16/2020 10:37
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class BusinessBasicInfoModule {

    @Binds
    abstract BusinessBasicInfoContract.Model bindBusinessBasicInfoModel(BusinessBasicInfoModel model);
}