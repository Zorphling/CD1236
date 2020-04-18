package com.business.cd1236.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.business.cd1236.mvp.contract.BusinessQualificationsContract;
import com.business.cd1236.mvp.model.BusinessQualificationsModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/18/2020 14:17
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class BusinessQualificationsModule {

    @Binds
    abstract BusinessQualificationsContract.Model bindBusinessQualificationsModel(BusinessQualificationsModel model);
}