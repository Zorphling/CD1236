package com.business.cd1236.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.business.cd1236.mvp.contract.BusinessQualificationsUploadContract;
import com.business.cd1236.mvp.model.BusinessQualificationsUploadModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/18/2020 14:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class BusinessQualificationsUploadModule {

    @Binds
    abstract BusinessQualificationsUploadContract.Model bindBusinessQualificationsUploadModel(BusinessQualificationsUploadModel model);
}